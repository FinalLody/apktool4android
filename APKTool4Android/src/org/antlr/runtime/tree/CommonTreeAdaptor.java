// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:07
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.tree;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.BaseTreeAdaptor;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

public class CommonTreeAdaptor extends BaseTreeAdaptor {

   public Object dupNode(Object var1) {
      return var1 == null?null:((Tree)var1).dupNode();
   }

   public Object create(Token var1) {
      return new CommonTree(var1);
   }

   public Token createToken(int var1, String var2) {
      return new CommonToken(var1, var2);
   }

   public Token createToken(Token var1) {
      return new CommonToken(var1);
   }

   public void setTokenBoundaries(Object var1, Token var2, Token var3) {
      if(var1 != null) {
         int var4 = 0;
         int var5 = 0;
         if(var2 != null) {
            var4 = var2.getTokenIndex();
         }

         if(var3 != null) {
            var5 = var3.getTokenIndex();
         }

         ((Tree)var1).setTokenStartIndex(var4);
         ((Tree)var1).setTokenStopIndex(var5);
      }
   }

   public int getTokenStartIndex(Object var1) {
      return var1 == null?-1:((Tree)var1).getTokenStartIndex();
   }

   public int getTokenStopIndex(Object var1) {
      return var1 == null?-1:((Tree)var1).getTokenStopIndex();
   }

   public String getText(Object var1) {
      return var1 == null?null:((Tree)var1).getText();
   }

   public int getType(Object var1) {
      return var1 == null?0:((Tree)var1).getType();
   }

   public Token getToken(Object var1) {
      return var1 instanceof CommonTree?((CommonTree)var1).getToken():null;
   }

   public Object getChild(Object var1, int var2) {
      return var1 == null?null:((Tree)var1).getChild(var2);
   }

   public int getChildCount(Object var1) {
      return var1 == null?0:((Tree)var1).getChildCount();
   }

   public Object getParent(Object var1) {
      return var1 == null?null:((Tree)var1).getParent();
   }

   public void setParent(Object var1, Object var2) {
      if(var1 != null) {
         ((Tree)var1).setParent((Tree)var2);
      }

   }

   public int getChildIndex(Object var1) {
      return var1 == null?0:((Tree)var1).getChildIndex();
   }

   public void setChildIndex(Object var1, int var2) {
      if(var1 != null) {
         ((Tree)var1).setChildIndex(var2);
      }

   }

   public void replaceChildren(Object var1, int var2, int var3, Object var4) {
      if(var1 != null) {
         ((Tree)var1).replaceChildren(var2, var3, var4);
      }

   }
}
