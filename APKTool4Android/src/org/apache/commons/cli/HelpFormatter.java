// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:43
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.cli;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;

public class HelpFormatter {

   public static final int DEFAULT_WIDTH = 74;
   public static final int DEFAULT_LEFT_PAD = 1;
   public static final int DEFAULT_DESC_PAD = 3;
   public static final String DEFAULT_SYNTAX_PREFIX = "usage: ";
   public static final String DEFAULT_OPT_PREFIX = "-";
   public static final String DEFAULT_LONG_OPT_PREFIX = "--";
   public static final String DEFAULT_ARG_NAME = "arg";
   public int defaultWidth = 74;
   public int defaultLeftPad = 1;
   public int defaultDescPad = 3;
   public String defaultSyntaxPrefix = "usage: ";
   public String defaultNewLine = System.getProperty("line.separator");
   public String defaultOptPrefix = "-";
   public String defaultLongOptPrefix = "--";
   public String defaultArgName = "arg";
   protected Comparator optionComparator = new HelpFormatter.OptionComparator((HelpFormatter.NamelessClass20026251)null);


   public void setWidth(int var1) {
      this.defaultWidth = var1;
   }

   public int getWidth() {
      return this.defaultWidth;
   }

   public void setLeftPadding(int var1) {
      this.defaultLeftPad = var1;
   }

   public int getLeftPadding() {
      return this.defaultLeftPad;
   }

   public void setDescPadding(int var1) {
      this.defaultDescPad = var1;
   }

   public int getDescPadding() {
      return this.defaultDescPad;
   }

   public void setSyntaxPrefix(String var1) {
      this.defaultSyntaxPrefix = var1;
   }

   public String getSyntaxPrefix() {
      return this.defaultSyntaxPrefix;
   }

   public void setNewLine(String var1) {
      this.defaultNewLine = var1;
   }

   public String getNewLine() {
      return this.defaultNewLine;
   }

   public void setOptPrefix(String var1) {
      this.defaultOptPrefix = var1;
   }

   public String getOptPrefix() {
      return this.defaultOptPrefix;
   }

   public void setLongOptPrefix(String var1) {
      this.defaultLongOptPrefix = var1;
   }

   public String getLongOptPrefix() {
      return this.defaultLongOptPrefix;
   }

   public void setArgName(String var1) {
      this.defaultArgName = var1;
   }

   public String getArgName() {
      return this.defaultArgName;
   }

   public Comparator getOptionComparator() {
      return this.optionComparator;
   }

   public void setOptionComparator(Comparator var1) {
      if(var1 == null) {
         this.optionComparator = new HelpFormatter.OptionComparator((HelpFormatter.NamelessClass20026251)null);
      } else {
         this.optionComparator = var1;
      }

   }

   public void printHelp(String var1, Options var2) {
      this.printHelp(this.defaultWidth, var1, (String)null, var2, (String)null, false);
   }

   public void printHelp(String var1, Options var2, boolean var3) {
      this.printHelp(this.defaultWidth, var1, (String)null, var2, (String)null, var3);
   }

   public void printHelp(String var1, String var2, Options var3, String var4) {
      this.printHelp(var1, var2, var3, var4, false);
   }

   public void printHelp(String var1, String var2, Options var3, String var4, boolean var5) {
      this.printHelp(this.defaultWidth, var1, var2, var3, var4, var5);
   }

   public void printHelp(int var1, String var2, String var3, Options var4, String var5) {
      this.printHelp(var1, var2, var3, var4, var5, false);
   }

   public void printHelp(int var1, String var2, String var3, Options var4, String var5, boolean var6) {
      PrintWriter var7 = new PrintWriter(System.out);
      this.printHelp(var7, var1, var2, var3, var4, this.defaultLeftPad, this.defaultDescPad, var5, var6);
      var7.flush();
   }

   public void printHelp(PrintWriter var1, int var2, String var3, String var4, Options var5, int var6, int var7, String var8) {
      this.printHelp(var1, var2, var3, var4, var5, var6, var7, var8, false);
   }

