package Object.Player;

import Game.GameManager;
import MonoBehavior.MonoBehavior;
import Util.AudioType;
import Util.Time;

public class PlayerHealth extends MonoBehavior {
	private Player origin;
	
	public int hp;
	public boolean isHitable = true;
	private float hitCounter = 0;
	private float unbeatableTime = 5;
	private float hitInterver = 1;
	
	public PlayerHealth(Player _origin, int _hp) {
		super(_origin);
		origin = _origin;
		hp = _hp;
	}
	
	@Override
	public void Update() {
		super.Update();
		HitEffect();
	}
	
	public boolean TryDamage() {
		if(!isHitable)
			return false;
		hp--;
		GameManager.instance.Audio.PlaySound(AudioType.PlayerHit, 0.85f);
		isHitable = false;
		return true;
	}
	
	private void HitEffect() {
		if(isHitable)
			return;
		if(hitCounter < unbeatableTime) {
			SetHitAlpha(hitCounter);
			hitCounter += Time.DeltaTime();
			return;
		}
		isHitable = true;
		hitCounter = 0;
		origin.sprite.alpha = 1;
	}
	private void SetHitAlpha(float _x) {
		float value = (float)((Math.cos(2 / hitInterver * _x * Math.PI) + 1) * 0.5f);
		origin.sprite.alpha = value;
	}
}
