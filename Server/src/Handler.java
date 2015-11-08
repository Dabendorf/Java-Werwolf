import java.util.List;

public class Handler {
	
	private Communicator com;
	private boolean running;
	
	public Handler (Communicator c){
		com = c;
	}
	
	public void sendAction(){}
	
	public void startGame(List<Player> players){
		StringBuilder str = new StringBuilder();
		str.append("players;");
		for (Player p : players){
			str.append(p.getName() + ";");
		}
		//Communicator stuff....
		
	}
	
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
