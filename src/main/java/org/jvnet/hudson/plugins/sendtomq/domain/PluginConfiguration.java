package org.jvnet.hudson.plugins.sendtomq.domain;

import org.kohsuke.stapler.DataBoundConstructor;


public class PluginConfiguration {

    public String user;
    public String password;
    public String host;
    public String port;
    public String queue;
    public int deliveryMode;
    public int ack;
    
    public PluginConfiguration() {}
    
    @DataBoundConstructor
    public PluginConfiguration(final String user, final String password,
            final String host, final String port, final String queue,
            final int deliveryMode, final int ack) {

        this.user = user;
        this.password = password;
        this.host = host;
        this.port = port;
        this.queue = queue;
        this.deliveryMode = deliveryMode;
        this.ack = ack;
    }
}
