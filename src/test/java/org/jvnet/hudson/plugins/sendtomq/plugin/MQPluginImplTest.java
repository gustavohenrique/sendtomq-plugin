package org.jvnet.hudson.plugins.sendtomq.plugin;

import hudson.model.Hudson;

import org.jvnet.hudson.plugins.sendtomq.util.HtmlUnitUtil;


public class MQPluginImplTest extends AbstractHudsonTestCase {

    public void testSavePluginConfiguration() throws Exception {
        page = createWebClient().goTo("configure");
        
        in("txt_MQPlugin_host").type("localhost");
        in("txt_MQPlugin_port").type("61613");
        in("txt_MQPlugin_queue").type("/queue/test");
        in("txt_MQPlugin_user").type("admin");
        in("txt_MQPlugin_password").type("admin");
        in("sel_MQPlugin_deliveryMode").select("2");
        in("sel_MQPlugin_ack").select("2");
        
        HtmlUnitUtil.submit(page, "config", "//span[@name='Submit']/span/button");
        
        assertEquals("localhost", in("txt_MQPlugin_host").value());
        assertEquals("61613", in("txt_MQPlugin_port").value());
        assertEquals("/queue/test", in("txt_MQPlugin_queue").value());
        assertEquals("admin", in("txt_MQPlugin_user").value());
        assertEquals("2", in("sel_MQPlugin_deliveryMode").selectedValue());
        assertEquals("2", in("sel_MQPlugin_ack").selectedValue());
        
        String file = Hudson.getInstance().getRootPath() + System.getProperty("file.separator") + "SendToMQ.xml";
        String xml = getXmlContent(file);
        String expected = "<?xml version='1.0' encoding='UTF-8'?>" + br +
                "<org.jvnet.hudson.plugins.sendtomq.plugin.MQPluginImpl>" + br +
                "  <pluginConfiguration>" + br + 
                "    <user>admin</user>" + br +
                "    <password>admin</password>" + br +
                "    <host>localhost</host>" + br +
                "    <port>61613</port>" + br +
                "    <queue>/queue/test</queue>" + br +
                "    <deliveryMode>2</deliveryMode>" + br +
                "    <ack>2</ack>" + br +
                "  </pluginConfiguration>" + br +
                "</org.jvnet.hudson.plugins.sendtomq.plugin.MQPluginImpl>" + br;
        assertEquals(expected, xml);
    }
}

