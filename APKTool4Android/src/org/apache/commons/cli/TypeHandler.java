// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:50
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.cli;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PatternOptionBuilder;

public class TypeHandler {

   public static Object createValue(String var0, Object var1) throws ParseException {
      return createValue(var0, (Class)var1);
   }

   public static Object createValue(String var0, Class var1) throws ParseException {
      return PatternOptionBuilder.STRING_VALUE == var1?var0:(PatternOptionBuilder.OBJECT_VALUE == var1?createObject(var0):(PatternOptionBuilder.NUMBER_VALUE == var1?createNumber(var0):(PatternOptionBuilder.DATE_VALUE == var1?createDate(var0):(PatternOptionBuilder.CLASS_VALUE == var1?createClass(var0):(PatternOptionBuilder.FILE_VALUE == var1?createFile(var0):(PatternOptionBuilder.EXISTING_FILE_VALUE == var1?createFile(var0):(PatternOptionBuilder.FILES_VALUE == var1?createFiles(var0):(PatternOptionBuilder.URL_VALUE == var1?createURL(var0):null))))))));
   }

   public static Object createObject(String var0) throws ParseException {
      Class var1 = null;

      try {
         var1 = Class.forName(var0);
      } catch (ClassNotFoundException var5) {
         throw new ParseException("Unable to find the class: " + var0);
      }

      Object var2 = null;

      try {
         var2 = var1.newInstance();
         return var2;
      } catch (Exception var4) {
         throw new ParseException(var4.getClass().getName() + "; Unable to create an instance of: " + var0);
      }
   }

   public static Number createNumber(String var0) throws ParseException {
      try {
         return (Number)(var0.indexOf(46) != -1?Double.valueOf(var0):Long.valueOf(var0));
      } catch (NumberFormatException var2) {
         throw new ParseException(var2.getMessage());
      }
   }

   public static Class createClass(String var0) throws ParseException {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new ParseException("Unable to find the class: " + var0);
      }
   }

   public static Date createDate(String var0) throws ParseException {
      throw new UnsupportedOperationException("Not yet implemented");
   }

   public static URL createURL(String var0) throws ParseException {
      try {
         return new URL(var0);
      } catch (MalformedURLException var2) {
         throw new ParseException("Unable to parse the URL: " + var0);
      }
   }

   public static File createFile(String var0) throws ParseException {
      return new File(var0);
   }

   public static File[] createFiles(String var0) throws ParseException {
      throw new UnsupportedOperationException("Not yet implemented");
   }
}
