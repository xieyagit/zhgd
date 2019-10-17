package com.hujiang.framework.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@Configuration
public class JmsConfig {

    public final static String TSP_PERSONNEL_QUEUE = "TSP_PERSONNEL_QUEUE";

    public final static String ELEVATOR_PERSONNEL_QUEUE = "ELEVATOR_PERSONNEL_QUEUE";

    public final static String CRANE_PERSONNEL_QUEUE = "CRANE_PERSONNEL_QUEUE";

    public final static String TEMPERATURE_PERSONNEL_QUEUE = "TEMPERATURE_PERSONNEL_QUEUE";

    //定义人才安居的扬尘设备存放消息的队列
    @Bean(value="tspPersonnelQueue")
    public Queue tspPersonnelQueue() {
        return new ActiveMQQueue(JmsConfig.TSP_PERSONNEL_QUEUE);
    }

    //定义人才安居的升降机设备存放消息的队列
    @Bean(value="elevatorPersonnelQueue")
    public Queue elevatorPersonnelQueue() {
        return new ActiveMQQueue(JmsConfig.ELEVATOR_PERSONNEL_QUEUE);
    }

    //定义人才安居的塔吊设备存放消息的队列
    @Bean(value="cranePersonnelQueue")
    public Queue cranePersonnelQueue() {
        return new ActiveMQQueue(JmsConfig.CRANE_PERSONNEL_QUEUE);
    }

    //定义人才安居的电箱设备存放消息的队列
    @Bean(value="temperaturePersonnelQueue")
    public Queue temperaturePersonnelQueue() {
        return new ActiveMQQueue(JmsConfig.TEMPERATURE_PERSONNEL_QUEUE);
    }

    @Value("${spring.activemq.user}")
    private String usrName;

    @Value("${spring.activemq.password}")
    private  String password;

    @Value("${spring.activemq.broker-url}")
    private  String brokerUrl;

    @Bean("connectionFactory")
    public ActiveMQConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(usrName, password, brokerUrl);
    }

    @Bean
    JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPriority(999);
        return jmsTemplate;
    }

    @Bean(value="jmsMessagingTemplate")
    JmsMessagingTemplate jmsMessagingTemplate(JmsTemplate jmsTemplate) {
        JmsMessagingTemplate messagingTemplate = new JmsMessagingTemplate(jmsTemplate);
        return messagingTemplate;
    }
}
