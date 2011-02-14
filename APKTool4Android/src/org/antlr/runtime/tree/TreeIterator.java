// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:07
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.tree;

import java.util.Iterator;
import org.antlr.runtime.misc.FastQueue;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.TreeAdaptor;

public class TreeIterator implements Iterator {

   protected TreeAdaptor adaptor;
   protected Object root;
   protected Object tree;
   protected boolean firstTime;
   public Object up;
   public Object down;
   public Object eof;
   protected FastQueue nodes;


   public TreeIterator(Object var1) {
      this(new CommonTreeAdaptor(), var1);
   }

   public TreeIterator(TreeAdaptor var1, Object var2) {
      this.firstTime = true;
      this.adaptor = var1;
      this.tree = var2;
      this.root = var2;
      this.nodes = new FastQueue();
      this.down = var1.create(2, "DOWN");
      this.up = var1.create(3, "UP");
      this.eof = var1.create(-1, "EOF");
   }

   public void reset() {
      this.firstTime = true;
      this.tree = this.root;
      this.nodes.clear();
   }

   public boolean hasNext() {
      return this.firstTime?this.root != null:(this.nodes != null && this.nodes.size() > 0?true:(this.tree == null?false:(this.adaptor.getChildCount(this.tree) > 0?true:this.adaptor.getParent(this.tree) != null)));
   }

   public Object next() {
      if(this.firstTime) {
         this.firstTime = false;
         if(this.adaptor.getChildCount(this.tree) == 0) {
            this.nodes.add(this.eof);
            return this.tree;
         } else {
            return this.tree;
         }
      } else if(this.nodes != null && this.nodes.size() > 0) {
         return this.nodes.remove();
      } else if(this.tree == null) {
         return this.eof;
      } else if(this.adaptor.getChildCount(this.tree) > 0) {
         this.tree = this.adaptor.getChild(this.tree, 0);
         this.nodes.add(this.tree);
         return this.down;
      } else {
         Object var1;
         for(var1 = this.adaptor.getParent(this.tree); var1 != null && this.adaptor.getChildIndex(this.tree) + 1 >= this.adaptor.getChildCount(var1); var1 = this.adaptor.getParent(this.tree)) {
            this.nodes.add(this.up);
            this.tree = var1;
         }

         if(var1 == null) {
            this.tree = null;
            this.nodes.add(this.eof);
            return this.nodes.remove();
         } else {
            int var2 = this.adaptor.getChildIndex(this.tree) + 1;
            this.tree = this.adaptor.getChild(var1, var2);
            this.nodes.add(this.tree);
            return this.nodes.remove();
         }
      }
   }

   public void remove() {
      throw new UnsupportedOperationException();
   }
}
