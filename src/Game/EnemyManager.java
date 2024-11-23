package Game;

import java.util.ArrayList;

import Object.GameObject;
import Object.Enemy.*;
import Util.*;

public class EnemyManager extends GameObject{
	public static EnemyManager instance;
	public ArrayList<Enemy> enemyList;
	
	private double timeCounter = 0;
	
	public EnemyManager() {
		super(Vector2.Zero());
		
		instance = this;
		
		sprite.isVisible = false;
		
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
}
