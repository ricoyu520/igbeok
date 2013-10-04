package com.igbeok.jms.rabbitmq.pubsub.publisher;

import com.rabbitmq.client.ConnectionFactory;

public class EmitLogSlashRico extends AbstractEmitLog {

	public static void main(String[] args) throws Exception {
		EmitLogSlashRico sender = new EmitLogSlashRico();
		sender.send(args);
	}

	@Override
	protected void setConnectionFactory(ConnectionFactory connectionFactory) {
		connectionFactory.setHost("localhost");
		connectionFactory.setUsername("rico");
		connectionFactory.setPassword("123456");
		connectionFactory.setVirtualHost("/");
	}
}