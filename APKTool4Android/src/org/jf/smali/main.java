/*
 * [The "BSD licence"]
 * Copyright (c) 2010 Ben Gruver (JesusFreke)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.jf.smali;


import org.jf.smali.smaliLexer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.jf.dexlib.CodeItem;
import org.jf.dexlib.DexFile;
import org.jf.dexlib.Util.ByteArrayAnnotatedOutput;
import org.jf.util.ConsoleUtil;
import org.jf.util.smaliHelpFormatter;

/**
 * Main class for smali. It recognizes enough options to be able to dispatch
 * to the right "actual" main.
 */
public class main {

    public static final String VERSION;

    private final static Options basicOptions;
    private final static Options debugOptions;
    private final static Options options;

    static {
        basicOptions = new Options();
        debugOptions = new Options();
        options = new Options();
        buildOptions();

        InputStream templateStream = main.class.getClassLoader().getResourceAsStream("smali.properties");
        Properties properties = new Properties();
        String version = "(unknown)";
        try {
            properties.load(templateStream);
            version = properties.getProperty("application.version");
        } catch (IOException ex) {
        }
        VERSION = version;
    }


    /**
     * This class is uninstantiable.
     */
    private main() {
    }

    /**
     * Run!
     */
    public static void main(String[] args) {
        CommandLineParser parser = new PosixParser();
        CommandLine commandLine;

        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException ex) {
            usage();
            return;
        }

        boolean sort = false;
        boolean fixStringConst = true;
        boolean fixGoto = true;
        boolean verboseErrors = false;
        boolean oldLexer = false;
        boolean printTokens = false;

        String outputDexFile = "out.dex";
        String dumpFileName = null;

        String[] remainingArgs = commandLine.getArgs();

        Option[] options = commandLine.getOptions();

        for (int i=0; i<options.length; i++) {
            Option option = options[i];
            String opt = option.getOpt();

            switch (opt.charAt(0)) {
                case 'v':
                    version();
                    return;
                case '?':
                    while (++i < options.length) {
                        if (options[i].getOpt().charAt(0) == '?') {
                            usage(true);
                            return;
                        }
                    }
                    usage(false);
                    return;
                case 'o':
                    outputDexFile = commandLine.getOptionValue("o");
                    break;
                case 'D':
                    dumpFileName = commandLine.getOptionValue("D", outputDexFile + ".dump");
                    break;
                case 'S':
                    sort = true;
                    break;
                case 'C':
                    fixStringConst = false;
                    break;
                case 'G':
                    fixGoto = false;
                    break;
                case 'V':
                    verboseErrors = true;
                    break;
                case 'L':
                    oldLexer = true;
                    break;
                case 'T':
                    printTokens = true;
                    break;
                default:
                    assert false;
            }
        }

        if (remainingArgs.length == 0) {
            usage();
            return;
        }

