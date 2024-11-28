package Object.CustomObject;

import java.util.Random;

import Object.Enemy.NormalEnemy;
import Util.Time;
import Util.Vector2;

public class TestObject3 extends NormalEnemy {

	public TestObject3(Vector2 _position, int _hp) {
		super(_position, _hp);
		// TODO Auto-generated constructor stub
	}

	
	private double timeCounter = 999;
	
	@Override
	public void Update() {
		super.Update();
		if(timeCounter < 1) {
			timeCounter += Time.DeltaTime();
		}
		else {
			timeCounter = 0;
			attack.CircleAttackToPlayer(300, 4);
		}
	}
}
