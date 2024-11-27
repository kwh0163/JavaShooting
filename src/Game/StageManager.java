package Game;

import Object.GameObject;
import Stage.Stage1.Stage1;
import Util.Vector2;

public class StageManager extends GameObject{

	public StageManager(Vector2 _position) {
		super(_position);
		
		sprite.isVisible = false;
		
		new Stage1(this);
	}

}
