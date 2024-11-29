package Stage.Stage1;

import Object.GameObject;
import Object.CustomObject.*;
import Stage.Wave;
import Util.Vector2;

public class Wave1_3 extends Wave{

	public Wave1_3(GameObject _gameObject) {
		super(_gameObject, 5);
		waveName = "Wave 1-3";
	}
	
	@Override
	public void StartWave() {
		super.StartWave();
		
		(new TestObject3(new Vector2(200, 400), 50,true)).SetWave(this);
	}

}
