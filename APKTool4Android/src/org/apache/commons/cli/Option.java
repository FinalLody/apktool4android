// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:50
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.OptionValidator;

public class Option implements Cloneable, Serializable {

   private static final long serialVersionUID = 1L;
   public static final int UNINITIALIZED = -1;
   public static final int UNLIMITED_VALUES = -2;
   private String opt;
   private String longOpt;
   private String argName;
   private String description;
   private boolean required;
   private boolean optionalArg;
   private int numberOfArgs;
   private Object type;
   private List values;
   private char valuesep;


   public Option(String var1, String var2) throws IllegalArgumentException {
      this(var1, (String)null, false, var2);
   }

   public Option(String var1, boolean var2, String var3) throws IllegalArgumentException {
      this(var1, (String)null, var2, var3);
   }

   public Option(String var1, String var2, boolean var3, String var4) throws IllegalArgumentException {
      this.argName = "arg";
      this.numberOfArgs = -1;
      this.values = new ArrayList();
      OptionValidator.validateOption(var1);
      this.opt = var1;
      this.longOpt = var2;
      if(var3) {
         this.numberOfArgs = 1;
      }

      this.description = var4;
   }

   public int getId() {
      return this.getKey().charAt(0);
   }

   String getKey() {
      return this.opt == null?this.longOpt:this.opt;
   }

   public String getOpt() {
      return this.opt;
   }

   public Object getType() {
      return this.type;
   }

   public void setType(Object var1) {
      this.type = var1;
   }

   public String getLongOpt() {
      return this.longOpt;
   }

   public void setLongOpt(String var1) {
      this.longOpt = var1;
   }

   public void setOptionalArg(boolean var1) {
      this.optionalArg = var1;
   }

   public boolean hasOptionalArg() {
      return this.optionalArg;
   }

   public boolean hasLongOpt() {
      return this.longOpt != null;
   }

   public boolean hasArg() {
      return this.numberOfArgs > 0 || this.numberOfArgs == -2;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String var1) {
      this.description = var1;
   }

   public boolean isRequired() {
      return this.required;
   }

   public void setRequired(boolean var1) {
      this.required = var1;
   }

   public void setArgName(String var1) {
      this.argName = var1;
   }

   public String getArgName() {
      return this.argName;
   }

   public boolean hasArgName() {
      return this.argName != null && this.argName.length() > 0;
   }

   public boolean hasArgs() {
      return this.numberOfArgs > 1 || this.numberOfArgs == -2;
   }

   public void setArgs(int var1) {
      this.numberOfArgs = var1;
   }

   public void setValueSeparator(char var1) {
      this.valuesep = var1;
   }

   public char getValueSeparator() {
      return this.valuesep;
   }

   public boolean hasValueSeparator() {
      return this.valuesep > 0;
   }

   public int getArgs() {
      return this.numberOfArgs;
   }

   void addValueForProcessing(String var1) {
      switch(this.numberOfArgs) {
      case -1:
         throw new RuntimeException("NO_ARGS_ALLOWED");
      default:
         this.processValue(var1);
      }
   }

   private void processValue(String var1) {
      if(this.hasValueSeparator()) {
         char var2 = this.getValueSeparator();

         for(int var3 = var1.indexOf(var2); var3 != -1 && this.values.size() != this.numberOfArgs - 1; var3 = var1.indexOf(var2)) {
            this.add(var1.substring(0, var3));
            var1 = var1.substring(var3 + 1);
         }
      }

      this.add(var1);
   }

   private void add(String var1) {
      if(this.numberOfArgs > 0 && this.values.size() > this.numberOfArgs - 1) {
         throw new RuntimeException("Cannot add value, list full.");
      } else {
         this.values.add(var1);
      }
   }

   public String getValue() {
      return this.hasNoValues()?null:(String)this.values.get(0);
   }

   public String getValue(int var1) throws IndexOutOfBoundsException {
      return this.hasNoValues()?null:(String)this.values.get(var1);
   }

   public String getValue(String var1) {
      String var2 = this.getValue();
      return var2 != null?var2:var1;
   }

   public String[] getValues() {
      return this.hasNoValues()?null:(String[])((String[])this.values.toArray(new String[this.values.size()]));
   }

   public List getValuesList() {
      return this.values;
   }

   public String toString() {
      StringBuffer var1 = (new StringBuffer()).append("[ option: ");
      var1.append(this.opt);
      if(this.longOpt != null) {
         var1.append(" ").append(this.longOpt);
      }

      var1.append(" ");
      if(this.hasArgs()) {
         var1.append("[ARG...]");
      } else if(this.hasArg()) {
         var1.append(" [ARG]");
      }

      var1.append(" :: ").append(this.description);
      if(this.type != null) {
         var1.append(" :: ").append(this.type);
      }

      var1.append(" ]");
      return var1.toString();
   }

   private boolean hasNoValues() {
      return this.values.equals("");
   }

   public boolean equals(Object var1) {
      if(this == var1) {
         return true;
      } else if(var1 != null && this.getClass() == var1.getClass()) {
         Option var2 = (Option)var1;
         if(this.opt != null) {
            if(!this.opt.equals(var2.opt)) {
               return false;
            }
         } else if(var2.opt != null) {
            return false;
         }

         if(this.longOpt != null) {
            if(!this.longOpt.equals(var2.longOpt)) {
               return false;
            }
         } else if(var2.longOpt != null) {
            return false;
         }

         return true;
      } else {
         return false;
      }
   }

   public int hashCode() {
      int var1 = this.opt != null?this.opt.hashCode():0;
      var1 = 31 * var1 + (this.longOpt != null?this.longOpt.hashCode():0);
      return var1;
   }

   public Object clone() {
      try {
         Option var1 = (Option)super.clone();
         var1.values = new ArrayList(this.values);
         return var1;
      } catch (CloneNotSupportedException var2) {
         throw new RuntimeException("A CloneNotSupportedException was thrown: " + var2.getMessage());
      }
   }

   void clearValues() {
      this.values.clear();
   }

   public boolean addValue(String var1) {
      throw new UnsupportedOperationException("The addValue method is not intended for client use. Subclasses should use the addValueForProcessing method instead. ");
   }
}
