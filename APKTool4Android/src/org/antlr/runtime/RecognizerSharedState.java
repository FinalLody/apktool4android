// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:13
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import java.util.Map;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.Token;

public class RecognizerSharedState {

   public BitSet[] following = new BitSet[100];
   public int _fsp = -1;
   public boolean errorRecovery = false;
   public int lastErrorIndex = -1;
   public boolean failed = false;
   public int syntaxErrors = 0;
   public int backtracking = 0;
   public Map[] ruleMemo;
   public Token token;
   public int tokenStartCharIndex = -1;
   public int tokenStartLine;
   public int tokenStartCharPositionInLine;
   public int channel;
   public int type;
   public String text;


   public RecognizerSharedState() {
   }

   public RecognizerSharedState(RecognizerSharedState var1) {
      if(this.following.length < var1.following.length) {
         this.following = new BitSet[var1.following.length];
      }

      System.arraycopy(var1.following, 0, this.following, 0, var1.following.length);
      this._fsp = var1._fsp;
      this.errorRecovery = var1.errorRecovery;
      this.lastErrorIndex = var1.lastErrorIndex;
      this.failed = var1.failed;
      this.syntaxErrors = var1.syntaxErrors;
      this.backtracking = var1.backtracking;
      if(var1.ruleMemo != null) {
         this.ruleMemo = new Map[var1.ruleMemo.length];
         System.arraycopy(var1.ruleMemo, 0, this.ruleMemo, 0, var1.ruleMemo.length);
      }

      this.token = var1.token;
      this.tokenStartCharIndex = var1.tokenStartCharIndex;
      this.tokenStartCharPositionInLine = var1.tokenStartCharPositionInLine;
      this.channel = var1.channel;
      this.type = var1.type;
      this.text = var1.text;
   }
}
