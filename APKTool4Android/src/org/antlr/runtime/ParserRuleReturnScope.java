// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:52
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import org.antlr.runtime.RuleReturnScope;
import org.antlr.runtime.Token;

public class ParserRuleReturnScope extends RuleReturnScope {

   public Token start;
   public Token stop;


   public Object getStart() {
      return this.start;
   }

   public Object getStop() {
      return this.stop;
   }
}
