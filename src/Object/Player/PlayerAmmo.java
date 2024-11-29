package Object.Player;

import Game.GameManager;
import Game.MainProgram;
import Object.AmmoObject;
import Object.GameObject;
import Object.Enemy.Enemy;
import Util.*;

public class PlayerAmmo extends AmmoObject{
	
	public int poolIndex;
	
	public int damage = 1;
	
	public PlayerAmmo(Vector2 _position) {
		super(_position);
		
		layer = Layer.PlayerAmmo;
		sprite.alpha = 0.5f;
	}
	
	@Override
	public void Awake() {
		super.Awake();

		collider.checkLayers.clear();
		collider.checkLayers.add(Layer.Enemy);
	}
	
	@Override
	public void FixedUpdate() {
		super.FixedUpdate();
		
		if(MainProgram.IsOveredWorld(rigidBody.position)) {
			Destroy();
			PlayerAttack.instance.ReturnAmmo(this);
		}
	}
	
	@Override
	public void OnCollisionEnter(GameObject _collision) {
		if(_collision.CompareTag(Tag.Enemy)) {
			Destroy();
			PlayerAttack.instance.ReturnAmmo(this);
			((Enemy)_collision).Damage(damage);
			GameManager.instance.Audio.PlaySound(AudioType.EnemyHit, 0.7f);
		}
	}
	
}
