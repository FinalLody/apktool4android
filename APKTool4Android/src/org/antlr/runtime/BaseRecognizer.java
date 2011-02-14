// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:12
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.FailedPredicateException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedNotSetException;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.runtime.MismatchedTreeNodeException;
import org.antlr.runtime.MissingTokenException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.UnwantedTokenException;

public abstract class BaseRecognizer {

   public static final int MEMO_RULE_FAILED = -2;
   public static final int MEMO_RULE_UNKNOWN = -1;
   public static final int INITIAL_FOLLOW_STACK_SIZE = 100;
   public static final int DEFAULT_TOKEN_CHANNEL = 0;
   public static final int HIDDEN = 99;
   public static final String NEXT_TOKEN_RULE_NAME = "nextToken";
   protected RecognizerSharedState state;


   public BaseRecognizer() {
      this.state = new RecognizerSharedState();
   }

   public BaseRecognizer(RecognizerSharedState var1) {
      if(var1 == null) {
         var1 = new RecognizerSharedState();
      }

      this.state = var1;
   }

   public void reset() {
      if(this.state != null) {
         this.state._fsp = -1;
         this.state.errorRecovery = false;
         this.state.lastErrorIndex = -1;
         this.state.failed = false;
         this.state.syntaxErrors = 0;
         this.state.backtracking = 0;

         for(int var1 = 0; this.state.ruleMemo != null && var1 < this.state.ruleMemo.length; ++var1) {
            this.state.ruleMemo[var1] = null;
         }

      }
   }

   public Object match(IntStream var1, int var2, BitSet var3) throws RecognitionException {
      Object var4 = this.getCurrentInputSymbol(var1);
      if(var1.LA(1) == var2) {
         var1.consume();
         this.state.errorRecovery = false;
         this.state.failed = false;
         return var4;
      } else if(this.state.backtracking > 0) {
         this.state.failed = true;
         return var4;
      } else {
         var4 = this.recoverFromMismatchedToken(var1, var2, var3);
         return var4;
      }
   }

   public void matchAny(IntStream var1) {
      this.state.errorRecovery = false;
      this.state.failed = false;
      var1.consume();
   }

   public boolean mismatchIsUnwantedToken(IntStream var1, int var2) {
      return var1.LA(2) == var2;
   }

   public boolean mismatchIsMissingToken(IntStream var1, BitSet var2) {
      if(var2 == null) {
         return false;
      } else {
         if(var2.member(1)) {
            BitSet var3 = this.computeContextSensitiveRuleFOLLOW();
            var2 = var2.or(var3);
            if(this.state._fsp >= 0) {
               var2.remove(1);
            }
         }

         return var2.member(var1.LA(1)) || var2.member(1);
      }
   }

   public void reportError(RecognitionException var1) {
      if(!this.state.errorRecovery) {
         ++this.state.syntaxErrors;
         this.state.errorRecovery = true;
         this.displayRecognitionError(this.getTokenNames(), var1);
      }
   }

   public void displayRecognitionError(String[] var1, RecognitionException var2) {
      String var3 = this.getErrorHeader(var2);
      String var4 = this.getErrorMessage(var2, var1);
      this.emitErrorMessage(var3 + " " + var4);
   }

