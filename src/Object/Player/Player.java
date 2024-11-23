package Object.Player;

import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;

import MonoBehavior.BoxCollider;
import MonoBehavior.Collider;
import MonoBehavior.RigidBody;
import Object.*;
import Util.*;

public class Player extends GameObject {
	PlayerInput input;
	PlayerMovement movement;
	PlayerAttack attack;
	PlayerHitBox hitBox;

	Collider collider;
	RigidBody rigidBody;
	
	public Player(Vector2 _position) {
		super(_position);
		
		name = "Player";
		layer = Layer.Player;
		tag = Tag.Player;
		
		sprite.sortIndex = 5;
		
		try {
			sprite.image = ImageIO.read(new File("Image\\Plane.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		input = new PlayerInput(this);
		movement = new PlayerMovement(this);
		attack = new PlayerAttack(this);
		
		rigidBody = new RigidBody(this);
		
		collider = new BoxCollider(this, rigidBody);
		collider.checkLayers.clear();
		
		hitBox = new PlayerHitBox(transform.GetPosition());
		transform.childTransform = hitBox.transform;
	}
	@Override
	public void Update() {
		super.Update();
		attack.FindTarget();
		attack.Attack();
	}
	
}
