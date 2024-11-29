package Object.Enemy;

import MonoBehavior.MonoBehavior;
import Object.GameObject;
import Util.CustomMath;
import Util.Debug;
import Util.Time;
import Util.Vector2;

public class EnemyMovement extends MonoBehavior{
	
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
	boolean isReverse = false;
	
	public EnemyMovement(GameObject _object) {
		super(_object);
		origin = (Enemy)_object;
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
		
		if(currentMoveType == MoveType.Cubic6) {
			double x = CustomMath.Lerp(defaultPosition.x, targetPosition.x, value);
			double yValue = CustomMath.CubicFunc6(value);
			if(isReverse)
				yValue = 1- yValue;
			double y = CustomMath.Lerp(defaultPosition.y, targetPosition.y, yValue);
			origin.transform.SetPosition(new Vector2(x, y));
			return;
		}
		else if(currentMoveType == MoveType.Cubic75) {
			double x = CustomMath.Lerp(defaultPosition.x, targetPosition.x, value);
			double yValue = CustomMath.CubicFunc75(value);
			if(isReverse)
				yValue = 1- yValue;
			double y = CustomMath.Lerp(defaultPosition.y, targetPosition.y, yValue);
			origin.transform.SetPosition(new Vector2(x, y));
			return;
		}
		else if(currentMoveType == MoveType.Straight) {
			origin.transform.SetPosition(Vector2.Lerp(value, defaultPosition, targetPosition));
			return;
		}
		else if(currentMoveType == MoveType.Quadric) {
			double x = CustomMath.Lerp(defaultPosition.x, targetPosition.x, value);
			double yValue = value * value;
			if(isReverse)
				yValue = 1 - yValue;
			double y = CustomMath.Lerp(defaultPosition.y, targetPosition.y, value);
			origin.transform.SetPosition(new Vector2(x, y));
			return;
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
		isReverse = defaultPosition.x < targetPosition.x;
	}

}
