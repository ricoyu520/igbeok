package com.igbeok.jms.rabbitmq.pubsub.subscriber;

import com.rabbitmq.client.ConnectionFactory;

public class ReceiveLogsSlashRico extends AbstractReceiveLogs {

	@Override
	protected void setConnectionFactory(ConnectionFactory connectionFactory) {
		connectionFactory.setVirtualHost("/");
		connectionFactory.setUsername("rico");
		connectionFactory.setPassword("123456");

	}

	public static void main(String[] argv) throws Exception {
		ReceiveLogsSlashRico receiver = new ReceiveLogsSlashRico();
		receiver.receive();
	}
}