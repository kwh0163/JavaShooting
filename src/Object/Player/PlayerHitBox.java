package Object.Player;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import MonoBehavior.CircleCollider;
import MonoBehavior.Collider;
import MonoBehavior.RigidBody;
import Object.GameObject;
import Util.*;

public class PlayerHitBox extends GameObject{
	
	private Player origin;
	
	RigidBody rigidBody;
	Collider collider;

	public PlayerHitBox(Vector2 _position, Player _origin) {
		super(_position);

		origin = _origin;
		
		name = "PlayerHitBox";
		layer = Layer.PlayerHitBox;
		tag = Tag.PlayerHitBox;
		transform.scale = new Vector2(0.3, 0.3);
		sprite.sortIndex = 6;
		
		sprite.image = GetBufferedImage("Hit.png");
		
		sprite.color = Color.RED;
		
		rigidBody = new RigidBody(this);
		collider = new CircleCollider(this, rigidBody);
		collider.checkLayers.clear();
		collider.checkLayers.add(Layer.Enemy);
		((CircleCollider)collider).radius = 0.2;
	}
	
	@Override
	public void OnCollisionEnter(GameObject _collision) {
		super.OnCollisionEnter(_collision);
		if(_collision.CompareTag(Tag.Enemy)) {
			TryDamage();
		}
	}
	
	public boolean TryDamage() {
		return origin.TryDamage();
	}
}
