package Object;

import Util.*;

public class RigidBody implements IBehavior {
	private static final boolean DEBUG_MODE = true;
	public Vector2 velocity = Vector2.Zero();
	
	public Vector2 position;
	
	private Transform transform;
	
	public RigidBody(Transform _transform) {
		if(DEBUG_MODE)
			System.out.println(String.format("%s position : %.2f, %.2f", _transform.gameObject.name, _transform.GetPosition().x, _transform.GetPosition().y));
		transform = _transform;
		position = transform.GetPosition();
		
		Time.behaviors.add(this);
	}

	@Override
	public void Update() {
		transform.SetPosition(position);
	}

	@Override
	public void FixedUpdate() {
		position = Vector2.Add(position, Vector2.Mult(velocity, Time.FixedDeltaTime()));
	}
	
	public void SyncPosition(Vector2 _position) {
		position = _position;
	}
}
