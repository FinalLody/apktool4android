// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:14
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedTokenException;

public class MissingTokenException extends MismatchedTokenException {

   public Object inserted;


   public MissingTokenException() {
   }

   public MissingTokenException(int var1, IntStream var2, Object var3) {
      super(var1, var2);
      this.inserted = var3;
   }

   public int getMissingType() {
      return this.expecting;
   }

   public String toString() {
      return this.inserted != null && this.token != null?"MissingTokenException(inserted " + this.inserted + " at " + this.token.getText() + ")":(this.token != null?"MissingTokenException(at " + this.token.getText() + ")":"MissingTokenException");
   }
}
