
public class Controler {
	
	static Communicator COM;
	static WerwolfGUI GUI;
	static boolean running
	
	public static void main(String[] args)
	{
		running = true;
		COM = new Communicator();
		
		
		Thread guiThread = new Thread(new Runnable() {
			@Override
			public void run() {
				GUI = new WerwolfGUI();
			}
		});
		guiThread.start();
		
		Thread messagThread = new Thread(new Runnable(){
			@Override
			public void run(){
				getMessage();
			}
		})
	}

	
	public static void sendMessage(String message)
	{
		//Nachricht generieren
		String output = "msg;" + message;
		
		//Nachricht senden
		COM.send(message);
	}
	
	private static void sendToServer(output)
	{
		COM.send(output);
	}
	
	private static void getMessage()
	{
		while (running)
		{
			Thread.sleep(500);
			String news = COM.getNewest();
			if (!(news == null))
			{
				//Message received
			}
		}
	}
	
	private void textMessageReceived(String message)
	{
		GUI.receiveText(message);
	}
}


//getNewest
