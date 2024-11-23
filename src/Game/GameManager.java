package Game;

import Object.CustomObject.TestObject;
import Object.Player.Player;
import Util.Vector2;

public class GameManager {
	public GameManager() {
		new EnemyManager();
		
		new Player(new Vector2(0, 0));
		new TestObject(new Vector2(400, 400));
		
	}
}
