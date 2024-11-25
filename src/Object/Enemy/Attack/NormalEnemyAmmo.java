package Object.Enemy.Attack;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import MonoBehavior.CircleCollider;
import Util.Vector2;

public class NormalEnemyAmmo extends EnemyAmmo {

	
	public NormalEnemyAmmo(Vector2 _position) {
		super(_position);
		
		transform.scale = new Vector2(0.3, 0.3);
		
		sprite.color = Color.black;
		
		try {
			sprite.image = ImageIO.read(new File("Image\\Bullet.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		collider = new CircleCollider(this, rigidBody);
		((CircleCollider)collider).radius = 0.2;
	}

	
	
}
