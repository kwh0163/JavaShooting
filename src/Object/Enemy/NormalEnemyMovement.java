package Object.Enemy;

import MonoBehavior.MonoBehavior;
import Object.GameObject;
import Util.CustomMath;
import Util.Debug;
import Util.Time;
import Util.Vector2;

public class NormalEnemyMovement extends MonoBehavior{
	
	enum MoveType{
		None,
		Cubic6,
		Cubic75,
		Straight,
		Quadric
	}
	
	MoveType currentMoveType = MoveType.None;
	Enemy origin;
	double moveTimeCounter = 0;
	double moveTime = 0;
	Vector2 defaultPosition = Vector2.Zero();
	Vector2 targetPosition = Vector2.Zero();
	boolean isReverseX = false;
	boolean isReverseY = false;
	
	public NormalEnemyMovement(GameObject _object) {
		super(_object);
		origin = (Enemy)_object;
	}
	
	public void OnDestroy() {
		currentMoveType = MoveType.None;
	}
	
	@Override
	public void FixedUpdate() {
		if(!origin.isActive) {
			currentMoveType = MoveType.None;
			return;
		}
		if(currentMoveType == MoveType.None)
			return;
		
		if(moveTimeCounter < moveTime) {
			moveTimeCounter += Time.FixedDeltaTime();
		}
		else {
			if(origin.isActive) {
				origin.OnDestroy();
				return;
			}
		}
		double value = moveTimeCounter / moveTime;
		Vector2 newPosition = Vector2.Zero();
		
		if(currentMoveType == MoveType.Cubic6) {
			double xValue = isReverseX ? (1 - value) : value;
			
			newPosition.x = CustomMath.Lerp(defaultPosition.x, targetPosition.x, xValue);
			double yValue = CustomMath.CubicFunc6(value);
			newPosition.y = CustomMath.Lerp(defaultPosition.y, targetPosition.y, yValue);
			if(isReverseY)
				newPosition.y = 1 - newPosition.y;
		}
		else if(currentMoveType == MoveType.Cubic75) {
			newPosition.x = CustomMath.Lerp(defaultPosition.x, targetPosition.x, value);
			if(isReverseX)
				value = 1 - value;
			double yValue = CustomMath.CubicFunc75(value);
			if(isReverseY)
				yValue = 1 - yValue;
			newPosition.y = CustomMath.Lerp(defaultPosition.y, targetPosition.y, 1 - yValue);
		}
		else if(currentMoveType == MoveType.Straight) {
			newPosition = Vector2.Lerp(value, defaultPosition, targetPosition);
		}
		else if(currentMoveType == MoveType.Quadric) {
			newPosition.x = CustomMath.Lerp(defaultPosition.x, targetPosition.x, value);
			if(isReverseX)
				value = 1 - value;
			double yValue = isReverseY ? ((value - 1) * (value - 1)) : value * value;
			newPosition.y = CustomMath.Lerp(defaultPosition.y, targetPosition.y, 1 - yValue);
		}
		
		origin.transform.SetPosition(newPosition);
	}
	public void StopMove() {
		if(CheckIsMoving()) {
			currentMoveType = MoveType.None;
		}
	}
	
	public void Move(double _moveTime, Vector2 _targetPosition, MoveType _moveType) {
		if(CheckIsMoving())
			return;
		Reset(_moveTime, _targetPosition);
		currentMoveType = _moveType;
	}
	
	public void MoveCubic_75(double _moveTime, Vector2 _targetPosition) {
		if(CheckIsMoving())
			return;
		Reset(_moveTime, _targetPosition);
		currentMoveType = MoveType.Cubic75;
		
	}
	public void MoveCubic_6(double _moveTime, Vector2 _targetPosition) {
		if(CheckIsMoving())
			return;
		Reset(_moveTime, _targetPosition);
		currentMoveType = MoveType.Cubic6;
		
	}
	public void MoveStraight(double _moveTime, Vector2 _targetPosition) {
		if(CheckIsMoving())
			return;
		Reset(_moveTime, _targetPosition);
		currentMoveType = MoveType.Straight;
	}
	public void MoveQuadric(double _moveTime, Vector2 _targetPosition) {
		if(CheckIsMoving())
			return;
		Reset(_moveTime, _targetPosition);
		currentMoveType = MoveType.Quadric;
	}
	
	boolean CheckIsMoving() {
		if(currentMoveType != MoveType.None){
			Debug.Log(origin.name + " is moving now");
			return true;
		}
		return false;
	}
	void Reset(double _movetime, Vector2 _targetPosition) {
		moveTimeCounter = 0;
		moveTime = _movetime;
		defaultPosition = origin.transform.GetPosition();
		targetPosition = _targetPosition;
		isReverseX = defaultPosition.x > targetPosition.x;
 		isReverseY = ((isReverseX && defaultPosition.y < targetPosition.y) || (!isReverseX && defaultPosition.y > targetPosition.y));
	}

}
