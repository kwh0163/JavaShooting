package Object.Player;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.MainProgram;
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
		
		transform.scale = new Vector2(0.3, 0.3);
		collider = new CircleCollider(this, rigidBody);
		((CircleCollider)collider).radius = 0.2;

		name = "GuidanceAmmo";
		
		sprite.sortIndex = 1;
		try {
			sprite.image = ImageIO.read(new File("Image\\Bullet.png"));
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		sprite.color = Color.BLUE;
	}
	
	public void Reset(Vector2 _position, Vector2 _firstDirection) {
		guideTimeCounter = 0;
		transform.SetPosition(_position);
		rigidBody.velocity = _firstDirection.GetNormalized().Mul(speed);
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
		if(origin.targetEnemy == null)
			return rigidBody.velocity.GetNormalized();
		Vector2 direction = origin.targetEnemy.transform.GetPosition().Sub(transform.GetPosition());
		return direction.GetNormalized();
	}
	
}
