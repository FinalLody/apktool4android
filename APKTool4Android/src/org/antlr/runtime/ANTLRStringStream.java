// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:12
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CharStreamState;

public class ANTLRStringStream implements CharStream {

   protected char[] data;
   protected int n;
   protected int p;
   protected int line;
   protected int charPositionInLine;
   protected int markDepth;
   protected List markers;
   protected int lastMarker;
   public String name;


   public ANTLRStringStream() {
      this.p = 0;
      this.line = 1;
      this.charPositionInLine = 0;
      this.markDepth = 0;
   }

   public ANTLRStringStream(String var1) {
      this();
      this.data = var1.toCharArray();
      this.n = var1.length();
   }

   public ANTLRStringStream(char[] var1, int var2) {
      this();
      this.data = var1;
      this.n = var2;
   }

   public void reset() {
      this.p = 0;
      this.line = 1;
      this.charPositionInLine = 0;
      this.markDepth = 0;
   }

   public void consume() {
      if(this.p < this.n) {
         ++this.charPositionInLine;
         if(this.data[this.p] == 10) {
            ++this.line;
            this.charPositionInLine = 0;
         }

         ++this.p;
      }

   }

   public int LA(int var1) {
      if(var1 == 0) {
         return 0;
      } else {
         if(var1 < 0) {
            ++var1;
            if(this.p + var1 - 1 < 0) {
               return -1;
            }
         }

         return this.p + var1 - 1 >= this.n?-1:this.data[this.p + var1 - 1];
      }
   }

   public int LT(int var1) {
      return this.LA(var1);
   }

   public int index() {
      return this.p;
   }

   public int size() {
      return this.n;
   }

   public int mark() {
      if(this.markers == null) {
         this.markers = new ArrayList();
         this.markers.add((Object)null);
      }

      ++this.markDepth;
      CharStreamState var1 = null;
      if(this.markDepth >= this.markers.size()) {
         var1 = new CharStreamState();
         this.markers.add(var1);
      } else {
         var1 = (CharStreamState)this.markers.get(this.markDepth);
      }

      var1.p = this.p;
      var1.line = this.line;
      var1.charPositionInLine = this.charPositionInLine;
      this.lastMarker = this.markDepth;
      return this.markDepth;
   }

   public void rewind(int var1) {
      CharStreamState var2 = (CharStreamState)this.markers.get(var1);
      this.seek(var2.p);
      this.line = var2.line;
      this.charPositionInLine = var2.charPositionInLine;
      this.release(var1);
   }

   public void rewind() {
      this.rewind(this.lastMarker);
   }

   public void release(int var1) {
      this.markDepth = var1;
      --this.markDepth;
   }

   public void seek(int var1) {
      if(var1 <= this.p) {
         this.p = var1;
      } else {
         while(this.p < var1) {
            this.consume();
         }

      }
   }

   public String substring(int var1, int var2) {
      return new String(this.data, var1, var2 - var1 + 1);
   }

   public int getLine() {
      return this.line;
   }

   public int getCharPositionInLine() {
      return this.charPositionInLine;
   }

   public void setLine(int var1) {
      this.line = var1;
   }

   public void setCharPositionInLine(int var1) {
      this.charPositionInLine = var1;
   }

   public String getSourceName() {
      return this.name;
   }
}
