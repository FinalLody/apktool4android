// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:51
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Util;

public class Options implements Serializable {

   private static final long serialVersionUID = 1L;
   private Map shortOpts = new HashMap();
   private Map longOpts = new HashMap();
   private List requiredOpts = new ArrayList();
   private Map optionGroups = new HashMap();


   public Options addOptionGroup(OptionGroup var1) {
      Iterator var2 = var1.getOptions().iterator();
      if(var1.isRequired()) {
         this.requiredOpts.add(var1);
      }

      while(var2.hasNext()) {
         Option var3 = (Option)var2.next();
         var3.setRequired(false);
         this.addOption(var3);
         this.optionGroups.put(var3.getKey(), var1);
      }

      return this;
   }

   Collection getOptionGroups() {
      return new HashSet(this.optionGroups.values());
   }

   public Options addOption(String var1, boolean var2, String var3) {
      this.addOption(var1, (String)null, var2, var3);
      return this;
   }

   public Options addOption(String var1, String var2, boolean var3, String var4) {
      this.addOption(new Option(var1, var2, var3, var4));
      return this;
   }

   public Options addOption(Option var1) {
      String var2 = var1.getKey();
      if(var1.hasLongOpt()) {
         this.longOpts.put(var1.getLongOpt(), var1);
      }

      if(var1.isRequired()) {
         if(this.requiredOpts.contains(var2)) {
            this.requiredOpts.remove(this.requiredOpts.indexOf(var2));
         }

         this.requiredOpts.add(var2);
      }

      this.shortOpts.put(var2, var1);
      return this;
   }

   public Collection getOptions() {
      return Collections.unmodifiableCollection(this.helpOptions());
   }

   List helpOptions() {
      return new ArrayList(this.shortOpts.values());
   }

   public List getRequiredOptions() {
      return this.requiredOpts;
   }

   public Option getOption(String var1) {
      var1 = Util.stripLeadingHyphens(var1);
      return this.shortOpts.containsKey(var1)?(Option)this.shortOpts.get(var1):(Option)this.longOpts.get(var1);
   }

   public boolean hasOption(String var1) {
      var1 = Util.stripLeadingHyphens(var1);
      return this.shortOpts.containsKey(var1) || this.longOpts.containsKey(var1);
   }

   public OptionGroup getOptionGroup(Option var1) {
      return (OptionGroup)this.optionGroups.get(var1.getKey());
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer();
      var1.append("[ Options: [ short ");
      var1.append(this.shortOpts.toString());
      var1.append(" ] [ long ");
      var1.append(this.longOpts);
      var1.append(" ]");
      return var1.toString();
   }
}
