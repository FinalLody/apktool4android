// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:53
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;

public interface TokenStream extends IntStream {

   Token LT(int var1);

   Token get(int var1);

   TokenSource getTokenSource();

   String toString(int var1, int var2);

   String toString(Token var1, Token var2);
}
