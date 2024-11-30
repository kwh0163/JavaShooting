package Stage.Stage1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Game.GameManager;
import Game.MainProgram;
import Object.GameObject;
import Object.Enemy.NormalEnemy;
import Object.Enemy.Enemies.Boss1;
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
		double waveAttackSpeed = 2;
		int waveAmmoSpeed = 300;
		//wave1
		for(int i = 0;i<20;i++) {
			int currentI = i;
			scheduler.schedule(()->{
				NormalEnemy temp = GetNormalEnemy(new Vector2(850, 700), 1, false, NormalEnemySprite.Blue);
				temp.movement.MoveQuadric(waveSpeed, new Vector2(-100, 200));
				if(currentI % 2 == 0)
					temp.PlayerStraightAttack(waveAttackSpeed, waveAmmoSpeed + MainProgram.GetRandom().nextInt(30) - 15);
				else {
					temp.NoAttack();
				}
			}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
			
			scheduleTime += 300;
		}
		scheduleTime += 1000;
		for(int i = 0;i<20;i++) {
			int currentI = i;
			scheduler.schedule(()->{
				NormalEnemy temp = GetNormalEnemy(new Vector2(-50, 700), 1, false, NormalEnemySprite.Blue);
				temp.movement.MoveQuadric(waveSpeed, new Vector2(900, 200));
				if(currentI % 2 == 0)
					temp.PlayerStraightAttack(waveAttackSpeed, waveAmmoSpeed + MainProgram.GetRandom().nextInt(30) - 15);
				else {
					temp.NoAttack();
				}
			}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
			scheduleTime += 300;
		}
		scheduler.schedule(()->{
			NormalEnemy temp = GetNormalEnemy(new Vector2(-50, 700), 3, true, NormalEnemySprite.RedYellow);
			temp.movement.MoveQuadric(waveSpeed, new Vector2(900, 200));
			temp.PlayerStraightAttack(waveAttackSpeed, waveAmmoSpeed + MainProgram.GetRandom().nextInt(30) - 15);
		}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
		scheduleTime += 300;
		//end wave1
		scheduleTime += 2000;
		//wave2
		for(int i = 0; i < 15;i++) {
			int currentI = i;
			scheduler.schedule(()->{
				NormalEnemy temp = GetNormalEnemy(new Vector2(-100, 200), 3, false, NormalEnemySprite.Blue);
				temp.movement.MoveStraight(waveSpeed, new Vector2(900, 700));
				if(currentI % 3 == 0)
					temp.PlayerThreePieAttack(waveAttackSpeed, waveAmmoSpeed, 1);
				else {
					temp.NoAttack();
				}
			}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
			scheduleTime += 300;
		}
		scheduler.schedule(()->{
			NormalEnemy temp = GetNormalEnemy(new Vector2(-100, 200), 3, true, NormalEnemySprite.RedYellow);
			temp.movement.MoveStraight(waveSpeed, new Vector2(900, 700));
			temp.PlayerStraightAttack(waveAttackSpeed, waveAmmoSpeed + MainProgram.GetRandom().nextInt(30) - 15);
		}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
		scheduleTime += 1000;
		for(int i = 0; i < 15;i++) {
			int currentI = i;
			scheduler.schedule(()->{
				NormalEnemy temp = GetNormalEnemy(new Vector2(900, 200), 3, false, NormalEnemySprite.Blue);
				temp.movement.MoveStraight(waveSpeed, new Vector2(-100, 700));
				if(currentI % 3 == 0)
					temp.PlayerThreePieAttack(waveAttackSpeed, waveAmmoSpeed, 1);
				else {
					temp.NoAttack();
				}
			}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
			scheduleTime += 300;
		}
		//wave2 End
		scheduleTime += 2000;
		//wave2.5
		for(int i = 0; i < 30;i++) {
			int currentI = i;
			scheduler.schedule(()->{
				NormalEnemy temp = GetNormalEnemy(new Vector2(750, 900), 7, false, NormalEnemySprite.Blue);
				temp.movement.MoveCubic_75(8, new Vector2(50, -100));
				if(currentI % 2 == 0)
					temp.RandomFourAttack(4, waveAmmoSpeed);
				else {
					temp.NoAttack();
				}
				
				temp = GetNormalEnemy(new Vector2(50, 900), 7, false, NormalEnemySprite.Blue);
				temp.movement.MoveCubic_75(8, new Vector2(750, -100));
				if(currentI % 2 == 0)
					temp.RandomFourAttack(4, waveAmmoSpeed);
				else {
					temp.NoAttack();
				}
			}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
			scheduleTime += 300;
		}
		//wave2.5 End
		scheduleTime += 6000;
		//wave3
		int wave2Speed = 4;
		for(int i = 0;i<40;i++) {
			int currentI = i;
			scheduler.schedule(()->{
				NormalEnemy temp = GetNormalEnemy(new Vector2(450, 700), 7, false, NormalEnemySprite.Blue);
				temp.movement.MoveQuadric(wave2Speed, new Vector2(-100, 200));
				if(currentI % 5 == 0) {
					temp.PlayerCircleAttack(1.5 + ((currentI % 2 == 0) ? 0.2 : -0.2), 200+MainProgram.GetRandom().nextInt(30) - 15, 8);
				}
				else {
					temp.NoAttack();
				}
				
				temp = GetNormalEnemy(new Vector2(450, 700), 7, false, NormalEnemySprite.Blue);
				temp.movement.MoveQuadric(wave2Speed, new Vector2(900, 200));
				if(currentI % 5 == 0) {
					temp.PlayerCircleAttack(1.5 + ((currentI % 2 == 0) ? 0.2 : -0.2), 200+MainProgram.GetRandom().nextInt(30) - 15, 8);
				}
				else {
					temp.NoAttack();
				}
			}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
			scheduleTime += 300;
		}
		//Wave3 End
		scheduleTime += 2000;
		//Wave4
		scheduler.schedule(() -> {
			NormalEnemy temp = GetNormalEnemy(new Vector2(600, 700), 40, false, NormalEnemySprite.RedYellow);
			temp.movement.MoveStraight(6, new Vector2(600, 300));
			temp.id = 0;
			scheduler.schedule(() ->{
				if(temp.id == 0 && temp.isActive) {
					temp.movement.StopMove();
					temp.NoAttack();
					temp.PlayerFivePieAttack(10, 300, 10);
					temp.AttackNow();
					scheduler.schedule(() -> {
						if(temp.isActive)
							temp.movement.MoveStraight(3, new Vector2(600, 700));
					}, 3000, TimeUnit.MILLISECONDS);
				}
			}, 3000, TimeUnit.MILLISECONDS);
			
		}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
		scheduleTime += 2000;
		scheduler.schedule(() -> {
			NormalEnemy temp = GetNormalEnemy(new Vector2(300, 700), 40, true, NormalEnemySprite.RedYellow);
			temp.id = 1;
			temp.movement.MoveStraight(6, new Vector2(300, 300));
			scheduler.schedule(() ->{
				if(temp.id == 2 && temp.isActive) {
					temp.movement.StopMove();
					temp.NoAttack();
					temp.PlayerFivePieAttack(10, 300, 10);
					temp.AttackNow();
					scheduler.schedule(() -> {
						if(temp.isActive)
							temp.movement.MoveStraight(3, new Vector2(300, 700));
					}, 3000, TimeUnit.MILLISECONDS);
				}
			}, 3000, TimeUnit.MILLISECONDS);
			
		}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
		scheduleTime += 5000;
		scheduler.schedule(() -> {
			NormalEnemy temp = GetNormalEnemy(new Vector2(100, 700), 80, false, NormalEnemySprite.RedYellow);
			temp.movement.MoveStraight(6, new Vector2(100, 300));
			scheduler.schedule(() ->{
				if(temp.isActive) {
					temp.movement.StopMove();
					temp.PlayerCircleAttack(1, 200, 18);
					temp.AttackNow();
					scheduler.schedule(() -> {
						if(temp.isActive) {
							temp.NoAttack();
						}
					}, 3000, TimeUnit.MILLISECONDS);
					scheduler.schedule(() -> {
						if(temp.isActive)
							temp.movement.MoveStraight(3, new Vector2(100, 700));
					}, 4000, TimeUnit.MILLISECONDS);
				}
			}, 3000, TimeUnit.MILLISECONDS);
			
		}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
		scheduleTime += 1000;
		scheduler.schedule(() -> {
			NormalEnemy temp = GetNormalEnemy(new Vector2(300, 700), 80, false, NormalEnemySprite.RedYellow);
			temp.movement.MoveStraight(6, new Vector2(300, 300));
			scheduler.schedule(() ->{
				if(temp.isActive) {
					temp.movement.StopMove();
					temp.PlayerCircleAttack(1, 200, 18);
					temp.AttackNow();
					scheduler.schedule(() -> {
						if(temp.isActive) {
							temp.NoAttack();
						}
					}, 3000, TimeUnit.MILLISECONDS);
					scheduler.schedule(() -> {
						if(temp.isActive)
							temp.movement.MoveStraight(3, new Vector2(300, 700));
					}, 4000, TimeUnit.MILLISECONDS);
				}
			}, 3000, TimeUnit.MILLISECONDS);
			
		}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
		scheduleTime += 1000;
		scheduler.schedule(() -> {
			NormalEnemy temp = GetNormalEnemy(new Vector2(500, 700), 80, false, NormalEnemySprite.RedYellow);
			temp.movement.MoveStraight(6, new Vector2(500, 300));
			scheduler.schedule(() ->{
				if(temp.isActive) {
					temp.movement.StopMove();
					temp.PlayerCircleAttack(1, 200, 18);
					temp.AttackNow();
					scheduler.schedule(() -> {
						if(temp.isActive) {
							temp.NoAttack();
						}
					}, 3000, TimeUnit.MILLISECONDS);
					scheduler.schedule(() -> {
						if(temp.isActive)
							temp.movement.MoveStraight(3, new Vector2(500, 700));
					}, 4000, TimeUnit.MILLISECONDS);
				}
			}, 3000, TimeUnit.MILLISECONDS);
			
		}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
		scheduleTime += 1000;
		scheduler.schedule(() -> {
			NormalEnemy temp = GetNormalEnemy(new Vector2(700, 700), 80, false, NormalEnemySprite.RedYellow);
			temp.movement.MoveStraight(6, new Vector2(700, 300));
			scheduler.schedule(() ->{
				if(temp.isActive) {
					temp.movement.StopMove();
					temp.PlayerCircleAttack(1, 200, 18);
					temp.AttackNow();
					scheduler.schedule(() -> {
						if(temp.isActive) {
							temp.NoAttack();
						}
					}, 3000, TimeUnit.MILLISECONDS);
					scheduler.schedule(() -> {
						if(temp.isActive)
							temp.movement.MoveStraight(3, new Vector2(700, 700));
					}, 4000, TimeUnit.MILLISECONDS);
				}
			}, 3000, TimeUnit.MILLISECONDS);
			
		}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
		//Wave4 End
		scheduleTime += 10000;
		scheduler.schedule(() -> {
			new Boss1();
		}, stageStartTime + scheduleTime, TimeUnit.MILLISECONDS);
		
	}
}
