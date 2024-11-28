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
	}
	
	private double patternTime = 2;
	private double patternTimeCounter = 50;
	private double attackTime = 0.3;
	private double attackTimeCounter = 50;
	private int attackCount = 3;
	private int count = 0;
	
	private float counter = 0;
	@Override
	public void Update() {
		super.Update();
		
		if(patternTimeCounter < patternTime) {
			patternTimeCounter += Time.DeltaTime();
		}
		else {
			if(count < attackCount) {
				if(attackTimeCounter < attackTime) {
					attackTimeCounter += Time.DeltaTime();
				}
				else {
					attackTimeCounter = 0;
					attack.PieAttackToPlayer(200, 3, 30);
					count++;
				}
			}
			else {
				count = 0;
				patternTimeCounter = 0;
				attackTimeCounter = 0;
			}
		}
		
		
	}

}
