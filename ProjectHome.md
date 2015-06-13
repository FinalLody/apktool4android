APKTool running natively on Android. Details will be added after further progress is made.


Second commit added. Check notes below. This will be updated with each commit.


---

**Current status**
  * _UI:_ Will decompile the app whose path is typed into the edit text.
  * _UI:_ Will output to a hard-coded /sdcard/apktool/testoutput folder and this must be deleted before each decompilation.
  * _UI:_ Won't be developed further until below items are addressed.
  * _Decompilation:_ Works on light apps without complex resources. [1 ](.md)
  * _Decompilation:_ Decompiled source folders can be pulled to a local desktop and recompiled.
  * _Recompilation:_ Does not work. Smali parsing needs to be rewritten. [2 ](.md)
  * _Recompilation:_ 4 main files for this are about 1MB and exhausts Dalvik's memory. [3 ](.md)


---

  * [1 ](.md): POSSIBLY FIXED: 9patch drawables and buffered imagewriters seem to be the two issues currently. Will need work to replace AWT's image handling that work on desktop versions of Java but aren't implemented in Android's version.
  * [2 ](.md): Getting permission denied writing compiled file. Looks like Android-native I/O operations need to be dropped into AndrolibResources. Makes sense, a pain to actually do.
  * [3 ](.md): Main smali files needing a rewrite: <org.jf.smali> smaliFlexLexer, smaliLexer, smaliParser and smaliTreeWalker. They're about 1MB of text