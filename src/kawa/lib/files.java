// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.FileUtils;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.IsEqual;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.FilePath;
import gnu.text.Path;
import gnu.text.URIPath;
import java.io.File;
import java.io.IOException;
import kawa.standard.readchar;

// Referenced classes of package kawa.lib:
//            ports

public class files extends ModuleBody
{

    public static final ModuleMethod $Mn$Grpathname;
    public static final ModuleMethod $Pcfile$Mnseparator;
    public static final files $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit29;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod URI$Qu;
    public static final ModuleMethod absolute$Mnpath$Qu;
    public static final ModuleMethod copy$Mnfile;
    public static final ModuleMethod create$Mndirectory;
    public static final ModuleMethod delete$Mnfile;
    public static final ModuleMethod directory$Mnfiles;
    public static final ModuleMethod file$Mndirectory$Qu;
    public static final ModuleMethod file$Mnexists$Qu;
    public static final ModuleMethod file$Mnreadable$Qu;
    public static final ModuleMethod file$Mnwritable$Qu;
    public static final ModuleMethod filepath$Qu;
    public static final ModuleMethod make$Mntemporary$Mnfile;
    public static final ModuleMethod path$Mnauthority;
    public static final ModuleMethod path$Mndirectory;
    public static final ModuleMethod path$Mnextension;
    public static final ModuleMethod path$Mnfile;
    public static final ModuleMethod path$Mnfragment;
    public static final ModuleMethod path$Mnhost;
    public static final ModuleMethod path$Mnlast;
    public static final ModuleMethod path$Mnparent;
    public static final ModuleMethod path$Mnport;
    public static final ModuleMethod path$Mnquery;
    public static final ModuleMethod path$Mnscheme;
    public static final ModuleMethod path$Mnuser$Mninfo;
    public static final ModuleMethod path$Qu;
    public static final ModuleMethod rename$Mnfile;
    public static final ModuleMethod resolve$Mnuri;
    public static final ModuleMethod system$Mntmpdir;

    public static String $PcFileSeparator()
    {
        return System.getProperty("file.separator");
    }

    public static Path $To$Pathname(Object obj)
    {
        return Path.valueOf(obj);
    }

    public files()
    {
        ModuleInfo.register(this);
    }

    public static boolean URI$Qu(Object obj)
    {
        return obj instanceof URIPath;
    }

    public static void copyFile(Path path, Path path1)
    {
        gnu.mapping.InPort inport = ports.openInputFile(path);
        gnu.mapping.OutPort outport = ports.openOutputFile(path1);
        for (Object obj = readchar.readChar.apply1(inport); !ports.isEofObject(obj); obj = readchar.readChar.apply1(inport))
        {
            ports.writeChar(obj, outport);
        }

        ports.closeOutputPort(outport);
        ports.closeInputPort(inport);
    }

    public static boolean createDirectory(FilePath filepath)
    {
        return filepath.toFile().mkdir();
    }

    public static void deleteFile(FilePath filepath)
    {
        if (!filepath.delete())
        {
            throw (Throwable)new IOException(Format.formatToString(0, new Object[] {
                "cannot delete ~a", filepath
            }).toString());
        } else
        {
            return;
        }
    }

    public static Object directoryFiles(FilePath filepath)
    {
        File file = filepath.toFile();
        String s;
        String as[];
        if (file == null)
        {
            s = null;
        } else
        {
            s = file.toString();
        }
        as = (new File(s)).list();
        if (as == null)
        {
            return Boolean.FALSE;
        } else
        {
            return LList.makeList(as, 0);
        }
    }

    public static boolean isAbsolutePath(Path path)
    {
        return path.isAbsolute();
    }

    public static boolean isFileDirectory(Path path)
    {
        return path.isDirectory();
    }

    public static boolean isFileExists(Path path)
    {
        return path.exists();
    }

    public static boolean isFileReadable(FilePath filepath)
    {
        return filepath.toFile().canRead();
    }

    public static boolean isFileWritable(FilePath filepath)
    {
        return filepath.toFile().canWrite();
    }

    public static boolean isFilepath(Object obj)
    {
        return obj instanceof FilePath;
    }

