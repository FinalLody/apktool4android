// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:46
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Parser;

public class PosixParser extends Parser {

   private List tokens = new ArrayList();
   private boolean eatTheRest;
   private Option currentOption;
   private Options options;


   private void init() {
      this.eatTheRest = false;
      this.tokens.clear();
   }

   protected String[] flatten(Options var1, String[] var2, boolean var3) {
      this.init();
      this.options = var1;

      for(Iterator var4 = Arrays.asList(var2).iterator(); var4.hasNext(); this.gobble(var4)) {
         String var5 = (String)var4.next();
         if(var5.startsWith("--")) {
            int var6 = var5.indexOf(61);
            String var7 = var6 == -1?var5:var5.substring(0, var6);
            if(!var1.hasOption(var7)) {
               this.processNonOptionToken(var5, var3);
            } else {
               this.currentOption = var1.getOption(var7);
               this.tokens.add(var7);
               if(var6 != -1) {
                  this.tokens.add(var5.substring(var6 + 1));
               }
            }
         } else if("-".equals(var5)) {
            this.tokens.add(var5);
         } else if(var5.startsWith("-")) {
            if(var5.length() != 2 && !var1.hasOption(var5)) {
               this.burstToken(var5, var3);
            } else {
               this.processOptionToken(var5, var3);
            }
         } else {
            this.processNonOptionToken(var5, var3);
         }
      }

      return (String[])((String[])this.tokens.toArray(new String[this.tokens.size()]));
   }

   private void gobble(Iterator var1) {
      if(this.eatTheRest) {
         while(var1.hasNext()) {
            this.tokens.add(var1.next());
         }
      }

   }

   private void processNonOptionToken(String var1, boolean var2) {
      if(var2 && (this.currentOption == null || !this.currentOption.hasArg())) {
         this.eatTheRest = true;
         this.tokens.add("--");
      }

      this.tokens.add(var1);
   }

   private void processOptionToken(String var1, boolean var2) {
      if(var2 && !this.options.hasOption(var1)) {
         this.eatTheRest = true;
      }

      if(this.options.hasOption(var1)) {
         this.currentOption = this.options.getOption(var1);
      }

      this.tokens.add(var1);
   }

   protected void burstToken(String var1, boolean var2) {
      for(int var3 = 1; var3 < var1.length(); ++var3) {
         String var4 = String.valueOf(var1.charAt(var3));
         if(!this.options.hasOption(var4)) {
            if(var2) {
               this.processNonOptionToken(var1.substring(var3), true);
            } else {
               this.tokens.add(var1);
            }
            break;
         }

         this.tokens.add("-" + var4);
         this.currentOption = this.options.getOption(var4);
         if(this.currentOption.hasArg() && var1.length() != var3 + 1) {
            this.tokens.add(var1.substring(var3 + 1));
            break;
         }
      }

   }
}
