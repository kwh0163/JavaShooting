package MonoBehavior;

import java.util.ArrayList;
import java.util.HashMap;

import Game.MainProgram;
import Object.GameObject;
import Util.*;

public abstract class Collider extends MonoBehavior{
	
	public Collider(GameObject _object, RigidBody _rigid) {
		super(_object);
		rigidBody = _rigid;
		collisionObjects = new HashMap<Collider, Boolean>();
		checkLayers.add(Layer.Default);
		colliderPivot = gameObject.transform.pivot;
		MainProgram.colliderManager.AddCollider(this);
	}

	public ArrayList<Layer> checkLayers = new ArrayList<Layer>();
	public HashMap<Collider, Boolean> collisionObjects;
	
	public Vector2 colliderPivot;
	
	public RigidBody rigidBody;
	
	private void OnCollisionEnter(GameObject collisionObject) {
		gameObject.OnCollisionEnter(collisionObject);
	}
	private void OnCollisionExit(GameObject collisionObject) {
		gameObject.OnCollisionExit(collisionObject);
	}
	
	protected abstract boolean CheckCollision(Collider _collider);
	
	@Override
	public void Start() {
		CheckCollision();
	}
	
	@Override
	public void FixedUpdate() {
		CheckCollision();
	}
	@Override
	public void OnDestroy() {
		MainProgram.colliderManager.RemoveCollider(this);
		rigidBody = null;
		checkLayers = null;
		collisionObjects = null;
		super.OnDestroy();
	}
	
	private void CheckCollision(){
		for(int i = 0;i<checkLayers.size();i++) {
			ArrayList<Collider> colliders = MainProgram.colliderManager.GetColliderList(checkLayers.get(i));
			if(colliders == null) {
				System.out.println(checkLayers.get(i).toString() + " is null");
				continue;
			}
			for(int j = 0;j < colliders.size();j++) {
				Collider currentCollider = colliders.get(j);
				if(!currentCollider.gameObject.isActive)
					continue;
				if(currentCollider == this)
					continue;
				if(CheckCollision(currentCollider)) {
					if(collisionObjects.containsKey(currentCollider))
						continue;
					OnCollisionEnter(currentCollider.gameObject);
					collisionObjects.put(currentCollider, true);
				}
				else {
					if(collisionObjects.containsKey(currentCollider)) {
						collisionObjects.remove(currentCollider);
						OnCollisionExit(currentCollider.gameObject);
					}
				}
			}
		}
	}
}
