package Object;

import MonoBehavior.BoxCollider;
import MonoBehavior.Collider;
import MonoBehavior.RigidBody;
import Util.Layer;
import Util.Tag;
import Util.Vector2;

public class Block extends GameObject {

	RigidBody rigidBody;
	Collider collider;
	
	public Block(Vector2 _position) {
		super(_position);
		
		name = "Block";
		layer = Layer.Block;
		tag = Tag.Block;
		
		rigidBody = new RigidBody(this);
		collider = new BoxCollider(this, rigidBody);
		
	}

}
