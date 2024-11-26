package Game;

import Object.BackGroundObject;
import Object.GameObject;
import Util.Vector2;

public class BackGroundManager extends GameObject {

	private double speed = 100;
	
	public BackGroundManager(Vector2 _position) {
		super(_position);
		
		sprite.isVisible = false;
		
		new BackGroundObject(new Vector2(400, -300), Vector2.Down().Mul(speed));
		new BackGroundObject(new Vector2(400, 300), Vector2.Down().Mul(speed));
		new BackGroundObject(new Vector2(400, 900), Vector2.Down().Mul(speed));
	}
	

}
