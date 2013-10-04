package com.igbeok;


/**
 * Hello world!
 *
 */
public class RabbitMQIpSimulator 
{
    public static void main( String[] args )
    {
    	System.out.println("simluator started.");
    	try{
	    	TempLoginSimulator objTempLoginSimulator = TempLoginSimulator.createMsgSendClient();
		    objTempLoginSimulator.start();
		    objTempLoginSimulator.join();
    	}catch(Exception ex){}
    }
}
