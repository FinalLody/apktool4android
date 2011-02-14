// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:02
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.tree;


public class RewriteCardinalityException extends RuntimeException {

   public String elementDescription;


   public RewriteCardinalityException(String var1) {
      this.elementDescription = var1;
   }

   public String getMessage() {
      return this.elementDescription != null?this.elementDescription:null;
   }
}
