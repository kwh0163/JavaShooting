package Object;

import Util.Vector2;

public class Transform {
	private Vector2 position = Vector2.Zero();
	
	public GameObject gameObject;
	public Vector2 scale = Vector2.One();
	public float rotation = 0;
	
	public Transform(GameObject _origin) {
		gameObject = _origin;
	}
	
	public void SetPosition(Vector2 _position) {
		position = new Vector2(_position);
		
		if(gameObject.rigidBody != null)
			gameObject.rigidBody.SyncPosition(new Vector2(_position));
	}
	public Vector2 GetPosition() {
		return new Vector2(position);
	}
}
