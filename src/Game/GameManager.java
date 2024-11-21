package Game;

import Object.GameObject;
import Object.Player.Player;
import Util.Vector2;

public class GameManager {
	public GameManager() {
		new Player(new Vector2(100, 100));
		new GameObject(new Vector2(100, 100));
		
	}
}