    public static boolean isPath(Object obj)
    {
        return obj instanceof Path;
    }

    public static FilePath makeTemporaryFile()
    {
        return makeTemporaryFile("kawa~d.tmp");
    }

    public static FilePath makeTemporaryFile(CharSequence charsequence)
    {
        return FilePath.makeFilePath(FileUtils.createTempFile(charsequence.toString()));
    }

    public static Object pathAuthority(Path path)
    {
        Object obj = path.getAuthority();
        if (obj == null)
        {
            obj = Boolean.FALSE;
        }
        return obj;
    }

    public static Object pathDirectory(Path path)
    {
        Path path1 = path.getDirectory();
        if (path1 == null)
        {
            return Boolean.FALSE;
        } else
        {
            return path1.toString();
        }
    }

    public static Object pathExtension(Path path)
    {
        Object obj = path.getExtension();
        if (obj == null)
        {
            obj = Boolean.FALSE;
        }
        return obj;
    }

    public static Object pathFile(Path path)
    {
        Object obj = path.getPath();
        if (obj == null)
        {
            obj = Boolean.FALSE;
        }
        return obj;
    }

    public static Object pathFragment(Path path)
    {
        Object obj = path.getFragment();
        if (obj == null)
        {
            obj = Boolean.FALSE;
        }
        return obj;
    }

    public static String pathHost(Path path)
    {
        return path.getHost();
    }

    public static Object pathLast(Path path)
    {
        Object obj = path.getLast();
        if (obj == null)
        {
            obj = Boolean.FALSE;
        }
        return obj;
    }

    public static Object pathParent(Path path)
    {
        Path path1 = path.getParent();
        if (path1 == null)
        {
            return Boolean.FALSE;
        } else
        {
            return path1.toString();
        }
    }

    public static int pathPort(Path path)
    {
        return path.getPort();
    }

    public static Object pathQuery(Path path)
    {
        Object obj = path.getQuery();
        if (obj == null)
        {
            obj = Boolean.FALSE;
        }
        return obj;
    }

    public static Object pathScheme(Path path)
    {
        Object obj = path.getScheme();
        if (obj == null)
        {
            obj = Boolean.FALSE;
        }
        return obj;
    }

    public static Object pathUserInfo(Path path)
    {
        Object obj = path.getUserInfo();
        if (obj == null)
        {
            obj = Boolean.FALSE;
        }
        return obj;
    }

    public static boolean renameFile(FilePath filepath, FilePath filepath1)
    {
        return filepath.toFile().renameTo(filepath1.toFile());
    }

    public static Path resolveUri(Path path, Path path1)
    {
        return path1.resolve(path);
    }

    public static String systemTmpdir()
    {
        String s = System.getProperty("java.io.tmpdir");
        if (s != null)
        {
            return s;
        }
        if (IsEqual.apply($PcFileSeparator(), "\\"))
        {
            return "C:\\temp";
        } else
        {
            return "/tmp";
        }
    }

