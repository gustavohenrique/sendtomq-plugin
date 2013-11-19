package org.jvnet.hudson.plugins.sendtomq.util;

import hudson.model.Hudson;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.jvnet.hudson.plugins.sendtomq.domain.PluginConfiguration;

import com.thoughtworks.xstream.XStream;

public final class ArquivoUtil {

    public static final XStream XStream = getXStream();
    public static final String SEPARADOR_LINHA = "\r\n"; //System.getProperty("line.separator", "\n");
    public static final String SEPARADOR_ARQUIVO = System.getProperty("file.separator", "/");
    private static final String ARQUIVO_CONFIGURACAO = "doc_qualidade_config.xml";

    public static String getArquivoConfiguracaoPath() {
        return Hudson.getInstance().getRootDir() + SEPARADOR_ARQUIVO + ARQUIVO_CONFIGURACAO;
    }
    
    public static OutputStreamWriter getArquivoDados() throws Exception {
        FileOutputStream outputStream = new FileOutputStream(getArquivoConfiguracaoPath());
        return new OutputStreamWriter(outputStream, "UTF-8");
    }
    
    private static XStream getXStream() {
        final XStream xstream = Hudson.XSTREAM;
        xstream.alias("configuracaoQualidade", PluginConfiguration.class);
        xstream.autodetectAnnotations(true);
        return xstream;
    }

}
