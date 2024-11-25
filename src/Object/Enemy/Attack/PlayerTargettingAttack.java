package Object.Enemy.Attack;

import Game.GameManager;
import MonoBehavior.MonoBehavior;
import Object.GameObject;
import Object.Transform;
import Util.Time;
import Util.Vector2;

public class PlayerTargettingAttack extends MonoBehavior implements IEnemyAttack{

	public PlayerTargettingAttack(GameObject _object) {
		super(_object);
	}

	Transform targetTransform;
	
	@Override
	public void Awake() {
		super.Awake();
		targetTransform = GameManager.instance.Player.transform;
	}

	@Override
	public void Attack() {
		CreateTargettingAmmo();
	}
	
	private void CreateTargettingAmmo() {
		Vector2 ammoDirection = targetTransform.GetPosition().Sub(transform.GetPosition()).GetNormalized();
		
		GameManager.instance.Enemy.GetNormalAmmo(transform.GetPosition(), ammoDirection);
	}
	
}
