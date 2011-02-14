// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:55
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FastQueue {

   protected List data = new ArrayList();
   protected int p = 0;


   public void reset() {
      this.p = 0;
      this.data.clear();
   }

   public Object remove() {
      Object var1 = this.get(0);
      ++this.p;
      if(this.p == this.data.size()) {
         this.clear();
      }

      return var1;
   }

   public void add(Object var1) {
      this.data.add(var1);
   }

   public int size() {
      return this.data.size() - this.p;
   }

   public Object head() {
      return this.get(0);
   }

   public Object get(int var1) {
      if(this.p + var1 >= this.data.size()) {
         throw new NoSuchElementException("queue index " + (this.p + var1) + " > size " + this.data.size());
      } else {
         return this.data.get(this.p + var1);
      }
   }

   public void clear() {
      this.p = 0;
      this.data.clear();
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer();
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         var1.append(this.get(var3));
         if(var3 + 1 < var2) {
            var1.append(" ");
         }
      }

      return var1.toString();
   }
}
