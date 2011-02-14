// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:41
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.cli;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.cli.ParseException;

public class MissingOptionException extends ParseException {

   private List missingOptions;


   public MissingOptionException(String var1) {
      super(var1);
   }

   public MissingOptionException(List var1) {
      this(createMessage(var1));
      this.missingOptions = var1;
   }

   public List getMissingOptions() {
      return this.missingOptions;
   }

   private static String createMessage(List var0) {
      StringBuffer var1 = new StringBuffer("Missing required option");
      var1.append(var0.size() == 1?"":"s");
      var1.append(": ");
      Iterator var2 = var0.iterator();

      while(var2.hasNext()) {
         var1.append(var2.next());
         if(var2.hasNext()) {
            var1.append(", ");
         }
      }

      return var1.toString();
   }
}
