package com.igbeok.jms.rabbitmq.pubsub.subscriber;

import com.rabbitmq.client.ConnectionFactory;

public class ReceiveLogsGuest extends AbstractReceiveLogs {

	@Override
	protected void setConnectionFactory(ConnectionFactory connectionFactory) {
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
	}

	public static void main(String[] argv) throws Exception {
		ReceiveLogsGuest receiver = new ReceiveLogsGuest();
		receiver.receive();
	}
}