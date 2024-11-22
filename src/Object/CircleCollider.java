package Object;

import Game.MainProgram;
import Util.Vector2;

public class CircleCollider extends Collider {
    public double radius; // 원의 반지름

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
        // BoxCollider가 CircleCollider와의 충돌을 처리하도록 위임
        return box.CheckCollisionWithCircle(this);
    }

    private boolean CheckCollisionWithCircle(CircleCollider other) {
        Vector2 centerA = gameObject.transform.GetPosition();
        Vector2 centerB = other.gameObject.transform.GetPosition();

        double combinedRadius = (this.radius + other.radius) * MainProgram.defaultPixel;

        // 두 원의 중심 간 거리 계산
        double distanceX = centerA.x - centerB.x;
        double distanceY = centerA.y - centerB.y;

        // 충돌 여부 반환
        return (distanceX * distanceX + distanceY * distanceY) < (combinedRadius * combinedRadius);
    }
}
