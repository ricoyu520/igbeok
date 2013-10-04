package com.igbeok.jms.rabbitmq.pubsub.subscriber;

import com.rabbitmq.client.ConnectionFactory;

public class ReceiveLogsRico extends AbstractReceiveLogs {

	@Override
	protected void setConnectionFactory(ConnectionFactory connectionFactory) {
		connectionFactory.setUsername("rico");
		connectionFactory.setPassword("123456");

	}

	public static void main(String[] argv) throws Exception {
		ReceiveLogsRico receiver = new ReceiveLogsRico();
		receiver.receive();
	}
}