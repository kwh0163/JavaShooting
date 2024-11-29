package Object.Player;

import java.awt.image.BufferedImage;

import Game.GameManager;
import Util.AudioType;
import Util.Time;
import Util.Vector2;

public class Player0Attack extends PlayerAttack {
	public Player0Attack(Player _player) {
		super(_player);
	}

	
	@Override
	protected void FirstAmmo() {
		if(firstAttackSpeedCounter < firstAttackSpeed) {
			firstAttackSpeedCounter += Time.DeltaTime();
			return;
		}
		if(!isAttacking)
			return;
		
		GameManager.instance.Audio.PlaySound(AudioType.PlayerShoot, attackVolume);
		firstAttackSpeedCounter = 0;
		
		switch (level) {
		case 1: 
			CreateStraightAmmo(origin.transform.GetPosition(),0, 0);
			break;
		case 2:
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(3, 0)), -2, 0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-3, 0)), 2, 0);
			break;
		case 3:
			CreateStraightAmmo(origin.transform.GetPosition(), 0, 0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(6, 0)), -4, 0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-6, 0)), 4, 0);
			break;
		case 4:
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(3, 0)), -2, 0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(9, 0)), -6, 0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-3, 0)), 2, 0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-9, 0)), 6, 0);
			break;
		case 5:
		default:
			CreateStraightAmmo(origin.transform.GetPosition(),0, 0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(6, 0)), -4, 0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(12, 0)), -8, 0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-6, 0)), 4, 0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-12, 0)), 8, 0);
			break;
		}
	}
	
	@Override
	protected void SecondAmmo() {
		if(secondAttackSpeedCounter < secondAttackSpeed) {
			secondAttackSpeedCounter += Time.DeltaTime();
			return;
		}
		if(!isAttacking)
			return;
		
		if(level > 2)
			FindTarget();
		secondAttackSpeedCounter = 0;

		GameManager.instance.Audio.PlaySound(AudioType.PlayerShoot, attackVolume);
		switch (level) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 0)),30);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(10, 0)), -30);
			break;
		case 4:
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 0)),30);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(10, 0)), -30);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 0)),50);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(10, 0)), -50);
			break;
		case 5:
		default:
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 0)),30);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(10, 0)), -30);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 0)), 50);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(10, 0)), -50);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 0)), 70);
			CreateGuideAmmo(origin.transform.GetPosition().Add(new Vector2(10, 0)), -70);
			break;
		}
	}
}
