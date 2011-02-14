// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:04
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.tree;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.BaseTree;
import org.antlr.runtime.tree.Tree;

public class CommonTree extends BaseTree {

   public Token token;
   protected int startIndex = -1;
   protected int stopIndex = -1;
   public CommonTree parent;
   public int childIndex = -1;


   public CommonTree() {
   }

   public CommonTree(CommonTree var1) {
      super(var1);
      this.token = var1.token;
      this.startIndex = var1.startIndex;
      this.stopIndex = var1.stopIndex;
   }

   public CommonTree(Token var1) {
      this.token = var1;
   }

   public Token getToken() {
      return this.token;
   }

   public Tree dupNode() {
      return new CommonTree(this);
   }

   public boolean isNil() {
      return this.token == null;
   }

   public int getType() {
      return this.token == null?0:this.token.getType();
   }

   public String getText() {
      return this.token == null?null:this.token.getText();
   }

   public int getLine() {
      return this.token != null && this.token.getLine() != 0?this.token.getLine():(this.getChildCount() > 0?this.getChild(0).getLine():0);
   }

   public int getCharPositionInLine() {
      return this.token != null && this.token.getCharPositionInLine() != -1?this.token.getCharPositionInLine():(this.getChildCount() > 0?this.getChild(0).getCharPositionInLine():0);
   }

   public int getTokenStartIndex() {
      return this.startIndex == -1 && this.token != null?this.token.getTokenIndex():this.startIndex;
   }

   public void setTokenStartIndex(int var1) {
      this.startIndex = var1;
   }

   public int getTokenStopIndex() {
      return this.stopIndex == -1 && this.token != null?this.token.getTokenIndex():this.stopIndex;
   }

   public void setTokenStopIndex(int var1) {
      this.stopIndex = var1;
   }

   public void setUnknownTokenBoundaries() {
      if(this.children == null) {
         if(this.startIndex < 0 || this.stopIndex < 0) {
            this.startIndex = this.stopIndex = this.token.getTokenIndex();
         }

      } else {
         for(int var1 = 0; var1 < this.children.size(); ++var1) {
            ((CommonTree)this.children.get(var1)).setUnknownTokenBoundaries();
         }

         if(this.startIndex < 0 || this.stopIndex < 0) {
            if(this.children.size() > 0) {
               CommonTree var3 = (CommonTree)this.children.get(0);
               CommonTree var2 = (CommonTree)this.children.get(this.children.size() - 1);
               this.startIndex = var3.getTokenStartIndex();
               this.stopIndex = var2.getTokenStopIndex();
            }

         }
      }
   }

   public int getChildIndex() {
      return this.childIndex;
   }

   public Tree getParent() {
      return this.parent;
   }

   public void setParent(Tree var1) {
      this.parent = (CommonTree)var1;
   }

   public void setChildIndex(int var1) {
      this.childIndex = var1;
   }

   public String toString() {
      return this.isNil()?"nil":(this.getType() == 0?"<errornode>":(this.token == null?null:this.token.getText()));
   }
}
