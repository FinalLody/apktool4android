// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:53
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;


public interface IntStream {

   void consume();

   int LA(int var1);

   int mark();

   int index();

   void rewind(int var1);

   void rewind();

   void release(int var1);

   void seek(int var1);

   int size();

   String getSourceName();
}
