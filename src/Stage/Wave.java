package Stage;

import MonoBehavior.MonoBehavior;
import Object.GameObject;
import Util.Debug;
import Util.Time;

public abstract class Wave extends MonoBehavior {
	static final boolean DEBUG_MODE = true;
	
	public String waveName = "Wave";
	
	double waveTime;
	double waveTimeCounter = 0;
	boolean isWaveEnd = false;
	boolean isWaveStarted = false;
	public Wave(GameObject _gameObject, double _waveTime) {
		super(_gameObject);
		waveTime = _waveTime;
	}
	
	public boolean IsWaveEnd() {
		return isWaveEnd;
	}
	
	public void StartWave() {
		if(DEBUG_MODE) 
			Debug.Log(String.format("%s is started.", waveName));
		isWaveStarted = true;
	}
	public void EndWave() {
		if(DEBUG_MODE) 
			Debug.Log(String.format("%s is end.", waveName));
		
		isWaveEnd = true;
	}
	@Override
	public void Update() {
		if(isWaveEnd)
			return;
		if(isWaveStarted) {
			if(waveTimeCounter < waveTime){
				waveTimeCounter += Time.DeltaTime();
			}
			else {
				EndWave();
			}
		}
	}
}
