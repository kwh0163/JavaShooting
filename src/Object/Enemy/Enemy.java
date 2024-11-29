package Object.Enemy;

import Game.GameManager;
import Object.GameObject;
import Stage.Wave;
import Util.Vector2;

public class Enemy extends GameObject{
	private EnemyHealth health;
	public EnemyMovement movement;
	
	private Wave checkWave;
	
	public boolean isDropPower = false;
	public int killScore = 150;
	
	public Enemy(Vector2 _position, int _hp, boolean _isDropPower) {
		super(_position);
		health = new EnemyHealth(this, _hp);
		movement = new EnemyMovement(this);
		isDropPower = _isDropPower;
		GameManager.instance.Enemy.AddEnemy(this);
	}
	
	public void SetWave(Wave _wave) {
		checkWave = _wave;
	}
	
	@Override
	public void OnDestroy() {
		super.OnDestroy();
		isActive = false;
		GameManager.instance.Enemy.RemoveEnemy(this);
		if(checkWave != null)
			checkWave.EndWave();
	}
	
	public void Damage(int _damage) {
		health.Damage(_damage);
	}
	
	public Vector2 GetDirectionToPlayer() {
	    Vector2 direction = GameManager.instance.Player.transform.GetPosition().Sub(transform.GetPosition());
	    return direction.GetNormalized();
	}
}
