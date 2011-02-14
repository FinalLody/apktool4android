// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:16:08
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime.tree;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.TreeAdaptor;

public interface TreeNodeStream extends IntStream {

   Object get(int var1);

   Object LT(int var1);

   Object getTreeSource();

   TokenStream getTokenStream();

   TreeAdaptor getTreeAdaptor();

   void setUniqueNavigationNodes(boolean var1);

   void reset();

   String toString(Object var1, Object var2);

   void replaceChildren(Object var1, int var2, int var3, Object var4);
}
