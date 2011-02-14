// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:41
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.io.input;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class ProxyInputStream extends FilterInputStream {

   public ProxyInputStream(InputStream var1) {
      super(var1);
   }

   public int read() throws IOException {
      return this.in.read();
   }

   public int read(byte[] var1) throws IOException {
      return this.in.read(var1);
   }

   public int read(byte[] var1, int var2, int var3) throws IOException {
      return this.in.read(var1, var2, var3);
   }

   public long skip(long var1) throws IOException {
      return this.in.skip(var1);
   }

   public int available() throws IOException {
      return this.in.available();
   }

   public void close() throws IOException {
      this.in.close();
   }

   public synchronized void mark(int var1) {
      this.in.mark(var1);
   }

   public synchronized void reset() throws IOException {
      this.in.reset();
   }

   public boolean markSupported() {
      return this.in.markSupported();
   }
}
