package Object.Enemy.Enemies;

import Game.GameManager;
import Game.MainProgram;
import MonoBehavior.BoxCollider;
import MonoBehavior.RigidBody;
import Object.Enemy.Enemy;
import Object.Enemy.NormalEnemyMovement;
import Object.Enemy.Attack.NormalAmmoEnemyAttack;
import Object.Enemy.Attack.Ammo.NormalEnemyAmmo;
import Object.Enemy.Attack.Ammo.TracePlayerAmmo;
import Util.AudioType;
import Util.Debug;
import Util.Layer;
import Util.NormalAmmoEnum;
import Util.Tag;
import Util.Time;
import Util.Vector2;

public class Boss1 extends Enemy{
	enum BossPattern{
		None,
		Start,
		PiePiePie,
		Circle,
		StraightPlayer,
		CircleAndTrace,
		CircleCircleCircle,
		Die
	}
	
	
	public RigidBody rigidBody;
	public BoxCollider collider;
	public NormalEnemyMovement movement;
	public NormalAmmoEnemyAttack attack;
	BossPattern currentPattern;
	boolean isBgmStarted = false;
	
	public Boss1() {
		super(new Vector2(400, 800), 3000, true);
		
		health.isBeatable = false;
		health.isBoss = true;
		name = "Boss";
		transform.scale = new Vector2(5, 2.5);
		transform.rotation = 180;
		sprite.image = GetBufferedImage("Boss.png");
		tag = Tag.Enemy;
		layer = Layer.Enemy;
		
		rigidBody = new RigidBody(this);
		collider = new BoxCollider(this, rigidBody);
		collider.checkLayers.clear();
		
		movement = new NormalEnemyMovement(this);
		attack = new NormalAmmoEnemyAttack(this);
		currentPattern = BossPattern.Start;
	}
	
