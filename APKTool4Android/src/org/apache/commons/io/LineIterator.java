// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:41
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.io.IOUtils;

public class LineIterator implements Iterator {

   private final BufferedReader bufferedReader;
   private String cachedLine;
   private boolean finished = false;


   public LineIterator(Reader var1) throws IllegalArgumentException {
      if(var1 == null) {
         throw new IllegalArgumentException("Reader must not be null");
      } else {
         if(var1 instanceof BufferedReader) {
            this.bufferedReader = (BufferedReader)var1;
         } else {
            this.bufferedReader = new BufferedReader(var1);
         }

      }
   }

   public boolean hasNext() {
      if(this.cachedLine != null) {
         return true;
      } else if(this.finished) {
         return false;
      } else {
         try {
            String var1;
            do {
               var1 = this.bufferedReader.readLine();
               if(var1 == null) {
                  this.finished = true;
                  return false;
               }
            } while(!this.isValidLine(var1));

            this.cachedLine = var1;
            return true;
         } catch (IOException var2) {
            this.close();
            throw new IllegalStateException(var2.toString());
         }
      }
   }

   protected boolean isValidLine(String var1) {
      return true;
   }

   public Object next() {
      return this.nextLine();
   }

   public String nextLine() {
      if(!this.hasNext()) {
         throw new NoSuchElementException("No more lines");
      } else {
         String var1 = this.cachedLine;
         this.cachedLine = null;
         return var1;
      }
   }

   public void close() {
      this.finished = true;
      IOUtils.closeQuietly(this.bufferedReader);
      this.cachedLine = null;
   }

   public void remove() {
      throw new UnsupportedOperationException("Remove unsupported on LineIterator");
   }

   public static void closeQuietly(LineIterator var0) {
      if(var0 != null) {
         var0.close();
      }

   }
}
