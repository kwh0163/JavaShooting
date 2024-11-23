package MonoBehavior;

import Object.GameObject;
import Object.Transform;

public abstract class MonoBehavior {
	
	public GameObject gameObject;
	public Transform transform;
	
	public MonoBehavior(GameObject _object) {
		gameObject = _object;
		transform = _object.transform;
		_object.AddBehavior(this);
	}
 	
	public void Awake() {
		
	}
	public void Start() {
		
	}
	public void Update() {
		
	}
	public void FixedUpdate() {
		
	}
	public void LateUpdate() {
		
	}
	public void OnDestroy() {
		
	}
}
