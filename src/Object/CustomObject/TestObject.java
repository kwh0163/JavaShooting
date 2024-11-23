package Object.CustomObject;

import java.awt.Color;

import MonoBehavior.BoxCollider;
import MonoBehavior.Collider;
import MonoBehavior.RigidBody;
import Object.GameObject;
import Object.Enemy.Enemy;
import Util.*;

public class TestObject extends Enemy{

	Collider collider;
	RigidBody rigidBody;
	
	public TestObject(Vector2 _position) {
		super(_position);

		name = "Test Object";
		tag = Tag.Enemy;
		layer = Layer.Enemy;
		
		rigidBody = new RigidBody(this);
		
		collider = new BoxCollider(this, rigidBody);
		collider.checkLayers.clear();
		collider.checkLayers.add(Layer.PlayerHitBox);
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

}
