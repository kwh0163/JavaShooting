package Game;

import Object.GameObject;
import Stage.Stage1.Stage1;
import Util.Time;
import Util.Vector2;

public class StageManager extends GameObject{

	public int score = 0;
	public int highScore = 0;
	public int lives = 0;
	public int enemiesDefeated = 0;
	public int startTime = 0;
	public int power = 0;
	
	double timeCount = 0;
	boolean isStageStart = false;
	
	public StageManager(Vector2 _position) {
		super(_position);
		
		sprite.isVisible = false;
		
		isStageStart = true;
		new Stage1(this);
	}

	@Override
	public void Update() {
		super.Update();
		
		if(isStageStart)
			timeCount += Time.DeltaTime();
		
		startTime = (int)timeCount;
		lives = GameManager.instance.Player.health.hp;
		power = GameManager.instance.Player.attack.level;
		score = GameManager.instance.Score.score;
	}
}
