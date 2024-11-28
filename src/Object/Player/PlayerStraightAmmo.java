package Object.Player;

import java.awt.Color;
import java.awt.image.BufferedImage;

import MonoBehavior.BoxCollider;
import Util.Vector2;

public class PlayerStraightAmmo extends PlayerAmmo{
	double speed = 1000;
	
	BufferedImage yellowAmmo = GetBufferedImage("Bullet1.png");
	BufferedImage spikeAmmo = GetBufferedImage("SpikeAmmo.png");
	
	public PlayerStraightAmmo(Vector2 _position) {
		super(_position);
		
		transform.scale = new Vector2(0.15, 0.4);
		collider = new BoxCollider(this, rigidBody);

		name = "StraightAmmo";
		sprite.sortIndex = 0;
		sprite.image = GetBufferedImage("Bullet1.png");
		
		sprite.color = Color.YELLOW;
	}
	
	public void SetAmmoImage(int i) {
		if(i == 0)
			sprite.image = yellowAmmo;
		else if(i == 1)
			sprite.image = spikeAmmo;
	}
	
	public void Reset(Vector2 _position, float _degree) {
		transform.SetPosition(_position);
		transform.rotation = _degree;
		rigidBody.velocity = Vector2.Up().rotate(_degree).GetNormalized().Mul(speed);
		isActive = true;
	}

}
