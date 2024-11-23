package Object.Player;

import Object.AmmoObject;
import Object.GameObject;
import Util.*;

public class PlayerAmmo extends AmmoObject{
	
	public int poolIndex;
	
	public PlayerAmmo(Vector2 _position) {
		super(_position);
		
		layer = Layer.PlayerAmmo;
	}
	
	@Override
	public void Awake() {
		super.Awake();

		collider.checkLayers.clear();
		collider.checkLayers.add(Layer.Enemy);
	}
	
	@Override
	public void OnCollisionEnter(GameObject _collision) {
		if(_collision.CompareTag(Tag.Enemy)) {
			Destroy();
			PlayerAttack.instance.ReturnAmmo(this);
		}
	}
}
