package Object.Player;

import Util.Vector2;

public class PlayerMovement {
	private Player origin;
	private float speed = 300;
	
	public PlayerMovement(Player _origin)
	{
		origin = _origin;
	}
	
	public void Move(Vector2 _direction) {
		origin.rigidBody.velocity = Vector2.Mult(_direction, speed);
	}
}
