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
	
	public void Attack(boolean _isAttacking) {
		origin.attack.isAttacking = _isAttacking;
	}
	public void Focus(boolean _isFocusing) {
		origin.movement.Focus(_isFocusing);
		origin.attack.Focus(_isFocusing);
	}
	public void LevelUp() {
		origin.attack.LevelUpTest();
	}
}
