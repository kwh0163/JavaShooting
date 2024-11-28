package Object.Enemy.Attack;

import Game.GameManager;
import Object.Enemy.Enemy;
import Object.Enemy.Attack.Ammo.NormalEnemyAmmo;
import Util.NormalAmmoEnum;
import Util.Vector2;

public class NormalAmmoEnemyAttack{
	protected Enemy origin;
	
	public NormalAmmoEnemyAttack(Enemy _origin) {
		origin = _origin;
	}
	
	protected NormalEnemyAmmo GetAmmo(NormalAmmoEnum ammoType) {
		return GameManager.instance.Enemy.GetNormalAmmo(origin.transform.GetPosition(), ammoType);
	}
	
	public void PlayerTargettingAttack(float _speed) {
		Vector2 direction = origin.GetDirectionToPlayer();
		GetAmmo(NormalAmmoEnum.Red).SetVelocity(direction.Mul(_speed), true);
	}
	public void StraightAttack(Vector2 _direction, float _speed) {
		GetAmmo(NormalAmmoEnum.Red).SetVelocity(_direction.Mul(_speed), true);
	}
	
	public void PieAttack(Vector2 _direction, int speed, int count, int degree) {
		Vector2 direction1 = Vector2.Zero();
		Vector2 direction2 = Vector2.Zero();
		if(count % 2 == 0) {
			direction1 = _direction.rotate(degree / 2);
			direction2 = _direction.rotate(-(degree / 2));
		}
		else {
			GetAmmo(NormalAmmoEnum.Red).SetVelocity(_direction.Mul(speed), true);
			direction1 = _direction.rotate(degree);
			direction2 = _direction.rotate(-degree);
		}
		for(int i = 0;i<count / 2;i++) {
			GetAmmo(NormalAmmoEnum.Red).SetVelocity(direction1.Mul(speed), true);
			GetAmmo(NormalAmmoEnum.Red).SetVelocity(direction2.Mul(speed), true);
			direction1 = direction1.rotate(degree);
			direction2 = direction2.rotate(-degree);
		}
	}
	
	public void PieAttackToPlayer(int speed, int count, int degree) {
		Vector2 direction = origin.GetDirectionToPlayer();

		PieAttack(direction, speed, count, degree);
	}
	
	public void CircleAttack(Vector2 _direction,int speed ,int count) {
		float degree = 360 / count;
		for(int i = 0;i<count;i++) {
			StraightAttack(_direction.rotate(degree * i), speed);
		}
	}
	public void CircleAttackToPlayer(int speed, int count) {
		Vector2 direction = origin.GetDirectionToPlayer();
		CircleAttack(direction, speed, count);
	}
	
}
