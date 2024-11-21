package Object.Player;

import Util.*;

public class PlayerInput{
	public static PlayerInput instance;
	
	private Player origin;
	
	public PlayerInput(Player _origin) {
		instance = this;
		origin = _origin;
	}
	
	
	
	public void Move(Vector2 direction) {
		origin.movement.Move(direction);
	}
}
