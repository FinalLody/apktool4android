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

package brut.androlib.res.decoder;

import brut.androlib.AndrolibException;
import brut.androlib.err.CantFind9PatchChunk;
import brut.androlib.res.data.ResResource;
import brut.androlib.res.data.value.ResBoolValue;
import brut.androlib.res.data.value.ResFileValue;
import brut.directory.Directory;
import brut.directory.DirectoryException;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ryszard Wiśniewski <brut.alll@gmail.com>
 */
public class ResFileDecoder {
    private final ResStreamDecoderContainer mDecoders;

    public ResFileDecoder(ResStreamDecoderContainer decoders) {
        this.mDecoders = decoders;
    }

    public void decode(ResResource res, Directory inDir, Directory outDir)
            throws AndrolibException {

        ResFileValue fileValue = (ResFileValue) res.getValue();
        String inFileName = fileValue.getStrippedPath();
        String outResName = res.getFilePath();
        String typeName = res.getResSpec().getType().getName();

        String ext = null;
        String outFileName;
        int extPos = inFileName.lastIndexOf(".");
        if (extPos == -1) {
            outFileName = outResName;
        } else {
            ext = inFileName.substring(extPos);
            outFileName = outResName + ext;
        }

        try {
            if (typeName.equals("raw")) {
                decode(inDir, inFileName, outDir, outFileName, "raw");
                return;
            }
            if (typeName.equals("drawable")) {
                if (inFileName.toLowerCase().endsWith(".9.png")) {
                    outFileName = outResName + ".9" + ext;

                    try {
                        decode(
                            inDir, inFileName, outDir, outFileName, "9patch");
                        return;
                    } catch (CantFind9PatchChunk ex) {
                        LOGGER.log(Level.WARNING, String.format(
                            "Cant find 9patch chunk in file: \"%s\". Renaming it to *.png.",
                            inFileName
                        ), ex);
                        outDir.removeFile(outFileName);
                        outFileName = outResName + ext;
                    }
                }
                if (! ".xml".equals(ext)) {
                    decode(inDir, inFileName, outDir, outFileName, "raw");
                    return;
                }
            }

            decode(inDir, inFileName, outDir, outFileName, "xml");
        } catch (AndrolibException ex) {
            LOGGER.log(Level.SEVERE, String.format(
                "Could not decode file, replacing by FALSE value: %s",
                inFileName, outFileName), ex);
            res.replace(new ResBoolValue(false));
        }
    }

    public void decode(Directory inDir, String inFileName, Directory outDir,
            String outFileName, String decoder) throws AndrolibException {
        try {
            InputStream in = inDir.getFileInput(inFileName);
            OutputStream out = outDir.getFileOutput(outFileName);
            mDecoders.decode(in, out, decoder);
            in.close();
            out.close();
        } catch (IOException ex) {
            throw new AndrolibException(ex);
        } catch (DirectoryException ex) {
            throw new AndrolibException(ex);
        }
    }

    private final static Logger LOGGER =
        Logger.getLogger(ResFileDecoder.class.getName());
}
