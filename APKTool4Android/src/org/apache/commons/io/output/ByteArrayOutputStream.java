// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:40
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.io.output;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ByteArrayOutputStream extends OutputStream {

   private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
   private List buffers;
   private int currentBufferIndex;
   private int filledBufferSum;
   private byte[] currentBuffer;
   private int count;


   public ByteArrayOutputStream() {
      this(1024);
   }

   public ByteArrayOutputStream(int var1) {
      this.buffers = new ArrayList();
      if(var1 < 0) {
         throw new IllegalArgumentException("Negative initial size: " + var1);
      } else {
         this.needNewBuffer(var1);
      }
   }

   private byte[] getBuffer(int var1) {
      return (byte[])((byte[])this.buffers.get(var1));
   }

   private void needNewBuffer(int var1) {
      if(this.currentBufferIndex < this.buffers.size() - 1) {
         this.filledBufferSum += this.currentBuffer.length;
         ++this.currentBufferIndex;
         this.currentBuffer = this.getBuffer(this.currentBufferIndex);
      } else {
         int var2;
         if(this.currentBuffer == null) {
            var2 = var1;
            this.filledBufferSum = 0;
         } else {
            var2 = Math.max(this.currentBuffer.length << 1, var1 - this.filledBufferSum);
            this.filledBufferSum += this.currentBuffer.length;
         }

         ++this.currentBufferIndex;
         this.currentBuffer = new byte[var2];
         this.buffers.add(this.currentBuffer);
      }

   }

   public void write(byte[] var1, int var2, int var3) {
      if(var2 >= 0 && var2 <= var1.length && var3 >= 0 && var2 + var3 <= var1.length && var2 + var3 >= 0) {
         if(var3 != 0) {
            synchronized(this) {
               int var5 = this.count + var3;
               int var6 = var3;
               int var7 = this.count - this.filledBufferSum;

               while(var6 > 0) {
                  int var8 = Math.min(var6, this.currentBuffer.length - var7);
                  System.arraycopy(var1, var2 + var3 - var6, this.currentBuffer, var7, var8);
                  var6 -= var8;
                  if(var6 > 0) {
                     this.needNewBuffer(var5);
                     var7 = 0;
                  }
               }

               this.count = var5;
            }
         }
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public synchronized void write(int var1) {
      int var2 = this.count - this.filledBufferSum;
      if(var2 == this.currentBuffer.length) {
         this.needNewBuffer(this.count + 1);
         var2 = 0;
      }

      this.currentBuffer[var2] = (byte)var1;
      ++this.count;
   }

   public synchronized int write(InputStream var1) throws IOException {
      int var2 = 0;
      int var3 = this.count - this.filledBufferSum;

      for(int var4 = var1.read(this.currentBuffer, var3, this.currentBuffer.length - var3); var4 != -1; var4 = var1.read(this.currentBuffer, var3, this.currentBuffer.length - var3)) {
         var2 += var4;
         var3 += var4;
         this.count += var4;
         if(var3 == this.currentBuffer.length) {
            this.needNewBuffer(this.currentBuffer.length);
            var3 = 0;
         }
      }

      return var2;
   }

   public synchronized int size() {
      return this.count;
   }

   public void close() throws IOException {
   }

   public synchronized void reset() {
      this.count = 0;
      this.filledBufferSum = 0;
      this.currentBufferIndex = 0;
      this.currentBuffer = this.getBuffer(this.currentBufferIndex);
   }

   public synchronized void writeTo(OutputStream var1) throws IOException {
      int var2 = this.count;

      for(int var3 = 0; var3 < this.buffers.size(); ++var3) {
         byte[] var4 = this.getBuffer(var3);
         int var5 = Math.min(var4.length, var2);
         var1.write(var4, 0, var5);
         var2 -= var5;
         if(var2 == 0) {
            break;
         }
      }

   }

   public synchronized byte[] toByteArray() {
      int var1 = this.count;
      if(var1 == 0) {
         return EMPTY_BYTE_ARRAY;
      } else {
         byte[] var2 = new byte[var1];
         int var3 = 0;

         for(int var4 = 0; var4 < this.buffers.size(); ++var4) {
            byte[] var5 = this.getBuffer(var4);
            int var6 = Math.min(var5.length, var1);
            System.arraycopy(var5, 0, var2, var3, var6);
            var3 += var6;
            var1 -= var6;
            if(var1 == 0) {
               break;
            }
         }

         return var2;
      }
   }

   public String toString() {
      return new String(this.toByteArray());
   }

   public String toString(String var1) throws UnsupportedEncodingException {
      return new String(this.toByteArray(), var1);
   }

}
