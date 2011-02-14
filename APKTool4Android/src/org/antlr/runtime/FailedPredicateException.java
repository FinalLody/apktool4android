// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:56
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;

public class FailedPredicateException extends RecognitionException {

   public String ruleName;
   public String predicateText;


   public FailedPredicateException() {
   }

   public FailedPredicateException(IntStream var1, String var2, String var3) {
      super(var1);
      this.ruleName = var2;
      this.predicateText = var3;
   }

   public String toString() {
      return "FailedPredicateException(" + this.ruleName + ",{" + this.predicateText + "}?)";
   }
}
