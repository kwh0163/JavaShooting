package Util;


public class Vector2 {
	public double x;
	public double y;
	
	public Vector2(double _x, double _y) {
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
	
    public Vector2 Add(Vector2 other) {
    	return new Vector2(x + other.x, y + other.y);
    }
    public Vector2 Sub(Vector2 other) {
    	return new Vector2(x - other.x, y - other.y);
    }
    public Vector2 Mul(double val) {
    	if(val == 0)
    		return Vector2.Zero();
    	return new Vector2(x * val, y * val);
    }
    
    public Vector2 rotate(double angleInRadians) {
    	
    	double radian = Math.toRadians(angleInRadians);
    	
        double cos =  Math.cos(radian);
        double sin = Math.sin(radian);
        
        double newX = x * cos - y * sin;
        double newY = x * sin + y * cos;
        
        return new Vector2(newX, newY);
    }
    
    @Override
    public String toString() {
        return String.format("Vector2(x=%.2f, y=%.2f)", x, y);
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
	public static Vector2 Mult(Vector2 a, double b) {
		return new Vector2(a.x*b, a.y*b);
	}
	public static double Distance(Vector2 a, Vector2 b) {
		double x = a.x - b.x;
		double y = a.y - b.y;
		x *= x;
		y *= y;
		return Math.sqrt(x + y);
	}
	public static Vector2 Lerp(float value, Vector2 a, Vector2 b) {
		a = a.Mul(1 - value);
		b = b.Mul(value);
		return a.Add(b);
	}
	public static Vector2 Lerp(double value, Vector2 a, Vector2 b) {
		a = a.Mul(1 - value);
		b = b.Mul(value);
		return a.Add(b);
	}

}