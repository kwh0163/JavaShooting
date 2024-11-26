package Object.Player;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.MainProgram;
import MonoBehavior.BoxCollider;
import MonoBehavior.CircleCollider;
import Util.Time;
import Util.Vector2;

public class PlayerGuidanceAmmo extends PlayerAmmo{

	private float speed = 1000;
	private double guideTime = 0.1;
	private double guideTimeCounter = 0;
	
	private PlayerAttack origin;
	
	public PlayerGuidanceAmmo(Vector2 _position, PlayerAttack _origin) {
		super(_position);
		
		origin = _origin;
		
		transform.scale = new Vector2(0.2, 0.5);
		collider = new BoxCollider(this, rigidBody);

		name = "GuidanceAmmo";
		
		sprite.sortIndex = 1;
		sprite.image = GetBufferedImage("Missile.png");
		
		sprite.color = Color.BLUE;
	}
	
	public void Reset(Vector2 _position, float _degree) {
		guideTimeCounter = 0;
		transform.SetPosition(_position);
		rigidBody.velocity = Vector2.Up().rotate(_degree).Mul(speed);
		transform.rotation = _degree;
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
		Vector2 direction;
		if(origin.targetEnemy == null || !origin.targetEnemy.isActive)
			direction = rigidBody.velocity.GetNormalized();
		else
			direction = origin.targetEnemy.transform.GetPosition().Sub(transform.GetPosition());
		transform.Look(direction);
		return direction.GetNormalized();
	}
	
}
