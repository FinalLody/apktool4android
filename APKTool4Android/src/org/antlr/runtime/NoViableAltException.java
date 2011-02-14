// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:54
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;

public class NoViableAltException extends RecognitionException {

   public String grammarDecisionDescription;
   public int decisionNumber;
   public int stateNumber;


   public NoViableAltException() {
   }

   public NoViableAltException(String var1, int var2, int var3, IntStream var4) {
      super(var4);
      this.grammarDecisionDescription = var1;
      this.decisionNumber = var2;
      this.stateNumber = var3;
   }

   public String toString() {
      return this.input instanceof CharStream?"NoViableAltException(\'" + (char)this.getUnexpectedType() + "\'@[" + this.grammarDecisionDescription + "])":"NoViableAltException(" + this.getUnexpectedType() + "@[" + this.grammarDecisionDescription + "])";
   }
}
