package Game;

import Object.CustomObject.TestObject;
import Object.Player.Player;
import Util.Vector2;

public class GameManager {
	
	public static GameManager instance;
	
	public Player Player;
	public EnemyManager Enemy;
	
	public GameManager() {
		instance = this;
		new BackGroundManager(Vector2.Zero());
		
		Enemy = new EnemyManager();
		
		Player = new Player(new Vector2(0, 0));
		new TestObject(new Vector2(400, 400));
		
	}
}
