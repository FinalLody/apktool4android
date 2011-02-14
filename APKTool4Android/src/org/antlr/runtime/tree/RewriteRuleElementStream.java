// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:07
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.tree;

import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.tree.RewriteCardinalityException;
import org.antlr.runtime.tree.RewriteEmptyStreamException;
import org.antlr.runtime.tree.TreeAdaptor;

public abstract class RewriteRuleElementStream {

   protected int cursor;
   protected Object singleElement;
   protected List elements;
   protected boolean dirty;
   protected String elementDescription;
   protected TreeAdaptor adaptor;


   public RewriteRuleElementStream(TreeAdaptor var1, String var2) {
      this.cursor = 0;
      this.dirty = false;
      this.elementDescription = var2;
      this.adaptor = var1;
   }

   public RewriteRuleElementStream(TreeAdaptor var1, String var2, Object var3) {
      this(var1, var2);
      this.add(var3);
   }

   public RewriteRuleElementStream(TreeAdaptor var1, String var2, List var3) {
      this(var1, var2);
      this.singleElement = null;
      this.elements = var3;
   }

   public void reset() {
      this.cursor = 0;
      this.dirty = true;
   }

   public void add(Object var1) {
      if(var1 != null) {
         if(this.elements != null) {
            this.elements.add(var1);
         } else if(this.singleElement == null) {
            this.singleElement = var1;
         } else {
            this.elements = new ArrayList(5);
            this.elements.add(this.singleElement);
            this.singleElement = null;
            this.elements.add(var1);
         }
      }
   }

   public Object nextTree() {
      int var1 = this.size();
      Object var2;
      if(!this.dirty && (this.cursor < var1 || var1 != 1)) {
         var2 = this._next();
         return var2;
      } else {
         var2 = this._next();
         return this.dup(var2);
      }
   }

   protected Object _next() {
      int var1 = this.size();
      if(var1 == 0) {
         throw new RewriteEmptyStreamException(this.elementDescription);
      } else if(this.cursor >= var1) {
         if(var1 == 1) {
            return this.toTree(this.singleElement);
         } else {
            throw new RewriteCardinalityException(this.elementDescription);
         }
      } else if(this.singleElement != null) {
         ++this.cursor;
         return this.toTree(this.singleElement);
      } else {
         Object var2 = this.toTree(this.elements.get(this.cursor));
         ++this.cursor;
         return var2;
      }
   }

   protected abstract Object dup(Object var1);

   protected Object toTree(Object var1) {
      return var1;
   }

   public boolean hasNext() {
      return this.singleElement != null && this.cursor < 1 || this.elements != null && this.cursor < this.elements.size();
   }

   public int size() {
      byte var1 = 0;
      if(this.singleElement != null) {
         var1 = 1;
      }

      return this.elements != null?this.elements.size():var1;
   }

   public String getDescription() {
      return this.elementDescription;
   }
}
