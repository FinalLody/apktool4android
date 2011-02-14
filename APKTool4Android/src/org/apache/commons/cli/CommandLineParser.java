// Decompiled by:       Fernflower v0.6
// Date:                16.01.2011 23:15:46
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package org.apache.commons.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public interface CommandLineParser {

   CommandLine parse(Options var1, String[] var2) throws ParseException;

   CommandLine parse(Options var1, String[] var2, boolean var3) throws ParseException;
}
