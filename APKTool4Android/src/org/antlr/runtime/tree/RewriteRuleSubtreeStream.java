// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:03
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.tree;

import java.util.List;
import org.antlr.runtime.tree.RewriteRuleElementStream;
import org.antlr.runtime.tree.TreeAdaptor;

public class RewriteRuleSubtreeStream extends RewriteRuleElementStream {

   public RewriteRuleSubtreeStream(TreeAdaptor var1, String var2) {
      super(var1, var2);
   }

   public RewriteRuleSubtreeStream(TreeAdaptor var1, String var2, Object var3) {
      super(var1, var2, var3);
   }

   public RewriteRuleSubtreeStream(TreeAdaptor var1, String var2, List var3) {
      super(var1, var2, var3);
   }

   public Object nextNode() {
      int var1 = this.size();
      Object var2;
      if(!this.dirty && (this.cursor < var1 || var1 != 1)) {
         var2 = this._next();
         return var2;
      } else {
         var2 = this._next();
         return this.adaptor.dupNode(var2);
      }
   }

   protected Object dup(Object var1) {
      return this.adaptor.dupTree(var1);
   }
}
