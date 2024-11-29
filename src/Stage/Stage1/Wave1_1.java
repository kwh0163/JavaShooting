package Stage.Stage1;

import java.util.Timer;
import java.util.TimerTask;

import Object.GameObject;
import Object.CustomObject.TestObject;
import Stage.Wave;
import Util.Vector2;

public class Wave1_1 extends Wave{
	GameObject origin;
	
	boolean isWaveEnd = false;
	
	public Wave1_1(GameObject _origin) {
		super(_origin, 3);
		waveName = "Wave 1-1";
		origin = _origin;
	}

	@Override
	public void StartWave() {
		super.StartWave();
		TestObject testObject = new TestObject(new Vector2(700, 800), 50, true);
		testObject.movement.MoveCubic_6(20, new Vector2(100, -200));
		testObject.SetWave(this);
		
	}

}
