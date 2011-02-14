// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:15
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.Token;

public class UnwantedTokenException extends MismatchedTokenException {

   public UnwantedTokenException() {
   }

   public UnwantedTokenException(int var1, IntStream var2) {
      super(var1, var2);
   }

   public Token getUnexpectedToken() {
      return this.token;
   }

   public String toString() {
      String var1 = ", expected " + this.expecting;
      if(this.expecting == 0) {
         var1 = "";
      }

      return this.token == null?"UnwantedTokenException(found=" + null + var1 + ")":"UnwantedTokenException(found=" + this.token.getText() + var1 + ")";
   }
}
