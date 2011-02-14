// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:51
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.antlr.runtime.ANTLRReaderStream;

public class ANTLRInputStream extends ANTLRReaderStream {

   public ANTLRInputStream() {
   }

   public ANTLRInputStream(InputStream var1) throws IOException {
      this(var1, (String)null);
   }

   public ANTLRInputStream(InputStream var1, int var2) throws IOException {
      this(var1, var2, (String)null);
   }

   public ANTLRInputStream(InputStream var1, String var2) throws IOException {
      this(var1, 1024, var2);
   }

   public ANTLRInputStream(InputStream var1, int var2, String var3) throws IOException {
      this(var1, var2, 1024, var3);
   }

   public ANTLRInputStream(InputStream var1, int var2, int var3, String var4) throws IOException {
      InputStreamReader var5;
      if(var4 != null) {
         var5 = new InputStreamReader(var1, var4);
      } else {
         var5 = new InputStreamReader(var1);
      }

      this.load(var5, var2, var3);
   }
}
