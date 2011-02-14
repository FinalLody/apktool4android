// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:13
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;

public class DFA {

   protected short[] eot;
   protected short[] eof;
   protected char[] min;
   protected char[] max;
   protected short[] accept;
   protected short[] special;
   protected short[][] transition;
   protected int decisionNumber;
   protected BaseRecognizer recognizer;
   public static final boolean debug = false;


   public int predict(IntStream var1) throws RecognitionException {
      int var2 = var1.mark();
      int var3 = 0;

      while(true) {
         byte var6;
         try {
            short var4 = this.special[var3];
            if(var4 >= 0) {
               var3 = this.specialStateTransition(var4, var1);
               if(var3 != -1) {
                  var1.consume();
                  continue;
               }

               this.noViableAlt(var3, var1);
               byte var11 = 0;
               return var11;
            }

            if(this.accept[var3] >= 1) {
               short var12 = this.accept[var3];
               return var12;
            }

            char var5 = (char)var1.LA(1);
            short var13;
            if(var5 >= this.min[var3] && var5 <= this.max[var3]) {
               var13 = this.transition[var3][var5 - this.min[var3]];
               if(var13 >= 0) {
                  var3 = var13;
                  var1.consume();
                  continue;
               }

               if(this.eot[var3] >= 0) {
                  var3 = this.eot[var3];
                  var1.consume();
                  continue;
               }

               this.noViableAlt(var3, var1);
               byte var7 = 0;
               return var7;
            }

            if(this.eot[var3] >= 0) {
               var3 = this.eot[var3];
               var1.consume();
               continue;
            }

            if(var5 == '\uffff' && this.eof[var3] >= 0) {
               var13 = this.accept[this.eof[var3]];
               return var13;
            }

            this.noViableAlt(var3, var1);
            var6 = 0;
         } finally {
            var1.rewind(var2);
         }

         return var6;
      }
   }

   protected void noViableAlt(int var1, IntStream var2) throws NoViableAltException {
      if(this.recognizer.state.backtracking > 0) {
         this.recognizer.state.failed = true;
      } else {
         NoViableAltException var3 = new NoViableAltException(this.getDescription(), this.decisionNumber, var1, var2);
         this.error(var3);
         throw var3;
      }
   }

   protected void error(NoViableAltException var1) {
   }

   public int specialStateTransition(int var1, IntStream var2) throws NoViableAltException {
      return -1;
   }

   public String getDescription() {
      return "n/a";
   }

   public static short[] unpackEncodedString(String var0) {
      int var1 = 0;

      for(int var2 = 0; var2 < var0.length(); var2 += 2) {
         var1 += var0.charAt(var2);
      }

      short[] var8 = new short[var1];
      int var3 = 0;

      for(int var4 = 0; var4 < var0.length(); var4 += 2) {
         char var5 = var0.charAt(var4);
         char var6 = var0.charAt(var4 + 1);

         for(int var7 = 1; var7 <= var5; ++var7) {
            var8[var3++] = (short)var6;
         }
      }

      return var8;
   }

   public static char[] unpackEncodedStringToUnsignedChars(String var0) {
      int var1 = 0;

      for(int var2 = 0; var2 < var0.length(); var2 += 2) {
         var1 += var0.charAt(var2);
      }

      char[] var8 = new char[var1];
      int var3 = 0;

      for(int var4 = 0; var4 < var0.length(); var4 += 2) {
         char var5 = var0.charAt(var4);
         char var6 = var0.charAt(var4 + 1);

         for(int var7 = 1; var7 <= var5; ++var7) {
            var8[var3++] = var6;
         }
      }

      return var8;
   }
}
