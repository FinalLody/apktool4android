// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:14
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;

public class MismatchedSetException extends RecognitionException {

   public BitSet expecting;


   public MismatchedSetException() {
   }

   public MismatchedSetException(BitSet var1, IntStream var2) {
      super(var2);
      this.expecting = var1;
   }

   public String toString() {
      return "MismatchedSetException(" + this.getUnexpectedType() + "!=" + this.expecting + ")";
   }
}
