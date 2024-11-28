package Object.Player;

import Util.Vector2;

public class PlayerMovement {
	private Player origin;
	private float speed;
	private float normalSpeed;
	private float focusSpeed;
	
	private Vector2 velocity = Vector2.Zero();
	
	public Vector2 GetVelocity() {
		return new Vector2(velocity.Mul(speed));
	}
	
	public PlayerMovement(Player _origin, float _normalSpeed, float _focusSpeed)
	{
		origin = _origin;
		speed = _normalSpeed;
		normalSpeed = _normalSpeed;
		focusSpeed = _focusSpeed;
	}
	
	private Vector2 currentDirection = Vector2.Zero();
	public void Move(Vector2 _direction) {
		currentDirection = _direction;
		origin.rigidBody.velocity = currentDirection.Mul(speed);
	}
	public void Focus(boolean isFocussing) {
		if(isFocussing)
			speed = focusSpeed;
		else {
			speed = normalSpeed;
		}
		origin.rigidBody.velocity = currentDirection.Mul(speed);
	}
}
