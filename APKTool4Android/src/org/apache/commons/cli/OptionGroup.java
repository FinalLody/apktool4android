// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:48
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.cli;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.cli.AlreadySelectedException;
import org.apache.commons.cli.Option;

public class OptionGroup implements Serializable {

   private static final long serialVersionUID = 1L;
   private Map optionMap = new HashMap();
   private String selected;
   private boolean required;


   public OptionGroup addOption(Option var1) {
      this.optionMap.put(var1.getKey(), var1);
      return this;
   }

   public Collection getNames() {
      return this.optionMap.keySet();
   }

   public Collection getOptions() {
      return this.optionMap.values();
   }

   public void setSelected(Option var1) throws AlreadySelectedException {
      if(this.selected != null && !this.selected.equals(var1.getOpt())) {
         throw new AlreadySelectedException(this, var1);
      } else {
         this.selected = var1.getOpt();
      }
   }

   public String getSelected() {
      return this.selected;
   }

   public void setRequired(boolean var1) {
      this.required = var1;
   }

   public boolean isRequired() {
      return this.required;
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer();
      Iterator var2 = this.getOptions().iterator();
      var1.append("[");

      while(var2.hasNext()) {
         Option var3 = (Option)var2.next();
         if(var3.getOpt() != null) {
            var1.append("-");
            var1.append(var3.getOpt());
         } else {
            var1.append("--");
            var1.append(var3.getLongOpt());
         }

         var1.append(" ");
         var1.append(var3.getDescription());
         if(var2.hasNext()) {
            var1.append(", ");
         }
      }

      var1.append("]");
      return var1.toString();
   }
}
