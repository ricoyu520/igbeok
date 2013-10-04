package com.igbeok.jms.rabbitmq.pubsub.publisher;

import com.rabbitmq.client.ConnectionFactory;

public class EmitLogRicoRico extends AbstractEmitLog {

	@Override
	protected void setConnectionFactory(ConnectionFactory connectionFactory) {
		connectionFactory.setUsername("rico");
		connectionFactory.setPassword("123456");
		connectionFactory.setVirtualHost("rico");
	}

	public static void main(String[] args) throws Exception {
		EmitLogRicoRico sender = new EmitLogRicoRico();
		sender.send(args);
	}

}