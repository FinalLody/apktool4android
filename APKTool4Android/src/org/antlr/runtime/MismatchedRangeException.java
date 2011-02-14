// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:54
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;

public class MismatchedRangeException extends RecognitionException {

   public int a;
   public int b;


   public MismatchedRangeException() {
   }

   public MismatchedRangeException(int var1, int var2, IntStream var3) {
      super(var3);
      this.a = var1;
      this.b = var2;
   }

   public String toString() {
      return "MismatchedNotSetException(" + this.getUnexpectedType() + " not in [" + this.a + "," + this.b + "])";
   }
}
