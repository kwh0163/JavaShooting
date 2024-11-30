package Object.Enemy.Enemies;

import MonoBehavior.BoxCollider;
import MonoBehavior.RigidBody;
import Object.Enemy.Enemy;
import Util.Layer;
import Util.Tag;
import Util.Vector2;

public class Boss1 extends Enemy{
	
	public RigidBody rigidBody;
	public BoxCollider collider;
	
	public Boss1() {
		super(new Vector2(450, 800), 3000, true);
		
		name = "Boss";
		transform.scale = new Vector2(5, 3);
		transform.rotation = 180;
		tag = Tag.Enemy;
		layer = Layer.Enemy;
		
		rigidBody = new RigidBody(this);
		collider = new BoxCollider(this, rigidBody);
		collider.checkLayers.clear();
	}
	
	@Override
	public void Update() {
		
	}

}
