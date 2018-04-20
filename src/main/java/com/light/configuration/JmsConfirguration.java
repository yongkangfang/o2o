package com.light.configuration;


import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfirguration {
	
	//负责创建队列和主题
	public static final String QUEUE_NAME = "activemq_queue";
	
	public static final String TOPIC_NAME = "activemq_topic";
	
	@Bean
	public Queue queue() {
		return new ActiveMQQueue(QUEUE_NAME);
	}
	
	@Bean
	public Topic topic() {
		return new ActiveMQTopic(TOPIC_NAME);
	}
}
