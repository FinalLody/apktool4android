// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:09
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.tree;

import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

public abstract class BaseTree implements Tree {

   protected List children;


   public BaseTree() {
   }

   public BaseTree(Tree var1) {
   }

   public Tree getChild(int var1) {
      return this.children != null && var1 < this.children.size()?(Tree)this.children.get(var1):null;
   }

   public List getChildren() {
      return this.children;
   }

   public Tree getFirstChildWithType(int var1) {
      for(int var2 = 0; this.children != null && var2 < this.children.size(); ++var2) {
         Tree var3 = (Tree)this.children.get(var2);
         if(var3.getType() == var1) {
            return var3;
         }
      }

      return null;
   }

   public int getChildCount() {
      return this.children == null?0:this.children.size();
   }

   public void addChild(Tree var1) {
      if(var1 != null) {
         BaseTree var2 = (BaseTree)var1;
         if(var2.isNil()) {
            if(this.children != null && this.children == var2.children) {
               throw new RuntimeException("attempt to add child list to itself");
            }

            if(var2.children != null) {
               if(this.children != null) {
                  int var3 = var2.children.size();

                  for(int var4 = 0; var4 < var3; ++var4) {
                     Tree var5 = (Tree)var2.children.get(var4);
                     this.children.add(var5);
                     var5.setParent(this);
                     var5.setChildIndex(this.children.size() - 1);
                  }
               } else {
                  this.children = var2.children;
                  this.freshenParentAndChildIndexes();
               }
            }
         } else {
            if(this.children == null) {
               this.children = this.createChildrenList();
            }

            this.children.add(var1);
            var2.setParent(this);
            var2.setChildIndex(this.children.size() - 1);
         }

      }
   }

   public void addChildren(List var1) {
      for(int var2 = 0; var2 < var1.size(); ++var2) {
         Tree var3 = (Tree)var1.get(var2);
         this.addChild(var3);
      }

   }

   public void setChild(int var1, Tree var2) {
      if(var2 != null) {
         if(var2.isNil()) {
            throw new IllegalArgumentException("Can\'t set single child to a list");
         } else {
            if(this.children == null) {
               this.children = this.createChildrenList();
            }

            this.children.set(var1, var2);
            var2.setParent(this);
            var2.setChildIndex(var1);
         }
      }
   }

   public Object deleteChild(int var1) {
      if(this.children == null) {
         return null;
      } else {
         Tree var2 = (Tree)this.children.remove(var1);
         this.freshenParentAndChildIndexes(var1);
         return var2;
      }
   }

   public void replaceChildren(int var1, int var2, Object var3) {
      if(this.children == null) {
         throw new IllegalArgumentException("indexes invalid; no children in list");
      } else {
         int var4 = var2 - var1 + 1;
         BaseTree var6 = (BaseTree)var3;
         Object var7 = null;
         if(var6.isNil()) {
            var7 = var6.children;
         } else {
            var7 = new ArrayList(1);
            ((List)var7).add(var6);
         }

         int var5 = ((List)var7).size();
         int var8 = ((List)var7).size();
         int var9 = var4 - var5;
         int var10;
         int var11;
         if(var9 == 0) {
            var10 = 0;

            for(var11 = var1; var11 <= var2; ++var11) {
               BaseTree var12 = (BaseTree)((List)var7).get(var10);
               this.children.set(var11, var12);
               var12.setParent(this);
               var12.setChildIndex(var11);
               ++var10;
            }
         } else if(var9 > 0) {
            for(var10 = 0; var10 < var8; ++var10) {
               this.children.set(var1 + var10, ((List)var7).get(var10));
            }

            var10 = var1 + var8;

            for(var11 = var10; var11 <= var2; ++var11) {
               this.children.remove(var10);
            }

            this.freshenParentAndChildIndexes(var1);
         } else {
            for(var10 = 0; var10 < var4; ++var10) {
               this.children.set(var1 + var10, ((List)var7).get(var10));
            }

            var10 = var5 - var4;

            for(var11 = var4; var11 < var5; ++var11) {
               this.children.add(var1 + var11, ((List)var7).get(var11));
            }

            this.freshenParentAndChildIndexes(var1);
         }

      }
   }

   protected List createChildrenList() {
      return new ArrayList();
   }

   public boolean isNil() {
      return false;
   }

   public void freshenParentAndChildIndexes() {
      this.freshenParentAndChildIndexes(0);
   }

   public void freshenParentAndChildIndexes(int var1) {
      int var2 = this.getChildCount();

      for(int var3 = var1; var3 < var2; ++var3) {
         Tree var4 = this.getChild(var3);
         var4.setChildIndex(var3);
         var4.setParent(this);
      }

   }

   public void sanityCheckParentAndChildIndexes() {
      this.sanityCheckParentAndChildIndexes((Tree)null, -1);
   }

   public void sanityCheckParentAndChildIndexes(Tree var1, int var2) {
      if(var1 != this.getParent()) {
         throw new IllegalStateException("parents don\'t match; expected " + var1 + " found " + this.getParent());
      } else if(var2 != this.getChildIndex()) {
         throw new IllegalStateException("child indexes don\'t match; expected " + var2 + " found " + this.getChildIndex());
      } else {
         int var3 = this.getChildCount();

         for(int var4 = 0; var4 < var3; ++var4) {
            CommonTree var5 = (CommonTree)this.getChild(var4);
            var5.sanityCheckParentAndChildIndexes(this, var4);
         }

      }
   }

   public int getChildIndex() {
      return 0;
   }

   public void setChildIndex(int var1) {
   }

   public Tree getParent() {
      return null;
   }

   public void setParent(Tree var1) {
   }

   public boolean hasAncestor(int var1) {
      return this.getAncestor(var1) != null;
   }

   public Tree getAncestor(int var1) {
      for(Tree var2 = this.getParent(); var2 != null; var2 = var2.getParent()) {
         if(var2.getType() == var1) {
            return var2;
         }
      }

      return null;
   }

   public List getAncestors() {
      if(this.getParent() == null) {
         return null;
      } else {
         ArrayList var1 = new ArrayList();

         for(Tree var2 = this.getParent(); var2 != null; var2 = var2.getParent()) {
            var1.add(0, var2);
         }

         return var1;
      }
   }

   public String toStringTree() {
      if(this.children != null && this.children.size() != 0) {
         StringBuffer var1 = new StringBuffer();
         if(!this.isNil()) {
            var1.append("(");
            var1.append(this.toString());
            var1.append(' ');
         }

         for(int var2 = 0; this.children != null && var2 < this.children.size(); ++var2) {
            Tree var3 = (Tree)this.children.get(var2);
            if(var2 > 0) {
               var1.append(' ');
            }

            var1.append(var3.toStringTree());
         }

         if(!this.isNil()) {
            var1.append(")");
         }

         return var1.toString();
      } else {
         return this.toString();
      }
   }

   public int getLine() {
      return 0;
   }

   public int getCharPositionInLine() {
      return 0;
   }

   public abstract String toString();
}
