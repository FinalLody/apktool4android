// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:57
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import java.util.List;

public class BitSet implements Cloneable {

   protected static final int BITS = 64;
   protected static final int LOG_BITS = 6;
   protected static final int MOD_MASK = 63;
   protected long[] bits;


   public BitSet() {
      this(64);
   }

   public BitSet(long[] var1) {
      this.bits = var1;
   }

   public BitSet(List var1) {
      this();

      for(int var2 = 0; var2 < var1.size(); ++var2) {
         Integer var3 = (Integer)var1.get(var2);
         this.add(var3.intValue());
      }

   }

   public BitSet(int var1) {
      this.bits = new long[(var1 - 1 >> 6) + 1];
   }

   public static BitSet of(int var0) {
      BitSet var1 = new BitSet(var0 + 1);
      var1.add(var0);
      return var1;
   }

   public static BitSet of(int var0, int var1) {
      BitSet var2 = new BitSet(Math.max(var0, var1) + 1);
      var2.add(var0);
      var2.add(var1);
      return var2;
   }

   public static BitSet of(int var0, int var1, int var2) {
      BitSet var3 = new BitSet();
      var3.add(var0);
      var3.add(var1);
      var3.add(var2);
      return var3;
   }

   public static BitSet of(int var0, int var1, int var2, int var3) {
      BitSet var4 = new BitSet();
      var4.add(var0);
      var4.add(var1);
      var4.add(var2);
      var4.add(var3);
      return var4;
   }

   public BitSet or(BitSet var1) {
      if(var1 == null) {
         return this;
      } else {
         BitSet var2 = (BitSet)this.clone();
         var2.orInPlace(var1);
         return var2;
      }
   }

   public void add(int var1) {
      int var2 = wordNumber(var1);
      if(var2 >= this.bits.length) {
         this.growToInclude(var1);
      }

      this.bits[var2] |= bitMask(var1);
   }

   public void growToInclude(int var1) {
      int var2 = Math.max(this.bits.length << 1, this.numWordsToHold(var1));
      long[] var3 = new long[var2];
      System.arraycopy(this.bits, 0, var3, 0, this.bits.length);
      this.bits = var3;
   }

   public void orInPlace(BitSet var1) {
      if(var1 != null) {
         if(var1.bits.length > this.bits.length) {
            this.setSize(var1.bits.length);
         }

         int var2 = Math.min(this.bits.length, var1.bits.length);

         for(int var3 = var2 - 1; var3 >= 0; --var3) {
            this.bits[var3] |= var1.bits[var3];
         }

      }
   }

   private void setSize(int var1) {
      long[] var2 = new long[var1];
      int var3 = Math.min(var1, this.bits.length);
      System.arraycopy(this.bits, 0, var2, 0, var3);
      this.bits = var2;
   }

   private static final long bitMask(int var0) {
      int var1 = var0 & 63;
      return 1L << var1;
   }

   public Object clone() {
      try {
         BitSet var1 = (BitSet)super.clone();
         var1.bits = new long[this.bits.length];
         System.arraycopy(this.bits, 0, var1.bits, 0, this.bits.length);
         return var1;
      } catch (CloneNotSupportedException var3) {
         throw new InternalError();
      }
   }

   public int size() {
      int var1 = 0;

      for(int var2 = this.bits.length - 1; var2 >= 0; --var2) {
         long var3 = this.bits[var2];
         if(var3 != 0L) {
            for(int var5 = 63; var5 >= 0; --var5) {
               if((var3 & 1L << var5) != 0L) {
                  ++var1;
               }
            }
         }
      }

      return var1;
   }

   public boolean equals(Object var1) {
      if(var1 != null && var1 instanceof BitSet) {
         BitSet var2 = (BitSet)var1;
         int var3 = Math.min(this.bits.length, var2.bits.length);

         int var4;
         for(var4 = 0; var4 < var3; ++var4) {
            if(this.bits[var4] != var2.bits[var4]) {
               return false;
            }
         }

         if(this.bits.length > var3) {
            for(var4 = var3 + 1; var4 < this.bits.length; ++var4) {
               if(this.bits[var4] != 0L) {
                  return false;
               }
            }
         } else if(var2.bits.length > var3) {
            for(var4 = var3 + 1; var4 < var2.bits.length; ++var4) {
               if(var2.bits[var4] != 0L) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean member(int var1) {
      if(var1 < 0) {
         return false;
      } else {
         int var2 = wordNumber(var1);
         return var2 >= this.bits.length?false:(this.bits[var2] & bitMask(var1)) != 0L;
      }
   }

   public void remove(int var1) {
      int var2 = wordNumber(var1);
      if(var2 < this.bits.length) {
         this.bits[var2] &= ~bitMask(var1);
      }

   }

   public boolean isNil() {
      for(int var1 = this.bits.length - 1; var1 >= 0; --var1) {
         if(this.bits[var1] != 0L) {
            return false;
         }
      }

      return true;
   }

   private final int numWordsToHold(int var1) {
      return (var1 >> 6) + 1;
   }

   public int numBits() {
      return this.bits.length << 6;
   }

   public int lengthInLongWords() {
      return this.bits.length;
   }

   public int[] toArray() {
      int[] var1 = new int[this.size()];
      int var2 = 0;

      for(int var3 = 0; var3 < this.bits.length << 6; ++var3) {
         if(this.member(var3)) {
            var1[var2++] = var3;
         }
      }

      return var1;
   }

   public long[] toPackedArray() {
      return this.bits;
   }

   private static final int wordNumber(int var0) {
      return var0 >> 6;
   }

   public String toString() {
      return this.toString((String[])null);
   }

   public String toString(String[] var1) {
      StringBuffer var2 = new StringBuffer();
      String var3 = ",";
      boolean var4 = false;
      var2.append('{');

      for(int var5 = 0; var5 < this.bits.length << 6; ++var5) {
         if(this.member(var5)) {
            if(var5 > 0 && var4) {
               var2.append(var3);
            }

            if(var1 != null) {
               var2.append(var1[var5]);
            } else {
               var2.append(var5);
            }

            var4 = true;
         }
      }

      var2.append('}');
      return var2.toString();
   }
}
