// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:48
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

public class MissingArgumentException extends ParseException {

   private Option option;


   public MissingArgumentException(String var1) {
      super(var1);
   }

   public MissingArgumentException(Option var1) {
      this("Missing argument for option: " + var1.getKey());
      this.option = var1;
   }

   public Option getOption() {
      return this.option;
   }
}
