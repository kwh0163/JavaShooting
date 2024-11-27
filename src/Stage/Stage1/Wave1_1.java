package Stage.Stage1;

import Object.GameObject;
import Object.CustomObject.TestObject;
import Stage.Wave;
import Util.Debug;
import Util.Vector2;
import Util.WaitUntil;

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
		TestObject testObject = new TestObject(new Vector2(400, 400), 50);
		testObject.SetWave(this);
	}

}
