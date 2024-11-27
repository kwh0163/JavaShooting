package Object.Enemy;

import MonoBehavior.BoxCollider;
import MonoBehavior.Collider;
import MonoBehavior.RigidBody;
import Object.Enemy.Attack.NormalAmmoEnemyAttack;
import Util.Layer;
import Util.Tag;
import Util.Vector2;

public class NormalEnemy extends Enemy{
	Collider collider;
	RigidBody rigidBody;
	
	NormalAmmoEnemyAttack attack;
	
	public NormalEnemy(Vector2 _position, int _hp) {
		super(_position, _hp);

		name = "Test Object";
		tag = Tag.Enemy;
		layer = Layer.Enemy;
		
		rigidBody = new RigidBody(this);
		
		collider = new BoxCollider(this, rigidBody);
		
		collider.checkLayers.clear();
		collider.checkLayers.add(Layer.PlayerHitBox);
	}
	
}
