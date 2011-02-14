// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:03
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.tree;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

public interface TreeAdaptor {

   Object create(Token var1);

   Object dupNode(Object var1);

   Object dupTree(Object var1);

   Object nil();

   Object errorNode(TokenStream var1, Token var2, Token var3, RecognitionException var4);

   boolean isNil(Object var1);

   void addChild(Object var1, Object var2);

   Object becomeRoot(Object var1, Object var2);

   Object rulePostProcessing(Object var1);

   int getUniqueID(Object var1);

   Object becomeRoot(Token var1, Object var2);

   Object create(int var1, Token var2);

   Object create(int var1, Token var2, String var3);

   Object create(int var1, String var2);

   int getType(Object var1);

   void setType(Object var1, int var2);

   String getText(Object var1);

   void setText(Object var1, String var2);

   Token getToken(Object var1);

   void setTokenBoundaries(Object var1, Token var2, Token var3);

   int getTokenStartIndex(Object var1);

   int getTokenStopIndex(Object var1);

   Object getChild(Object var1, int var2);

   void setChild(Object var1, int var2, Object var3);

   Object deleteChild(Object var1, int var2);

   int getChildCount(Object var1);

   Object getParent(Object var1);

   void setParent(Object var1, Object var2);

   int getChildIndex(Object var1);

   void setChildIndex(Object var1, int var2);

   void replaceChildren(Object var1, int var2, int var3, Object var4);
}
