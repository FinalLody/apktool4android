/**
 *  Copyright 2011 Ryszard Wiśniewski <brut.alll@gmail.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package brut.androlib.mod;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.jf.dexlib.DexFile;
import org.jf.smali.LexerErrorInterface;
import org.jf.smali.smaliFlexLexer;
import org.jf.smali.smaliLexer;
import org.jf.smali.smaliParser;
import org.jf.smali.smaliTreeWalker;

import android.util.Log;

/**
 * @author Ryszard Wiśniewski <brut.alll@gmail.com>
 */
public class SmaliMod {

    public static boolean assembleSmaliFile(InputStream smaliStream,String name, DexFile dexFile, boolean verboseErrors,boolean oldLexer, boolean printTokens)throws IOException, RecognitionException {
        CommonTokenStream tokens;
        boolean lexerErrors = false;
        LexerErrorInterface lexer;
try{
        if (oldLexer) {
            ANTLRInputStream input = new ANTLRInputStream(smaliStream, "UTF-8");
            input.name = name;
            lexer = new smaliLexer(input);
            tokens = new CommonTokenStream((TokenSource)lexer);
        } else {
            InputStreamReader reader =new InputStreamReader(smaliStream, "UTF-8");
            lexer = new smaliFlexLexer(reader);
            tokens = new CommonTokenStream((TokenSource)lexer);
        }
        if (1==1||printTokens) {
            tokens.getTokens();
            for (int i=0; i<tokens.size(); i++) {
                Token token = tokens.get(i);
                if (token.getChannel() == smaliLexer.HIDDEN) {continue;}
                System.out.println(smaliParser.tokenNames[token.getType()] + ": " + token.getText());
            }
        }
    	Log.d("SmaliMod:::::::::::::::::::::::::::::","mark 1");
        smaliParser parser = new smaliParser(tokens);
        Log.d("SmaliMod:::::::::::::::::::::::::::::","mark 2");
        parser.setVerboseErrors(verboseErrors);
        Log.d("SmaliMod:::::::::::::::::::::::::::::","mark 3");
        smaliParser.smali_file_return result = parser.smali_file();
        Log.d("SmaliMod:::::::::::::::::::::::::::::","mark 4");
        if (parser.getNumberOfSyntaxErrors() > 0 || lexer.getNumberOfSyntaxErrors() > 0) {
        	Log.d("SmaliMod:::::::::::::::::::::::::::::","mark 5");
        	Log.d("SmaliMod:::::::::::::::::::::::::::::","PARSER:");
        	return false;
        }
        CommonTree t = (CommonTree) result.getTree();
        Log.d("SmaliMod:::::::::::::::::::::::::::::","mark 6");
        CommonTreeNodeStream treeStream = new CommonTreeNodeStream(t);
        treeStream.setTokenStream(tokens);
        Log.d("SmaliMod:::::::::::::::::::::::::::::","mark 7");
        smaliTreeWalker dexGen = new smaliTreeWalker(treeStream);
        dexGen.dexFile = dexFile;
        dexGen.smali_file();
        Log.d("SmaliMod:::::::::::::::::::::::::::::","mark 8");
        if (dexGen.getNumberOfSyntaxErrors() > 0) {Log.d("SmaliMod:::::::::::::::::::::::::::::","mark 9");return false;}
}catch(Exception e){Log.d("SmaliMod:::::::::::::::::::::::::",""+e);}
	Log.d("SmaliMod:::::::::::::::::::::::::::::","mark 10");
        return true;
    }
}
