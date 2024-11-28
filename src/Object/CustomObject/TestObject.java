package Object.CustomObject;

import java.awt.Color;

import MonoBehavior.BoxCollider;
import MonoBehavior.Collider;
import MonoBehavior.RigidBody;
import Object.GameObject;
import Object.Enemy.Enemy;
import Object.Enemy.NormalEnemy;
import Object.Enemy.Attack.NormalAmmoEnemyAttack;
import Util.*;

public class TestObject extends NormalEnemy{
	
	public TestObject(Vector2 _position, int _hp) {
		super(_position, _hp);
		
		sprite.image = GetBufferedImage("EnemyPlaneBlue.png");
	}

	
	private float counter = 0;
	@Override
	public void Update() {
		super.Update();
		
		if(counter < 1) {
			counter += Time.DeltaTime();
		}
		else {
			counter = 0;
			attack.CircleAttackToPlayer(100, 30);
			//System.out.println("Attack");
		}
		
		
	}
	
}
