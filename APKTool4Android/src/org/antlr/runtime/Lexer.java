// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:52
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.MismatchedNotSetException;
import org.antlr.runtime.MismatchedRangeException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;

public abstract class Lexer extends BaseRecognizer implements TokenSource {

   protected CharStream input;


   public Lexer() {
   }

   public Lexer(CharStream var1) {
      this.input = var1;
   }

   public Lexer(CharStream var1, RecognizerSharedState var2) {
      super(var2);
      this.input = var1;
   }

   public void reset() {
      super.reset();
      if(this.input != null) {
         this.input.seek(0);
      }

      if(this.state != null) {
         this.state.token = null;
         this.state.type = 0;
         this.state.channel = 0;
         this.state.tokenStartCharIndex = -1;
         this.state.tokenStartCharPositionInLine = -1;
         this.state.tokenStartLine = -1;
         this.state.text = null;
      }
   }

   public Token nextToken() {
      while(true) {
         this.state.token = null;
         this.state.channel = 0;
         this.state.tokenStartCharIndex = this.input.index();
         this.state.tokenStartCharPositionInLine = this.input.getCharPositionInLine();
         this.state.tokenStartLine = this.input.getLine();
         this.state.text = null;
         if(this.input.LA(1) == -1) {
            return Token.EOF_TOKEN;
         }

         try {
            this.mTokens();
            if(this.state.token == null) {
               this.emit();
            } else if(this.state.token == Token.SKIP_TOKEN) {
               continue;
            }

            return this.state.token;
         } catch (NoViableAltException var2) {
            this.reportError(var2);
            this.recover(var2);
         } catch (RecognitionException var3) {
            this.reportError(var3);
         }
      }
   }

   public void skip() {
      this.state.token = Token.SKIP_TOKEN;
   }

   public abstract void mTokens() throws RecognitionException;

   public void setCharStream(CharStream var1) {
      this.input = null;
      this.reset();
      this.input = var1;
   }

   public CharStream getCharStream() {
      return this.input;
   }

   public String getSourceName() {
      return this.input.getSourceName();
   }

   public void emit(Token var1) {
      this.state.token = var1;
   }

   public Token emit() {
      CommonToken var1 = new CommonToken(this.input, this.state.type, this.state.channel, this.state.tokenStartCharIndex, this.getCharIndex() - 1);
      var1.setLine(this.state.tokenStartLine);
      var1.setText(this.state.text);
      var1.setCharPositionInLine(this.state.tokenStartCharPositionInLine);
      this.emit(var1);
      return var1;
   }

   public void match(String var1) throws MismatchedTokenException {
      for(int var2 = 0; var2 < var1.length(); this.state.failed = false) {
         if(this.input.LA(1) != var1.charAt(var2)) {
            if(this.state.backtracking > 0) {
               this.state.failed = true;
               return;
            }

            MismatchedTokenException var3 = new MismatchedTokenException(var1.charAt(var2), this.input);
            this.recover(var3);
            throw var3;
         }

         ++var2;
         this.input.consume();
      }

   }

   public void matchAny() {
      this.input.consume();
   }

   public void match(int var1) throws MismatchedTokenException {
      if(this.input.LA(1) != var1) {
         if(this.state.backtracking > 0) {
            this.state.failed = true;
         } else {
            MismatchedTokenException var2 = new MismatchedTokenException(var1, this.input);
            this.recover(var2);
            throw var2;
         }
      } else {
         this.input.consume();
         this.state.failed = false;
      }
   }

   public void matchRange(int var1, int var2) throws MismatchedRangeException {
      if(this.input.LA(1) >= var1 && this.input.LA(1) <= var2) {
         this.input.consume();
         this.state.failed = false;
      } else if(this.state.backtracking > 0) {
         this.state.failed = true;
      } else {
         MismatchedRangeException var3 = new MismatchedRangeException(var1, var2, this.input);
         this.recover(var3);
         throw var3;
      }
   }

   public int getLine() {
      return this.input.getLine();
   }

   public int getCharPositionInLine() {
      return this.input.getCharPositionInLine();
   }

   public int getCharIndex() {
      return this.input.index();
   }

   public String getText() {
      return this.state.text != null?this.state.text:this.input.substring(this.state.tokenStartCharIndex, this.getCharIndex() - 1);
   }

   public void setText(String var1) {
      this.state.text = var1;
   }

   public void reportError(RecognitionException var1) {
      this.displayRecognitionError(this.getTokenNames(), var1);
   }

   public String getErrorMessage(RecognitionException var1, String[] var2) {
      String var3 = null;
      if(var1 instanceof MismatchedTokenException) {
         MismatchedTokenException var4 = (MismatchedTokenException)var1;
         var3 = "mismatched character " + this.getCharErrorDisplay(var1.c) + " expecting " + this.getCharErrorDisplay(var4.expecting);
      } else if(var1 instanceof NoViableAltException) {
         NoViableAltException var5 = (NoViableAltException)var1;
         var3 = "no viable alternative at character " + this.getCharErrorDisplay(var1.c);
      } else if(var1 instanceof EarlyExitException) {
         EarlyExitException var6 = (EarlyExitException)var1;
         var3 = "required (...)+ loop did not match anything at character " + this.getCharErrorDisplay(var1.c);
      } else if(var1 instanceof MismatchedNotSetException) {
         MismatchedNotSetException var7 = (MismatchedNotSetException)var1;
         var3 = "mismatched character " + this.getCharErrorDisplay(var1.c) + " expecting set " + var7.expecting;
      } else if(var1 instanceof MismatchedSetException) {
         MismatchedSetException var8 = (MismatchedSetException)var1;
         var3 = "mismatched character " + this.getCharErrorDisplay(var1.c) + " expecting set " + var8.expecting;
      } else if(var1 instanceof MismatchedRangeException) {
         MismatchedRangeException var9 = (MismatchedRangeException)var1;
         var3 = "mismatched character " + this.getCharErrorDisplay(var1.c) + " expecting set " + this.getCharErrorDisplay(var9.a) + ".." + this.getCharErrorDisplay(var9.b);
      } else {
         var3 = super.getErrorMessage(var1, var2);
      }

      return var3;
   }

   public String getCharErrorDisplay(int var1) {
      String var2 = String.valueOf((char)var1);
      switch(var1) {
      case -1:
         var2 = "<EOF>";
         break;
      case 9:
         var2 = "\\t";
         break;
      case 10:
         var2 = "\\n";
         break;
      case 13:
         var2 = "\\r";
      }

      return "\'" + var2 + "\'";
   }

   public void recover(RecognitionException var1) {
      this.input.consume();
   }

   public void traceIn(String var1, int var2) {
      String var3 = (char)this.input.LT(1) + " line=" + this.getLine() + ":" + this.getCharPositionInLine();
      super.traceIn(var1, var2, var3);
   }

   public void traceOut(String var1, int var2) {
      String var3 = (char)this.input.LT(1) + " line=" + this.getLine() + ":" + this.getCharPositionInLine();
      super.traceOut(var1, var2, var3);
   }
}
