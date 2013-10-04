package com.igbeok.jms.rabbitmq.pubsub.subscriber;

import com.rabbitmq.client.ConnectionFactory;

public class ReceiveLogsSlashGuest extends AbstractReceiveLogs {

	@Override
	protected void setConnectionFactory(ConnectionFactory connectionFactory) {
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		connectionFactory.setVirtualHost("/");
	}

	private static final String EXCHANGE_NAME = "logs";

	public static void main(String[] argv) throws Exception {
		ReceiveLogsSlashGuest receiver = new ReceiveLogsSlashGuest();
		receiver.receive();
	}
}