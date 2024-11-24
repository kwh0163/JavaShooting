package Object.Player;

import Game.EnemyManager;
import Object.Enemy.Enemy;
import Util.Pool;
import Util.Time;
import Util.Vector2;

public class PlayerAttack {
	public static PlayerAttack instance;
	
	private Player origin;
	public int level = 5;
	public boolean isAttacking = false;
	
	private Pool<PlayerStraightAmmo> straightPool;
	private Pool<PlayerGuidanceAmmo> guidancePool;
	
	private double straightAttackSpeed = 0.1;
	private double straightAttackSpeedCounter = 0;
	private double guideAttackSpeed = 0.1;
	private double guideAttackSpeedCounter = 0;
	
	public Enemy targetEnemy;

	public void FindTarget() {
		if(targetEnemy == null || !targetEnemy.isActive) {
			double distance = -1;
			for(int i = 0;i<EnemyManager.instance.enemyList.size();i++) {
				Enemy currentEnemy = EnemyManager.instance.enemyList.get(i);
				double enemyDistance = Vector2.Distance(origin.transform.GetPosition(), currentEnemy.transform.GetPosition());
				if(distance < 0) {
					targetEnemy = currentEnemy;
					distance = enemyDistance;
					continue;
				}
				if(distance > enemyDistance) {
					targetEnemy = currentEnemy;
					distance = enemyDistance;
				}
			}
			
		}
	}
	
	public PlayerAttack(Player _player) {
		instance = this;
		straightPool = new Pool<PlayerStraightAmmo>();
		guidancePool = new Pool<PlayerGuidanceAmmo>();
		origin = _player;
	}
	
	public void Attack() {
		StraightAmmo();
		GuideAmmo();
	}

	private void CreateStraightAmmo(Vector2 _position, float _degree) {
		PlayerStraightAmmo ammo;
		if(straightPool.IsEmpty()) {
			ammo = new PlayerStraightAmmo(_position);
			ammo.poolIndex = straightPool.AddPool(ammo);
		}
		else {
			ammo = straightPool.GetPool();
		}
		ammo.Reset(_position, _degree);
	}
	private void CreateGuideAmmo(Vector2 _position, Vector2 _firstDirection) {
		PlayerGuidanceAmmo ammo;
		if(guidancePool.IsEmpty()) {
			ammo = new PlayerGuidanceAmmo(_position, this);
			ammo.poolIndex = guidancePool.AddPool(ammo);
		}
		else {
			ammo = guidancePool.GetPool();
			if(ammo.isActive)
				System.out.println("Ammo Is Already Active");
		}
		ammo.Reset(_position, _firstDirection);
	}
	
	public void ReturnAmmo(PlayerAmmo _ammo) {
		if (_ammo instanceof PlayerStraightAmmo) {
			straightPool.ReturnPool(_ammo.poolIndex);
		}
		else {
			guidancePool.ReturnPool(_ammo.poolIndex);
		}
	}
	
	private void StraightAmmo() {
		if(straightAttackSpeedCounter < straightAttackSpeed) {
			straightAttackSpeedCounter += Time.DeltaTime();
			return;
		}
		if(!isAttacking)
			return;
		
		straightAttackSpeedCounter = 0;
		
		switch (level) {
		case 1: 
			CreateStraightAmmo(origin.transform.GetPosition(),0);
			break;
		case 2:
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(7, 0)),0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-7, 0)),0);
			break;
		case 3:
			CreateStraightAmmo(origin.transform.GetPosition(), 0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(15, 0)), -20);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-15, 0)),20);
			break;
		case 4:
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(7, 0)),0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-7, 0)),0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(21, 0)),-20);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-21, 0)),20);
		case 5:
		default:
			CreateStraightAmmo(origin.transform.GetPosition(),0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(15, 0)), -10);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(30, 0)), -20);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-15, 0)), 10);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-30, 0)), 20);
			break;
		}
	}
	private void GuideAmmo() {
		if(guideAttackSpeedCounter < guideAttackSpeed) {
			guideAttackSpeedCounter += Time.DeltaTime();
			return;
		}
		if(!isAttacking)
			return;
		guideAttackSpeedCounter = 0;
		
		switch (level) {
		case 1:
			break;
		case 2:
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up());
			break;
		case 3:
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(10));
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(-10));
			break;
		case 4:
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up());
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(20));
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(-20));
			break;
		case 5:
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(10));
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(30));
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(-10));
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(-30));
			break;
		default:
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up());
			break;
		}
	}
}
