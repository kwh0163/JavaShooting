package Object;

import java.util.ArrayList;
import java.util.HashMap;

import Game.MainProgram;
import Util.IBehavior;
import Util.Layer;
import Util.Time;
import Util.Vector2;

public abstract class Collider{
	
	public GameObject gameObject;
	public ArrayList<Layer> checkLayers = new ArrayList<Layer>();
	public HashMap<Collider, Boolean> collisionObjects;
	
	public Vector2 colliderPivot;
	
	public Collider(GameObject _object) {
		collisionObjects = new HashMap<Collider, Boolean>();
		checkLayers.add(Layer.Default);
		gameObject = _object;
		colliderPivot = gameObject.transform.pivot;
		MainProgram.colliderManager.AddCollider(this);
	}
	
	private void OnCollisionEnter(GameObject collisionObject) {
		gameObject.OnCollisionEnter(collisionObject);
	}
	private void OnCollisionExit(GameObject collisionObject) {
		gameObject.OnCollisionExit(collisionObject);
	}
	
	protected abstract boolean CheckCollision(Collider _collider);
	
	public void FixedUpdate() {
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
