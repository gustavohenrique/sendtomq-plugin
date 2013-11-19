package org.jvnet.hudson.plugins.sendtomq.dao;

import hudson.model.Run;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.fusesource.stomp.jms.StompJmsConnectionFactory;
import org.fusesource.stomp.jms.StompJmsDestination;
import org.jvnet.hudson.plugins.sendtomq.domain.PluginConfiguration;


public class ActiveMQDaoImpl implements MQDao {
    
    public void publisher(final PluginConfiguration pluginConfiguration, final Run build) throws Exception {
        Connection connection = null;
        try {
            StompJmsConnectionFactory factory = new StompJmsConnectionFactory();
            factory.setBrokerURI("tcp://" + pluginConfiguration.host + ":" + pluginConfiguration.port);
    
            connection = factory.createConnection(pluginConfiguration.user, pluginConfiguration.password);
            connection.start();
            Session session = connection.createSession(false, pluginConfiguration.ack);
            MessageProducer producer = session.createProducer(new StompJmsDestination(pluginConfiguration.queue));
            producer.setDeliveryMode(pluginConfiguration.deliveryMode);
            
            producer.send(session.createTextMessage(getMessage(build)));
        }
        finally {
            connection.close();
        }
    }

    private String getMessage(final Run build) {
        StringBuffer stb = new StringBuffer();
        stb.append("{\"job\":\"" + build.getParent().getName() + "\",[{\"build\":\"" + build.getId() + "\"}]}");
        return stb.toString();
    }
}
