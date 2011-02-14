/**
 *  Copyright 2011 Ryszard Wiśniewski <brut.alll@gmail.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package brut.androlib.src;

import brut.androlib.AndrolibException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jf.dexlib.Code.Analysis.RegisterType;
import org.jf.dexlib.Code.Opcode;

/**
 * @author Ryszard Wiśniewski <brut.alll@gmail.com>
 */
public class DebugInjector {

    public static void inject(ListIterator<String> it, StringBuilder out)
            throws AndrolibException {
        new DebugInjector(it, out).inject();
    }

    private DebugInjector(ListIterator<String> it, StringBuilder out) {
        mIt = it;
        mOut = out;
    }

    private void inject() throws AndrolibException {
        String definition = nextAndAppend();
        if (
            definition.contains(" abstract ") ||
            definition.contains(" native ")
        ) {
            nextAndAppend();
            return;
        }
        injectParameters(definition);

        boolean end = false;
        while (!end) {
            end = step();
        }
    }

    private void injectParameters(String definition) throws AndrolibException {
        int pos = definition.indexOf('(');
        if (pos == -1) {
            throw new AndrolibException();
        }
        int pos2 = definition.indexOf(')', pos);
        if (pos2 == -1) {
            throw new AndrolibException();
        }
        String params = definition.substring(pos + 1, pos2);

        int i = definition.contains(" static ") ? 0 : 1;
        int argc = TypeName.listFromInternalName(params).size() + i;
        while(i < argc) {
            mOut.append(".parameter \"p").append(i).append("\"\n");
            i++;
        }
    }

    private boolean step() {
        String line = next();
        if (line.length()==0) {
            return false;
        }

        switch (line.charAt(0)) {
            case '#':
                return processComment(line);
            case ':':
                append(line);
                return false;
            case '.':
                return processDirective(line);
            default:
                return processInstruction(line);
        }
    }

    private boolean processComment(String line) {
        if (mFirstInstruction) {
            return false;
        }

        Matcher m = REGISTER_INFO_PATTERN.matcher(line);

        while (m.find()) {
            String localName = m.group(1);
            String localType = null;
            switch (RegisterType.Category.valueOf(m.group(2))) {
                case Reference:
                case Null:
                case UninitRef:
                case UninitThis:
                    localType = "Ljava/lang/Object;";
                    break;
                case Boolean:
                    localType = "Z";
                    break;
                case Integer:
                case One:
                case Unknown:
                    localType = "I";
                    break;
                case Uninit:
                case Conflicted:
                    if (mInitializedRegisters.remove(localName)) {
                        mOut.append(".end local ").append(localName)
                            .append('\n');
                    }
                    continue;
                case Short:
                case PosShort:
                    localType = "S";
                    break;
                case Byte:
                case PosByte:
                    localType = "B";
                    break;
                case Char:
                    localType = "C";
                    break;
                case Float:
                    localType = "F";
                    break;
                case LongHi:
                case LongLo:
                    localType = "J";
                    break;
                case DoubleHi:
                case DoubleLo:
                    localType = "D";
                    break;
                default:
                    assert false;
            }

            mInitializedRegisters.add(localName);
            mOut.append(".local ").append(localName).append(", ")
                .append(localName).append(':').append(localType).append('\n');
        }

        return false;
    }

    private boolean processDirective(String line) {
        String line2 = line.substring(1);
        if (
            line2.startsWith("line ") ||
            line2.equals("prologue") ||
            line2.startsWith("parameter") ||
            line2.startsWith("local ") ||
            line2.startsWith("end local ")
        ) {
            return false;
        }

        append(line);
        if (line2.equals("end method")) {
            return true;
        }
        if (
            line2.startsWith("annotation ") ||
            line2.equals("sparse-switch") ||
            line2.startsWith("packed-switch ") ||
            line2.startsWith("array-data ")
        ) {
            while(true) {
                line2 = nextAndAppend();
                if (line2.startsWith(".end ")) {
                    break;
                }
            }
        }
        return false;
    }

    private boolean processInstruction(String line) {
        if (mFirstInstruction) {
            mOut.append(".prologue\n");
            mFirstInstruction = false;
        }
        mOut.append(".line ").append(mIt.nextIndex()).append('\n')
            .append(line).append('\n');

        return false;
    }

    private String next() {
        return mIt.next().trim();
    }

    private String nextAndAppend() {
        String line = next();
        append(line);
        return line;
    }

    private void append(String append) {
        mOut.append(append).append('\n');
    }

    private final ListIterator<String> mIt;
    private final StringBuilder mOut;

    private boolean mFirstInstruction = true;
    private final Set<String> mInitializedRegisters = new HashSet<String>();

    private static final Pattern REGISTER_INFO_PATTERN =
        Pattern.compile("((?:p|v)\\d+)=\\(([^)]+)\\);");
}
