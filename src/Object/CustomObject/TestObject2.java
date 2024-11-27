package Object.CustomObject;

import Object.Enemy.NormalEnemy;
import Object.Enemy.Attack.NormalAmmoEnemyAttack;
import Util.Layer;
import Util.Tag;
import Util.Time;
import Util.Vector2;

public class TestObject2 extends NormalEnemy{

	public TestObject2(Vector2 _position, int _hp) {
		super(_position, _hp);
		
		name = "Test Object";
		tag = Tag.Enemy;
		layer = Layer.Enemy;
		
		attack = new NormalAmmoEnemyAttack(this);
	}
	
	NormalAmmoEnemyAttack attack;

	
	private float counter = 0;
	@Override
	public void Update() {
		super.Update();
		
		if(counter < 0.2) {
			counter += Time.DeltaTime();
		}
		else {
			counter = 0;
			attack.PieAttackToPlayer(200, 3, 15);
			//System.out.println("Attack");
		}
		
		
	}

}
