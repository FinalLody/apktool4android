// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:14
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.TreeNodeStream;

public class MismatchedTreeNodeException extends RecognitionException {

   public int expecting;


   public MismatchedTreeNodeException() {
   }

   public MismatchedTreeNodeException(int var1, TreeNodeStream var2) {
      super(var2);
      this.expecting = var1;
   }

   public String toString() {
      return "MismatchedTreeNodeException(" + this.getUnexpectedType() + "!=" + this.expecting + ")";
   }
}
