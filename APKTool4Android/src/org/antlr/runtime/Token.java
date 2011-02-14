// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:14
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonToken;

public interface Token {

   int EOR_TOKEN_TYPE = 1;
   int DOWN = 2;
   int UP = 3;
   int MIN_TOKEN_TYPE = 4;
   int EOF = -1;
   Token EOF_TOKEN = new CommonToken(-1);
   int INVALID_TOKEN_TYPE = 0;
   Token INVALID_TOKEN = new CommonToken(0);
   Token SKIP_TOKEN = new CommonToken(0);
   int DEFAULT_CHANNEL = 0;
   int HIDDEN_CHANNEL = 99;


   String getText();

   void setText(String var1);

   int getType();

   void setType(int var1);

   int getLine();

   void setLine(int var1);

   int getCharPositionInLine();

   void setCharPositionInLine(int var1);

   int getChannel();

   void setChannel(int var1);

   int getTokenIndex();

   void setTokenIndex(int var1);

   CharStream getInputStream();

   void setInputStream(CharStream var1);

}
