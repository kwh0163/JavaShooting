package Object.CustomObject;

import java.awt.Color;

import Object.GameObject;
import Util.*;
import Object.BoxCollider;

public class TestObject extends GameObject{

	public TestObject(Vector2 _position) {
		super(_position);
		name = "Test Object";
		tag = Tag.Enemy;
		layer = Layer.Enemy;
		collider = new BoxCollider(this);
		collider.checkLayers.clear();
		collider.checkLayers.add(Layer.Player);
	}
	
	@Override
	public void OnCollisionEnter(GameObject _collision) {
		if(_collision.CompareTag(Tag.Player))
			sprite.color = Color.BLUE;
	}
	
	@Override
	public void OnCollisionExit(GameObject _collision) {
		if(_collision.CompareTag(Tag.Player))
			sprite.color = Color.RED;
	}

}
