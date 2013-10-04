package com.igbeok.jms.rabbitmq.pubsub.subscriber;

import com.rabbitmq.client.ConnectionFactory;

public class ReceiveLogsRicoRico extends AbstractReceiveLogs {

	@Override
	protected void setConnectionFactory(ConnectionFactory connectionFactory) {
		connectionFactory.setVirtualHost("rico");
		connectionFactory.setUsername("rico");
		connectionFactory.setPassword("123456");
	}

	public static void main(String[] argv) throws Exception {
		ReceiveLogsRicoRico receiver = new ReceiveLogsRicoRico();
		receiver.receive();
	}
}