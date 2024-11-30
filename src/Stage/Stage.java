package Stage;



import Game.GameManager;
import MonoBehavior.MonoBehavior;
import Object.GameObject;
import Object.Enemy.NormalEnemy;
import Util.NormalEnemySprite;
import Util.Vector2;

public class Stage extends MonoBehavior{
	static final boolean DEBUG_MODE = false;
	
	public Stage(GameObject _object) {
		super(_object);
	}
	
	protected NormalEnemy GetNormalEnemy(Vector2 _position, int _hp, boolean _isDropPower, NormalEnemySprite _sprite) {
		return GameManager.instance.Enemy.GetEnemy(_position, _hp, _isDropPower, _sprite);
	}
}