package com.igbeok;


import java.util.Random;

public class TempLoginSimulator extends Thread{
	  private static TempLoginSimulator objRabitMsgReceiver;
	  
	  private TempLoginSimulator(){
		  
	  }
	  
	  public static TempLoginSimulator createMsgSendClient(){
		  if(TempLoginSimulator.objRabitMsgReceiver == null){
			  TempLoginSimulator.objRabitMsgReceiver = new TempLoginSimulator();
		  }		  
		  return TempLoginSimulator.objRabitMsgReceiver;	  
	  }
	  
	  public void run() {
		  this.tempSimulate();
	  }
	  
	  public void tempSimulate(){
		try{
			String[] clientNames = new String[] { "Unknown", "Battlefront", "www.starwars.com" };
			String[] profileNames = new String[] { "Unknown", "PSN", "facebook", "LIVE", "LucasArts" };

			RabbitMsgSender objMsgSender = new RabbitMsgSender();
			String str;
			int count = 0;
			while (true) {
				Random rand = new Random();
				str = "remoteAddress=" + (rand.nextInt(220)+10)+"."+(rand.nextInt(220)+10)+"."+(rand.nextInt(220)+10)+"."+(rand.nextInt(220)+10);
				str += "&clientName=" + clientNames[rand.nextInt(clientNames.length)];
				str += "&profileName=" + profileNames[rand.nextInt(profileNames.length)];		
			
				String logStr = "";
				if (count % 2 == 0) {
					objMsgSender.sendMessage_login(str);
					logStr += "LOGIN: ";
				} else {
					objMsgSender.sendMessage_reg(str);
					logStr += "REG:   ";
				}
				logStr += " sent msg.." + " " + str;

				System.out.println(logStr);
				count++;
				Thread.sleep(1000);
			}  
		}catch(Exception ex)
		{
			System.out.println("Error in Login Simulator :"+ex);
		}
	  }	 
}
