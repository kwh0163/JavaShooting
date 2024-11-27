package Object.Enemy.Attack.Ammo;

import Game.GameManager;
import Game.MainProgram;
import Object.AmmoObject;
import Object.GameObject;
import Object.Player.*;
import Util.Layer;
import Util.Tag;
import Util.Vector2;

public class EnemyAmmo extends AmmoObject{
	
	public int poolIndex;
	
	protected float speed = 500;
	
	public EnemyAmmo(Vector2 _position) {
		super(_position);
		
		layer = Layer.EnemyAmmo;
		
		sprite.sortIndex = 5;
	}
	
	@Override
	public void Awake() {
		super.Awake();
		collider.checkLayers.clear();
		collider.checkLayers.add(Layer.PlayerHitBox);
		
	}
	
	@Override
	public void Update() {
		super.Update();
		rigidBody.velocity = GetVelocity().Mul(speed);
	}

	
	@Override
	public void FixedUpdate() {
		super.FixedUpdate();
		
		if(MainProgram.IsOveredWorld(rigidBody.position)) {
			Destroy();
			GameManager.instance.Enemy.ReturnAmmo(this);
		}
	}
	@Override
	public void OnCollisionEnter(GameObject _collision) {
		if(_collision.CompareTag(Tag.PlayerHitBox)) {
			if(((PlayerHitBox)_collision).TryDamage()) {
				Destroy();
				GameManager.instance.Enemy.ReturnAmmo(this);
			}
		}
	}
	
	public void SetSpeed(float _speed) {
		speed = _speed;
	}
	
	protected Vector2 GetVelocity() {
		return rigidBody.velocity.GetNormalized();
	}
	
	public void Reset(Vector2 _position, Vector2 _firstDirection) {
		transform.SetPosition(_position);
		rigidBody.velocity = _firstDirection.GetNormalized();
		isActive = true;
	}

}
