package Object.Enemy.Attack.Ammo;

import Game.GameManager;
import Game.MainProgram;
import MonoBehavior.CircleCollider;
import Util.Time;
import Util.Vector2;

public class TracePlayerAmmo extends EnemyAmmo {

	boolean isRandomAccel = false;
	Vector2 startVelocity = Vector2.Zero();
	double accel = 0;
	
	public TracePlayerAmmo(Vector2 _position) {
		super(_position);
		
		sprite.image = GetBufferedImage("YellowBullet.png");
		transform.scale = new Vector2(0.5, 0.5);
		
		collider = new CircleCollider(this, rigidBody);
		((CircleCollider)collider).radius = 0.4;
	}
	
	double timeCounter = 0;
	double time = 0;
	boolean isTraced = false;
	double traceSpeed = 0;
	@Override
	public void Update() {
		super.Update();
		if(isTraced)
			return;
		
		if(timeCounter < time) {
			timeCounter += Time.DeltaTime();
		}
		else {
			isTraced = true;
			Vector2 direction = GameManager.instance.Player.transform.GetPosition().Sub(transform.GetPosition()).GetNormalized();
			rigidBody.velocity = direction.Mul(traceSpeed);
		}
	}
	@Override
	public void FixedUpdate() {
		super.FixedUpdate();
		
		if(isTraced&&isRandomAccel) {
			rigidBody.velocity = rigidBody.velocity.Add(startVelocity.Mul(accel).Mul(Time.FixedDeltaTime()));
		}
	}
	
	
	public void SetVelocity(Vector2 _velocity, boolean _isRandomAccel, double traceTime, double _traceSpeed) {
		startVelocity = _velocity;
		rigidBody.velocity = _velocity;
		isRandomAccel = _isRandomAccel;
		if(_isRandomAccel)
			SetAccel();
		time = traceTime;
		traceSpeed = _traceSpeed;
		timeCounter = 0;
		isTraced = false;
	}
	
	void SetAccel() {
		double random = (MainProgram.GetRandom().nextDouble() * 0.05);
		accel = random;
	}
}
