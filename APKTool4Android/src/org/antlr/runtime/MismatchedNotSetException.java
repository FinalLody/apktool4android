// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:16
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.BitSet;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedSetException;

public class MismatchedNotSetException extends MismatchedSetException {

   public MismatchedNotSetException() {
   }

   public MismatchedNotSetException(BitSet var1, IntStream var2) {
      super(var1, var2);
   }

   public String toString() {
      return "MismatchedNotSetException(" + this.getUnexpectedType() + "!=" + this.expecting + ")";
   }
}
