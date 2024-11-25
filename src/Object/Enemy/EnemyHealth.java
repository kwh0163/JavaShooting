package Object.Enemy;

import MonoBehavior.MonoBehavior;

public class EnemyHealth extends MonoBehavior {
	private static boolean DEBUG_MODE = false;
	
	private Enemy origin;
	
	private int hp;
	
	public EnemyHealth(Enemy _origin, int _hp) {
		super(_origin);
		origin = _origin;
		hp = _hp;
	}
	
	public void Damage(int _damage) {
		hp -= _damage;
		if(DEBUG_MODE) {
			System.out.println(String.format("%s got %d damages.", origin.name, _damage));
			System.out.println(String.format("%s current HP : %d", origin.name, hp));
		}
		
		if(hp <= 0)
			KillEnemy();
	}
	
	private void KillEnemy() {
		System.out.println("Kill " + origin.name);
		origin.isActive = false;
	}
}