package com.igbeok;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class RabbitMsgSender {
	private final String loginQueue = "universal_user_login_ip";
	private final String regQueue = "universal_user_register_ip";
	private final String rmqHost = "localhost";
	private final String rmqVirtualHost = "rico";
	private final String rmqUsername = "rico";
	private final String rmqPassword = "123456";
/*	private final String loginQueue = System.getProperty("rabbitmq.loginQueue","universal_user_login_ip");
	private final String regQueue = System.getProperty("rabbitmq.regQueue","universal_user_register_ip");
	private final String rmqHost = System.getProperty("rabbitmq.host","localhost");
	private final String rmqVirtualHost = System.getProperty("rabbitmq.vhost","leconline");
	private final String rmqUsername = System.getProperty("rabbitmq.username","fsikder");
	private final String rmqPassword = System.getProperty("rabbitmq.password","fsikder");
*/	
	private Channel objChannel;
	private Connection objConnection;
	
	public RabbitMsgSender(){
		try{
			System.out.println("Queue: " + rmqHost + ":" + rmqVirtualHost + "@" + rmqUsername);
		    ConnectionFactory factory = new ConnectionFactory();
			factory.setUsername(rmqUsername);
			factory.setPassword(rmqPassword);
			factory.setVirtualHost(rmqVirtualHost);
			factory.setHost(rmqHost);
			this.objConnection = factory.newConnection();
		    this.objChannel = objConnection.createChannel();		    
		    System.out.println("Sending message in : " + factory.getHost());
	 	}catch(Exception ex){
	 		ex.printStackTrace();
	 	}
	}
	
	/**
	 * This method send message to the message queue
	 * @param message
	 */
	public void sendMessage_login(String message){
	 	try{
	 		this.objChannel.basicPublish( "", this.loginQueue, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());		    
	 	}catch(Exception ex){
	 		ex.printStackTrace();
	 	}
	}
	
	/**
	 * This method send message to the message queue
	 * @param message
	 */
	public void sendMessage_reg(String message){
	 	try{
	 		this.objChannel.basicPublish( "", this.regQueue, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());		    
	 	}catch(Exception ex){
	 		ex.printStackTrace();
	 	}
	}
	/**
	 * This method close sender connection, this is specially done to call during garbage collection.
	 *
	 */
	public void stopSender(){
		try{
			this.objChannel.close();
			this.objConnection.close();
		}catch(Exception ex){}
    }
	
	/**
	 * this method override finalize method 
	 */
	protected void finalize(){
		this.stopSender();
	}
}
