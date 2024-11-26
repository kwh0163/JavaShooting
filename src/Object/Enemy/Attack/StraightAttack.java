package Object.Enemy.Attack;

import Object.Enemy.Enemy;
import Util.Vector2;

public class StraightAttack extends NormalAmmoEnemyAttack {
	
	private Vector2 velocity;
	
	public StraightAttack(Enemy _origin) {
		super(_origin);
	}
	
	@Override
	public void Attack() {
		GetAmmo(velocity);
	}
	
	public void SetVelocity(Vector2 _velocity) {
		velocity = _velocity;
	}
}
