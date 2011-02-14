// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:56
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;

public class EarlyExitException extends RecognitionException {

   public int decisionNumber;


   public EarlyExitException() {
   }

   public EarlyExitException(int var1, IntStream var2) {
      super(var2);
      this.decisionNumber = var1;
   }
}
