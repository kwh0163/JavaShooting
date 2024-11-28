package Object.CustomObject;

import java.awt.Color;

import MonoBehavior.BoxCollider;
import MonoBehavior.Collider;
import MonoBehavior.RigidBody;
import Object.GameObject;
import Object.Enemy.Enemy;
import Object.Enemy.Attack.NormalAmmoEnemyAttack;
import Util.*;

public class TestObject extends Enemy{

	Collider collider;
	RigidBody rigidBody;
	
	NormalAmmoEnemyAttack attack;
	
	public TestObject(Vector2 _position, int _hp) {
		super(_position, _hp);

		name = "Test Object";
		tag = Tag.Enemy;
		layer = Layer.Enemy;
		
		rigidBody = new RigidBody(this);
		
		collider = new BoxCollider(this, rigidBody);
		
		collider.checkLayers.clear();
		collider.checkLayers.add(Layer.PlayerHitBox);
		
		attack = new NormalAmmoEnemyAttack(this);
	}

	
	private float counter = 0;
	@Override
	public void Update() {
		super.Update();
		
		if(counter < 1) {
			counter += Time.DeltaTime();
		}
		else {
			counter = 0;
			attack.CircleAttackToPlayer(100, 30);
			//System.out.println("Attack");
		}
		
		
	}
	
}