   public void printHelp(PrintWriter var1, int var2, String var3, String var4, Options var5, int var6, int var7, String var8, boolean var9) {
      if(var3 != null && var3.length() != 0) {
         if(var9) {
            this.printUsage(var1, var2, var3, var5);
         } else {
            this.printUsage(var1, var2, var3);
         }

         if(var4 != null && var4.trim().length() > 0) {
            this.printWrapped(var1, var2, var4);
         }

         this.printOptions(var1, var2, var5, var6, var7);
         if(var8 != null && var8.trim().length() > 0) {
            this.printWrapped(var1, var2, var8);
         }

      } else {
         throw new IllegalArgumentException("cmdLineSyntax not provided");
      }
   }

   public void printUsage(PrintWriter var1, int var2, String var3, Options var4) {
      StringBuffer var5 = (new StringBuffer(this.defaultSyntaxPrefix)).append(var3).append(" ");
      ArrayList var6 = new ArrayList();
      ArrayList var8 = new ArrayList(var4.getOptions());
      Collections.sort(var8, this.getOptionComparator());
      Iterator var9 = var8.iterator();

      while(var9.hasNext()) {
         Option var7 = (Option)var9.next();
         OptionGroup var10 = var4.getOptionGroup(var7);
         if(var10 != null) {
            if(!var6.contains(var10)) {
               var6.add(var10);
               this.appendOptionGroup(var5, var10);
            }
         } else {
            appendOption(var5, var7, var7.isRequired());
         }

         if(var9.hasNext()) {
            var5.append(" ");
         }
      }

      this.printWrapped(var1, var2, var5.toString().indexOf(32) + 1, var5.toString());
   }

   private void appendOptionGroup(StringBuffer var1, OptionGroup var2) {
      if(!var2.isRequired()) {
         var1.append("[");
      }

      ArrayList var3 = new ArrayList(var2.getOptions());
      Collections.sort(var3, this.getOptionComparator());
      Iterator var4 = var3.iterator();

      while(var4.hasNext()) {
         appendOption(var1, (Option)var4.next(), true);
         if(var4.hasNext()) {
            var1.append(" | ");
         }
      }

      if(!var2.isRequired()) {
         var1.append("]");
      }

   }

   private static void appendOption(StringBuffer var0, Option var1, boolean var2) {
      if(!var2) {
         var0.append("[");
      }

      if(var1.getOpt() != null) {
         var0.append("-").append(var1.getOpt());
      } else {
         var0.append("--").append(var1.getLongOpt());
      }

      if(var1.hasArg() && var1.hasArgName()) {
         var0.append(" <").append(var1.getArgName()).append(">");
      }

      if(!var2) {
         var0.append("]");
      }

   }

   public void printUsage(PrintWriter var1, int var2, String var3) {
      int var4 = var3.indexOf(32) + 1;
      this.printWrapped(var1, var2, this.defaultSyntaxPrefix.length() + var4, this.defaultSyntaxPrefix + var3);
   }

   public void printOptions(PrintWriter var1, int var2, Options var3, int var4, int var5) {
      StringBuffer var6 = new StringBuffer();
      this.renderOptions(var6, var2, var3, var4, var5);
      var1.println(var6.toString());
   }

   public void printWrapped(PrintWriter var1, int var2, String var3) {
      this.printWrapped(var1, var2, 0, var3);
   }

   public void printWrapped(PrintWriter var1, int var2, int var3, String var4) {
      StringBuffer var5 = new StringBuffer(var4.length());
      this.renderWrappedText(var5, var2, var3, var4);
      var1.println(var5.toString());
   }

