// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:53
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;

public class MismatchedTokenException extends RecognitionException {

   public int expecting = 0;


   public MismatchedTokenException() {
   }

   public MismatchedTokenException(int var1, IntStream var2) {
      super(var2);
      this.expecting = var1;
   }

   public String toString() {
      return "MismatchedTokenException(" + this.getUnexpectedType() + "!=" + this.expecting + ")";
   }
}
