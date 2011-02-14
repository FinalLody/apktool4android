// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:40
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.input.ProxyInputStream;

public class CountingInputStream extends ProxyInputStream {

   private long count;


   public CountingInputStream(InputStream var1) {
      super(var1);
   }

   public int read(byte[] var1) throws IOException {
      int var2 = super.read(var1);
      this.count += var2 >= 0?(long)var2:0L;
      return var2;
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      int var4 = super.read(var1, var2, var3);
      this.count += var4 >= 0?(long)var4:0L;
      return var4;
   }

   public int read() throws IOException {
      int var1 = super.read();
      this.count += var1 >= 0?1L:0L;
      return var1;
   }

   public long skip(long var1) throws IOException {
      long var3 = super.skip(var1);
      this.count += var3;
      return var3;
   }

   public synchronized int getCount() {
      long var1 = this.getByteCount();
      if(var1 > 2147483647L) {
         throw new ArithmeticException("The byte count " + var1 + " is too large to be converted to an int");
      } else {
         return (int)var1;
      }
   }

   public synchronized int resetCount() {
      long var1 = this.resetByteCount();
      if(var1 > 2147483647L) {
         throw new ArithmeticException("The byte count " + var1 + " is too large to be converted to an int");
      } else {
         return (int)var1;
      }
   }

   public synchronized long getByteCount() {
      return this.count;
   }

   public synchronized long resetByteCount() {
      long var1 = this.count;
      this.count = 0L;
      return var1;
   }
}
