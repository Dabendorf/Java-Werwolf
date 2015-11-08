package werwolfpackage;

import java.io.IOException;

public class Controler {
	
	static Communicator COM;
	static WerwolfGUI GUI;
	static boolean running;
	
	public static void main(String[] args)
	{
		//wenn running = true läuft das Programm weiter
		running = true;
		
		COM = new Communicator();
		
		//Thread für GUI erstellen und starten
		Thread guiThread = new Thread(new Runnable() {
			@Override
			public void run() {
				GUI = new WerwolfGUI();
			}
		});
		guiThread.start();
		
		//Thread zum überprüfen auf neue Nachrichten starten
		Thread messageThread = new Thread(new Runnable(){
			@Override
			public void run(){
				try {
					getMessage();
				} catch (InterruptedException e) {
					running = false;
					e.printStackTrace();
				}
			}
		});
		messageThread.start();
	}
	
	//Programm beenden
	public static void killProgramm()
	{
		running = false;
	}

	//Zu Server verbinden
	public static void connectToServer(String ipAdress, String username)
	{
		//Zu Server verbinden
		try {
			COM.connect(ipAdress,8080);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Verbunden");
		//Usernamen weitergeben
		try {
			sendToServer("user;" + username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Chatnachricht an Server senden
	public static void sendMessage(String message)
	{
		//Nachricht generieren
		String output = "msg;" + message;
		
		//Nachricht senden
		try{
		COM.send(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//String an Server senden
	private static void sendToServer(String output) throws IOException
	{
		COM.send(output);
	}
	
	//Auf eingehende Nachrichten überprüfen (alle 500 ms)
	private static void getMessage() throws InterruptedException
	{
		while (running)
		{
			Thread.sleep(500);
			String news = COM.getLatestMessage();
			if (!(news == null))
			{
				String[] inputStream = news.split(";");
				if (inputStream[0].equals("msg")){
					GUI.receiveText(inputStream[1]);
				} else if (inputStream[0].equals("players")) {
					String[] playerArr = new String[inputStream.length -1];
					for (int i = 1; i<inputStream.length; i++)
					{
						playerArr[i-1] = inputStream[i];
					}
					Model.setPlayers(playerArr);
				}
			}
		}
	}
	
	
}

