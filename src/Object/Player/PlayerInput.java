package Object.Player;

import Util.*;
import Object.CustomObject.*;

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
	
	public void Attack(boolean _isAttacking) {
		origin.attack.isAttacking = _isAttacking;
	}
	public void LevelUp() {
		origin.attack.LevelUpTest();
	}
}
