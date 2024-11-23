package MonoBehavior;

import Object.GameObject;
import Object.Transform;
import Util.*;

public class RigidBody extends MonoBehavior{
	public RigidBody(GameObject _object) {
		super(_object);
	}

	private static final boolean DEBUG_MODE = false;
	public Vector2 velocity = Vector2.Zero();
	
	public Vector2 position;
	public Vector2 GetPosition() {
		return new Vector2(position);
	}
	
	@Override
	public void Awake() {
		if(DEBUG_MODE)
			System.out.println(gameObject.name + " position : " + transform.GetPosition().toString());
		transform.rigidBody = this;
		position = transform.GetPosition();
	}

	@Override
	public void Update() {
		transform.SetPosition(position);
	}

	@Override
	public void FixedUpdate() {
		position = position.Add(velocity.Mul(Time.FixedDeltaTime()));
	}
	
	public void SyncPosition(Vector2 _position) {
		position = _position;
	}
}
