package MonoBehavior;

import Game.MainProgram;
import Object.GameObject;
import Util.Debug;
import Util.Vector2;

public class CircleCollider extends Collider {
	private static final boolean DEBUG_MODE = false;
    public double radius;

    public CircleCollider(GameObject _object, RigidBody _rigid) {
        super(_object, _rigid);
        
        radius = transform.scale.x;
    }

    @Override
    protected boolean CheckCollision(Collider _collider) {
        if (_collider instanceof BoxCollider) {
            return CheckCollisionWithBox((BoxCollider) _collider);
        } else if (_collider instanceof CircleCollider) {
            return CheckCollisionWithCircle((CircleCollider) _collider);
        }
        return false;
    }
    
    private boolean CheckCollisionWithBox(BoxCollider box) {
        return box.CheckCollisionWithCircle(this);
    }

    private boolean CheckCollisionWithCircle(CircleCollider other) {
        Vector2 centerA = rigidBody.GetPosition();
        Vector2 centerB = other.rigidBody.GetPosition();

        double combinedRadius = (this.radius + other.radius) * MainProgram.defaultPixel * 0.5;

        
        double distanceX = centerA.x - centerB.x;
        double distanceY = centerA.y - centerB.y;
        
        boolean isCollision = (distanceX * distanceX + distanceY * distanceY) < (combinedRadius * combinedRadius);
        	
        if(DEBUG_MODE) {
        	if(isCollision) {
        		Debug.Log(String.format("distance : %f", Vector2.Distance(centerA, centerB)));
        		Debug.Log(String.format("a radius : %f, b radius : %f, combined Radius : %f, defaultPixel : %d", 
        						this.radius, other.radius, combinedRadius, MainProgram.defaultPixel));
        	}
        }
        		
        return isCollision;
    }
}
