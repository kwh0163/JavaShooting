package Object.Enemy.Attack;

import Game.GameManager;
import Object.Enemy.Enemy;
import Object.Enemy.Attack.Ammo.NormalEnemyAmmo;
import Util.Vector2;

public class NormalAmmoEnemyAttack{
	protected Enemy origin;
	
	public NormalAmmoEnemyAttack(Enemy _origin) {
		origin = _origin;
	}
	
	protected NormalEnemyAmmo GetAmmo(Vector2 _direction) {
		return GameManager.instance.Enemy.GetNormalAmmo(origin.transform.GetPosition(), _direction);
	}
	
	public void PlayerTargettingAttack(float _speed) {
		Vector2 direction = GameManager.instance.Player.transform.GetPosition().Sub(origin.transform.GetPosition()).GetNormalized();
		GetAmmo(direction).SetSpeed(_speed);
	}
	public void StraightAttack(Vector2 _velocity, float _speed) {
		GetAmmo(_velocity).SetSpeed(_speed);
	}
	
	public void PieAttack(Vector2 _direction, int speed, int count, int degree) {
		Vector2 direction1 = Vector2.Zero();
		Vector2 direction2 = Vector2.Zero();
		if(count % 2 == 0) {
			direction1 = _direction.rotate(degree / 2);
			direction2 = _direction.rotate(-(degree / 2));
		}
		else {
			GetAmmo(_direction).SetSpeed(speed);
			direction1 = _direction.rotate(degree);
			direction2 = _direction.rotate(-degree);
		}
		for(int i = 0;i<count / 2;i++) {
			GetAmmo(direction1).SetSpeed(speed);
			GetAmmo(direction2).SetSpeed(speed);
			direction1 = direction1.rotate(degree);
			direction2 = direction2.rotate(-degree);
		}
	}
	
	public void PieAttackToPlayer(int speed, int count, int degree) {
		Vector2 direction = GameManager.instance.Player.transform.GetPosition().Sub(origin.transform.GetPosition()).GetNormalized();

		PieAttack(direction, speed, count, degree);
	}
	
	public void EightWayAttackWithDegree() {
		
	}
	public void EightWayAttackToPlayer(int _speed) {
		Vector2 direction = GameManager.instance.Player.transform.GetPosition().Sub(origin.transform.GetPosition()).GetNormalized();
		for(int i = 0;i < 8;i++) {
			StraightAttack(direction, _speed);
			direction = direction.rotate(45);
		}
	}
}
