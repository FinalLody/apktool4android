// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:46
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.cli;

import org.apache.commons.cli.ParseException;

public class UnrecognizedOptionException extends ParseException {

   private String option;


   public UnrecognizedOptionException(String var1) {
      super(var1);
   }

   public UnrecognizedOptionException(String var1, String var2) {
      this(var1);
      this.option = var2;
   }

   public String getOption() {
      return this.option;
   }
}
