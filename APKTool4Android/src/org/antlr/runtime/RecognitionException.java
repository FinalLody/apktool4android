// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:54
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.antlr.runtime.tree.TreeAdaptor;
import org.antlr.runtime.tree.TreeNodeStream;

public class RecognitionException extends Exception {

   public transient IntStream input;
   public int index;
   public Token token;
   public Object node;
   public int c;
   public int line;
   public int charPositionInLine;
   public boolean approximateLineInfo;


   public RecognitionException() {
   }

   public RecognitionException(IntStream var1) {
      this.input = var1;
      this.index = var1.index();
      if(var1 instanceof TokenStream) {
         this.token = ((TokenStream)var1).LT(1);
         this.line = this.token.getLine();
         this.charPositionInLine = this.token.getCharPositionInLine();
      }

      if(var1 instanceof TreeNodeStream) {
         this.extractInformationFromTreeNodeStream(var1);
      } else if(var1 instanceof CharStream) {
         this.c = var1.LA(1);
         this.line = ((CharStream)var1).getLine();
         this.charPositionInLine = ((CharStream)var1).getCharPositionInLine();
      } else {
         this.c = var1.LA(1);
      }

   }

   protected void extractInformationFromTreeNodeStream(IntStream var1) {
      TreeNodeStream var2 = (TreeNodeStream)var1;
      this.node = var2.LT(1);
      TreeAdaptor var3 = var2.getTreeAdaptor();
      Token var4 = var3.getToken(this.node);
      int var5;
      if(var4 != null) {
         this.token = var4;
         if(var4.getLine() <= 0) {
            var5 = -1;

            for(Object var6 = var2.LT(var5); var6 != null; var6 = var2.LT(var5)) {
               Token var7 = var3.getToken(var6);
               if(var7 != null && var7.getLine() > 0) {
                  this.line = var7.getLine();
                  this.charPositionInLine = var7.getCharPositionInLine();
                  this.approximateLineInfo = true;
                  break;
               }

               --var5;
            }
         } else {
            this.line = var4.getLine();
            this.charPositionInLine = var4.getCharPositionInLine();
         }
      } else if(this.node instanceof Tree) {
         this.line = ((Tree)this.node).getLine();
         this.charPositionInLine = ((Tree)this.node).getCharPositionInLine();
         if(this.node instanceof CommonTree) {
            this.token = ((CommonTree)this.node).token;
         }
      } else {
         var5 = var3.getType(this.node);
         String var8 = var3.getText(this.node);
         this.token = new CommonToken(var5, var8);
      }

   }

   public int getUnexpectedType() {
      if(this.input instanceof TokenStream) {
         return this.token.getType();
      } else if(this.input instanceof TreeNodeStream) {
         TreeNodeStream var1 = (TreeNodeStream)this.input;
         TreeAdaptor var2 = var1.getTreeAdaptor();
         return var2.getType(this.node);
      } else {
         return this.c;
      }
   }
}
