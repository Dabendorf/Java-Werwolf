
public class Controler {
	
	Communicator COM;
	Werwolf GUI;
	
	
	public static void main(String[] args)
	{
		COM = new Communicator();
		GUI = new Werwolf();
		
		
	}
	
	private String encode()
	
	private static void sendMessage(String message)
	{
		//Nachricht senden
		COM.send(message)
	}
	
	private static void getMessage()
	{
		COM.getNewest();
	}
	

	
	private void textMessageReceived(String message)
	{
		GUI.receiveText(message);
	}
}


//getNewest