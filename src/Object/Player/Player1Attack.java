package Object.Player;

import Game.GameManager;
import Util.AudioType;
import Util.Time;
import Util.Vector2;

public class Player1Attack extends PlayerAttack{

	public Player1Attack(Player _player) {
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
		case 2:
		case 3:
			if(isFocussing) {
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(13, 27)), 0, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-13, 27)), 0, 0);
			}
			else {
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(13, 27)), -4, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-13, 27)), 4, 0);
			}
			break;
		case 4:
			if(isFocussing) {
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(13, 27)), 0, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-13, 27)), 0, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(23, 19)), 0, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-23, 19)), 0, 0);
			}
			else {
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(13, 27)), -2, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-13, 27)), 2, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(23, 19)), -6, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-23, 19)), 6, 0);
			}
			break;
		case 5:
		default:
			if(isFocussing) {
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(3, 27)), 0, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-3, 27)), 0, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(13, 27)), 0, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-13, 27)), 0, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(23, 19)), 0, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-23, 19)), 0, 0);
			}
			else {
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(3, 27)), -2, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-3, 27)), 2, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(13, 27)), -6, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-13, 27)), 6, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(23, 19)), -10, 0);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-23, 19)), 10, 0);
			}
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

		GameManager.instance.Audio.PlaySound(AudioType.PlayerShoot, attackVolume);
		secondAttackSpeedCounter = 0;
		
		switch (level) {
		case 1:
			break;
		case 2:
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(0, 35)),0, 1);
			break;
		case 3:
			if(isFocussing) {
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(0, 35)),0, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 35)),0, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(10, 35)),0, 1);
			}
			else {
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(0, 35)),0, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 35)),10, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(10, 35)),-10, 1);
			}
			break;
		case 4:
			if(isFocussing) {
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(0, 35)),0, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 35)),0, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(10, 35)),0, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-20, 35)),0, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(20, 35)),0, 1);
			}
			else {
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(0, 35)),0, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 35)),10, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(10, 35)),-10, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-20, 35)),20, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(20, 35)),-20, 1);
			}
			break;
		case 5:
			if(isFocussing) {
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(0, 35)),0, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 35)),0, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(10, 35)),0, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(20, 35)),0, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-20, 35)),0, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-30, 35)),0, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(30, 35)),0, 1);
			}
			else {
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(0, 35)),0, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-10, 35)),10, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(10, 35)),-10, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-20, 35)),20, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(20, 35)),-20, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-30, 35)),30, 1);
				CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(30, 35)),-30, 1);
			}
		default:
			break;
		}
	}

}
