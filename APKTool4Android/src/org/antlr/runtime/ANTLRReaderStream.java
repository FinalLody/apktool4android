// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:59
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import java.io.IOException;
import java.io.Reader;
import org.antlr.runtime.ANTLRStringStream;

public class ANTLRReaderStream extends ANTLRStringStream {

   public static final int READ_BUFFER_SIZE = 1024;
   public static final int INITIAL_BUFFER_SIZE = 1024;


   public ANTLRReaderStream() {
   }

   public ANTLRReaderStream(Reader var1) throws IOException {
      this(var1, 1024, 1024);
   }

   public ANTLRReaderStream(Reader var1, int var2) throws IOException {
      this(var1, var2, 1024);
   }

   public ANTLRReaderStream(Reader var1, int var2, int var3) throws IOException {
      this.load(var1, var2, var3);
   }

   public void load(Reader var1, int var2, int var3) throws IOException {
      if(var1 != null) {
         if(var2 <= 0) {
            var2 = 1024;
         }

         if(var3 <= 0) {
            var3 = 1024;
         }

         try {
            this.data = new char[var2];
            boolean var4 = false;
            int var5 = 0;

            int var10;
            do {
               if(var5 + var3 > this.data.length) {
                  char[] var6 = new char[this.data.length * 2];
                  System.arraycopy(this.data, 0, var6, 0, this.data.length);
                  this.data = var6;
               }

               var10 = var1.read(this.data, var5, var3);
               var5 += var10;
            } while(var10 != -1);

            super.n = var5 + 1;
         } finally {
            var1.close();
         }

      }
   }
}
