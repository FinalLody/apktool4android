// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:01
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.tree;

import org.antlr.runtime.TokenStream;
import org.antlr.runtime.misc.IntArray;
import org.antlr.runtime.misc.LookaheadStream;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;
import org.antlr.runtime.tree.TreeIterator;
import org.antlr.runtime.tree.TreeNodeStream;

public class CommonTreeNodeStream extends LookaheadStream implements TreeNodeStream {

   public static final int DEFAULT_INITIAL_BUFFER_SIZE = 100;
   public static final int INITIAL_CALL_STACK_SIZE = 10;
   protected Object root;
   protected TokenStream tokens;
   TreeAdaptor adaptor;
   protected TreeIterator it;
   protected IntArray calls;
   protected boolean hasNilRoot;
   protected int level;


   public CommonTreeNodeStream(Object var1) {
      this(new CommonTreeAdaptor(), var1);
   }

   public CommonTreeNodeStream(TreeAdaptor var1, Object var2) {
      super(var1.create(-1, "EOF"));
      this.hasNilRoot = false;
      this.level = 0;
      this.root = var2;
      this.adaptor = var1;
      this.it = new TreeIterator(this.root);
      this.it.eof = this.eof;
   }

   public void reset() {
      super.reset();
      this.it.reset();
      this.hasNilRoot = false;
      this.level = 0;
      if(this.calls != null) {
         this.calls.clear();
      }

   }

   public Object nextElement() {
      Object var1 = this.it.next();
      if(var1 == this.it.up) {
         --this.level;
         if(this.level == 0 && this.hasNilRoot) {
            return this.it.next();
         }
      } else if(var1 == this.it.down) {
         ++this.level;
      }

      if(this.level == 0 && this.adaptor.isNil(var1)) {
         this.hasNilRoot = true;
         var1 = this.it.next();
         ++this.level;
         var1 = this.it.next();
      }

      return var1;
   }

   public void setUniqueNavigationNodes(boolean var1) {
   }

   public Object getTreeSource() {
      return this.root;
   }

   public String getSourceName() {
      return this.getTokenStream().getSourceName();
   }

   public TokenStream getTokenStream() {
      return this.tokens;
   }

   public void setTokenStream(TokenStream var1) {
      this.tokens = var1;
   }

   public TreeAdaptor getTreeAdaptor() {
      return this.adaptor;
   }

   public void setTreeAdaptor(TreeAdaptor var1) {
      this.adaptor = var1;
   }

   public int LA(int var1) {
      return this.adaptor.getType(this.LT(var1));
   }

   public void push(int var1) {
      if(this.calls == null) {
         this.calls = new IntArray();
      }

      this.calls.push(this.p);
      this.seek(var1);
   }

   public int pop() {
      int var1 = this.calls.pop();
      this.seek(var1);
      return var1;
   }

   public void replaceChildren(Object var1, int var2, int var3, Object var4) {
      if(var1 != null) {
         this.adaptor.replaceChildren(var1, var2, var3, var4);
      }

   }

   public String toString(Object var1, Object var2) {
      return "n/a";
   }

   public String toTokenTypeString() {
      this.reset();
      StringBuffer var1 = new StringBuffer();
      Object var2 = this.LT(1);

      for(int var3 = this.adaptor.getType(var2); var3 != -1; var3 = this.adaptor.getType(var2)) {
         var1.append(" ");
         var1.append(var3);
         this.consume();
         var2 = this.LT(1);
      }

      return var1.toString();
   }
}
