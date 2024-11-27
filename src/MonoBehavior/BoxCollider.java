package MonoBehavior;

import Game.MainProgram;
import Object.GameObject;
import Util.Vector2;

public class BoxCollider extends Collider {
	
	public Vector2 colliderSize;
	
	public BoxCollider(GameObject gameObject, RigidBody _rigid) {
		super(gameObject, _rigid);
		
		colliderSize = transform.GetScale();
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

    public boolean CheckCollisionWithBox(BoxCollider other) {
        Vector2 posA = GetBoxPivotPosition(rigidBody.GetPosition(), this);
        Vector2 posB = GetBoxPivotPosition(other.rigidBody.GetPosition(), other);

        Vector2 sizeA = colliderSize.Mul(MainProgram.defaultPixel);
        Vector2 sizeB = other.colliderSize.Mul(MainProgram.defaultPixel);

        // AABB 충돌 검사
        return posA.x < posB.x + sizeB.x &&
               posA.x + sizeA.x > posB.x &&
               posA.y < posB.y + sizeB.y &&
               posA.y + sizeA.y > posB.y;
    }

    public boolean CheckCollisionWithCircle(CircleCollider circle) {
        Vector2 boxPos = GetBoxPivotPosition(rigidBody.GetPosition(), this);
        Vector2 boxSize = colliderSize.Mul(MainProgram.defaultPixel);

        Vector2 circleCenter = circle.rigidBody.GetPosition();
        double circleRadius = circle.radius * MainProgram.defaultPixel;

        // 원의 중심을 사각형의 영역으로 제한
        double closestX = Math.max(boxPos.x, Math.min(circleCenter.x, boxPos.x + boxSize.x));
        double closestY = Math.max(boxPos.y, Math.min(circleCenter.y, boxPos.y + boxSize.y));

        // 원의 중심과 가장 가까운 점 간 거리 계산
        double distanceX = circleCenter.x - closestX;
        double distanceY = circleCenter.y - closestY;

        // 충돌 여부 반환
        return (distanceX * distanceX + distanceY * distanceY) < (circleRadius * circleRadius);
    }
    
    private Vector2 GetBoxPivotPosition(Vector2 _position, BoxCollider _object) {
    	Vector2 position = _position;
    	position.x -= MainProgram.defaultPixel * _object.colliderSize.x * _object.colliderPivot.x;
    	position.y -= MainProgram.defaultPixel * _object.colliderSize.y * _object.colliderPivot.y;
    	return position;
    }
}
