package com.igbeok.jms.rabbitmq.pubsub.subscriber;

import com.rabbitmq.client.ConnectionFactory;

public class ReceiveLogsDefault extends AbstractReceiveLogs{

	public static void main(String[] argv) throws Exception {
		ReceiveLogsDefault receiver = new ReceiveLogsDefault();
		receiver.receive();
	}

	@Override
	protected void setConnectionFactory(ConnectionFactory connectionFactory) {
		// TODO Auto-generated method stub
		
	}
}