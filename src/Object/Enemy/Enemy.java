package Object.Enemy;

import Game.GameManager;
import Object.GameObject;
import Util.Debug;
import Util.Vector2;

public class Enemy extends GameObject{
	 EnemyHealth health;
	
	
	public boolean isDropPower = false;
	public int killScore = 150;
	
	public Enemy(Vector2 _position, int _hp, boolean _isDropPower) {
		super(_position);
		health = new EnemyHealth(this, _hp);
		isDropPower = _isDropPower;
		GameManager.instance.Enemy.AddEnemy(this);
	}
	@Override
	public void OnDestroy() {
		isActive = false;
		GameManager.instance.Enemy.RemoveEnemy(this);
	}
	
	public void Damage(int _damage) {
		health.Damage(_damage);
	}
	public void Reset(Vector2 _position, int hp, boolean _isDropPower) {
		transform.SetPosition(_position);
		health.hp = hp;
		isDropPower = _isDropPower;
	}
	
	public Vector2 GetDirectionToPlayer() {
	    Vector2 direction = GameManager.instance.Player.transform.GetPosition().Sub(transform.GetPosition());
	    return direction.GetNormalized();
	}
}
