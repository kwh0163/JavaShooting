package Object.Player;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import MonoBehavior.BoxCollider;
import Util.Vector2;

public class PlayerStraightAmmo extends PlayerAmmo{
	double speed = 1000;
	
	public PlayerStraightAmmo(Vector2 _position) {
		super(_position);
		
		transform.scale = new Vector2(0.15, 0.4);
		collider = new BoxCollider(this, rigidBody);

		name = "StraightAmmo";
		sprite.sortIndex = 0;
		try {
			sprite.image = ImageIO.read(new File("Image\\Bullet1.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sprite.color = Color.YELLOW;
	}
	
	public void Reset(Vector2 _position, float _degree) {
		transform.SetPosition(_position);
		transform.rotation = _degree;
		rigidBody.velocity = Vector2.Up().rotate(_degree).GetNormalized().Mul(speed);
		isActive = true;
	}
	
	@Override
	public void FixedUpdate() {
		super.FixedUpdate();
		if(transform.GetPosition().y >= 800)
			Destroy();
	}

}
