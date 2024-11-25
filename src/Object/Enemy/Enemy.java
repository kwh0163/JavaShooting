package Object.Enemy;

import Game.GameManager;
import Object.GameObject;
import Util.Vector2;

public class Enemy extends GameObject{
	private EnemyHealth health;
	
	public Enemy(Vector2 _position, int _hp) {
		super(_position);
		health = new EnemyHealth(this, _hp);
		GameManager.instance.Enemy.AddEnemy(this);
	}
	
	@Override
	public void OnDestroy() {
		super.OnDestroy();
		GameManager.instance.Enemy.RemoveEnemy(this);
	}
	
	public void Damage(int _damage) {
		health.Damage(_damage);
	}
}
