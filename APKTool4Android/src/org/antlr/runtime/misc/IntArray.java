// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:55
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.misc;


public class IntArray {

   public static final int INITIAL_SIZE = 10;
   public int[] data;
   protected int p = -1;


   public void add(int var1) {
      this.ensureCapacity(this.p + 1);
      this.data[this.p += 1] = var1;
   }

   public void push(int var1) {
      this.add(var1);
   }

   public int pop() {
      int var1 = this.data[this.p];
      --this.p;
      return var1;
   }

   public int size() {
      return this.p;
   }

   public void clear() {
      this.p = -1;
   }

   public void ensureCapacity(int var1) {
      if(this.data == null) {
         this.data = new int[10];
      } else if(var1 + 1 >= this.data.length) {
         int var2 = this.data.length * 2;
         if(var1 > var2) {
            var2 = var1 + 1;
         }

         int[] var3 = new int[var2];
         System.arraycopy(this.data, 0, var3, 0, this.data.length);
         this.data = var3;
      }

   }
}
