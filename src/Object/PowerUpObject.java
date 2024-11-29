package Object;

import Game.GameManager;
import Game.MainProgram;
import MonoBehavior.BoxCollider;
import MonoBehavior.RigidBody;
import Util.Debug;
import Util.Layer;
import Util.Tag;
import Util.Time;
import Util.Vector2;

public class PowerUpObject extends GameObject{

	Vector2 worldLimitLeftUp;
	Vector2 worldLimitRightDown;
	
	BoxCollider collider;
	RigidBody rigidBody;
	
	public int poolIndex;
	
	float speed = 250;
	
	public PowerUpObject(Vector2 _position) {
		super(_position);
		
		transform.scale = new Vector2(0.6, 0.6);
		sprite.image = GetBufferedImage("PowerUp.png");
		
		worldLimitLeftUp = new Vector2(MainProgram.defaultPixel * transform.pivot.x * transform.scale.x,
				MainProgram.height - MainProgram.defaultPixel * transform.pivot.y * transform.scale.y);
		worldLimitRightDown = new Vector2(MainProgram.width - MainProgram.defaultPixel * transform.pivot.x * transform.scale.x, 
				MainProgram.defaultPixel * transform.pivot.y * transform.scale.y);
		
		
		layer = Layer.Item;
		tag = Tag.PowerUp;
		
		rigidBody = new RigidBody(this);
		collider = new BoxCollider(this, rigidBody);
		collider.checkLayers.clear();
	}
	
	double disappearTime = 20;
	double disappearTimeCounter = 0;
	
	@Override
	public void Update() {
		super.Update();
		
		if(disappearTimeCounter < disappearTime) {
			disappearTimeCounter += Time.DeltaTime();
			CheckEndOfWorld();
		}
		else {
			if(MainProgram.IsOveredWorld(transform.GetPosition()));
				Destroy();
		}
	}
	
	public void Reset(Vector2 position) {
		transform.SetPosition(position);
		rigidBody.velocity = Vector2.Down().Mul(speed);
		disappearTimeCounter = 0;
		
	}
	
	private void CheckEndOfWorld() {
		Vector2 position = transform.GetPosition();
		if(position.x < worldLimitLeftUp.x) {
			if(rigidBody.velocity.x < 0) {
				SetRandomVelocity(0, 1, -1, 1);
			}
		}
		else if(position.x > worldLimitRightDown.x) {
			if(rigidBody.velocity.x > 0){
				SetRandomVelocity(-1, 0, -1, 1);
			}
		}
		
		if(position.y < worldLimitRightDown.y) {
			if(rigidBody.velocity.y < 0){
				SetRandomVelocity(-1, 1, 0, 1);
			}
		}
		else if(position.y > worldLimitLeftUp.y) {
			if(rigidBody.velocity.y > 0) {
				SetRandomVelocity(-1, 1, -1, 0);
			}
		}
	}
	
	void SetRandomVelocity(double minX, double maxX, double minY, double maxY) {
		Vector2 velocity = new Vector2(MainProgram.GetRandom().nextDouble(maxX - minX) + minX,
				MainProgram.GetRandom().nextDouble(maxY - minY) + minY).GetNormalized();
		float randomSpeed = (1 + (MainProgram.GetRandom().nextFloat(0.1f) - 0.05f)) * speed;
		rigidBody.velocity = velocity.Mul(randomSpeed);
	}
	
	public void Destroy() {
		isActive = false;
		GameManager.instance.ReturnPowerUp(this);
	}
	
}
