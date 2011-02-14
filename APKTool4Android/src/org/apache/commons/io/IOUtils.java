// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:39
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.output.ByteArrayOutputStream;

public class IOUtils {

   public static final char DIR_SEPARATOR_UNIX = '/';
   public static final char DIR_SEPARATOR_WINDOWS = '\\';
   public static final char DIR_SEPARATOR = File.separatorChar;
   public static final String LINE_SEPARATOR_UNIX = "\n";
   public static final String LINE_SEPARATOR_WINDOWS = "\r\n";
   public static final String LINE_SEPARATOR;
   private static final int DEFAULT_BUFFER_SIZE = 4096;


   public static void closeQuietly(Reader var0) {
      try {
         if(var0 != null) {
            var0.close();
         }
      } catch (IOException var2) {
         ;
      }

   }

   public static void closeQuietly(Writer var0) {
      try {
         if(var0 != null) {
            var0.close();
         }
      } catch (IOException var2) {
         ;
      }

   }

   public static void closeQuietly(InputStream var0) {
      try {
         if(var0 != null) {
            var0.close();
         }
      } catch (IOException var2) {
         ;
      }

   }

   public static void closeQuietly(OutputStream var0) {
      try {
         if(var0 != null) {
            var0.close();
         }
      } catch (IOException var2) {
         ;
      }

   }

   public static byte[] toByteArray(InputStream var0) throws IOException {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      copy(var0, var1);
      return var1.toByteArray();
   }

   public static byte[] toByteArray(Reader var0) throws IOException {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      copy(var0, var1);
      return var1.toByteArray();
   }

