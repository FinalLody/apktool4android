// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:47
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.TypeHandler;
import org.apache.commons.cli.Util;

public class CommandLine implements Serializable {

   private static final long serialVersionUID = 1L;
   private List args = new LinkedList();
   private List options = new ArrayList();


   public boolean hasOption(String var1) {
      return this.options.contains(this.resolveOption(var1));
   }

   public boolean hasOption(char var1) {
      return this.hasOption(String.valueOf(var1));
   }

   public Object getOptionObject(String var1) {
      try {
         return this.getParsedOptionValue(var1);
      } catch (ParseException var3) {
         System.err.println("Exception found converting " + var1 + " to desired type: " + var3.getMessage());
         return null;
      }
   }

   public Object getParsedOptionValue(String var1) throws ParseException {
      String var2 = this.getOptionValue(var1);
      Option var3 = this.resolveOption(var1);
      if(var3 == null) {
         return null;
      } else {
         Object var4 = var3.getType();
         return var2 == null?null:TypeHandler.createValue(var2, var4);
      }
   }

   public Object getOptionObject(char var1) {
      return this.getOptionObject(String.valueOf(var1));
   }

   public String getOptionValue(String var1) {
      String[] var2 = this.getOptionValues(var1);
      return var2 == null?null:var2[0];
   }

   public String getOptionValue(char var1) {
      return this.getOptionValue(String.valueOf(var1));
   }

   public String[] getOptionValues(String var1) {
      ArrayList var2 = new ArrayList();
      Iterator var3 = this.options.iterator();

      while(var3.hasNext()) {
         Option var4 = (Option)var3.next();
         if(var1.equals(var4.getOpt()) || var1.equals(var4.getLongOpt())) {
            var2.addAll(var4.getValuesList());
         }
      }

      return var2.equals("")?null:(String[])((String[])var2.toArray(new String[var2.size()]));
   }

   private Option resolveOption(String var1) {
      var1 = Util.stripLeadingHyphens(var1);
      Iterator var2 = this.options.iterator();

      Option var3;
      do {
         if(!var2.hasNext()) {
            return null;
         }

         var3 = (Option)var2.next();
         if(var1.equals(var3.getOpt())) {
            return var3;
         }
      } while(!var1.equals(var3.getLongOpt()));

      return var3;
   }

   public String[] getOptionValues(char var1) {
      return this.getOptionValues(String.valueOf(var1));
   }

   public String getOptionValue(String var1, String var2) {
      String var3 = this.getOptionValue(var1);
      return var3 != null?var3:var2;
   }

   public String getOptionValue(char var1, String var2) {
      return this.getOptionValue(String.valueOf(var1), var2);
   }

   public Properties getOptionProperties(String var1) {
      Properties var2 = new Properties();
      Iterator var3 = this.options.iterator();

      while(var3.hasNext()) {
         Option var4 = (Option)var3.next();
         if(var1.equals(var4.getOpt()) || var1.equals(var4.getLongOpt())) {
            List var5 = var4.getValuesList();
            if(var5.size() >= 2) {
               var2.put(var5.get(0), var5.get(1));
            } else if(var5.size() == 1) {
               var2.put(var5.get(0), "true");
            }
         }
      }

      return var2;
   }

   public String[] getArgs() {
      String[] var1 = new String[this.args.size()];
      this.args.toArray(var1);
      return var1;
   }

   public List getArgList() {
      return this.args;
   }

   void addArg(String var1) {
      this.args.add(var1);
   }

   void addOption(Option var1) {
      this.options.add(var1);
   }

   public Iterator iterator() {
      return this.options.iterator();
   }

   public Option[] getOptions() {
      List var1 = this.options;
      Option[] var2 = new Option[var1.size()];
      return (Option[])((Option[])var1.toArray(var2));
   }
}
