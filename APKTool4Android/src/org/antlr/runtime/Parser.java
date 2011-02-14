// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:58
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

public class Parser extends BaseRecognizer {

   public TokenStream input;


   public Parser(TokenStream var1) {
      this.setTokenStream(var1);
   }

   public Parser(TokenStream var1, RecognizerSharedState var2) {
      super(var2);
      this.setTokenStream(var1);
   }

   public void reset() {
      super.reset();
      if(this.input != null) {
         this.input.seek(0);
      }

   }

   protected Object getCurrentInputSymbol(IntStream var1) {
      return ((TokenStream)var1).LT(1);
   }

   protected Object getMissingSymbol(IntStream var1, RecognitionException var2, int var3, BitSet var4) {
      String var5 = null;
      if(var3 == -1) {
         var5 = "<missing EOF>";
      } else {
         var5 = "<missing " + this.getTokenNames()[var3] + ">";
      }

      CommonToken var6 = new CommonToken(var3, var5);
      Token var7 = ((TokenStream)var1).LT(1);
      if(var7.getType() == -1) {
         var7 = ((TokenStream)var1).LT(-1);
      }

      var6.line = var7.getLine();
      var6.charPositionInLine = var7.getCharPositionInLine();
      var6.channel = 0;
      return var6;
   }

   public void setTokenStream(TokenStream var1) {
      this.input = null;
      this.reset();
      this.input = var1;
   }

   public TokenStream getTokenStream() {
      return this.input;
   }

   public String getSourceName() {
      return this.input.getSourceName();
   }

   public void traceIn(String var1, int var2) {
      super.traceIn(var1, var2, this.input.LT(1));
   }

   public void traceOut(String var1, int var2) {
      super.traceOut(var1, var2, this.input.LT(1));
   }
}
