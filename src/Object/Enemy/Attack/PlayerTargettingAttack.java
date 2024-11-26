package Object.Enemy.Attack;

import Game.GameManager;
import Object.Transform;
import Object.Enemy.Enemy;
import Util.Vector2;

public class PlayerTargettingAttack extends NormalAmmoEnemyAttack{
	Transform targetTransform;
	
	public PlayerTargettingAttack(Enemy _origin) {
		super(_origin);
		
		targetTransform = GameManager.instance.Player.transform;
	}

	@Override
	public void Attack() {
		GetAmmo(GetDirection());
	}
	
	private Vector2 GetDirection() {
		return targetTransform.GetPosition().Sub(origin.transform.GetPosition()).GetNormalized();
	}
	
}
