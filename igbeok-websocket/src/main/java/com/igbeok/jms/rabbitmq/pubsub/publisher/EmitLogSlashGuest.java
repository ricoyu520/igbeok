package com.igbeok.jms.rabbitmq.pubsub.publisher;

import com.rabbitmq.client.ConnectionFactory;

public class EmitLogSlashGuest extends AbstractEmitLog {

	public static void main(String[] args) throws Exception {
		EmitLogSlashGuest sender = new EmitLogSlashGuest();
		sender.send(args);
	}

	@Override
	protected void setConnectionFactory(ConnectionFactory connectionFactory) {
		connectionFactory.setHost("localhost");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		connectionFactory.setVirtualHost("/");
	}

}