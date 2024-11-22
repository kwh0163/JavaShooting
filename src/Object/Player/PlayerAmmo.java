package Object.Player;

import Object.GameObject;
import Util.Layer;
import Util.Tag;
import Util.Vector2;

public class PlayerAmmo extends GameObject{
	public PlayerAmmo(Vector2 _position) {
		super(_position);
	}
	
	protected void Init() {
		layer = Layer.PlayerAmmo;
		collider.checkLayers.clear();
		collider.checkLayers.add(Layer.Enemy);
	}

	@Override
	public void Destroy() {
		isActive = false;
	}
	@Override
	public void OnCollisionEnter(GameObject _collision) {
		if(_collision.CompareTag(Tag.Enemy)) {
			Destroy();
		}
	}
}
