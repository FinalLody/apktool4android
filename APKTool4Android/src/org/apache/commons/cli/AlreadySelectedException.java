// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:48
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.ParseException;

public class AlreadySelectedException extends ParseException {

   private OptionGroup group;
   private Option option;


   public AlreadySelectedException(String var1) {
      super(var1);
   }

   public AlreadySelectedException(OptionGroup var1, Option var2) {
      this("The option \'" + var2.getKey() + "\' was specified but an option from this group " + "has already been selected: \'" + var1.getSelected() + "\'");
      this.group = var1;
      this.option = var2;
   }

   public OptionGroup getOptionGroup() {
      return this.group;
   }

   public Option getOption() {
      return this.option;
   }
}
