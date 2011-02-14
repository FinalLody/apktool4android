// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:46
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.cli;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Date;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

public class PatternOptionBuilder {

   public static final Class STRING_VALUE = String.class;
   public static final Class OBJECT_VALUE = Object.class;
   public static final Class NUMBER_VALUE = Number.class;
   public static final Class DATE_VALUE = Date.class;
   public static final Class CLASS_VALUE = Class.class;
   public static final Class EXISTING_FILE_VALUE = FileInputStream.class;
   public static final Class FILE_VALUE = File.class;
   public static final Class FILES_VALUE = File[].class;
   public static final Class URL_VALUE = URL.class;


   public static Object getValueClass(char var0) {
      switch(var0) {
      case 35:
         return DATE_VALUE;
      case 36:
      case 38:
      case 39:
      case 40:
      case 41:
      case 44:
      case 45:
      case 46:
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
      case 59:
      case 61:
      case 63:
      default:
         return null;
      case 37:
         return NUMBER_VALUE;
      case 42:
         return FILES_VALUE;
      case 43:
         return CLASS_VALUE;
      case 47:
         return URL_VALUE;
      case 58:
         return STRING_VALUE;
      case 60:
         return EXISTING_FILE_VALUE;
      case 62:
         return FILE_VALUE;
      case 64:
         return OBJECT_VALUE;
      }
   }

   public static boolean isValueCode(char var0) {
      return var0 == 64 || var0 == 58 || var0 == 37 || var0 == 43 || var0 == 35 || var0 == 60 || var0 == 62 || var0 == 42 || var0 == 47 || var0 == 33;
   }

   public static Options parsePattern(String var0) {
      char var1 = 32;
      boolean var2 = false;
      Object var3 = null;
      Options var4 = new Options();

      for(int var5 = 0; var5 < var0.length(); ++var5) {
         char var6 = var0.charAt(var5);
         if(!isValueCode(var6)) {
            if(var1 != 32) {
               OptionBuilder.hasArg(var3 != null);
               OptionBuilder.isRequired(var2);
               OptionBuilder.withType(var3);
               var4.addOption(OptionBuilder.create(var1));
               var2 = false;
               var3 = null;
               boolean var7 = true;
            }

            var1 = var6;
         } else if(var6 == 33) {
            var2 = true;
         } else {
            var3 = getValueClass(var6);
         }
      }

      if(var1 != 32) {
         OptionBuilder.hasArg(var3 != null);
         OptionBuilder.isRequired(var2);
         OptionBuilder.withType(var3);
         var4.addOption(OptionBuilder.create(var1));
      }

      return var4;
   }

}
