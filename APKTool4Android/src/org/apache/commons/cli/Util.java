// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:41
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.cli;


class Util {

   static String stripLeadingHyphens(String var0) {
      return var0 == null?null:(var0.startsWith("--")?var0.substring(2, var0.length()):(var0.startsWith("-")?var0.substring(1, var0.length()):var0));
   }

   static String stripLeadingAndTrailingQuotes(String var0) {
      if(var0.startsWith("\"")) {
         var0 = var0.substring(1, var0.length());
      }

      if(var0.endsWith("\"")) {
         var0 = var0.substring(0, var0.length() - 1);
      }

      return var0;
   }
}
