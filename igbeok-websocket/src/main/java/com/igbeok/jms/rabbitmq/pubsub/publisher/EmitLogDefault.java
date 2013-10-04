package com.igbeok.jms.rabbitmq.pubsub.publisher;

import com.rabbitmq.client.ConnectionFactory;

public class EmitLogDefault extends AbstractEmitLog {

	public static void main(String[] args) throws Exception {
		EmitLogDefault sender = new EmitLogDefault();
		sender.send(args);
	}

	@Override
	protected void setConnectionFactory(ConnectionFactory connectionFactory) {
		// TODO Auto-generated method stub

	}
}