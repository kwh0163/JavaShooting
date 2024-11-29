package Stage.Stage1;

import Object.GameObject;
import Object.CustomObject.TestObject2;
import Stage.Wave;
import Util.Vector2;

public class Wave1_2 extends Wave{


	public Wave1_2(GameObject _gameObject) {
		super(_gameObject, 3);
		waveName = "Wave 1-2";
	}

	@Override
	public void StartWave() {
		super.StartWave();
		
		(new TestObject2(new Vector2(600, 500), 50, true)).SetWave(this);
	}

}