	double timeCounter = 0;
	int counter = 0;
	double ammoCounter = 0;
	int pattern = 0;
	@Override
	public void Update() {
		super.Update();
		
		timeCounter += Time.DeltaTime();
		if(currentPattern == BossPattern.Start) {
			if(timeCounter < 3) {
				transform.SetPosition(Vector2.Lerp(timeCounter / 3, new Vector2(400, 800), new Vector2(400, 400)));
			}
			else if(timeCounter <= 4) {
				if(isBgmStarted)
					return;
				GameManager.instance.Audio.PlayBGM(11);
				isBgmStarted = true;
			}
			else if(timeCounter <= 5){
				health.isBeatable = true;
				currentPattern = BossPattern.None;
				timeCounter = 0;
			}
		}
		else if(currentPattern == BossPattern.None) {
			if(timeCounter >= 2) {
				//int rand = MainProgram.GetRandom().nextInt(5);
				int rand = (++pattern) % 5;
				switch (rand) {
				case 0:
					Debug.Log("pattern1");
					currentPattern = BossPattern.PiePiePie;
					counter = 0;
					ammoCounter = 10;
					break;
				case 1:
					currentPattern = BossPattern.Circle;
					ammoCounter = 0;
					break;
				case 2:
					currentPattern = BossPattern.StraightPlayer;
					counter = 0;
					ammoCounter = 10;
					break;
				case 3:
					currentPattern = BossPattern.CircleAndTrace;
					ammoCounter = 10;
					break;
				case 4:
					currentPattern = BossPattern.CircleCircleCircle;
					ammoCounter = 10;
					break;
				}
				timeCounter = 0;
			}
		}
		else if(currentPattern == BossPattern.PiePiePie) {
			if(timeCounter < 10) {
				if(ammoCounter < 0.5) {
					ammoCounter += Time.DeltaTime();
				}
				else {
					PlayAttackSound();
					counter++;
					ammoCounter = 0;
					Vector2 position = transform.GetPosition().Add(new Vector2((counter % 2 == 0) ? - 100 : 100, 0));
					Vector2 direction = (counter % 2 == 0) ? Vector2.Left().rotate(135) : Vector2.Right().rotate(135);
					direction = direction.rotate(MainProgram.GetRandom().nextInt(10) - 5);
					double speed = 250;
					for(int i = 0;i<15;i++) {
						GetAmmo(position, NormalAmmoEnum.Red).SetVelocity(direction.Mul(speed), false);
						direction = direction.rotate(-18);
					}
				}
			}
			else if(timeCounter >= 11) {
				timeCounter = 0;
				currentPattern = BossPattern.None;
			}
		}
		else if(currentPattern == BossPattern.Circle) {
			if(timeCounter < 10) {
				if(ammoCounter < 0.5) {
					ammoCounter += Time.DeltaTime();
				}
				else {
					PlayAttackSound();
					ammoCounter = 0;
					Vector2 position = transform.GetPosition();
					double rotation = MainProgram.GetRandom().nextInt(10) - 5;
					double speed = 250 + MainProgram.GetRandom().nextInt(30) - 15;
					for(int i = 0;i<36;i++) {
						GetAmmo(position, NormalAmmoEnum.Blue).SetVelocity(Vector2.Up().rotate(rotation).Mul(speed), true);
						rotation += 10;
					}
				}
			}
			else if(timeCounter >= 11){
				timeCounter = 0;
				currentPattern = BossPattern.None;
			}
		}
		else if(currentPattern == BossPattern.StraightPlayer) {
			if(timeCounter < 10) {
				if(ammoCounter < 1) {
					ammoCounter += Time.DeltaTime();
				}
				else {
					PlayAttackSound();
					ammoCounter = 0;
					Vector2 position1 = transform.GetPosition().Add(new Vector2(-50, 0));
					Vector2 position2 = transform.GetPosition().Add(new Vector2(50, 0));
					Vector2 position3 = transform.GetPosition().Add(new Vector2(150, 0));
					Vector2 position4 = transform.GetPosition().Add(new Vector2(-150, 0));
					Vector2 direction = GameManager.instance.Player.transform.GetPosition().Sub(position1).GetNormalized();
					Vector2 direction2 = GameManager.instance.Player.transform.GetPosition().Sub(position2).GetNormalized();
					Vector2 direction3 = GameManager.instance.Player.transform.GetPosition().Sub(position3).GetNormalized();
					Vector2 direction4 = GameManager.instance.Player.transform.GetPosition().Sub(position4).GetNormalized();
					double speed = 200;
					for(int i = 0;i<5;i++) {
						GetAmmo(position1, NormalAmmoEnum.Red).SetVelocity(direction.Mul(speed + 25 * i), false);
						GetAmmo(position2, NormalAmmoEnum.Red).SetVelocity(direction2.Mul(speed + 25 * i), false);
						GetAmmo(position3, NormalAmmoEnum.Red).SetVelocity(direction3.Mul(speed + 25 * i), false);
						GetAmmo(position4, NormalAmmoEnum.Red).SetVelocity(direction4.Mul(speed + 25 * i), false);
					}
				}
			}
			else if (timeCounter >= 11) {
				timeCounter = 0;
				currentPattern = BossPattern.None;
			}
		}
		else if(currentPattern == BossPattern.CircleAndTrace) {
			if(timeCounter < 10) {
				if(ammoCounter < 2) {
					ammoCounter += Time.DeltaTime();
				}
				else {
					PlayAttackSound();
					ammoCounter = 0;
					for(int i = 0;i<20;i++) {
						GetTraceAmmo(transform.GetPosition()).SetVelocity(Vector2.Up().rotate(18 * i).Mul(100), true, 1, 300);
					}
				}
			}
			else if(timeCounter >= 11) {
				timeCounter = 0;
				currentPattern = BossPattern.None;
			}
		}
		else if(currentPattern == BossPattern.CircleCircleCircle) {
			if(timeCounter < 10) {
				if(ammoCounter < 0.2) {
					ammoCounter += Time.DeltaTime();
				}
				else {
					PlayAttackSound();
					for(int i = 0;i<10;i++) {
						Vector2 direction = new Vector2(MainProgram.GetRandom().nextDouble(2) - 1, MainProgram.GetRandom().nextDouble(2) - 1).GetNormalized();
						double speed = MainProgram.GetRandom().nextInt(200) + 200;
						GetAmmo(transform.GetPosition(), MainProgram.GetRandom().nextBoolean() ? NormalAmmoEnum.Red : NormalAmmoEnum.Blue).SetVelocity(direction.Mul(speed), true);
					}
					ammoCounter = 0;
				}
				
			}
			else if(timeCounter >= 11) {
				timeCounter = 0;
				currentPattern = BossPattern.None;
			}
		}
	}
	
	NormalEnemyAmmo GetAmmo(Vector2 position, NormalAmmoEnum ammoType) {
		return GameManager.instance.Enemy.GetNormalAmmo(position,ammoType);
	}
	TracePlayerAmmo GetTraceAmmo(Vector2 position) {
		return GameManager.instance.Enemy.GetTraceAmmo(position);
	}

	float attackVolume = 0.7f;
	void PlayAttackSound() {

		switch(MainProgram.GetRandom().nextInt(3)) {
		case 0:
			GameManager.instance.Audio.PlaySound(AudioType.EnemyShoot0,attackVolume);
			break;
		case 1:
			GameManager.instance.Audio.PlaySound(AudioType.EnemyShoot1, attackVolume);
			break;
		case 2:
			GameManager.instance.Audio.PlaySound(AudioType.EnemyShoot2, attackVolume);
			break;
		}
	}

}
