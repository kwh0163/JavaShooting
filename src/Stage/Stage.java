package Stage;


import java.util.ArrayList;

import MonoBehavior.MonoBehavior;
import Object.GameObject;
import Util.Debug;
import Util.Time;

public class Stage extends MonoBehavior{
	static final boolean DEBUG_MODE = false;
	int currentWave = 0;
	
	boolean isStageStarted = false;
	double stageStartCounter = 0;
	double stageStartTime = 9;
	
	
	boolean isStageCleared = false;
	ArrayList<Wave> waveList = new ArrayList<Wave>();
	public Stage(GameObject _object) {
		super(_object);
	}

	public void AddWave(Wave _wave) {
		waveList.add(_wave);
	}
	
	@Override
	public void Update() {
		if(isStageCleared)
			return;
		
		if(!isStageStarted) {
			if(stageStartCounter < stageStartTime) {
				stageStartCounter += Time.DeltaTime();
				return;
			}
			else {
				isStageStarted = true;
				waveList.get(currentWave).StartWave();
			}
		}
		
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