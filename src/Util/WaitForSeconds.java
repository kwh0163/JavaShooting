package Util;

import MonoBehavior.MonoBehavior;
import Object.GameObject;

public class WaitForSeconds extends MonoBehavior{
	Runnable run;
	double time;
	double timeCounter = 0;
	
	public WaitForSeconds(GameObject _gameObject, Runnable _run, double _time) {
		super(_gameObject);
		run = _run;
		time = _time;
	}
	
	@Override
	public void Update() {
		super.Update();
		if(timeCounter < time) {
			timeCounter += Time.DeltaTime();
		}
		else {
			run.run();
			OnDestroy();
		}
	}
	
	@Override
	public void OnDestroy() {
		super.OnDestroy();
		
		run = null;
	}
}