   public static byte[] toByteArray(Reader var0, String var1) throws IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      copy(var0, var2, var1);
      return var2.toByteArray();
   }

   public static byte[] toByteArray(String var0) throws IOException {
      return var0.getBytes();
   }

   public static char[] toCharArray(InputStream var0) throws IOException {
      CharArrayWriter var1 = new CharArrayWriter();
      copy(var0, var1);
      return var1.toCharArray();
   }

   public static char[] toCharArray(InputStream var0, String var1) throws IOException {
      CharArrayWriter var2 = new CharArrayWriter();
      copy(var0, var2, var1);
      return var2.toCharArray();
   }

   public static char[] toCharArray(Reader var0) throws IOException {
      CharArrayWriter var1 = new CharArrayWriter();
      copy(var0, var1);
      return var1.toCharArray();
   }

   public static String toString(InputStream var0) throws IOException {
      StringWriter var1 = new StringWriter();
      copy(var0, var1);
      return var1.toString();
   }

   public static String toString(InputStream var0, String var1) throws IOException {
      StringWriter var2 = new StringWriter();
      copy(var0, var2, var1);
      return var2.toString();
   }

   public static String toString(Reader var0) throws IOException {
      StringWriter var1 = new StringWriter();
      copy(var0, var1);
      return var1.toString();
   }

   public static String toString(byte[] var0) throws IOException {
      return new String(var0);
   }

   public static String toString(byte[] var0, String var1) throws IOException {
      return var1 == null?new String(var0):new String(var0, var1);
   }

   public static List readLines(InputStream var0) throws IOException {
      InputStreamReader var1 = new InputStreamReader(var0);
      return readLines(var1);
   }

   public static List readLines(InputStream var0, String var1) throws IOException {
      if(var1 == null) {
         return readLines(var0);
      } else {
         InputStreamReader var2 = new InputStreamReader(var0, var1);
         return readLines(var2);
      }
   }

   public static List readLines(Reader var0) throws IOException {
      BufferedReader var1 = new BufferedReader(var0);
      ArrayList var2 = new ArrayList();

      for(String var3 = var1.readLine(); var3 != null; var3 = var1.readLine()) {
         var2.add(var3);
      }

      return var2;
   }

   public static LineIterator lineIterator(Reader var0) {
      return new LineIterator(var0);
   }

   public static LineIterator lineIterator(InputStream var0, String var1) throws IOException {
      InputStreamReader var2 = null;
      if(var1 == null) {
         var2 = new InputStreamReader(var0);
      } else {
         var2 = new InputStreamReader(var0, var1);
      }

      return new LineIterator(var2);
   }

   public static InputStream toInputStream(String var0) {
      byte[] var1 = var0.getBytes();
      return new ByteArrayInputStream(var1);
   }

   public static InputStream toInputStream(String var0, String var1) throws IOException {
      byte[] var2 = var1 != null?var0.getBytes(var1):var0.getBytes();
      return new ByteArrayInputStream(var2);
   }

   public static void write(byte[] var0, OutputStream var1) throws IOException {
      if(var0 != null) {
         var1.write(var0);
      }

   }

   public static void write(byte[] var0, Writer var1) throws IOException {
      if(var0 != null) {
         var1.write(new String(var0));
      }

   }

   public static void write(byte[] var0, Writer var1, String var2) throws IOException {
      if(var0 != null) {
         if(var2 == null) {
            write(var0, var1);
         } else {
            var1.write(new String(var0, var2));
         }
      }

   }

   public static void write(char[] var0, Writer var1) throws IOException {
      if(var0 != null) {
         var1.write(var0);
      }

   }

   public static void write(char[] var0, OutputStream var1) throws IOException {
      if(var0 != null) {
         var1.write((new String(var0)).getBytes());
      }

   }

   public static void write(char[] var0, OutputStream var1, String var2) throws IOException {
      if(var0 != null) {
         if(var2 == null) {
            write(var0, var1);
         } else {
            var1.write((new String(var0)).getBytes(var2));
         }
      }

   }

   public static void write(String var0, Writer var1) throws IOException {
      if(var0 != null) {
         var1.write(var0);
      }

   }

   public static void write(String var0, OutputStream var1) throws IOException {
      if(var0 != null) {
         var1.write(var0.getBytes());
      }

   }

   public static void write(String var0, OutputStream var1, String var2) throws IOException {
      if(var0 != null) {
         if(var2 == null) {
            write(var0, var1);
         } else {
            var1.write(var0.getBytes(var2));
         }
      }

   }

   public static void write(StringBuffer var0, Writer var1) throws IOException {
      if(var0 != null) {
         var1.write(var0.toString());
      }

   }

   public static void write(StringBuffer var0, OutputStream var1) throws IOException {
      if(var0 != null) {
         var1.write(var0.toString().getBytes());
      }

   }

   public static void write(StringBuffer var0, OutputStream var1, String var2) throws IOException {
      if(var0 != null) {
         if(var2 == null) {
            write(var0, var1);
         } else {
            var1.write(var0.toString().getBytes(var2));
         }
      }

   }

   public static void writeLines(Collection var0, String var1, OutputStream var2) throws IOException {
      if(var0 != null) {
         if(var1 == null) {
            var1 = LINE_SEPARATOR;
         }

         for(Iterator var3 = var0.iterator(); var3.hasNext(); var2.write(var1.getBytes())) {
            Object var4 = var3.next();
            if(var4 != null) {
               var2.write(var4.toString().getBytes());
            }
         }

      }
   }

   public static void writeLines(Collection var0, String var1, OutputStream var2, String var3) throws IOException {
      if(var3 == null) {
         writeLines(var0, var1, var2);
      } else {
         if(var0 == null) {
            return;
         }

         if(var1 == null) {
            var1 = LINE_SEPARATOR;
         }

         for(Iterator var4 = var0.iterator(); var4.hasNext(); var2.write(var1.getBytes(var3))) {
            Object var5 = var4.next();
            if(var5 != null) {
               var2.write(var5.toString().getBytes(var3));
            }
         }
      }

   }

   public static void writeLines(Collection var0, String var1, Writer var2) throws IOException {
      if(var0 != null) {
         if(var1 == null) {
            var1 = LINE_SEPARATOR;
         }

         for(Iterator var3 = var0.iterator(); var3.hasNext(); var2.write(var1)) {
            Object var4 = var3.next();
            if(var4 != null) {
               var2.write(var4.toString());
            }
         }

      }
   }

   public static int copy(InputStream var0, OutputStream var1) throws IOException {
      long var2 = copyLarge(var0, var1);
      return var2 > 2147483647L?-1:(int)var2;
   }

   public static long copyLarge(InputStream var0, OutputStream var1) throws IOException {
      byte[] var2 = new byte[4096];
      long var3 = 0L;

      int var6;
      for(boolean var5 = false; -1 != (var6 = var0.read(var2)); var3 += (long)var6) {
         var1.write(var2, 0, var6);
      }

      return var3;
   }

   public static void copy(InputStream var0, Writer var1) throws IOException {
      InputStreamReader var2 = new InputStreamReader(var0);
      copy(var2, var1);
   }

   public static void copy(InputStream var0, Writer var1, String var2) throws IOException {
      if(var2 == null) {
         copy(var0, var1);
      } else {
         InputStreamReader var3 = new InputStreamReader(var0, var2);
         copy(var3, var1);
      }

   }

   public static int copy(Reader var0, Writer var1) throws IOException {
      long var2 = copyLarge(var0, var1);
      return var2 > 2147483647L?-1:(int)var2;
   }

   public static long copyLarge(Reader var0, Writer var1) throws IOException {
      char[] var2 = new char[4096];
      long var3 = 0L;

      int var6;
      for(boolean var5 = false; -1 != (var6 = var0.read(var2)); var3 += (long)var6) {
         var1.write(var2, 0, var6);
      }

      return var3;
   }

   public static void copy(Reader var0, OutputStream var1) throws IOException {
      OutputStreamWriter var2 = new OutputStreamWriter(var1);
      copy(var0, var2);
      var2.flush();
   }

   public static void copy(Reader var0, OutputStream var1, String var2) throws IOException {
      if(var2 == null) {
         copy(var0, var1);
      } else {
         OutputStreamWriter var3 = new OutputStreamWriter(var1, var2);
         copy(var0, var3);
         var3.flush();
      }

   }

   public static boolean contentEquals(InputStream var0, InputStream var1) throws IOException {
      if(!(var0 instanceof BufferedInputStream)) {
         var0 = new BufferedInputStream((InputStream)var0);
      }

      if(!(var1 instanceof BufferedInputStream)) {
         var1 = new BufferedInputStream((InputStream)var1);
      }

      int var3;
      for(int var2 = ((InputStream)var0).read(); -1 != var2; var2 = ((InputStream)var0).read()) {
         var3 = ((InputStream)var1).read();
         if(var2 != var3) {
            return false;
         }
      }

      var3 = ((InputStream)var1).read();
      return var3 == -1;
   }

   public static boolean contentEquals(Reader var0, Reader var1) throws IOException {
      if(!(var0 instanceof BufferedReader)) {
         var0 = new BufferedReader((Reader)var0);
      }

      if(!(var1 instanceof BufferedReader)) {
         var1 = new BufferedReader((Reader)var1);
      }

      int var3;
      for(int var2 = ((Reader)var0).read(); -1 != var2; var2 = ((Reader)var0).read()) {
         var3 = ((Reader)var1).read();
         if(var2 != var3) {
            return false;
         }
      }

      var3 = ((Reader)var1).read();
      return var3 == -1;
   }

   static {
      StringWriter var0 = new StringWriter(4);
      PrintWriter var1 = new PrintWriter(var0);
      var1.println();
      LINE_SEPARATOR = var0.toString();
   }
}
