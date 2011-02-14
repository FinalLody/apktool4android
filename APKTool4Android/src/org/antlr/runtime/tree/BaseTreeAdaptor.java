// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:06
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.tree;

import java.util.HashMap;
import java.util.Map;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonErrorNode;
import org.antlr.runtime.tree.Tree;
import org.antlr.runtime.tree.TreeAdaptor;

public abstract class BaseTreeAdaptor implements TreeAdaptor {

   protected Map treeToUniqueIDMap;
   protected int uniqueNodeID = 1;


   public Object nil() {
      return this.create((Token)null);
   }

   public Object errorNode(TokenStream var1, Token var2, Token var3, RecognitionException var4) {
      CommonErrorNode var5 = new CommonErrorNode(var1, var2, var3, var4);
      return var5;
   }

   public boolean isNil(Object var1) {
      return ((Tree)var1).isNil();
   }

   public Object dupTree(Object var1) {
      return this.dupTree(var1, (Object)null);
   }

   public Object dupTree(Object var1, Object var2) {
      if(var1 == null) {
         return null;
      } else {
         Object var3 = this.dupNode(var1);
         this.setChildIndex(var3, this.getChildIndex(var1));
         this.setParent(var3, var2);
         int var4 = this.getChildCount(var1);

         for(int var5 = 0; var5 < var4; ++var5) {
            Object var6 = this.getChild(var1, var5);
            Object var7 = this.dupTree(var6, var1);
            this.addChild(var3, var7);
         }

         return var3;
      }
   }

   public void addChild(Object var1, Object var2) {
      if(var1 != null && var2 != null) {
         ((Tree)var1).addChild((Tree)var2);
      }

   }

   public Object becomeRoot(Object var1, Object var2) {
      Tree var3 = (Tree)var1;
      Tree var4 = (Tree)var2;
      if(var2 == null) {
         return var1;
      } else {
         if(var3.isNil()) {
            int var5 = var3.getChildCount();
            if(var5 == 1) {
               var3 = var3.getChild(0);
            } else if(var5 > 1) {
               throw new RuntimeException("more than one node as root (TODO: make exception hierarchy)");
            }
         }

         var3.addChild(var4);
         return var3;
      }
   }

   public Object rulePostProcessing(Object var1) {
      Tree var2 = (Tree)var1;
      if(var2 != null && var2.isNil()) {
         if(var2.getChildCount() == 0) {
            var2 = null;
         } else if(var2.getChildCount() == 1) {
            var2 = var2.getChild(0);
            var2.setParent((Tree)null);
            var2.setChildIndex(-1);
         }
      }

      return var2;
   }

   public Object becomeRoot(Token var1, Object var2) {
      return this.becomeRoot(this.create(var1), var2);
   }

   public Object create(int var1, Token var2) {
      var2 = this.createToken(var2);
      var2.setType(var1);
      Tree var3 = (Tree)this.create(var2);
      return var3;
   }

   public Object create(int var1, Token var2, String var3) {
      var2 = this.createToken(var2);
      var2.setType(var1);
      var2.setText(var3);
      Tree var4 = (Tree)this.create(var2);
      return var4;
   }

   public Object create(int var1, String var2) {
      Token var3 = this.createToken(var1, var2);
      Tree var4 = (Tree)this.create(var3);
      return var4;
   }

   public int getType(Object var1) {
      return ((Tree)var1).getType();
   }

   public void setType(Object var1, int var2) {
      throw new NoSuchMethodError("don\'t know enough about Tree node");
   }

   public String getText(Object var1) {
      return ((Tree)var1).getText();
   }

   public void setText(Object var1, String var2) {
      throw new NoSuchMethodError("don\'t know enough about Tree node");
   }

   public Object getChild(Object var1, int var2) {
      return ((Tree)var1).getChild(var2);
   }

   public void setChild(Object var1, int var2, Object var3) {
      ((Tree)var1).setChild(var2, (Tree)var3);
   }

   public Object deleteChild(Object var1, int var2) {
      return ((Tree)var1).deleteChild(var2);
   }

   public int getChildCount(Object var1) {
      return ((Tree)var1).getChildCount();
   }

   public int getUniqueID(Object var1) {
      if(this.treeToUniqueIDMap == null) {
         this.treeToUniqueIDMap = new HashMap();
      }

      Integer var2 = (Integer)this.treeToUniqueIDMap.get(var1);
      if(var2 != null) {
         return var2.intValue();
      } else {
         int var3 = this.uniqueNodeID;
         this.treeToUniqueIDMap.put(var1, new Integer(var3));
         ++this.uniqueNodeID;
         return var3;
      }
   }

   public abstract Token createToken(int var1, String var2);

   public abstract Token createToken(Token var1);
}
