package Object.Enemy;

import java.awt.image.BufferedImage;

import Game.GameManager;
import MonoBehavior.BoxCollider;
import MonoBehavior.Collider;
import MonoBehavior.RigidBody;
import Object.Enemy.Attack.NormalAmmoEnemyAttack;
import Util.Layer;
import Util.NormalEnemySprite;
import Util.Tag;
import Util.Time;
import Util.Vector2;

public class NormalEnemy extends Enemy{
	enum AttackType{
		None,
		PlayerStraight,
		ThreePie,
		CircleToPlayer,
		RandomCircle
	}
	
	Collider collider;
	RigidBody rigidBody;
	public NormalEnemyMovement movement;
	
	BufferedImage redYellow;
	BufferedImage blue;
	
	
	protected NormalAmmoEnemyAttack attack;
	AttackType currentAttack = AttackType.None;
	
	public int poolIndex;
	
	public NormalEnemy(Vector2 _position, int _hp, boolean _isDropPower, NormalEnemySprite _spriteType) {
		super(_position, _hp, _isDropPower);

		transform.rotation = 180;
		redYellow = GetBufferedImage("EnemyPlaneRedYellow.png");
		blue = GetBufferedImage("EnemyPlaneBlue.png");

		SetSprite(_spriteType);
		
		name = "Test Object";
		tag = Tag.Enemy;
		layer = Layer.Enemy;
		
		rigidBody = new RigidBody(this);
		
		collider = new BoxCollider(this, rigidBody);
		
		collider.checkLayers.clear();
		collider.checkLayers.add(Layer.PlayerHitBox);

		movement = new NormalEnemyMovement(this);
		attack = new NormalAmmoEnemyAttack(this);
	}
	
	
	public void NormalReset(Vector2 _position, int _hp, boolean _isDropPower, NormalEnemySprite _SpriteType) {
		super.Reset(_position, _hp, _isDropPower);
		SetSprite(_SpriteType);
		isActive = true;
		counter = 0;
		ammoTimeCounter = 0;
	}
	
	int speed = 100;
	double attackTime = 0;
	double attackTimeCounter = 0;
	
	int counter = 0;
	double ammoTimeCounter = 0;
	
	int ammoCount = 0;
	
	@Override
	public void Update() {
		super.Update();
		if(!isActive)
			return;
		
		if(attackTimeCounter < attackTime) {
			attackTimeCounter += Time.DeltaTime();
		}
		else {
			if(currentAttack == AttackType.PlayerStraight) {
				attack.PlayerTargettingAttack(speed);
				attackTimeCounter = 0;
			}
			else if(currentAttack == AttackType.ThreePie) {
				if(counter < ammoCount) {
					if(ammoTimeCounter < 0.2)
					{
						ammoTimeCounter += Time.DeltaTime();
					}
					else {
						ammoTimeCounter = 0;
						attack.PieAttackToPlayer(speed, 3, 30);
						counter++;
					}
				}
				else {
					ammoTimeCounter = 0;
					counter = 0;
					attackTimeCounter = 0;
				}
			}
			else if(currentAttack == AttackType.CircleToPlayer) {
				attack.CircleAttackToPlayer(speed, ammoCount);
				attackTimeCounter = 0;
			}
		}
		
	}
	@Override
	public void OnDestroy() {
		super.OnDestroy();
		GameManager.instance.Enemy.ReturnEnemy(this);
		movement.OnDestroy();
	}
	
	public void NoAttack() {
		currentAttack = AttackType.None;
	}
	public void PlayerStraightAttack(double _attackTime, int _speed) {
		speed = _speed;
		attackTime = _attackTime;
		attackTimeCounter = 0;
		
		currentAttack = AttackType.PlayerStraight;
	}
	public void PlayerPieAttack(double _attackTime, int _speed, int _ammoCount) {
		currentAttack = AttackType.ThreePie;
		speed = _speed;
		attackTime = _attackTime;
		attackTimeCounter = 0;
		ammoTimeCounter = 10;
		counter = 10;
		ammoCount = _ammoCount;
	}
	public void PlayerCircleAttack(double _attackTime, int _speed, int _ammoCount) {
		currentAttack = AttackType.CircleToPlayer;
		speed = _speed;
		attackTime = _attackTime;
		attackTimeCounter = 0;
		ammoCount = _ammoCount;
	}
	
	public void SetSprite(NormalEnemySprite _SpriteType) {
		if(_SpriteType == NormalEnemySprite.Blue)
			sprite.image = blue;
		else if(_SpriteType == NormalEnemySprite.RedYellow)
			sprite.image = redYellow;
	}
}
