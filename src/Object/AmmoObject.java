package Object;

import MonoBehavior.Collider;
import MonoBehavior.RigidBody;
import Util.Vector2;

public class AmmoObject extends GameObject{

	public Collider collider;
	public RigidBody rigidBody;
	
	public AmmoObject(Vector2 _position) {
		super(_position);
		
		rigidBody = new RigidBody(this); 
	}

	public void Destroy() {
		isActive = false;
		rigidBody.velocity = Vector2.Zero();
	}
}
