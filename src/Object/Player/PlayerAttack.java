package Object.Player;

import java.util.ArrayList;

import Util.Time;
import Util.Vector2;

public class PlayerAttack {
	private static boolean DEBUG_MODE = false;
	
	private Player origin;
	public int level = 5;
	public boolean isAttacking = false;
	
	private ArrayList<PlayerStraightAmmo> ammosList;
	private ArrayList<PlayerGuidanceAmmo> guideAmmosList;
	
	private double straightAttackSpeed = 0.1;
	private double straightAttackSpeedCounter = 0;
	private double guideAttackSpeed = 0.1;
	private double guideAttackSpeedCounter = 0;
	
	public PlayerAttack(Player _player) {
		ammosList = new ArrayList<PlayerStraightAmmo>();
		guideAmmosList = new ArrayList<PlayerGuidanceAmmo>();
		origin = _player;
	}
	
	public void Attack() {
		StraightAmmo();
		GuideAmmo();
	}

	private void CreateStraightAmmo(Vector2 _position, float _degree) {
		for(int i = 0;i<ammosList.size();i++) {
			if(!ammosList.get(i).isActive) {
				if(DEBUG_MODE)
					System.out.println("Set " + i + " Straight Ammo");
				ammosList.get(i).Reset(_position, _degree);
				return;
			}
		}
		if(DEBUG_MODE)
			System.out.println("new Straight Ammo");
		ammosList.add(new PlayerStraightAmmo(_position, _degree));
	}
	private void CreateGuideAmmo(Vector2 _position, Vector2 _firstDirection) {

		for(int i = 0;i<guideAmmosList.size();i++) {
			if(!guideAmmosList.get(i).isActive) {
				if(DEBUG_MODE)
					System.out.println("Set " + i + " Guide Ammo");
				guideAmmosList.get(i).Reset(_position, _firstDirection);
				return;
			}
		}

		if(DEBUG_MODE)
			System.out.println("new Guide Ammo");
		guideAmmosList.add(new PlayerGuidanceAmmo(_position, _firstDirection));
	}
	
	private void StraightAmmo() {
		if(straightAttackSpeedCounter < straightAttackSpeed) {
			straightAttackSpeedCounter += Time.DeltaTime();
			return;
		}
		if(!isAttacking)
			return;
		
		straightAttackSpeedCounter = 0;
		
		switch (level) {
		case 1: 
			CreateStraightAmmo(origin.transform.GetPosition(),0);
			break;
		case 2:
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(7, 0)),0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-7, 0)),0);
			break;
		case 3:
			CreateStraightAmmo(origin.transform.GetPosition(), 0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(15, 0)), -20);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-15, 0)),20);
			break;
		case 4:
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(7, 0)),0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-7, 0)),0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(21, 0)),-20);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-21, 0)),20);
		case 5:
		default:
			CreateStraightAmmo(origin.transform.GetPosition(),0);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(15, 0)), -10);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(30, 0)), -20);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-15, 0)), 10);
			CreateStraightAmmo(origin.transform.GetPosition().Add(new Vector2(-30, 0)), 20);
			break;
		}
	}
	private void GuideAmmo() {
		if(guideAttackSpeedCounter < guideAttackSpeed) {
			guideAttackSpeedCounter += Time.DeltaTime();
			return;
		}
		if(!isAttacking)
			return;
		guideAttackSpeedCounter = 0;
		
		switch (level) {
		case 1:
			break;
		case 2:
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up());
			break;
		case 3:
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(10));
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(-10));
			break;
		case 4:
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(5));
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(15));
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(-5));
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(-15));
			break;
		case 5:
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(5));
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(15));
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(25));
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(-5));
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(-15));
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up().rotate(-25));
			break;
		default:
			CreateGuideAmmo(origin.transform.GetPosition(), Vector2.Up());
			break;
		}
	}
}
