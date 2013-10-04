package com.igbeok.jms.rabbitmq.pubsub.publisher;

import org.apache.commons.lang3.StringUtils;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public abstract class AbstractEmitLog {

	private static final String EXCHANGE_NAME = "logs";

	protected final void send(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		setConnectionFactory(factory);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

		String message = joinStrings(args);
		if (StringUtils.isBlank(message)) {
			message = "info: " + getClass().getSimpleName() + "!";
		}
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
		System.out.println(" [x] " + getClass().getSimpleName() + " Sent '" + message + "'");

		channel.close();
		connection.close();
	}

	protected abstract void setConnectionFactory(ConnectionFactory connectionFactory);

	public static String joinStrings(String[] strings) {
		int length = strings.length;
		if (length == 0)
			return "";
		StringBuilder words = new StringBuilder(strings[0]);
		for (int i = 1; i < length; i++) {
			words.append(", ").append(strings[i]);
		}
		return words.toString();
	}
}