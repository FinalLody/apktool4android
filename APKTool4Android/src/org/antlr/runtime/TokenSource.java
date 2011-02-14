// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:15
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.Token;

public interface TokenSource {

   Token nextToken();

   String getSourceName();
}
