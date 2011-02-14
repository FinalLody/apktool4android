// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:55
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.misc;

import org.antlr.runtime.misc.FastQueue;

public abstract class LookaheadStream extends FastQueue {

   public static final int UNINITIALIZED_EOF_ELEMENT_INDEX = Integer.MAX_VALUE;
   protected int eofElementIndex = Integer.MAX_VALUE;
   public Object eof = null;
   protected int lastMarker;
   protected int markDepth = 0;


   public LookaheadStream(Object var1) {
      this.eof = var1;
   }

   public void reset() {
      this.eofElementIndex = Integer.MAX_VALUE;
      super.reset();
   }

   public abstract Object nextElement();

   public Object remove() {
      Object var1 = this.get(0);
      ++this.p;
      if(this.p == this.data.size() && this.markDepth == 0) {
         this.clear();
      }

      return var1;
   }

   public void consume() {
      this.sync(1);
      this.remove();
   }

   public void sync(int var1) {
      int var2 = this.p + var1 - 1 - this.data.size() + 1;
      if(var2 > 0) {
         this.fill(var2);
      }

   }

   public void fill(int var1) {
      for(int var2 = 1; var2 <= var1; ++var2) {
         Object var3 = this.nextElement();
         if(var3 == this.eof) {
            this.data.add(this.eof);
            this.eofElementIndex = this.data.size() - 1;
         } else {
            this.data.add(var3);
         }
      }

   }

   public int size() {
      throw new UnsupportedOperationException("streams are of unknown size");
   }

   public Object LT(int var1) {
      if(var1 == 0) {
         return null;
      } else if(var1 < 0) {
         return this.LB(-var1);
      } else if(this.p + var1 - 1 >= this.eofElementIndex) {
         return this.eof;
      } else {
         this.sync(var1);
         return this.get(var1 - 1);
      }
   }

   protected Object LB(int var1) {
      return var1 == 0?null:(this.p - var1 < 0?null:this.get(-var1));
   }

   public Object getCurrentSymbol() {
      return this.LT(1);
   }

   public int index() {
      return this.p;
   }

   public int mark() {
      ++this.markDepth;
      this.lastMarker = this.index();
      return this.lastMarker;
   }

   public void release(int var1) {
   }

   public void rewind(int var1) {
      --this.markDepth;
      this.seek(var1);
   }

   public void rewind() {
      this.seek(this.lastMarker);
   }

   public void seek(int var1) {
      this.p = var1;
   }
}
