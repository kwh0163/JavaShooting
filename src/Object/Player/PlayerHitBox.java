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
	
	RigidBody rigidBody;
	Collider collider;

	public PlayerHitBox(Vector2 _position) {
		super(_position);

		name = "PlayerHitBox";
		layer = Layer.PlayerHitBox;
		tag = Tag.PlayerHitBox;
		transform.scale = new Vector2(0.3, 0.3);
		sprite.sortIndex = 6;
		
		try {
			sprite.image = ImageIO.read(new File("Image\\Hit.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sprite.color = Color.RED;
		
		rigidBody = new RigidBody(this);
		collider = new CircleCollider(this, rigidBody);
		collider.checkLayers.clear();
		((CircleCollider)collider).radius *= 0.9;
	}
}
