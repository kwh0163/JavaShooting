package Object;

import Game.MainProgram;
import Util.Vector2;

public class CircleCollider extends Collider {
    public double radius;

    public CircleCollider(GameObject _object) {
        super(_object);
        radius = _object.transform.scale.x;
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
        Vector2 centerA = gameObject.transform.GetPosition();
        Vector2 centerB = other.gameObject.transform.GetPosition();

        double combinedRadius = (this.radius + other.radius) * MainProgram.defaultPixel;

        double distanceX = centerA.x - centerB.x;
        double distanceY = centerA.y - centerB.y;

        return (distanceX * distanceX + distanceY * distanceY) < (combinedRadius * combinedRadius);
    }
}