   public String getErrorMessage(RecognitionException var1, String[] var2) {
      String var3 = var1.getMessage();
      String var5;
      if(var1 instanceof UnwantedTokenException) {
         UnwantedTokenException var4 = (UnwantedTokenException)var1;
         var5 = "<unknown>";
         if(var4.expecting == -1) {
            var5 = "EOF";
         } else {
            var5 = var2[var4.expecting];
         }

         var3 = "extraneous input " + this.getTokenErrorDisplay(var4.getUnexpectedToken()) + " expecting " + var5;
      } else if(var1 instanceof MissingTokenException) {
         MissingTokenException var10 = (MissingTokenException)var1;
         var5 = "<unknown>";
         if(var10.expecting == -1) {
            var5 = "EOF";
         } else {
            var5 = var2[var10.expecting];
         }

         var3 = "missing " + var5 + " at " + this.getTokenErrorDisplay(var1.token);
      } else if(var1 instanceof MismatchedTokenException) {
         MismatchedTokenException var6 = (MismatchedTokenException)var1;
         var5 = "<unknown>";
         if(var6.expecting == -1) {
            var5 = "EOF";
         } else {
            var5 = var2[var6.expecting];
         }

         var3 = "mismatched input " + this.getTokenErrorDisplay(var1.token) + " expecting " + var5;
      } else if(var1 instanceof MismatchedTreeNodeException) {
         MismatchedTreeNodeException var7 = (MismatchedTreeNodeException)var1;
         var5 = "<unknown>";
         if(var7.expecting == -1) {
            var5 = "EOF";
         } else {
            var5 = var2[var7.expecting];
         }

         var3 = "mismatched tree node: " + var7.node + " expecting " + var5;
      } else if(var1 instanceof NoViableAltException) {
         var3 = "no viable alternative at input " + this.getTokenErrorDisplay(var1.token);
      } else if(var1 instanceof EarlyExitException) {
         var3 = "required (...)+ loop did not match anything at input " + this.getTokenErrorDisplay(var1.token);
      } else if(var1 instanceof MismatchedSetException) {
         MismatchedSetException var8 = (MismatchedSetException)var1;
         var3 = "mismatched input " + this.getTokenErrorDisplay(var1.token) + " expecting set " + var8.expecting;
      } else if(var1 instanceof MismatchedNotSetException) {
         MismatchedNotSetException var9 = (MismatchedNotSetException)var1;
         var3 = "mismatched input " + this.getTokenErrorDisplay(var1.token) + " expecting set " + var9.expecting;
      } else if(var1 instanceof FailedPredicateException) {
         FailedPredicateException var11 = (FailedPredicateException)var1;
         var3 = "rule " + var11.ruleName + " failed predicate: {" + var11.predicateText + "}?";
      }

      return var3;
   }

   public int getNumberOfSyntaxErrors() {
      return this.state.syntaxErrors;
   }

   public String getErrorHeader(RecognitionException var1) {
      return "line " + var1.line + ":" + var1.charPositionInLine;
   }

   public String getTokenErrorDisplay(Token var1) {
      String var2 = var1.getText();
      if(var2 == null) {
         if(var1.getType() == -1) {
            var2 = "<EOF>";
         } else {
            var2 = "<" + var1.getType() + ">";
         }
      }

      var2 = var2.replaceAll("\n", "\\\\n");
      var2 = var2.replaceAll("\r", "\\\\r");
      var2 = var2.replaceAll("\t", "\\\\t");
      return "\'" + var2 + "\'";
   }

   public void emitErrorMessage(String var1) {
      System.err.println(var1);
   }

   public void recover(IntStream var1, RecognitionException var2) {
      if(this.state.lastErrorIndex == var1.index()) {
         var1.consume();
      }

      this.state.lastErrorIndex = var1.index();
      BitSet var3 = this.computeErrorRecoverySet();
      this.beginResync();
      this.consumeUntil(var1, var3);
      this.endResync();
   }

   public void beginResync() {
   }

   public void endResync() {
   }

   protected BitSet computeErrorRecoverySet() {
      return this.combineFollows(false);
   }

   protected BitSet computeContextSensitiveRuleFOLLOW() {
      return this.combineFollows(true);
   }

   protected BitSet combineFollows(boolean var1) {
      int var2 = this.state._fsp;
      BitSet var3 = new BitSet();

      for(int var4 = var2; var4 >= 0; --var4) {
         BitSet var5 = this.state.following[var4];
         var3.orInPlace(var5);
         if(var1) {
            if(!var5.member(1)) {
               break;
            }

            if(var4 > 0) {
               var3.remove(1);
            }
         }
      }

      return var3;
   }

   protected Object recoverFromMismatchedToken(IntStream var1, int var2, BitSet var3) throws RecognitionException {
      MismatchedTokenException var4 = null;
      Object var5;
      if(this.mismatchIsUnwantedToken(var1, var2)) {
         UnwantedTokenException var7 = new UnwantedTokenException(var2, var1);
         this.beginResync();
         var1.consume();
         this.endResync();
         this.reportError(var7);
         var5 = this.getCurrentInputSymbol(var1);
         var1.consume();
         return var5;
      } else if(this.mismatchIsMissingToken(var1, var3)) {
         var5 = this.getMissingSymbol(var1, var4, var2, var3);
         MissingTokenException var6 = new MissingTokenException(var2, var1, var5);
         this.reportError(var6);
         return var5;
      } else {
         var4 = new MismatchedTokenException(var2, var1);
         throw var4;
      }
   }

   public Object recoverFromMismatchedSet(IntStream var1, RecognitionException var2, BitSet var3) throws RecognitionException {
      if(this.mismatchIsMissingToken(var1, var3)) {
         this.reportError(var2);
         return this.getMissingSymbol(var1, var2, 0, var3);
      } else {
         throw var2;
      }
   }

   protected Object getCurrentInputSymbol(IntStream var1) {
      return null;
   }

