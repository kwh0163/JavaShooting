package Game;

import java.util.ArrayList;

import Object.GameObject;
import Object.Enemy.*;
import Object.Enemy.Attack.Ammo.EnemyAmmo;
import Object.Enemy.Attack.Ammo.NormalEnemyAmmo;
import Util.*;

public class EnemyManager extends GameObject{
	public ArrayList<Enemy> enemyList;
	
	public Pool<NormalEnemyAmmo> normalAmmoPool;
	
	public EnemyManager() {
		super(Vector2.Zero());
		
		sprite.isVisible = false;
		
		normalAmmoPool = new Pool<NormalEnemyAmmo>();
		
		enemyList = new ArrayList<Enemy>();
	}
	public void AddEnemy(Enemy _enemy) {
		enemyList.add(_enemy);
	}
	public void RemoveEnemy(Enemy _enemy) {
		enemyList.remove(_enemy);
	}
	int count = 0;
	public NormalEnemyAmmo GetNormalAmmo(Vector2 _position, NormalAmmoEnum ammoType) {
		NormalEnemyAmmo ammo;
		if(normalAmmoPool.IsEmpty()) {
			ammo = new NormalEnemyAmmo(_position);
			ammo.poolIndex = normalAmmoPool.AddPool(ammo);
		}
		else {
			ammo = normalAmmoPool.GetPool();
			if(ammo.isActive)
				Debug.Log("Ammo Is Already Active");
		}
		ammo.Reset(_position);
		ammo.SetImage(ammoType);
		
		return ammo;
	}
	public void ReturnAmmo(EnemyAmmo _ammo) {
		if (_ammo instanceof NormalEnemyAmmo) {
			normalAmmoPool.ReturnPool(_ammo.poolIndex);
		}
	}
}
