package Object.Player;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Object.GameObject;
import Util.Vector2;

public class Player extends GameObject {
	PlayerInput input;
	PlayerMovement movement;
	
	public Player(Vector2 _position) {
		super(_position);
		name = "Player";
		input = new PlayerInput(this);
		movement = new PlayerMovement(this);
		
		sprite.sortIndex = 1;
		try {
			sprite.image = ImageIO.read(new File("Image\\Plane.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
