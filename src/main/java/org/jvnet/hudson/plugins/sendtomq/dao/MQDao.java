package org.jvnet.hudson.plugins.sendtomq.dao;

import hudson.model.Run;

import org.jvnet.hudson.plugins.sendtomq.domain.PluginConfiguration;

public interface MQDao {

    public void publisher(final PluginConfiguration pluginConfiguration, final Run build) throws Exception;
}
