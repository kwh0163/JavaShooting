package Util;


public class Vector2 {
	public float x;
	public float y;
	
	public Vector2(float _x, float _y) {
		x = _x;
		y = _y;
	}
	public Vector2(Vector2 vector) {
		x = vector.x;
		y = vector.y;
	}
	 public float GetMagnitude() {
        return (float) Math.sqrt(x * x + y * y);
	}

	    // 벡터 정규화
    public Vector2 GetNormalized() {
        float magnitude = GetMagnitude();
        if (magnitude == 0) {
            return new Vector2(0, 0); // 크기가 0인 벡터는 정규화 불가, (0, 0) 반환
        }
        return new Vector2(x / magnitude, y / magnitude);
	    }
	
	public static Vector2 Zero() {
		return new Vector2(0,0);
	}
	public static Vector2 Up() {
		return new Vector2(0,1);
	}
	public static Vector2 Down() {
		return new Vector2(0,-1);
	}
	public static Vector2 Left() {
		return new Vector2(-1,0);
	}
	public static Vector2 Right() {
		return new Vector2(1,0);
	}
	public static Vector2 One() {
		return new Vector2(1, 1);
	}
	public static Vector2 Add(Vector2 a, Vector2 b) {
		return new Vector2(a.x + b.x, a.y + b.y );
	}
	public static Vector2 Sub(Vector2 a, Vector2 b) {
		return new Vector2(a.x - b.x, a.y - b.y );
	}
	public static Vector2 Mult(Vector2 a, float b) {
		return new Vector2(a.x*b, a.y*b);
	}

}