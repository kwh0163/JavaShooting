package Object.Enemy;

import Game.EnemyManager;
import Object.GameObject;
import Util.Vector2;

public class Enemy extends GameObject{

	public Enemy(Vector2 _position) {
		super(_position);
		EnemyManager.instance.AddEnemy(this);
	}
	
	@Override
	public void OnDestroy() {
		super.OnDestroy();
		
		EnemyManager.instance.RemoveEnemy(this);
	}

}
