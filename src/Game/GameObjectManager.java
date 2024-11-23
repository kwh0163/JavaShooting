package Game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import Object.GameObject;

public class GameObjectManager {
	private ArrayList<GameObject> gameObjects;
	private Deque<GameObject> awakeObjects;
	private Deque<GameObject> startObjects;
	
	public GameObjectManager() {
		awakeObjects = new ArrayDeque<GameObject>();
		startObjects = new ArrayDeque<GameObject>();
		gameObjects = new ArrayList<GameObject>();
	}
	
	public void AddGameObject(GameObject _gameObject) {
		gameObjects.add(_gameObject);
		startObjects.add(_gameObject);
		awakeObjects.add(_gameObject);
	}
	public void DestroyObject(GameObject _gameObject) {
		GameObject temp = _gameObject;
		gameObjects.remove(_gameObject);
		temp = null;
	}
	
	public void Awake() {
		while(!awakeObjects.isEmpty()) {
			GameObject tempGameObject = awakeObjects.pop();
			tempGameObject.Awake();
		}
	}
	public void Start() {
		while(!startObjects.isEmpty()) {
			GameObject tempGameObject = startObjects.pop();
			tempGameObject.Start();
		}
	}
	public void Update() {
		for(int i = 0;i<gameObjects.size();i++) {
			GameObject tempGameObject = gameObjects.get(i);
			if(tempGameObject.panelAdded && tempGameObject.isActive)
				tempGameObject.Update();
		}
	}
	public void FixedUpdate() {
		for(int i = 0;i<gameObjects.size();i++) {
			GameObject tempGameObject = gameObjects.get(i);
			if(tempGameObject.panelAdded && tempGameObject.isActive)
				tempGameObject.FixedUpdate();
		}
	}
	public void LateUpdate() {
		for(int i = 0;i<gameObjects.size();i++) {
			GameObject tempGameObject = gameObjects.get(i);
			if(tempGameObject.panelAdded && tempGameObject.isActive)
				tempGameObject.LateUpdate();
		}
	}

}
