package Object;

import MonoBehavior.RigidBody;
import Util.Vector2;

public class Transform {
	private Vector2 position = Vector2.Zero();
	public Vector2 pivot = new Vector2(0.5, 0.5);
	
	public GameObject gameObject;
	public Vector2 scale = Vector2.One();
	public float rotation = 0;
	
	public RigidBody rigidBody = null;
	
	public Transform childTransform = null;
	
	public Transform(GameObject _origin) {
		gameObject = _origin;
	}
	
	public void SetPosition(Vector2 _position) {
		position = new Vector2(_position);
		if(childTransform != null)
			childTransform.SetPosition(_position);
		if(rigidBody != null)
			rigidBody.SyncPosition(new Vector2(_position));
	}
	public Vector2 GetPosition() {
		return new Vector2(position);
	}
	public Vector2 GetScale() {
		return new Vector2(scale);
	}
	public void Look(Vector2 _target) {
		double dotProduct = _target.y;
		double magnitudeTarget = Math.sqrt(_target.x * _target.x + _target.y * _target.y);
		double rotate = Math.acos(dotProduct / (magnitudeTarget));
		
		if(_target.x >= 0)
			rotate = -rotate;
		
		float degree = (float)Math.toDegrees(rotate);
		
		
		rotation = degree;
	}
}
