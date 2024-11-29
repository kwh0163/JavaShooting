package Object.Player;

import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;

import Game.MainProgram;
import MonoBehavior.BoxCollider;
import MonoBehavior.Collider;
import MonoBehavior.RigidBody;
import Object.*;
import Util.*;

public class Player extends GameObject {
	public int characterNumber;
	
	PlayerInput input;
	PlayerMovement movement;
	PlayerAttack attack;
	PlayerHitBox hitBox;
	public PlayerHealth health;

	Collider collider;
	RigidBody rigidBody;
	
	Vector2 worldLimitLeftUp;
	Vector2 worldLimitRightDown;
	
	public Player(Vector2 _position, int _characterNumber) {
		super(_position);
		
		worldLimitLeftUp = new Vector2(MainProgram.defaultPixel * transform.pivot.x, MainProgram.height - MainProgram.defaultPixel * transform.pivot.y);
		worldLimitRightDown = new Vector2(MainProgram.width - MainProgram.defaultPixel * transform.pivot.x, MainProgram.defaultPixel * transform.pivot.y);
		
		characterNumber = _characterNumber;
		
		name = "Player";
		layer = Layer.Player;
		tag = Tag.Player;
		
		sprite.sortIndex = 3;
		
		input = new PlayerInput(this);
		
		switch (characterNumber){
		case 0:
			sprite.image = GetBufferedImage("PlaneGray.png");
			movement = new PlayerMovement(this, 300, 150);
			attack = new Player0Attack(this);
			break;
		case 1:
			sprite.image = GetBufferedImage("PlaneRedAndWhite.png");
			movement = new PlayerMovement(this, 400, 250);
			attack = new Player1Attack(this);
			break;
		case 2:
		default:
			sprite.image = GetBufferedImage("PlaneGreen.png");
			movement = new PlayerMovement(this, 300, 150);
			attack = new Player0Attack(this);
			
				break;
		}
		health = new PlayerHealth(this, 5);
		
		rigidBody = new RigidBody(this);
		
		collider = new BoxCollider(this, rigidBody);
		collider.checkLayers.clear();
		
		hitBox = new PlayerHitBox(transform.GetPosition(), this);
		transform.childTransform = hitBox.transform;
	}
	
	@Override
	public void Update() {
		super.Update();
		
		CheckEndOfWorld();
		
	}
	
	public boolean TryDamage() {
		return health.TryDamage();
	}
	
	private void CheckEndOfWorld() {
		Vector2 position = transform.GetPosition();
		if(position.x < worldLimitLeftUp.x) 
			position.x = worldLimitLeftUp.x;
		else if(position.x > worldLimitRightDown.x)
			position.x = worldLimitRightDown.x;
		
		if(position.y < worldLimitRightDown.y)
			position.y = worldLimitRightDown.y;
		else if(position.y > worldLimitLeftUp.y)
			position.y = worldLimitLeftUp.y;
		
		transform.SetPosition(position);
	}
}
