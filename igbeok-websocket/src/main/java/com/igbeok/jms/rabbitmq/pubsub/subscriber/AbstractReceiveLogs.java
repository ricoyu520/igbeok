package com.igbeok.jms.rabbitmq.pubsub.subscriber;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public abstract class AbstractReceiveLogs {

	private static final String EXCHANGE_NAME = "logs";

	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss sss");

	public final void receive() throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		setConnectionFactory(factory);

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, "");

		System.out.println(" [*] " + getClass().getSimpleName() + " Waiting for messages. To exit press CTRL+C");

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, true, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			//当没有消息时，consumer.nextDelivery()将wait， 所以下面的打印信息应该在有消息到来时才会打印。
			System.out.println("Message is coming!");
			
			String message = new String(delivery.getBody());

			System.out.println(FORMAT.format(new Date()) + " " + getClass().getSimpleName() + " Received '" + message
					+ "'");
		}
	}

	protected abstract void setConnectionFactory(ConnectionFactory connectionFactory);
}