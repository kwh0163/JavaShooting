package Object.Enemy;

import Game.GameManager;
import Object.GameObject;
import Stage.Wave;
import Util.Vector2;

public class Enemy extends GameObject{
	private EnemyHealth health;
	
	private Wave checkWave;
	
	public Enemy(Vector2 _position, int _hp) {
		super(_position);
		health = new EnemyHealth(this, _hp);
		GameManager.instance.Enemy.AddEnemy(this);
	}
	
	public void SetWave(Wave _wave) {
		checkWave = _wave;
	}
	
	@Override
	public void OnDestroy() {
		super.OnDestroy();
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
