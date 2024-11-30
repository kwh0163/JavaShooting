package Game;

import java.util.ArrayList;

import Object.GameObject;
import Object.PowerUpObject;
import Object.Enemy.*;
import Object.Enemy.Attack.Ammo.EnemyAmmo;
import Object.Enemy.Attack.Ammo.NormalEnemyAmmo;
import Object.Enemy.Attack.Ammo.TracePlayerAmmo;
import Util.*;

public class EnemyManager extends GameObject{
	public ArrayList<Enemy> enemyList;
	
	public Pool<NormalEnemyAmmo> normalAmmoPool;
	public Pool<NormalEnemy> normalEnemyPool;
	public Pool<TracePlayerAmmo> traceAmmoPool;
	
	public EnemyManager() {
		super(Vector2.Zero());
		
		sprite.isVisible = false;
		
		normalAmmoPool = new Pool<NormalEnemyAmmo>();
		traceAmmoPool = new Pool<TracePlayerAmmo>();
		normalEnemyPool = new Pool<NormalEnemy>();
		
		enemyList = new ArrayList<Enemy>();
	}
	public void AddEnemy(Enemy _enemy) {
		enemyList.add(_enemy);
	}
	public void RemoveEnemy(Enemy _enemy) {
	    if (enemyList.contains(_enemy)) {
	        enemyList.remove(_enemy);
	    }
	}
	int count = 0;
	public NormalEnemyAmmo GetNormalAmmo(Vector2 _position, NormalAmmoEnum ammoType) {
	    NormalEnemyAmmo ammo = AcquireAmmo(_position);
	    ammo.Reset(_position);
	    ammo.SetImage(ammoType);
	    return ammo;
	}
	public TracePlayerAmmo GetTraceAmmo(Vector2 _position) {
		TracePlayerAmmo ammo;
	    if(traceAmmoPool.IsEmpty()) {
	    	ammo = new TracePlayerAmmo(_position);
	    	ammo.poolIndex = traceAmmoPool.AddPool(ammo);
	    	return ammo;
	    }
	    else {
	    	ammo = traceAmmoPool.GetPool();
	    }
	    ammo.Reset(_position);
	    return ammo;
	}

	private NormalEnemyAmmo AcquireAmmo(Vector2 _position) {
	    if (normalAmmoPool.IsEmpty()) {
	        NormalEnemyAmmo ammo = new NormalEnemyAmmo(_position);
	        ammo.poolIndex = normalAmmoPool.AddPool(ammo);
	        return ammo;
	    }
	    NormalEnemyAmmo ammo = normalAmmoPool.GetPool();
	    if (ammo.isActive) {
	        Debug.Log("Ammo Is Already Active");
	    }
	    return ammo;
	}
	public void ReturnAmmo(EnemyAmmo _ammo) {
		if (_ammo instanceof NormalEnemyAmmo) {
			normalAmmoPool.ReturnPool(_ammo.poolIndex);
		}
		else if(_ammo instanceof TracePlayerAmmo) {
			traceAmmoPool.ReturnPool(_ammo.poolIndex);
		}
	}
	
	public NormalEnemy GetEnemy(Vector2 _position, int _hp, boolean _isDropPower, NormalEnemySprite _sprite) {
		NormalEnemy enemy;
		if(normalEnemyPool.IsEmpty()) {
			enemy = new NormalEnemy(_position, _hp, _isDropPower, _sprite);
			enemy.poolIndex = normalEnemyPool.AddPool(enemy);
		}
		else {
			enemy = normalEnemyPool.GetPool();
		}
		enemy.NormalReset(_position, _hp, _isDropPower, _sprite);
		return enemy;
	}
	public void ReturnEnemy(NormalEnemy _enemy) {
		normalEnemyPool.ReturnPool(_enemy.poolIndex);
	}
	
}
