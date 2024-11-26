package Object.CustomObject;

import java.awt.Color;

import MonoBehavior.BoxCollider;
import MonoBehavior.Collider;
import MonoBehavior.RigidBody;
import Object.GameObject;
import Object.Enemy.Enemy;
import Object.Enemy.Attack.IEnemyAttack;
import Object.Enemy.Attack.PlayerTargettingAttack;
import Util.*;

public class TestObject extends Enemy{

	Collider collider;
	RigidBody rigidBody;
	
	IEnemyAttack attack;
	
	public TestObject(Vector2 _position) {
		super(_position, 100);

		name = "Test Object";
		tag = Tag.Enemy;
		layer = Layer.Enemy;
		
		rigidBody = new RigidBody(this);
		
		collider = new BoxCollider(this, rigidBody);
		collider.checkLayers.clear();
		collider.checkLayers.add(Layer.PlayerHitBox);
		
		attack = new PlayerTargettingAttack(this);
	}
	
	@Override
	public void OnCollisionEnter(GameObject _collision) {
		if(_collision.CompareTag(Tag.PlayerHitBox))
			sprite.color = Color.BLUE;
	}
	
	@Override
	public void OnCollisionExit(GameObject _collision) {
		if(_collision.CompareTag(Tag.PlayerHitBox))
			sprite.color = Color.RED;
	}

	
	private float counter = 0;
	@Override
	public void Update() {
		super.Update();
		
		if(counter < 0.2) {
			counter += Time.DeltaTime();
		}
		else {
			counter = 0;
			attack.Attack();
			//System.out.println("Attack");
		}
		
		
	}
	
}
