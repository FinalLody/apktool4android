// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:59
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.antlr.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenSource;
import org.antlr.runtime.TokenStream;

public class CommonTokenStream implements TokenStream {

   protected TokenSource tokenSource;
   protected List tokens;
   protected Map channelOverrideMap;
   protected Set discardSet;
   protected int channel;
   protected boolean discardOffChannelTokens;
   protected int lastMarker;
   protected int p;


   public CommonTokenStream() {
      this.channel = 0;
      this.discardOffChannelTokens = false;
      this.p = -1;
      this.tokens = new ArrayList(500);
   }

   public CommonTokenStream(TokenSource var1) {
      this();
      this.tokenSource = var1;
   }

   public CommonTokenStream(TokenSource var1, int var2) {
      this(var1);
      this.channel = var2;
   }

   public void setTokenSource(TokenSource var1) {
      this.tokenSource = var1;
      this.tokens.clear();
      this.p = -1;
      this.channel = 0;
   }

   protected void fillBuffer() {
      int var1 = 0;

      for(Token var2 = this.tokenSource.nextToken(); var2 != null && var2.getType() != -1; var2 = this.tokenSource.nextToken()) {
         boolean var3 = false;
         if(this.channelOverrideMap != null) {
            Integer var4 = (Integer)this.channelOverrideMap.get(new Integer(var2.getType()));
            if(var4 != null) {
               var2.setChannel(var4.intValue());
            }
         }

         if(this.discardSet != null && this.discardSet.contains(new Integer(var2.getType()))) {
            var3 = true;
         } else if(this.discardOffChannelTokens && var2.getChannel() != this.channel) {
            var3 = true;
         }

         if(!var3) {
            var2.setTokenIndex(var1);
            this.tokens.add(var2);
            ++var1;
         }
      }

      this.p = 0;
      this.p = this.skipOffTokenChannels(this.p);
   }

   public void consume() {
      if(this.p < this.tokens.size()) {
         ++this.p;
         this.p = this.skipOffTokenChannels(this.p);
      }

   }

   protected int skipOffTokenChannels(int var1) {
      for(int var2 = this.tokens.size(); var1 < var2 && ((Token)this.tokens.get(var1)).getChannel() != this.channel; ++var1) {
         ;
      }

      return var1;
   }

   protected int skipOffTokenChannelsReverse(int var1) {
      while(var1 >= 0 && ((Token)this.tokens.get(var1)).getChannel() != this.channel) {
         --var1;
      }

      return var1;
   }

   public void setTokenTypeChannel(int var1, int var2) {
      if(this.channelOverrideMap == null) {
         this.channelOverrideMap = new HashMap();
      }

      this.channelOverrideMap.put(new Integer(var1), new Integer(var2));
   }

   public void discardTokenType(int var1) {
      if(this.discardSet == null) {
         this.discardSet = new HashSet();
      }

      this.discardSet.add(new Integer(var1));
   }

   public void discardOffChannelTokens(boolean var1) {
      this.discardOffChannelTokens = var1;
   }

   public List getTokens() {
      if(this.p == -1) {
         this.fillBuffer();
      }

      return this.tokens;
   }

   public List getTokens(int var1, int var2) {
      return this.getTokens(var1, var2, (BitSet)null);
   }

   public List getTokens(int var1, int var2, BitSet var3) {
      if(this.p == -1) {
         this.fillBuffer();
      }

      if(var2 >= this.tokens.size()) {
         var2 = this.tokens.size() - 1;
      }

      if(var1 < 0) {
         var1 = 0;
      }

      if(var1 > var2) {
         return null;
      } else {
         ArrayList var4 = new ArrayList();

         for(int var5 = var1; var5 <= var2; ++var5) {
            Token var6 = (Token)this.tokens.get(var5);
            if(var3 == null || var3.member(var6.getType())) {
               var4.add(var6);
            }
         }

         if(var4.size() == 0) {
            var4 = null;
         }

         return var4;
      }
   }

   public List getTokens(int var1, int var2, List var3) {
      return this.getTokens(var1, var2, new BitSet(var3));
   }

   public List getTokens(int var1, int var2, int var3) {
      return this.getTokens(var1, var2, BitSet.of(var3));
   }

   public Token LT(int var1) {
      if(this.p == -1) {
         this.fillBuffer();
      }

      if(var1 == 0) {
         return null;
      } else if(var1 < 0) {
         return this.LB(-var1);
      } else if(this.p + var1 - 1 >= this.tokens.size()) {
         return Token.EOF_TOKEN;
      } else {
         int var2 = this.p;

         for(int var3 = 1; var3 < var1; ++var3) {
            var2 = this.skipOffTokenChannels(var2 + 1);
         }

         return var2 >= this.tokens.size()?Token.EOF_TOKEN:(Token)this.tokens.get(var2);
      }
   }

   protected Token LB(int var1) {
      if(this.p == -1) {
         this.fillBuffer();
      }

      if(var1 == 0) {
         return null;
      } else if(this.p - var1 < 0) {
         return null;
      } else {
         int var2 = this.p;

         for(int var3 = 1; var3 <= var1; ++var3) {
            var2 = this.skipOffTokenChannelsReverse(var2 - 1);
         }

         return var2 < 0?null:(Token)this.tokens.get(var2);
      }
   }

   public Token get(int var1) {
      return (Token)this.tokens.get(var1);
   }

   public int LA(int var1) {
      return this.LT(var1).getType();
   }

   public int mark() {
      if(this.p == -1) {
         this.fillBuffer();
      }

      this.lastMarker = this.index();
      return this.lastMarker;
   }

   public void release(int var1) {
   }

   public int size() {
      return this.tokens.size();
   }

   public int index() {
      return this.p;
   }

   public void rewind(int var1) {
      this.seek(var1);
   }

   public void rewind() {
      this.seek(this.lastMarker);
   }

   public void reset() {
      this.p = 0;
      this.lastMarker = 0;
   }

   public void seek(int var1) {
      this.p = var1;
   }

   public TokenSource getTokenSource() {
      return this.tokenSource;
   }

   public String getSourceName() {
      return this.getTokenSource().getSourceName();
   }

   public String toString() {
      if(this.p == -1) {
         this.fillBuffer();
      }

      return this.toString(0, this.tokens.size() - 1);
   }

   public String toString(int var1, int var2) {
      if(var1 >= 0 && var2 >= 0) {
         if(this.p == -1) {
            this.fillBuffer();
         }

         if(var2 >= this.tokens.size()) {
            var2 = this.tokens.size() - 1;
         }

         StringBuffer var3 = new StringBuffer();

         for(int var4 = var1; var4 <= var2; ++var4) {
            Token var5 = (Token)this.tokens.get(var4);
            var3.append(var5.getText());
         }

         return var3.toString();
      } else {
         return null;
      }
   }

   public String toString(Token var1, Token var2) {
      return var1 != null && var2 != null?this.toString(var1.getTokenIndex(), var2.getTokenIndex()):null;
   }
}
