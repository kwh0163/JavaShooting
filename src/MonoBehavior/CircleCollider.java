package MonoBehavior;

import Game.MainProgram;
import Object.GameObject;
import Util.Vector2;

public class CircleCollider extends Collider {
    public double radius;

    public CircleCollider(GameObject _object, RigidBody _rigid) {
        super(_object, _rigid);
    }
    
    @Override
    public void Awake() {
    	super.Awake();
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

        double combinedRadius = (this.radius + other.radius) * MainProgram.defaultPixel;

        double distanceX = centerA.x - centerB.x;
        double distanceY = centerA.y - centerB.y;

        return (distanceX * distanceX + distanceY * distanceY) < (combinedRadius * combinedRadius);
    }
}
