package Game;

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
		
		new StageManager(Vector2.Zero());
		
		Player = new Player(new Vector2(0, 0));
	}
}
