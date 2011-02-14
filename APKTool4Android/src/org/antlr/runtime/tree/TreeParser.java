// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:00
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.tree;

import java.util.regex.Pattern;
import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedTreeNodeException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.TreeAdaptor;
import org.antlr.runtime.tree.TreeNodeStream;

public class TreeParser extends BaseRecognizer {

   public static final int DOWN = 2;
   public static final int UP = 3;
   static String dotdot = ".*[^.]\\.\\.[^.].*";
   static String doubleEtc = ".*\\.\\.\\.\\s+\\.\\.\\..*";
   static Pattern dotdotPattern = Pattern.compile(dotdot);
   static Pattern doubleEtcPattern = Pattern.compile(doubleEtc);
   protected TreeNodeStream input;


   public TreeParser(TreeNodeStream var1) {
      this.setTreeNodeStream(var1);
   }

   public TreeParser(TreeNodeStream var1, RecognizerSharedState var2) {
      super(var2);
      this.setTreeNodeStream(var1);
   }

   public void reset() {
      super.reset();
      if(this.input != null) {
         this.input.seek(0);
      }

   }

   public void setTreeNodeStream(TreeNodeStream var1) {
      this.input = var1;
   }

   public TreeNodeStream getTreeNodeStream() {
      return this.input;
   }

   public String getSourceName() {
      return this.input.getSourceName();
   }

   protected Object getCurrentInputSymbol(IntStream var1) {
      return ((TreeNodeStream)var1).LT(1);
   }

   protected Object getMissingSymbol(IntStream var1, RecognitionException var2, int var3, BitSet var4) {
      String var5 = "<missing " + this.getTokenNames()[var3] + ">";
      return new CommonTree(new CommonToken(var3, var5));
   }

   public void matchAny(IntStream var1) {
      this.state.errorRecovery = false;
      this.state.failed = false;
      Object var2 = this.input.LT(1);
      if(this.input.getTreeAdaptor().getChildCount(var2) == 0) {
         this.input.consume();
      } else {
         int var3 = 0;
         int var4 = this.input.getTreeAdaptor().getType(var2);

         while(var4 != -1 && (var4 != 3 || var3 != 0)) {
            this.input.consume();
            var2 = this.input.LT(1);
            var4 = this.input.getTreeAdaptor().getType(var2);
            if(var4 == 2) {
               ++var3;
            } else if(var4 == 3) {
               --var3;
            }
         }

         this.input.consume();
      }
   }

   protected Object recoverFromMismatchedToken(IntStream var1, int var2, BitSet var3) throws RecognitionException {
      throw new MismatchedTreeNodeException(var2, (TreeNodeStream)var1);
   }

   public String getErrorHeader(RecognitionException var1) {
      return this.getGrammarFileName() + ": node from " + (var1.approximateLineInfo?"after ":"") + "line " + var1.line + ":" + var1.charPositionInLine;
   }

   public String getErrorMessage(RecognitionException var1, String[] var2) {
      if(this instanceof TreeParser) {
         TreeAdaptor var3 = ((TreeNodeStream)var1.input).getTreeAdaptor();
         var1.token = var3.getToken(var1.node);
         if(var1.token == null) {
            var1.token = new CommonToken(var3.getType(var1.node), var3.getText(var1.node));
         }
      }

      return super.getErrorMessage(var1, var2);
   }

   public void traceIn(String var1, int var2) {
      super.traceIn(var1, var2, this.input.LT(1));
   }

   public void traceOut(String var1, int var2) {
      super.traceOut(var1, var2, this.input.LT(1));
   }

}
