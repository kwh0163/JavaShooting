package Object;

import java.util.ArrayList;

import Game.MainProgram;
import MonoBehavior.MonoBehavior;
import Util.*;

public class GameObject {
	private static boolean DEBUG_MODE = false;
	
	public boolean panelAdded = false;
	
	public String name = "GameObject";
	public Transform transform;
	public Sprite sprite;
	public Layer layer;
	public Tag tag;
	public boolean CompareTag(Tag _tag) {
		return tag == _tag;
	}
	public boolean isActive = true;
	
	public ArrayList<MonoBehavior> behaviors;
	public void AddBehavior(MonoBehavior _behavior) {
		behaviors.add(_behavior);
	}
	
	public GameObject(Vector2 _position) {
		if(DEBUG_MODE)
			System.out.println(name + " is Instantiated");
		
		behaviors = new ArrayList<MonoBehavior>();
		
		layer = Layer.Default;
		tag = Tag.Default;
		transform = new Transform(this);
		transform.SetPosition(_position);
		sprite = new Sprite(null);
		
		MainProgram.gameObjectManager.AddGameObject(this);
	}
	public void OnCollisionEnter(GameObject collisionObject) {
		
	}

	public void OnCollisionExit(GameObject collisisonObject) {
		
	}
	
	public void Awake() {
		panelAdded = true;
		MainProgram.panel.AddObject(this);
		
		for(int i = 0;i<behaviors.size();i++) {
			behaviors.get(i).Awake();
			if(DEBUG_MODE)
				System.out.println(behaviors.get(i).getClass().toString() + " of " + name + " Awaked");
		}
	}
	public void Start() {
		
		for(int i = 0;i<behaviors.size();i++) {
			behaviors.get(i).Start();
			if(DEBUG_MODE)
				System.out.println(behaviors.get(i).getClass().toString() + " of " + name + " Started");
		}
	}

	public void Update() {
		if(!panelAdded)
			System.out.println(name + " not added in panel");
		
		for(int i = 0;i<behaviors.size();i++) {
			behaviors.get(i).Update();
			if(DEBUG_MODE)
				System.out.println(behaviors.get(i).getClass().toString() + " of " + name + " Updated");
		}
	}

	public void FixedUpdate() {
		for(int i = 0;i<behaviors.size();i++) {
			behaviors.get(i).FixedUpdate();
			if(DEBUG_MODE)
				System.out.println(behaviors.get(i).getClass().toString() + " of " + name + " FixedUpdated");
		}
	}
	public void LateUpdate() {
		for(int i = 0;i<behaviors.size();i++) {
			behaviors.get(i).LateUpdate();
			if(DEBUG_MODE)
				System.out.println(behaviors.get(i).getClass().toString() + " of " + name + " LateUpdated");
		}
	}
	
	public void OnDestroy() 
	{
		isActive = false;
		MainProgram.panel.RemoveObject(this);
		
		for(int i = 0;i<behaviors.size();i++) {
			MonoBehavior temp = behaviors.get(i);
			temp.OnDestroy();
			temp = null;
			if(DEBUG_MODE)
				System.out.println(behaviors.get(i).getClass().toString() + " of " + name + " Destroyed");
		}
		transform = null;
		sprite = null;
		behaviors = null;
		MainProgram.gameObjectManager.DestroyObject(this);
	}
	
	public static void Destroy(GameObject _gameObject) {
		_gameObject.OnDestroy();
	}

}
