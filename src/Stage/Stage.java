package Stage;


import java.util.ArrayList;

import MonoBehavior.MonoBehavior;
import Object.GameObject;
import Util.Debug;

public class Stage extends MonoBehavior{
	static final boolean DEBUG_MODE = false;
	int currentWave = 0;
	
	boolean isStageCleared = false;
	ArrayList<Wave> waveList = new ArrayList<Wave>();
	public Stage(GameObject _object) {
		super(_object);
	}

	public void AddWave(Wave _wave) {
		waveList.add(_wave);
	}
	
	@Override
	public void Start() {
		waveList.get(currentWave).StartWave();
	}
	@Override
	public void Update() {
		if(isStageCleared)
			return;
		
		if(CheckCurrentStageCleared()) {
			waveList.set(currentWave, null);
			currentWave++;
			if(currentWave < waveList.size()) {
				waveList.get(currentWave).StartWave();
			}
			else {
				isStageCleared = true;
				if(DEBUG_MODE)
					Debug.Log("Stage Cleared");
			}
		}
	}
	boolean CheckCurrentStageCleared() {
		return waveList.get(currentWave).IsWaveEnd();
	}
}