        try {
            LinkedHashSet<File> filesToProcess = new LinkedHashSet<File>();

            for (String arg: remainingArgs) {
                    File argFile = new File(arg);

                    if (!argFile.exists()) {
                        throw new RuntimeException("Cannot find file or directory \"" + arg + "\"");
                    }

                    if (argFile.isDirectory()) {
                        getSmaliFilesInDir(argFile, filesToProcess);
                    } else if (argFile.isFile()) {
                        filesToProcess.add(argFile);
                    }
            }

            DexFile dexFile = new DexFile();

            boolean errors = false;

            for (File file: filesToProcess) {
                if (!assembleSmaliFile(file, dexFile, verboseErrors, oldLexer, printTokens)) {
                    errors = true;
                }
            }

            if (errors) {
                System.exit(1);
            }


            if (sort) {
                dexFile.setSortAllItems(true);
            }

            if (fixStringConst || fixGoto) {
                fixInstructions(dexFile, fixStringConst, fixGoto);
            }

            dexFile.place();

            ByteArrayAnnotatedOutput out = new ByteArrayAnnotatedOutput();

            if (dumpFileName != null) {
                out.enableAnnotations(120, true);
            }

            dexFile.writeTo(out);

            byte[] bytes = out.toByteArray();

            DexFile.calcSignature(bytes);
            DexFile.calcChecksum(bytes);

            if (dumpFileName != null) {
                out.finishAnnotating();

                FileWriter fileWriter = new FileWriter(dumpFileName);
                out.writeAnnotationsTo(fileWriter);
                fileWriter.close();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(outputDexFile);

            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (RuntimeException ex) {
            System.err.println("\nUNEXPECTED TOP-LEVEL EXCEPTION:");
            ex.printStackTrace();
            System.exit(2);
        } catch (Throwable ex) {
            System.err.println("\nUNEXPECTED TOP-LEVEL ERROR:");
            ex.printStackTrace();
            System.exit(3);
        }
    }

    private static void getSmaliFilesInDir(File dir, Set<File> smaliFiles) {
        for(File file: dir.listFiles()) {
            if (file.isDirectory()) {
                getSmaliFilesInDir(file, smaliFiles);
            } else if (file.getName().endsWith(".smali")) {
                smaliFiles.add(file);
            }
        }
    }

    private static void fixInstructions(DexFile dexFile, boolean fixStringConst, boolean fixGoto) {
        dexFile.place();

        byte[] newInsns = null;

        for (CodeItem codeItem: dexFile.CodeItemsSection.getItems()) {
            codeItem.fixInstructions(fixStringConst, fixGoto);
        }
    }

    private static boolean assembleSmaliFile(File smaliFile, DexFile dexFile, boolean verboseErrors, boolean oldLexer,
                                             boolean printTokens)
            throws Exception {
        CommonTokenStream tokens;


        boolean lexerErrors = false;
        LexerErrorInterface lexer;

        if (oldLexer) {
            ANTLRFileStream input = new ANTLRFileStream(smaliFile.getAbsolutePath(), "UTF-8");
            input.name = smaliFile.getAbsolutePath();

            lexer = new smaliLexer(input);
            tokens = new CommonTokenStream((TokenSource)lexer);
        } else {
            FileInputStream fis = new FileInputStream(smaliFile.getAbsolutePath());
            InputStreamReader reader = new InputStreamReader(fis, "UTF-8");

            lexer = new smaliFlexLexer(reader);
            ((smaliFlexLexer)lexer).setSourceFile(smaliFile);
            tokens = new CommonTokenStream((TokenSource)lexer);
        }

        if (printTokens) {
            tokens.getTokens();
            
            for (int i=0; i<tokens.size(); i++) {
                Token token = tokens.get(i);
                if (token.getChannel() == smaliLexer.HIDDEN) {
                    continue;
                }

                System.out.println(smaliParser.tokenNames[token.getType()] + ": " + token.getText());
            }
        }

        smaliParser parser = new smaliParser(tokens);
        parser.setVerboseErrors(verboseErrors);

        smaliParser.smali_file_return result = parser.smali_file();

        if (parser.getNumberOfSyntaxErrors() > 0 || lexer.getNumberOfSyntaxErrors() > 0) {
            return false;
        }

        CommonTree t = (CommonTree) result.getTree();

        CommonTreeNodeStream treeStream = new CommonTreeNodeStream(t);
        treeStream.setTokenStream(tokens);

        smaliTreeWalker dexGen = new smaliTreeWalker(treeStream);

        dexGen.dexFile = dexFile;
        dexGen.smali_file();

        if (dexGen.getNumberOfSyntaxErrors() > 0) {
            return false;
        }

        return true;
    }


    /**
     * Prints the usage message.
     */
    private static void usage(boolean printDebugOptions) {
        smaliHelpFormatter formatter = new smaliHelpFormatter();
        formatter.setWidth(ConsoleUtil.getConsoleWidth());

        formatter.printHelp("java -jar smali.jar [options] [--] [<smali-file>|folder]*",
                "assembles a set of smali files into a dex file", basicOptions, "");

        if (printDebugOptions) {
            System.out.println();
            System.out.println("Debug Options:");

            StringBuffer sb = new StringBuffer();
            formatter.renderOptions(sb, debugOptions);
            System.out.println(sb.toString());
        }
    }

    private static void usage() {
        usage(false);
    }

    /**
     * Prints the version message.
     */
    private static void version() {
        System.out.println("smali " + VERSION + " (http://smali.googlecode.com)");
        System.out.println("Copyright (C) 2010 Ben Gruver (JesusFreke@JesusFreke.com)");
        System.out.println("BSD license (http://www.opensource.org/licenses/bsd-license.php)");
        System.exit(0);
    }

    private static void buildOptions() {
        Option versionOption = OptionBuilder.withLongOpt("version")
                .withDescription("prints the version then exits")
                .create("v");

        Option helpOption = OptionBuilder.withLongOpt("help")
                .withDescription("prints the help message then exits. Specify twice for debug options")
                .create("?");

        Option outputOption = OptionBuilder.withLongOpt("output")
                .withDescription("the name of the dex file that will be written. The default is out.dex")
                .hasArg()
                .withArgName("FILE")
                .create("o");

        Option dumpOption = OptionBuilder.withLongOpt("dump-to")
                .withDescription("additionally writes a dump of written dex file to FILE (<dexfile>.dump by default)")
                .hasOptionalArg()
                .withArgName("FILE")
                .create("D");

        Option sortOption = OptionBuilder.withLongOpt("sort")
                .withDescription("sort the items in the dex file into a canonical order before writing")
                .create("S");

        Option noFixStringConstOption = OptionBuilder.withLongOpt("no-fix-string-const")
                .withDescription("Don't replace string-const instructions with string-const/jumbo where appropriate")
                .create("C");

        Option noFixGotoOption = OptionBuilder.withLongOpt("no-fix-goto")
                .withDescription("Don't replace goto type instructions with a larger version where appropriate")
                .create("G");

        Option verboseErrorsOption = OptionBuilder.withLongOpt("verbose-errors")
                .withDescription("Generate verbose error messages")
                .create("V");

        Option oldLexerOption = OptionBuilder.withLongOpt("old-lexer")
                .withDescription("Use the old lexer")
                .create("L");

        Option printTokensOption = OptionBuilder.withLongOpt("print-tokens")
                .withDescription("Print the name and text of each token")
                .create("T");

        basicOptions.addOption(versionOption);
        basicOptions.addOption(helpOption);
        basicOptions.addOption(outputOption);

        debugOptions.addOption(dumpOption);
        debugOptions.addOption(sortOption);
        debugOptions.addOption(noFixStringConstOption);
        debugOptions.addOption(noFixGotoOption);
        debugOptions.addOption(verboseErrorsOption);
        debugOptions.addOption(oldLexerOption);
        debugOptions.addOption(printTokensOption);

        for (Object option: basicOptions.getOptions()) {
            options.addOption((Option)option);
        }

        for (Object option: debugOptions.getOptions()) {
            options.addOption((Option)option);
        }
    }
}