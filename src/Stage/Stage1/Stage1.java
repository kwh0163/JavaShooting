package Stage.Stage1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Game.GameManager;
import Object.GameObject;
import Object.Enemy.NormalEnemy;
import Stage.Stage;
import Util.NormalEnemySprite;
import Util.Vector2;

public class Stage1 extends Stage {
	
	public Stage1(GameObject _object) {
		super(_object);
		
		int stageStartTime = 5000;
		
		GameManager.instance.Audio.PlayBGM(1);
		
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		
		int scheduleTime = 0;
		
		int waveSpeed = 6;
		double waveAttackSpeed = 3;
		int waveAmmoSpeed = 300;
		
		//wave1
		for(int i = 0;i<15;i++) {
			scheduler.schedule(()->{
				NormalEnemy temp = GetNormalEnemy(new Vector2(850, 700), 1, false, NormalEnemySprite.Blue);
				temp.movement.MoveQuadric(waveSpeed, new Vector2(-100, 200));
				temp.NoAttack();
				
				temp = GetNormalEnemy(new Vector2(900, 700), 1, false, NormalEnemySprite.Blue);
				temp.movement.MoveQuadric(waveSpeed, new Vector2(-100, 150));
				temp.NoAttack();
			}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
			
			scheduleTime += 300;
		}
		for(int i = 0;i<5;i++) {
			
			scheduler.schedule(()->{
				NormalEnemy temp = GetNormalEnemy(new Vector2(850, 700), 1, false, NormalEnemySprite.Blue);
				temp.movement.MoveQuadric(waveSpeed, new Vector2(-100, 200));
				temp.PlayerStraightAttack(waveAttackSpeed, waveAmmoSpeed);
				
				temp = GetNormalEnemy(new Vector2(900, 700), 1, false, NormalEnemySprite.Blue);
				temp.movement.MoveQuadric(waveSpeed, new Vector2(-100, 150));
				temp.PlayerStraightAttack(waveAttackSpeed, waveAmmoSpeed);
			}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
			
			scheduleTime += 300;
		}
		scheduleTime += 1500;
		for(int i = 0;i<15;i++) {
			
			scheduler.schedule(()->{
				NormalEnemy temp = GetNormalEnemy(new Vector2(-50, 700), 1, false, NormalEnemySprite.Blue);
				temp.movement.MoveQuadric(waveSpeed, new Vector2(900, 200));
				temp.NoAttack();
				
				temp = GetNormalEnemy(new Vector2(-100, 700), 1, false, NormalEnemySprite.Blue);
				temp.movement.MoveQuadric(waveSpeed, new Vector2(900, 150));
				temp.NoAttack();
			}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
			scheduleTime += 300;
		}
		for(int i = 0;i<14;i++) {
			
			scheduler.schedule(()->{
				NormalEnemy temp = GetNormalEnemy(new Vector2(-50, 700), 1, false, NormalEnemySprite.Blue);
				temp.movement.MoveQuadric(waveSpeed, new Vector2(900, 200));
				temp.PlayerStraightAttack(waveAttackSpeed, waveAmmoSpeed);
				
				temp = GetNormalEnemy(new Vector2(-100, 700), 1, false, NormalEnemySprite.Blue);
				temp.movement.MoveQuadric(waveSpeed, new Vector2(900, 150));
				temp.PlayerStraightAttack(waveAttackSpeed, waveAmmoSpeed);
			}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
			scheduleTime += 300;
		}
		scheduler.schedule(()->{
			NormalEnemy temp = GetNormalEnemy(new Vector2(-50, 700), 1, false, NormalEnemySprite.Blue);
			temp.movement.MoveQuadric(waveSpeed, new Vector2(900, 200));
			temp.PlayerStraightAttack(waveAttackSpeed, waveAmmoSpeed);
			
			temp = GetNormalEnemy(new Vector2(-100, 700), 1, true, NormalEnemySprite.RedYellow);
			temp.movement.MoveQuadric(waveSpeed, new Vector2(900, 150));
			temp.PlayerStraightAttack(waveAttackSpeed, waveAmmoSpeed);
		}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
		scheduleTime += 1500;
		//end wave1
		
		
	}
}
