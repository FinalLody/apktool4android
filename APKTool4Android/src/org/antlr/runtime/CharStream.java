// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:54
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.IntStream;

public interface CharStream extends IntStream {

   int EOF = -1;


   String substring(int var1, int var2);

   int LT(int var1);

   int getLine();

   void setLine(int var1);

   void setCharPositionInLine(int var1);

   int getCharPositionInLine();
}
