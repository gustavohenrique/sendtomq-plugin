package org.jvnet.hudson.plugins.sendtomq.plugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jvnet.hudson.plugins.sendtomq.util.HtmlUnitUtil;
import org.jvnet.hudson.test.HudsonTestCase;

import com.gargoylesoftware.htmlunit.html.HtmlPage;


public abstract class AbstractHudsonTestCase extends HudsonTestCase {

    protected HtmlPage page;
    
    protected String br = "\r\n";
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        final Logger logger = Logger.getLogger ("");
        logger.setLevel(Level.OFF);
    }

    protected HtmlUnitUtil in(String id) {
        return HtmlUnitUtil.getInstance(page, id);
    }
    
    protected HtmlUnitUtil element(String xpathExpr) {
        return HtmlUnitUtil.getInstanceByXpath(page, xpathExpr);
    }
    
    protected String getXmlContent(String path) {
        final File file = new File(path);
        final StringBuilder contents = new StringBuilder();
        try {
            final BufferedReader input = new BufferedReader(new FileReader(file));
            try {
                String line = null;
                while ((line = input.readLine()) != null) {
                    contents.append(line);
                    contents.append(br);
                }
            } finally {
                input.close();
            }
        }
        catch (final IOException e) {
            e.printStackTrace();
        }

        return contents.toString();
    }

    protected void createFile(String path, String conteudo) throws IOException {
        final Writer out = new OutputStreamWriter(new FileOutputStream(path));
        try {
            out.write(conteudo);
        }
        finally {
            out.close();
        }
    }
}
