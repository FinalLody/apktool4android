// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:03
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.tree;

import java.util.List;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.RewriteRuleElementStream;
import org.antlr.runtime.tree.TreeAdaptor;

public class RewriteRuleTokenStream extends RewriteRuleElementStream {

   public RewriteRuleTokenStream(TreeAdaptor var1, String var2) {
      super(var1, var2);
   }

   public RewriteRuleTokenStream(TreeAdaptor var1, String var2, Object var3) {
      super(var1, var2, var3);
   }

   public RewriteRuleTokenStream(TreeAdaptor var1, String var2, List var3) {
      super(var1, var2, var3);
   }

   public Object nextNode() {
      Token var1 = (Token)this._next();
      return this.adaptor.create(var1);
   }

   public Token nextToken() {
      return (Token)this._next();
   }

   protected Object toTree(Object var1) {
      return var1;
   }

   protected Object dup(Object var1) {
      throw new UnsupportedOperationException("dup can\'t be called for a token stream.");
   }
}
