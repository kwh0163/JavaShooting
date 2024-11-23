package Game;

import Util.Time;
import Util.Vector2;

public class MainProgram {
	public static String title = "Strike";
	public static int width = 800;
	public static int height = 600;
	public static int defaultPixel = 48;
	
	public static GamePanel panel;
	public static ColliderManager colliderManager;
	public static GameObjectManager gameObjectManager;
	
	public static void main(String args[]) {
		panel = new GamePanel(new KeyActionHandler());
		
		gameObjectManager = new GameObjectManager();
		colliderManager = new ColliderManager();
		
		
		new GameManager();
		
		Time.StartProgram(gameObjectManager, panel);
	}
	
	public static boolean IsOveredWorld(Vector2 _position) {
		if(_position.x <= -50 || _position.x >= 850)
			return true;
		if(_position.y <= -50 || _position.y >= 650)
			return true;
		return false;
	}
}
