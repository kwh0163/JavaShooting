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
	public boolean isGrazed = false;
	
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
	
	public void Reset(Vector2 _position) {
		transform.SetPosition(_position);
		isActive = true;
		isGrazed = false;
		if(!GameManager.instance.Player.health.isHitable) {
			Destroy();
			GameManager.instance.Enemy.ReturnAmmo(this);
		}
	}

}