   protected StringBuffer renderOptions(StringBuffer var1, int var2, Options var3, int var4, int var5) {
      String var6 = this.createPadding(var4);
      String var7 = this.createPadding(var5);
      int var8 = 0;
      ArrayList var10 = new ArrayList();
      List var11 = var3.helpOptions();
      Collections.sort(var11, this.getOptionComparator());

      StringBuffer var9;
      for(Iterator var12 = var11.iterator(); var12.hasNext(); var8 = var9.length() > var8?var9.length():var8) {
         Option var13 = (Option)var12.next();
         var9 = new StringBuffer(8);
         if(var13.getOpt() == null) {
            var9.append(var6).append("   " + this.defaultLongOptPrefix).append(var13.getLongOpt());
         } else {
            var9.append(var6).append(this.defaultOptPrefix).append(var13.getOpt());
            if(var13.hasLongOpt()) {
               var9.append(',').append(this.defaultLongOptPrefix).append(var13.getLongOpt());
            }
         }

         if(var13.hasArg()) {
            if(var13.hasArgName()) {
               var9.append(" <").append(var13.getArgName()).append(">");
            } else {
               var9.append(' ');
            }
         }

         var10.add(var9);
      }

      int var16 = 0;
      Iterator var17 = var11.iterator();

      while(var17.hasNext()) {
         Option var14 = (Option)var17.next();
         var9 = new StringBuffer(var10.get(var16++).toString());
         if(var9.length() < var8) {
            var9.append(this.createPadding(var8 - var9.length()));
         }

         var9.append(var7);
         int var15 = var8 + var5;
         if(var14.getDescription() != null) {
            var9.append(var14.getDescription());
         }

         this.renderWrappedText(var1, var2, var15, var9.toString());
         if(var17.hasNext()) {
            var1.append(this.defaultNewLine);
         }
      }

      return var1;
   }

   protected StringBuffer renderWrappedText(StringBuffer var1, int var2, int var3, String var4) {
      int var5 = this.findWrapPos(var4, var2, 0);
      if(var5 == -1) {
         var1.append(this.rtrim(var4));
         return var1;
      } else {
         var1.append(this.rtrim(var4.substring(0, var5))).append(this.defaultNewLine);
         if(var3 >= var2) {
            var3 = 1;
         }

         String var6 = this.createPadding(var3);

         while(true) {
            var4 = var6 + var4.substring(var5).trim();
            var5 = this.findWrapPos(var4, var2, 0);
            if(var5 == -1) {
               var1.append(var4);
               return var1;
            }

            if(var4.length() > var2 && var5 == var3 - 1) {
               var5 = var2;
            }

            var1.append(this.rtrim(var4.substring(0, var5))).append(this.defaultNewLine);
         }
      }
   }

   protected int findWrapPos(String var1, int var2, int var3) {
      boolean var4 = true;
      int var6;
      if(((var6 = var1.indexOf(10, var3)) == -1 || var6 > var2) && ((var6 = var1.indexOf(9, var3)) == -1 || var6 > var2)) {
         if(var3 + var2 >= var1.length()) {
            return -1;
         } else {
            char var5;
            for(var6 = var3 + var2; var6 >= var3 && (var5 = var1.charAt(var6)) != 32 && var5 != 10 && var5 != 13; --var6) {
               ;
            }

            if(var6 > var3) {
               return var6;
            } else {
               for(var6 = var3 + var2; var6 <= var1.length() && (var5 = var1.charAt(var6)) != 32 && var5 != 10 && var5 != 13; ++var6) {
                  ;
               }

               return var6 == var1.length()?-1:var6;
            }
         }
      } else {
         return var6 + 1;
      }
   }

   protected String createPadding(int var1) {
      StringBuffer var2 = new StringBuffer(var1);

      for(int var3 = 0; var3 < var1; ++var3) {
         var2.append(' ');
      }

      return var2.toString();
   }

   protected String rtrim(String var1) {
      if(var1 != null && var1.length() != 0) {
         int var2;
         for(var2 = var1.length(); var2 > 0 && Character.isWhitespace(var1.charAt(var2 - 1)); --var2) {
            ;
         }

         return var1.substring(0, var2);
      } else {
         return var1;
      }
   }

   private static class OptionComparator implements Comparator {

      private OptionComparator() {
      }

      public int compare(Object var1, Object var2) {
         Option var3 = (Option)var1;
         Option var4 = (Option)var2;
         return var3.getKey().compareToIgnoreCase(var4.getKey());
      }

      // $FF: synthetic method
      OptionComparator(HelpFormatter.NamelessClass20026251 var1) {
         this();
      }
   }

   // $FF: synthetic class
   static class NamelessClass20026251 {
   }
}
