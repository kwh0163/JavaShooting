package Object.Player;

import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;

import Object.*;
import Util.*;

public class Player extends GameObject {
	PlayerInput input;
	PlayerMovement movement;
	PlayerAttack attack;
	
	public Player(Vector2 _position) {
		super(_position);
		name = "Player";
		layer = Layer.Player;
		tag = Tag.Player;
		input = new PlayerInput(this);
		movement = new PlayerMovement(this);
		attack = new PlayerAttack(this);
		collider = new BoxCollider(this);
		collider.checkLayers.clear();
		
		sprite.sortIndex = 5;
		try {
			sprite.image = ImageIO.read(new File("Image\\Plane.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void Update() {
		super.Update();
		attack.Attack();
	}
	
}
