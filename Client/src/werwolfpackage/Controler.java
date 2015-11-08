package werwolfpackage;

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
				getMessage();
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
		COM.connect(ipAdress,8080);
		//Usernamen weitergeben
		sendToServer("user;" + username);
	}
	
	//Chatnachricht an Server senden
	public static void sendMessage(String message)
	{
		//Nachricht generieren
		String output = "msg;" + message;
		
		//Nachricht senden
		COM.send(message);
	}
	
	//String an Server senden
	private static void sendToServer(String output)
	{
		COM.send(output);
	}
	
	//Auf eingehende Nachrichten überprüfen (alle 500 ms)
	private static void getMessage()
	{
		while (running)
		{
			Thread.sleep(500);
			String news = COM.getNewest();
			if (!(news == null))
			{
				String[] inputStream = news.split(";");
				if (inputStream[0].equals("msg"))
				{
					GUI.receiveText(inputStream[1]);
				}
			}
		}
	}
	
	
}

