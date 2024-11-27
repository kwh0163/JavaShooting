package Util;

import java.util.function.Supplier;

import MonoBehavior.MonoBehavior;
import Object.GameObject;

public class WaitUntil extends MonoBehavior{
	Runnable run;
	Supplier<Boolean> condition;
	
	public WaitUntil(GameObject _gameObject, Runnable _run, Supplier<Boolean> _condition) {
		super(_gameObject);
		run = _run;
		condition = _condition;
	}
	
	@Override
	public void Update() {
		super.Update();
		if(condition.get()) {
			run.run();
			OnDestroy();
		}
	}
	
	@Override
	public void OnDestroy() {
		super.OnDestroy();
		run = null;
		condition = null;
	}
}