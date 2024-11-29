package Stage.Stage1;

import Game.GameManager;
import Object.GameObject;
import Stage.Stage;

public class Stage1 extends Stage {
	
	public Stage1(GameObject _object) {
		super(_object);
		AddWave(new Wave1_1(_object));
		AddWave(new Wave1_2(_object));
		AddWave(new Wave1_3(_object));
		
		GameManager.instance.Audio.PlayBGM(1);
	}

}
