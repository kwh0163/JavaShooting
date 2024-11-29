package Game;

import Object.PowerUpObject;
import Object.Enemy.Attack.Ammo.NormalEnemyAmmo;
import Object.Player.Player;
import Util.Debug;
import Util.Pool;
import Util.Vector2;

public class GameManager {
	
	public static GameManager instance;
	
	public Player Player;
	public EnemyManager Enemy;
	public StageManager Stage;
	public AudioManager Audio;
	public ScoreManager Score;
	
	private Pool<PowerUpObject> powerUpPool = new Pool<PowerUpObject>();
	
	public GameManager(int characterNumber) {
		instance = this;
		new BackGroundManager(Vector2.Zero());
		
		Audio = new AudioManager();
		
		Enemy = new EnemyManager();
		
		Stage = new StageManager(Vector2.Zero());
		
		Player = new Player(new Vector2(0, 0), characterNumber);
		
		Score = new ScoreManager();
	}
	
	public PowerUpObject GetPowerUp(Vector2 position) {
		PowerUpObject powerUp;
		
		if(powerUpPool.IsEmpty()) {
			powerUp = new PowerUpObject(position);
			powerUp.poolIndex = powerUpPool.AddPool(powerUp);
		}
		else {
			powerUp = powerUpPool.GetPool();
		}
		powerUp.Reset(position);
		return powerUp;
	}
	public void ReturnPowerUp(PowerUpObject powerUp) {
		powerUpPool.ReturnPool(powerUp.poolIndex);
	}
}
