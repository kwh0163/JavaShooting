package Game;

import Util.Time;

public class MainProgram {
	public static void main(String args[]) {
		KeyActionHandler keyAction = new KeyActionHandler();
		GamePanel panel = new GamePanel(keyAction);
		
		panel.CreateFrame();
		
		GameManager gameManager = new GameManager();
		
		Time.StartProgram();
	}
}
