package org.jvnet.hudson.plugins.sendtomq.plugin;

import hudson.Extension;
import hudson.Plugin;
import hudson.model.Descriptor.FormException;
import hudson.model.Result;
import hudson.model.TaskListener;
import hudson.model.Hudson;
import hudson.model.Run;
import hudson.model.listeners.RunListener;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;

import net.sf.json.JSONObject;

import org.jvnet.hudson.plugins.sendtomq.dao.ActiveMQDaoImpl;
import org.jvnet.hudson.plugins.sendtomq.dao.MQDao;
import org.jvnet.hudson.plugins.sendtomq.domain.PluginConfiguration;
import org.kohsuke.stapler.StaplerRequest;

public class MQPluginImpl extends Plugin {

    public PluginConfiguration pluginConfiguration;
    
    @Override
    public void start() throws Exception {
        super.start();
        load();
    }
    
    @Override
    public void configure(StaplerRequest request, JSONObject form) throws IOException, ServletException, FormException {
        super.configure(request, form);
        pluginConfiguration = request.bindJSON(PluginConfiguration.class, form.getJSONObject("pluginConfiguration"));
        save();
    }
    
    @Extension
    public static class BuildRunListener extends RunListener<Run> {
        public BuildRunListener() {
            super(Run.class);
        }

        @Override
        public void onCompleted(Run build, TaskListener listener) {
            PrintStream logger = listener.getLogger();
            logger.println("Sending data to MQ...");
            
            MQPluginImpl plugin = Hudson.getInstance().getPlugin(MQPluginImpl.class);
            Result result = build.getResult();
            if (plugin != null && result != Result.FAILURE && result != Result.ABORTED) {
                try {
                    MQDao dao = new ActiveMQDaoImpl();
                    dao.publisher(plugin.pluginConfiguration, build);
                    logger.println("... data sent!");
                }
                catch (Exception e) {
                    logger.println("[ERROR] " + e.getMessage());
                }
            }
        }
    }
}
