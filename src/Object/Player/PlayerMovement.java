package Object.Player;

import Util.Vector2;

public class PlayerMovement {
	private Player origin;
	private double speed = 300;
	
	public PlayerMovement(Player _origin)
	{
		origin = _origin;
	}
	
	public void Move(Vector2 _direction) {
		origin.rigidBody.velocity = _direction.Mul(speed);
	}
}
