package Object.Enemy;

import Game.GameManager;
import MonoBehavior.MonoBehavior;
import Util.AudioType;
import Util.Debug;

public class EnemyHealth extends MonoBehavior {
	private static boolean DEBUG_MODE = false;
	
	private Enemy origin;
	
	public int hp;
	
	public EnemyHealth(Enemy _origin, int _hp) {
		super(_origin);
		origin = _origin;
		hp = _hp;
	}
	
	public void Damage(int _damage) {
		hp -= _damage;
		if(DEBUG_MODE) {
			Debug.Log(String.format("%s got %d damages.", origin.name, _damage));
			Debug.Log(String.format("%s current HP : %d", origin.name, hp));
		}
		
		if(hp <= 0)
			KillEnemy();
	}
	
	private void KillEnemy() {
		if(DEBUG_MODE)
			Debug.Log("Kill " + origin.name);
		GameManager.instance.Stage.enemiesDefeated++;
		GameManager.instance.Score.score += origin.killScore;
		if(origin.isDropPower)
			GameManager.instance.GetPowerUp(origin.transform.GetPosition());
		GameManager.instance.Audio.PlaySound(AudioType.NormalEnemyDie, 0.85f);
		origin.OnDestroy();
	}
}
