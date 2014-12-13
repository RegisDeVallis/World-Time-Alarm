// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.servlet;

import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleContext;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleManager;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;

// Referenced classes of package gnu.kawa.servlet:
//            HttpRequestContext, ServletPrinter

public class KawaAutoHandler
{

    static final String MODULE_MAP_ATTRIBUTE = "gnu.kawa.module-map";

    public KawaAutoHandler()
    {
    }

    public static Object getModule(HttpRequestContext httprequestcontext, CallContext callcontext, boolean flag)
        throws Exception
    {
        String s;
        Hashtable hashtable;
        ModuleContext modulecontext;
        ModuleInfo moduleinfo;
        long l;
        ModuleManager modulemanager;
        URL url;
        String s1;
        String s4;
        int k;
        s = httprequestcontext.getRequestPath().substring(-1 + httprequestcontext.getContextPath().length());
        hashtable = (Hashtable)httprequestcontext.getAttribute("gnu.kawa.module-map");
        if (hashtable == null)
        {
            hashtable = new Hashtable();
            httprequestcontext.setAttribute("gnu.kawa.module-map", hashtable);
        }
        modulecontext = (ModuleContext)httprequestcontext.getAttribute("gnu.kawa.module-context");
        if (modulecontext == null)
        {
            modulecontext = ModuleContext.getContext();
        }
        modulecontext.addFlags(ModuleContext.IN_HTTP_SERVER);
        if (httprequestcontext.getClass().getName().endsWith("KawaServlet$Context"))
        {
            modulecontext.addFlags(ModuleContext.IN_SERVLET);
        }
        moduleinfo = (ModuleInfo)hashtable.get(s);
        l = System.currentTimeMillis();
        modulemanager = modulecontext.getManager();
        if (moduleinfo != null && l - moduleinfo.lastCheckedTime < modulemanager.lastModifiedCacheTime)
        {
            return modulecontext.findInstance(moduleinfo);
        }
        int i = s.length();
        byte abyte2[];
        OutputStream outputstream2;
        if (i == 0 || s.charAt(i - 1) == '/')
        {
            url = null;
        } else
        {
            url = httprequestcontext.getResourceURL(s);
        }
        s1 = s;
        if (url != null)
        {
            break MISSING_BLOCK_LABEL_366;
        }
        s4 = s;
_L4:
        k = s4.lastIndexOf('/');
        if (k >= 0) goto _L2; else goto _L1
_L1:
        if (url == null)
        {
            abyte2 = (new StringBuilder()).append("The requested URL ").append(s).append(" was not found on this server.").append(" res/:").append(httprequestcontext.getResourceURL("/")).append("\r\n").toString().getBytes();
            httprequestcontext.sendResponseHeaders(404, null, abyte2.length);
            outputstream2 = httprequestcontext.getResponseStream();
            try
            {
                outputstream2.write(abyte2);
            }
            catch (IOException ioexception1)
            {
                RuntimeException runtimeexception1 = new RuntimeException(ioexception1);
                throw runtimeexception1;
            }
            return null;
        }
        break MISSING_BLOCK_LABEL_392;
_L2:
        s4 = s4.substring(0, k);
        s1 = (new StringBuilder()).append(s4).append("/+default+").toString();
        url = httprequestcontext.getResourceURL(s1);
        if (url == null) goto _L4; else goto _L3
_L3:
        httprequestcontext.setScriptAndLocalPath(s.substring(1, k + 1), s.substring(k + 1));
          goto _L1
        httprequestcontext.setScriptAndLocalPath(s, "");
          goto _L1
        SourceMessages sourcemessages;
        Compilation compilation;
        String s2 = url.toExternalForm();
        if (moduleinfo == null || !s2.equals(moduleinfo.getSourceAbsPathname()))
        {
            moduleinfo = modulemanager.findWithURL(url);
        }
        if (moduleinfo.checkCurrent(modulemanager, l))
        {
            return modulecontext.findInstance(moduleinfo);
        }
        hashtable.put(s, moduleinfo);
        Path path = moduleinfo.getSourceAbsPath();
        Object obj = path.openInputStream();
        if (!(obj instanceof BufferedInputStream))
        {
            BufferedInputStream bufferedinputstream = new BufferedInputStream(((InputStream) (obj)));
            obj = bufferedinputstream;
        }
        Language language = Language.getInstanceFromFilenameExtension(s);
        InPort inport;
        boolean flag1;
        String s3;
        Compilation compilation1;
        if (language != null)
        {
            httprequestcontext.log((new StringBuilder()).append("Compile ").append(s).append(" - a ").append(language.getName()).append(" source file (based on extension)").toString());
        } else
        {
            language = Language.detect(((InputStream) (obj)));
            if (language != null)
            {
                httprequestcontext.log((new StringBuilder()).append("Compile ").append(s).append(" - a ").append(language.getName()).append(" source file (detected from content)").toString());
            } else
            {
                if (s != s1)
                {
                    byte abyte1[] = (new StringBuilder()).append("The requested URL ").append(s).append(" was not found on this server.").append(" upath=").append(s1).append(".\r\n").toString().getBytes();
                    httprequestcontext.sendResponseHeaders(404, null, abyte1.length);
                    OutputStream outputstream1 = httprequestcontext.getResponseStream();
                    try
                    {
                        outputstream1.write(abyte1);
                    }
                    catch (IOException ioexception)
                    {
                        RuntimeException runtimeexception = new RuntimeException(ioexception);
                        throw runtimeexception;
                    }
                    return null;
                }
                httprequestcontext.sendResponseHeaders(200, null, path.getContentLength());
                OutputStream outputstream = httprequestcontext.getResponseStream();
                byte abyte0[] = new byte[4096];
                do
                {
                    int j = ((InputStream) (obj)).read(abyte0);
                    if (j < 0)
                    {
                        ((InputStream) (obj)).close();
                        outputstream.close();
                        return null;
                    }
                    outputstream.write(abyte0, 0, j);
                } while (true);
            }
        }
        inport = new InPort(((InputStream) (obj)), path);
        Language.setCurrentLanguage(language);
        sourcemessages = new SourceMessages();
        compilation1 = language.parse(inport, sourcemessages, 9, moduleinfo);
        compilation = compilation1;
_L5:
        flag1 = sourcemessages.seenErrors();
        Class class1 = null;
        if (!flag1)
        {
            compilation.getModule();
            class1 = (Class)ModuleExp.evalModule1(Environment.getCurrent(), compilation, url, null);
        }
        SyntaxException syntaxexception;
        if (sourcemessages.seenErrors())
        {
            s3 = (new StringBuilder()).append("script syntax error:\n").append(sourcemessages.toString(20)).toString();
            ((ServletPrinter)callcontext.consumer).addHeader("Content-type", "text/plain");
            httprequestcontext.sendResponseHeaders(500, "Syntax errors", -1L);
            callcontext.consumer.write(s3);
            moduleinfo.cleanupAfterCompilation();
            return null;
        } else
        {
            moduleinfo.setModuleClass(class1);
            return modulecontext.findInstance(moduleinfo);
        }
        syntaxexception;
        if (syntaxexception.getMessages() != sourcemessages)
        {
            throw syntaxexception;
        }
        compilation = null;
          goto _L5
    }

    public static void run(HttpRequestContext httprequestcontext, CallContext callcontext)
        throws Throwable
    {
        boolean flag;
        Object obj;
        if (httprequestcontext.getRequestParameter("qexo-save-class") != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj = getModule(httprequestcontext, callcontext, flag);
        if (obj instanceof ModuleBody)
        {
            ((ModuleBody)obj).run(callcontext);
        }
    }
}
