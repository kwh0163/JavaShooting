package Object.Enemy.Attack;

import Game.GameManager;
import Object.Enemy.Enemy;
import Object.Enemy.Attack.Ammo.NormalEnemyAmmo;
import Util.Vector2;

public abstract class NormalAmmoEnemyAttack implements IEnemyAttack {
	protected Enemy origin;
	
	public NormalAmmoEnemyAttack(Enemy _origin) {
		origin = _origin;
	}
	
	protected NormalEnemyAmmo GetAmmo(Vector2 _direction) {
		return GameManager.instance.Enemy.GetNormalAmmo(origin.transform.GetPosition(), _direction);
	}
}
