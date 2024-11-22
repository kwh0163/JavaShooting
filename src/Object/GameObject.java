package Object;

import Game.MainProgram;
import Util.IBehavior;
import Util.Layer;
import Util.Tag;
import Util.Time;
import Util.Vector2;

public class GameObject implements IBehavior {
	public String name = "";
	public Transform transform;
	public Sprite sprite;
	public RigidBody rigidBody;
	public Collider collider;
	
	public Layer layer;
	public Tag tag;
	public boolean CompareTag(Tag _tag) {
		return tag == _tag;
	}
	public boolean isActive = true;
	@Override
	public boolean GetIsActive() {
		return isActive;
	}
	
	public GameObject(Vector2 _position) {
		Time.behaviors.add(this);
		layer = Layer.Default;
		tag = Tag.Default;
		
		transform = new Transform(this);
		transform.SetPosition(_position);
		sprite = new Sprite(null);
		rigidBody = new RigidBody(transform);
		MainProgram.panel.AddObject(this);
	}
	
	public void OnCollisionEnter(GameObject collisionObject) {
		
	}

	public void OnCollisionExit(GameObject collisisonObject) {
		
	}

	@Override
	public void Update() {
		rigidBody.Update();
	}

	@Override
	public void FixedUpdate() {
		rigidBody.FixedUpdate();
		collider.FixedUpdate();
		
	}
	
	public void Destroy() {
		System.out.println(name + " Destoryed");
		Time.behaviors.remove(this);
		MainProgram.colliderManager.RemoveCollider(collider);
		MainProgram.panel.RemoveObject(this);
	}

}
