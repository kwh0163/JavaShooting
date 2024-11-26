package Game;

import java.util.ArrayList;

import Object.GameObject;
import Object.Enemy.*;
import Object.Enemy.Attack.Ammo.EnemyAmmo;
import Object.Enemy.Attack.Ammo.NormalEnemyAmmo;
import Object.Player.PlayerAmmo;
import Object.Player.PlayerGuidanceAmmo;
import Object.Player.PlayerStraightAmmo;
import Util.*;

public class EnemyManager extends GameObject{
	public ArrayList<Enemy> enemyList;
	
	public Pool<NormalEnemyAmmo> normalAmmoPool;
	
	private double timeCounter = 0;
	
	public EnemyManager() {
		super(Vector2.Zero());
		
		sprite.isVisible = false;
		
		normalAmmoPool = new Pool<NormalEnemyAmmo>();
		
		enemyList = new ArrayList<Enemy>();
	}
	
	@Override
	public void Update() {
		super.Update();
		timeCounter += Time.DeltaTime();
	}
	
	public void AddEnemy(Enemy _enemy) {
		enemyList.add(_enemy);
	}
	public void RemoveEnemy(Enemy _enemy) {
		enemyList.remove(_enemy);
	}
	
	public NormalEnemyAmmo GetNormalAmmo(Vector2 _position, Vector2 _firstDirection) {
		
		NormalEnemyAmmo ammo;
		if(normalAmmoPool.IsEmpty()) {
			ammo = new NormalEnemyAmmo(_position);
			ammo.poolIndex = normalAmmoPool.AddPool(ammo);
		}
		else {
			ammo = normalAmmoPool.GetPool();
			if(ammo.isActive)
				System.out.println("Ammo Is Already Active");
		}
		ammo.Reset(_position, _firstDirection);
		
		return ammo;
	}
	public void ReturnAmmo(EnemyAmmo _ammo) {
		if (_ammo instanceof NormalEnemyAmmo) {
			normalAmmoPool.ReturnPool(_ammo.poolIndex);
		}
	}
}
