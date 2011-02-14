// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:05
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.tree;

import java.util.List;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public interface Tree {

   Tree INVALID_NODE = new CommonTree(Token.INVALID_TOKEN);


   Tree getChild(int var1);

   int getChildCount();

   Tree getParent();

   void setParent(Tree var1);

   boolean hasAncestor(int var1);

   Tree getAncestor(int var1);

   List getAncestors();

   int getChildIndex();

   void setChildIndex(int var1);

   void freshenParentAndChildIndexes();

   void addChild(Tree var1);

   void setChild(int var1, Tree var2);

   Object deleteChild(int var1);

   void replaceChildren(int var1, int var2, Object var3);

   boolean isNil();

   int getTokenStartIndex();

   void setTokenStartIndex(int var1);

   int getTokenStopIndex();

   void setTokenStopIndex(int var1);

   Tree dupNode();

   int getType();

   String getText();

   int getLine();

   int getCharPositionInLine();

   String toStringTree();

   String toString();

}
