package com.igbeok.jms.rabbitmq.pubsub.publisher;

import com.rabbitmq.client.ConnectionFactory;

public class EmitLogRicoGuest extends AbstractEmitLog {

	@Override
	protected void setConnectionFactory(ConnectionFactory connectionFactory) {
		connectionFactory.setVirtualHost("rico");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
	}

	public static void main(String[] args) throws Exception {
		EmitLogRicoGuest sender = new EmitLogRicoGuest();
		sender.send(args);
	}

}