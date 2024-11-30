package Object.Player;

import java.awt.image.BufferedImage;

import Game.GameManager;
import MonoBehavior.MonoBehavior;
import Object.Enemy.Enemy;
import Util.AudioType;
import Util.Debug;
import Util.Pool;
import Util.Time;
import Util.Vector2;

public abstract class PlayerAttack extends MonoBehavior {
	public static PlayerAttack instance;
	
	protected Player origin;
	public int level = 1;
	public boolean isAttacking = false;
	
	protected float attackVolume = 0.7f;
	
	private Pool<PlayerStraightAmmo> straightPool;
	private Pool<PlayerGuidanceAmmo> guidancePool;
	
	protected double firstAttackSpeed = 0.1;
	protected double firstAttackSpeedCounter = 0;
	protected double secondAttackSpeed = 0.1;
	protected double secondAttackSpeedCounter = 0;
	protected boolean isFocussing = false;
	
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
	public void LateUpdate() {
		super.LateUpdate();
		Attack();
	}
	public void Focus(boolean _isFocussing) {
		isFocussing = _isFocussing;
	}
	
	public void Attack() {
		FirstAmmo();
		SecondAmmo();
	}

	protected void CreateStraightAmmo(Vector2 _position, float _degree, int imageNumber) {
		PlayerStraightAmmo ammo;
		if(straightPool.IsEmpty()) {
			ammo = new PlayerStraightAmmo(_position);
			ammo.poolIndex = straightPool.AddPool(ammo);
		}
		else {
			ammo = straightPool.GetPool();
		}
		ammo.Reset(_position, _degree);
		ammo.SetAmmoImage(imageNumber);
	}
	protected void CreateGuideAmmo(Vector2 _position, float _degree) {
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
	
	protected abstract void FirstAmmo() ;
	
	protected abstract void SecondAmmo();
	
	public void LevelUpTest() {
		level = level % 5 + 1;
		Debug.Log(String.format("Current Level : %d", level));
	}
	public void LevelUp() {
		if(level < 5) {
			GameManager.instance.Audio.PlaySound(AudioType.PowerUp, 0.8f);
			GameManager.instance.Score.score += 100;
			level++;
		}
		else {
			GameManager.instance.Score.score += 1000;
			Debug.Log("level max");
		}
	}
}
