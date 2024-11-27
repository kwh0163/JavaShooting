package Object;

import MonoBehavior.RigidBody;
import Util.Vector2;

public class BackGroundObject extends GameObject{

	private RigidBody rigidBody;
	
	public BackGroundObject(Vector2 _position, Vector2 _velocity) {
		super(_position);
		
		name = "BackGround";
		
		transform.scale = new Vector2(16.7, 12.5);
		
		rigidBody = new RigidBody(this);
		sprite.sortIndex = -1;
		sprite.image = GetBufferedImage("BackGround.png");
		rigidBody.velocity = _velocity;
		
	}

	@Override
	public void FixedUpdate() {
		super.FixedUpdate();
		if(rigidBody.transform.GetPosition().y <= -400) {
			transform.SetPosition(new Vector2(400, 1390));
		}
	}
}
