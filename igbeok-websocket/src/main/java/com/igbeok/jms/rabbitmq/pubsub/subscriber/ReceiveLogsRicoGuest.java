package com.igbeok.jms.rabbitmq.pubsub.subscriber;

import com.rabbitmq.client.ConnectionFactory;

public class ReceiveLogsRicoGuest extends AbstractReceiveLogs {

	@Override
	protected void setConnectionFactory(ConnectionFactory connectionFactory) {
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		connectionFactory.setVirtualHost("rico");

	}

	public static void main(String[] argv) throws Exception {
		ReceiveLogsRicoGuest receiver = new ReceiveLogsRicoGuest();
		receiver.receive();
	}
}