   protected Object getMissingSymbol(IntStream var1, RecognitionException var2, int var3, BitSet var4) {
      return null;
   }

   public void consumeUntil(IntStream var1, int var2) {
      for(int var3 = var1.LA(1); var3 != -1 && var3 != var2; var3 = var1.LA(1)) {
         var1.consume();
      }

   }

   public void consumeUntil(IntStream var1, BitSet var2) {
      for(int var3 = var1.LA(1); var3 != -1 && !var2.member(var3); var3 = var1.LA(1)) {
         var1.consume();
      }

   }

   protected void pushFollow(BitSet var1) {
      if(this.state._fsp + 1 >= this.state.following.length) {
         BitSet[] var2 = new BitSet[this.state.following.length * 2];
         System.arraycopy(this.state.following, 0, var2, 0, this.state.following.length);
         this.state.following = var2;
      }

      this.state.following[this.state._fsp += 1] = var1;
   }

   public List getRuleInvocationStack() {
      String var1 = this.getClass().getName();
      return getRuleInvocationStack(new Throwable(), var1);
   }

   public static List getRuleInvocationStack(Throwable var0, String var1) {
      ArrayList var2 = new ArrayList();
      StackTraceElement[] var3 = var0.getStackTrace();
      boolean var4 = false;

      for(int var6 = var3.length - 1; var6 >= 0; --var6) {
         StackTraceElement var5 = var3[var6];
         if(!var5.getClassName().startsWith("org.antlr.runtime.") && !var5.getMethodName().equals("nextToken") && var5.getClassName().equals(var1)) {
            var2.add(var5.getMethodName());
         }
      }

      return var2;
   }

   public int getBacktrackingLevel() {
      return this.state.backtracking;
   }

   public void setBacktrackingLevel(int var1) {
      this.state.backtracking = var1;
   }

   public boolean failed() {
      return this.state.failed;
   }

   public String[] getTokenNames() {
      return null;
   }

   public String getGrammarFileName() {
      return null;
   }

   public abstract String getSourceName();

   public List toStrings(List var1) {
      if(var1 == null) {
         return null;
      } else {
         ArrayList var2 = new ArrayList(var1.size());

         for(int var3 = 0; var3 < var1.size(); ++var3) {
            var2.add(((Token)var1.get(var3)).getText());
         }

         return var2;
      }
   }

   public int getRuleMemoization(int var1, int var2) {
      if(this.state.ruleMemo[var1] == null) {
         this.state.ruleMemo[var1] = new HashMap();
      }

      Integer var3 = (Integer)this.state.ruleMemo[var1].get(new Integer(var2));
      return var3 == null?-1:var3.intValue();
   }

   public boolean alreadyParsedRule(IntStream var1, int var2) {
      int var3 = this.getRuleMemoization(var2, var1.index());
      if(var3 == -1) {
         return false;
      } else {
         if(var3 == -2) {
            this.state.failed = true;
         } else {
            var1.seek(var3 + 1);
         }

         return true;
      }
   }

   public void memoize(IntStream var1, int var2, int var3) {
      int var4 = this.state.failed?-2:var1.index() - 1;
      if(this.state.ruleMemo == null) {
         System.err.println("!!!!!!!!! memo array is null for " + this.getGrammarFileName());
      }

      if(var2 >= this.state.ruleMemo.length) {
         System.err.println("!!!!!!!!! memo size is " + this.state.ruleMemo.length + ", but rule index is " + var2);
      }

      if(this.state.ruleMemo[var2] != null) {
         this.state.ruleMemo[var2].put(new Integer(var3), new Integer(var4));
      }

   }

   public int getRuleMemoizationCacheSize() {
      int var1 = 0;

      for(int var2 = 0; this.state.ruleMemo != null && var2 < this.state.ruleMemo.length; ++var2) {
         Map var3 = this.state.ruleMemo[var2];
         if(var3 != null) {
            var1 += var3.size();
         }
      }

      return var1;
   }

   public void traceIn(String var1, int var2, Object var3) {
      System.out.print("enter " + var1 + " " + var3);
      if(this.state.backtracking > 0) {
         System.out.print(" backtracking=" + this.state.backtracking);
      }

      System.out.println();
   }

   public void traceOut(String var1, int var2, Object var3) {
      System.out.print("exit " + var1 + " " + var3);
      if(this.state.backtracking > 0) {
         System.out.print(" backtracking=" + this.state.backtracking);
         if(this.state.failed) {
            System.out.print(" failed");
         } else {
            System.out.print(" succeeded");
         }
      }

      System.out.println();
   }
}
