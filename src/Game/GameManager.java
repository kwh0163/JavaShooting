package Game;

import Object.Player.Player;
import Util.Vector2;

public class GameManager {
	
	public static GameManager instance;
	
	public Player Player;
	public EnemyManager Enemy;
	public StageManager Stage;
	public AudioManager Audio;
	
	public GameManager(int characterNumber) {
		instance = this;
		new BackGroundManager(Vector2.Zero());
		
		Audio = new AudioManager();
		
		Enemy = new EnemyManager();
		
		Stage = new StageManager(Vector2.Zero());
		
		Player = new Player(new Vector2(0, 0), characterNumber);
	}
}
