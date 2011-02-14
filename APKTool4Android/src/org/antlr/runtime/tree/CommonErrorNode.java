// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:00
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.tree;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.MissingTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.UnwantedTokenException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.antlr.runtime.tree.TreeNodeStream;

public class CommonErrorNode extends CommonTree {

   public IntStream input;
   public Token start;
   public Token stop;
   public RecognitionException trappedException;


   public CommonErrorNode(TokenStream var1, Token var2, Token var3, RecognitionException var4) {
      if(var3 == null || var3.getTokenIndex() < var2.getTokenIndex() && var3.getType() != -1) {
         var3 = var2;
      }

      this.input = var1;
      this.start = var2;
      this.stop = var3;
      this.trappedException = var4;
   }

   public boolean isNil() {
      return false;
   }

   public int getType() {
      return 0;
   }

   public String getText() {
      String var1 = null;
      if(this.start instanceof Token) {
         int var2 = this.start.getTokenIndex();
         int var3 = this.stop.getTokenIndex();
         if(this.stop.getType() == -1) {
            var3 = ((TokenStream)this.input).size();
         }

         var1 = ((TokenStream)this.input).toString(var2, var3);
      } else if(this.start instanceof Tree) {
         var1 = ((TreeNodeStream)this.input).toString(this.start, this.stop);
      } else {
         var1 = "<unknown>";
      }

      return var1;
   }

   public String toString() {
      return this.trappedException instanceof MissingTokenException?"<missing type: " + ((MissingTokenException)this.trappedException).getMissingType() + ">":(this.trappedException instanceof UnwantedTokenException?"<extraneous: " + ((UnwantedTokenException)this.trappedException).getUnexpectedToken() + ", resync=" + this.getText() + ">":(this.trappedException instanceof MismatchedTokenException?"<mismatched token: " + this.trappedException.token + ", resync=" + this.getText() + ">":(this.trappedException instanceof NoViableAltException?"<unexpected: " + this.trappedException.token + ", resync=" + this.getText() + ">":"<error: " + this.getText() + ">")));
   }
}
