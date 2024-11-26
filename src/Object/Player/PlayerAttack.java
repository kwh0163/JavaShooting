package Object.Player;

import Game.GameManager;
import MonoBehavior.MonoBehavior;
import Object.Enemy.Enemy;
import Util.Debug;
import Util.Pool;
import Util.Time;
import Util.Vector2;

public class PlayerAttack extends MonoBehavior {
	public static PlayerAttack instance;
	
	private Player origin;
	public int level = 3;
	public boolean isAttacking = false;
	
	private Pool<PlayerStraightAmmo> straightPool;
	private Pool<PlayerGuidanceAmmo> guidancePool;
	
	private double straightAttackSpeed = 0.1;
	private double straightAttackSpeedCounter = 0;
	private double guideAttackSpeed = 0.1;
	private double guideAttackSpeedCounter = 0;
	
	public Enemy targetEnemy;

	public void FindTarget() {
		double distance = -1;
		for(int i = 0;i<GameManager.instance.Enemy.enemyList.size();i++) {
			Enemy currentEnemy = GameManager.instance.Enemy.enemyList.get(i);
			if(!currentEnemy.isActive)
				continue;
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
	
	public PlayerAttack(Player _player) {
		super(_player);
		instance = this;
		straightPool = new Pool<PlayerStraightAmmo>();
		guidancePool = new Pool<PlayerGuidanceAmmo>();
		origin = _player;
	}
	@Override
	public void Update() {
		super.Update();
		Attack();
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
	private void CreateGuideAmmo(Vector2 _position, float _degree) {
		PlayerGuidanceAmmo ammo;
		if(guidancePool.IsEmpty()) {
			ammo = new PlayerGuidanceAmmo(_position, this);
			ammo.poolIndex = guidancePool.AddPool(ammo);
		}
		else {
			ammo = guidancePool.GetPool();
			if(ammo.isActive)
				Debug.Log("Ammo Is Already Active");
		}
		ammo.Reset(_position, _degree);
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
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(3, 0)), -2);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-3, 0)), 2);
			break;
		case 3:
			CreateStraightAmmo(origin.transform.GetPosition(), 0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(6, 0)), -4);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-6, 0)), 4);
			break;
		case 4:
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(3, 0)), -2);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(9, 0)), -6);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-3, 0)), 2);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-9, 0)), 6);
			break;
		case 5:
		default:
			CreateStraightAmmo(origin.transform.GetPosition(),0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(6, 0)), -4);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(12, 0)), -8);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-6, 0)), 4);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-12, 0)), 8);
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
		
		if(level > 2)
			FindTarget();
		guideAttackSpeedCounter = 0;
		
		switch (level) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 0)),30);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(10, 0)), -30);
			break;
		case 4:
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 0)),30);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(10, 0)), -30);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 0)),50);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(10, 0)), -50);
			break;
		case 5:
		default:
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 0)),30);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(10, 0)), -30);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 0)), 50);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(10, 0)), -50);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 0)), 70);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(10, 0)), -70);
			break;
		}
	}
	
	public void LevelUpTest() {
		level = level % 5 + 1;
		Debug.Log(String.format("Current Level : %d", level));
	}
}
