package Object;

import Game.GamePanel;
import Util.Vector2;

public class GameObject {
	public String name = "";
	public Transform transform;
	public Sprite sprite;
	public RigidBody rigidBody;
	
	public GameObject(Vector2 _position) {
		transform = new Transform(this);
		transform.SetPosition(_position);
		sprite = new Sprite(null);
		rigidBody = new RigidBody(transform);
		GamePanel.AddObject(this);
	}

}
