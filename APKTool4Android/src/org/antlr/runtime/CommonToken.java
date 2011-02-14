// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:15
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import java.io.Serializable;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.Token;

public class CommonToken implements Token, Serializable {

   protected int type;
   protected int line;
   protected int charPositionInLine = -1;
   protected int channel = 0;
   protected transient CharStream input;
   protected String text;
   protected int index = -1;
   protected int start;
   protected int stop;


   public CommonToken(int var1) {
      this.type = var1;
   }

   public CommonToken(CharStream var1, int var2, int var3, int var4, int var5) {
      this.input = var1;
      this.type = var2;
      this.channel = var3;
      this.start = var4;
      this.stop = var5;
   }

   public CommonToken(int var1, String var2) {
      this.type = var1;
      this.channel = 0;
      this.text = var2;
   }

   public CommonToken(Token var1) {
      this.text = var1.getText();
      this.type = var1.getType();
      this.line = var1.getLine();
      this.index = var1.getTokenIndex();
      this.charPositionInLine = var1.getCharPositionInLine();
      this.channel = var1.getChannel();
      if(var1 instanceof CommonToken) {
         this.start = ((CommonToken)var1).start;
         this.stop = ((CommonToken)var1).stop;
      }

   }

   public int getType() {
      return this.type;
   }

   public void setLine(int var1) {
      this.line = var1;
   }

   public String getText() {
      if(this.text != null) {
         return this.text;
      } else if(this.input == null) {
         return null;
      } else {
         this.text = this.input.substring(this.start, this.stop);
         return this.text;
      }
   }

   public void setText(String var1) {
      this.text = var1;
   }

   public int getLine() {
      return this.line;
   }

   public int getCharPositionInLine() {
      return this.charPositionInLine;
   }

   public void setCharPositionInLine(int var1) {
      this.charPositionInLine = var1;
   }

   public int getChannel() {
      return this.channel;
   }

   public void setChannel(int var1) {
      this.channel = var1;
   }

   public void setType(int var1) {
      this.type = var1;
   }

   public int getStartIndex() {
      return this.start;
   }

   public void setStartIndex(int var1) {
      this.start = var1;
   }

   public int getStopIndex() {
      return this.stop;
   }

   public void setStopIndex(int var1) {
      this.stop = var1;
   }

   public int getTokenIndex() {
      return this.index;
   }

   public void setTokenIndex(int var1) {
      this.index = var1;
   }

   public CharStream getInputStream() {
      return this.input;
   }

   public void setInputStream(CharStream var1) {
      this.input = var1;
   }

   public String toString() {
      String var1 = "";
      if(this.channel > 0) {
         var1 = ",channel=" + this.channel;
      }

      String var2 = this.getText();
      if(var2 != null) {
         var2 = var2.replaceAll("\n", "\\\\n");
         var2 = var2.replaceAll("\r", "\\\\r");
         var2 = var2.replaceAll("\t", "\\\\t");
      } else {
         var2 = "<no text>";
      }

      return "[@" + this.getTokenIndex() + "," + this.start + ":" + this.stop + "=\'" + var2 + "\',<" + this.type + ">" + var1 + "," + this.line + ":" + this.getCharPositionInLine() + "]";
   }
}
