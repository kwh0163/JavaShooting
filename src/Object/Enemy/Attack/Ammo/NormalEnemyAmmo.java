package Object.Enemy.Attack.Ammo;

import java.awt.image.BufferedImage;

import Game.GameManager;
import Game.MainProgram;
import MonoBehavior.CircleCollider;
import Util.Debug;
import Util.NormalAmmoEnum;
import Util.Time;
import Util.Vector2;



public class NormalEnemyAmmo extends EnemyAmmo {
	
	BufferedImage red;
	BufferedImage blue;

	boolean isRandomAccel = false;
	Vector2 startVelocity = Vector2.Zero();
	double accel = 0;
	
	public NormalEnemyAmmo(Vector2 _position) {
		super(_position);
		
		transform.scale = new Vector2(0.3, 0.3);
		
		red = GetBufferedImage("RedNormalAmmo.png");
		blue = GetBufferedImage("BlueNormalAmmo.png");
		
		collider = new CircleCollider(this, rigidBody);
		((CircleCollider)collider).radius = 0.2;
	}
	
	@Override
	public void FixedUpdate() {
		super.FixedUpdate();
		
		if(isRandomAccel) {
			rigidBody.velocity = rigidBody.velocity.Add(startVelocity.Mul(accel).Mul(Time.FixedDeltaTime()));
		}
	}
	
	public void SetImage(NormalAmmoEnum _ammoType) {
		switch (_ammoType) {
		case Red:
			sprite.image = red;
			break;
		case Blue:
			sprite.image = blue;
			break;
		}
	}
	public void SetVelocity(Vector2 _velocity, boolean _isRandomAccel) {
		startVelocity = _velocity;
		rigidBody.velocity = _velocity;
		isRandomAccel = _isRandomAccel;
		if(_isRandomAccel)
			SetAccel();
	}
	
	void SetAccel() {
		double random = (MainProgram.GetRandom().nextDouble() * 0.05);
		accel = random;
	}
}