    public Object apply0(ModuleMethod modulemethod)
    {
        switch (modulemethod.selector)
        {
        case 29: // '\035'
        default:
            return super.apply0(modulemethod);

        case 27: // '\033'
            return $PcFileSeparator();

        case 28: // '\034'
            return systemTmpdir();

        case 30: // '\036'
            return makeTemporaryFile();
        }
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        case 22: // '\026'
        case 23: // '\027'
        case 27: // '\033'
        case 28: // '\034'
        case 29: // '\035'
        default:
            return super.apply1(modulemethod, obj);

        case 1: // '\001'
            if (isPath(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 2: // '\002'
            if (isFilepath(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 3: // '\003'
            if (URI$Qu(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 4: // '\004'
            CharSequence charsequence;
            FilePath filepath;
            FilePath filepath1;
            FilePath filepath2;
            FilePath filepath3;
            FilePath filepath4;
            Path path;
            Path path1;
            Path path2;
            Path path3;
            Path path4;
            Path path5;
            Path path6;
            Path path7;
            Path path8;
            Path path9;
            Path path10;
            Path path11;
            Path path12;
            Path path13;
            Path path14;
            try
            {
                path14 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception20)
            {
                throw new WrongType(classcastexception20, "absolute-path?", 1, obj);
            }
            if (isAbsolutePath(path14))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 5: // '\005'
            try
            {
                path13 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception19)
            {
                throw new WrongType(classcastexception19, "path-scheme", 1, obj);
            }
            return pathScheme(path13);

        case 6: // '\006'
            try
            {
                path12 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception18)
            {
                throw new WrongType(classcastexception18, "path-authority", 1, obj);
            }
            return pathAuthority(path12);

        case 7: // '\007'
            try
            {
                path11 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception17)
            {
                throw new WrongType(classcastexception17, "path-user-info", 1, obj);
            }
            return pathUserInfo(path11);

        case 8: // '\b'
            try
            {
                path10 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception16)
            {
                throw new WrongType(classcastexception16, "path-host", 1, obj);
            }
            return pathHost(path10);

        case 9: // '\t'
            try
            {
                path9 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception15)
            {
                throw new WrongType(classcastexception15, "path-file", 1, obj);
            }
            return pathFile(path9);

        case 10: // '\n'
            try
            {
                path8 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception14)
            {
                throw new WrongType(classcastexception14, "path-directory", 1, obj);
            }
            return pathDirectory(path8);

        case 11: // '\013'
            try
            {
                path7 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception13)
            {
                throw new WrongType(classcastexception13, "path-parent", 1, obj);
            }
            return pathParent(path7);

        case 12: // '\f'
            try
            {
                path6 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception12)
            {
                throw new WrongType(classcastexception12, "path-last", 1, obj);
            }
            return pathLast(path6);

        case 13: // '\r'
            try
            {
                path5 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "path-extension", 1, obj);
            }
            return pathExtension(path5);

        case 14: // '\016'
            try
            {
                path4 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "path-port", 1, obj);
            }
            return Integer.valueOf(pathPort(path4));

        case 15: // '\017'
            try
            {
                path3 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "path-query", 1, obj);
            }
            return pathQuery(path3);

        case 16: // '\020'
            try
            {
                path2 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "path-fragment", 1, obj);
            }
            return pathFragment(path2);

        case 17: // '\021'
            try
            {
                path1 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "file-exists?", 1, obj);
            }
            if (isFileExists(path1))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 18: // '\022'
            try
            {
                path = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "file-directory?", 1, obj);
            }
            if (isFileDirectory(path))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 19: // '\023'
            try
            {
                filepath4 = FilePath.makeFilePath(obj);
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "file-readable?", 1, obj);
            }
            if (isFileReadable(filepath4))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 20: // '\024'
            try
            {
                filepath3 = FilePath.makeFilePath(obj);
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "file-writable?", 1, obj);
            }
            if (isFileWritable(filepath3))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 21: // '\025'
            try
            {
                filepath2 = FilePath.makeFilePath(obj);
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "delete-file", 1, obj);
            }
            deleteFile(filepath2);
            return Values.empty;

        case 24: // '\030'
            try
            {
                filepath1 = FilePath.makeFilePath(obj);
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "create-directory", 1, obj);
            }
            if (createDirectory(filepath1))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 25: // '\031'
            try
            {
                filepath = FilePath.makeFilePath(obj);
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "directory-files", 1, obj);
            }
            return directoryFiles(filepath);

        case 26: // '\032'
            return $To$Pathname(obj);

        case 30: // '\036'
            break;
        }
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "make-temporary-file", 1, obj);
        }
        return makeTemporaryFile(charsequence);
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 22: // '\026'
            Path path;
            Path path1;
            Path path2;
            Path path3;
            FilePath filepath;
            FilePath filepath1;
            try
            {
                filepath = FilePath.makeFilePath(obj);
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "rename-file", 1, obj);
            }
            try
            {
                filepath1 = FilePath.makeFilePath(obj1);
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "rename-file", 2, obj1);
            }
            if (renameFile(filepath, filepath1))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 23: // '\027'
            try
            {
                path2 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "copy-file", 1, obj);
            }
            try
            {
                path3 = Path.valueOf(obj1);
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "copy-file", 2, obj1);
            }
            copyFile(path2, path3);
            return Values.empty;

        case 29: // '\035'
            break;
        }
        try
        {
            path = Path.valueOf(obj);
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "resolve-uri", 1, obj);
        }
        try
        {
            path1 = Path.valueOf(obj1);
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "resolve-uri", 2, obj1);
        }
        return resolveUri(path, path1);
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 29: // '\035'
        default:
            return super.match0(modulemethod, callcontext);

        case 30: // '\036'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 28: // '\034'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 27: // '\033'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 22: // '\026'
        case 23: // '\027'
        case 27: // '\033'
        case 28: // '\034'
        case 29: // '\035'
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 30: // '\036'
            if (obj instanceof CharSequence)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 26: // '\032'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 25: // '\031'
            if (FilePath.coerceToFilePathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 24: // '\030'
            if (FilePath.coerceToFilePathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 21: // '\025'
            if (FilePath.coerceToFilePathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 20: // '\024'
            if (FilePath.coerceToFilePathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 19: // '\023'
            if (FilePath.coerceToFilePathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 18: // '\022'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 17: // '\021'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 16: // '\020'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 15: // '\017'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 14: // '\016'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 13: // '\r'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 12: // '\f'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 11: // '\013'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 10: // '\n'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 9: // '\t'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 8: // '\b'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 7: // '\007'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 6: // '\006'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 5: // '\005'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 4: // '\004'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 3: // '\003'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 2: // '\002'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 1: // '\001'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
        }
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match2(modulemethod, obj, obj1, callcontext);

        case 29: // '\035'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                if (Path.coerceToPathOrNull(obj1) != null)
                {
                    callcontext.value2 = obj1;
                    callcontext.proc = modulemethod;
                    callcontext.pc = 2;
                    return 0;
                } else
                {
                    return 0xfff40002;
                }
            } else
            {
                return 0xfff40001;
            }

        case 23: // '\027'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                if (Path.coerceToPathOrNull(obj1) != null)
                {
                    callcontext.value2 = obj1;
                    callcontext.proc = modulemethod;
                    callcontext.pc = 2;
                    return 0;
                } else
                {
                    return 0xfff40002;
                }
            } else
            {
                return 0xfff40001;
            }

        case 22: // '\026'
            break;
        }
        if (FilePath.coerceToFilePathOrNull(obj) != null)
        {
            callcontext.value1 = obj;
            if (FilePath.coerceToFilePathOrNull(obj1) != null)
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40002;
            }
        } else
        {
            return 0xfff40001;
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
    }

    static 
    {
        Lit29 = (SimpleSymbol)(new SimpleSymbol("make-temporary-file")).readResolve();
        Lit28 = (SimpleSymbol)(new SimpleSymbol("resolve-uri")).readResolve();
        Lit27 = (SimpleSymbol)(new SimpleSymbol("system-tmpdir")).readResolve();
        Lit26 = (SimpleSymbol)(new SimpleSymbol("%file-separator")).readResolve();
        Lit25 = (SimpleSymbol)(new SimpleSymbol("->pathname")).readResolve();
        Lit24 = (SimpleSymbol)(new SimpleSymbol("directory-files")).readResolve();
        Lit23 = (SimpleSymbol)(new SimpleSymbol("create-directory")).readResolve();
        Lit22 = (SimpleSymbol)(new SimpleSymbol("copy-file")).readResolve();
        Lit21 = (SimpleSymbol)(new SimpleSymbol("rename-file")).readResolve();
        Lit20 = (SimpleSymbol)(new SimpleSymbol("delete-file")).readResolve();
        Lit19 = (SimpleSymbol)(new SimpleSymbol("file-writable?")).readResolve();
        Lit18 = (SimpleSymbol)(new SimpleSymbol("file-readable?")).readResolve();
        Lit17 = (SimpleSymbol)(new SimpleSymbol("file-directory?")).readResolve();
        Lit16 = (SimpleSymbol)(new SimpleSymbol("file-exists?")).readResolve();
        Lit15 = (SimpleSymbol)(new SimpleSymbol("path-fragment")).readResolve();
        Lit14 = (SimpleSymbol)(new SimpleSymbol("path-query")).readResolve();
        Lit13 = (SimpleSymbol)(new SimpleSymbol("path-port")).readResolve();
        Lit12 = (SimpleSymbol)(new SimpleSymbol("path-extension")).readResolve();
        Lit11 = (SimpleSymbol)(new SimpleSymbol("path-last")).readResolve();
        Lit10 = (SimpleSymbol)(new SimpleSymbol("path-parent")).readResolve();
        Lit9 = (SimpleSymbol)(new SimpleSymbol("path-directory")).readResolve();
        Lit8 = (SimpleSymbol)(new SimpleSymbol("path-file")).readResolve();
        Lit7 = (SimpleSymbol)(new SimpleSymbol("path-host")).readResolve();
        Lit6 = (SimpleSymbol)(new SimpleSymbol("path-user-info")).readResolve();
        Lit5 = (SimpleSymbol)(new SimpleSymbol("path-authority")).readResolve();
        Lit4 = (SimpleSymbol)(new SimpleSymbol("path-scheme")).readResolve();
        Lit3 = (SimpleSymbol)(new SimpleSymbol("absolute-path?")).readResolve();
        Lit2 = (SimpleSymbol)(new SimpleSymbol("URI?")).readResolve();
        Lit1 = (SimpleSymbol)(new SimpleSymbol("filepath?")).readResolve();
        Lit0 = (SimpleSymbol)(new SimpleSymbol("path?")).readResolve();
        $instance = new files();
        files files1 = $instance;
        path$Qu = new ModuleMethod(files1, 1, Lit0, 4097);
        filepath$Qu = new ModuleMethod(files1, 2, Lit1, 4097);
        URI$Qu = new ModuleMethod(files1, 3, Lit2, 4097);
        absolute$Mnpath$Qu = new ModuleMethod(files1, 4, Lit3, 4097);
        path$Mnscheme = new ModuleMethod(files1, 5, Lit4, 4097);
        path$Mnauthority = new ModuleMethod(files1, 6, Lit5, 4097);
        path$Mnuser$Mninfo = new ModuleMethod(files1, 7, Lit6, 4097);
        path$Mnhost = new ModuleMethod(files1, 8, Lit7, 4097);
        path$Mnfile = new ModuleMethod(files1, 9, Lit8, 4097);
        path$Mndirectory = new ModuleMethod(files1, 10, Lit9, 4097);
        path$Mnparent = new ModuleMethod(files1, 11, Lit10, 4097);
        path$Mnlast = new ModuleMethod(files1, 12, Lit11, 4097);
        path$Mnextension = new ModuleMethod(files1, 13, Lit12, 4097);
        path$Mnport = new ModuleMethod(files1, 14, Lit13, 4097);
        path$Mnquery = new ModuleMethod(files1, 15, Lit14, 4097);
        path$Mnfragment = new ModuleMethod(files1, 16, Lit15, 4097);
        file$Mnexists$Qu = new ModuleMethod(files1, 17, Lit16, 4097);
        file$Mndirectory$Qu = new ModuleMethod(files1, 18, Lit17, 4097);
        file$Mnreadable$Qu = new ModuleMethod(files1, 19, Lit18, 4097);
        file$Mnwritable$Qu = new ModuleMethod(files1, 20, Lit19, 4097);
        delete$Mnfile = new ModuleMethod(files1, 21, Lit20, 4097);
        rename$Mnfile = new ModuleMethod(files1, 22, Lit21, 8194);
        copy$Mnfile = new ModuleMethod(files1, 23, Lit22, 8194);
        create$Mndirectory = new ModuleMethod(files1, 24, Lit23, 4097);
        directory$Mnfiles = new ModuleMethod(files1, 25, Lit24, 4097);
        $Mn$Grpathname = new ModuleMethod(files1, 26, Lit25, 4097);
        $Pcfile$Mnseparator = new ModuleMethod(files1, 27, Lit26, 0);
        system$Mntmpdir = new ModuleMethod(files1, 28, Lit27, 0);
        resolve$Mnuri = new ModuleMethod(files1, 29, Lit28, 8194);
        make$Mntemporary$Mnfile = new ModuleMethod(files1, 30, Lit29, 4096);
        $instance.run();
    }
}
