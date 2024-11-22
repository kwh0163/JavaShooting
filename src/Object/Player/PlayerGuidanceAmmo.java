package Object.Player;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.MainProgram;
import Object.CircleCollider;
import Util.Time;
import Util.Vector2;

public class PlayerGuidanceAmmo extends PlayerAmmo{

	private float speed = 1000;
	private double guideTime = 0.5;
	private double guideTimeCounter = 0;
	
	private Vector2 tempVector2 = Vector2.Up();
	
	public PlayerGuidanceAmmo(Vector2 _position, Vector2 _firstDirection) {
		super(_position);
		name = "GuidanceAmmo";
		
		sprite.sortIndex = 1;
		try {
			sprite.image = ImageIO.read(new File("Image\\Bullet.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sprite.color = Color.BLUE;
		transform.scale = new Vector2(0.3, 0.3);
		collider = new CircleCollider(this);
		((CircleCollider)collider).radius = 0.2;
		Reset(_position, _firstDirection);
		Init();
	}
	
	public void Reset(Vector2 _position, Vector2 _firstDirection) {
		transform.SetPosition(_position);
		rigidBody.velocity = _firstDirection.GetNormalized().Mul(speed);
		tempVector2 = _firstDirection.GetNormalized().Mul(speed);
		isActive = true;
	}
	
	@Override
	public void Update() {
		super.Update();
		
		if(guideTimeCounter < guideTime) {
			guideTimeCounter += Time.DeltaTime();
		}
		else {
			rigidBody.velocity = GetGuideVelocity().Mul(speed);
		}
	}
	
	@Override
	public void FixedUpdate() {
		super.FixedUpdate();
		if(MainProgram.IsOveredWorld(transform.GetPosition()))
			Destroy();
	}
	
	private Vector2 GetGuideVelocity() {
		return tempVector2.GetNormalized();
	}
}
