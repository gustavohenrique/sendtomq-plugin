package org.jvnet.hudson.plugins.sendtomq.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

    private static final String FORMATO_DATA = "dd/MM/yyyy";

    public static Long getTimestamp(String data) {
        if (data != null && ! data.equals("")) {
            try {
                SimpleDateFormat df = new SimpleDateFormat(FORMATO_DATA);
                return (df.parse(data).getTime());
            }
            catch (Exception e) {}
        }
        return 0L;
    }
    
    public static String getDataFormatada(Long dataVerificacaoTimestamp) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(FORMATO_DATA);
            return df.format(new Date(dataVerificacaoTimestamp));
        }
        catch (Exception e) {}
        
        return "";
    }
}
