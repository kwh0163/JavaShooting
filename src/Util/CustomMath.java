package Util;

public class CustomMath {
	public static double CubicFunc75(double x) {
		x = Math.clamp(x, 0, 1);
		double y = (10.67 * x * x * x) - (16 * x * x) + (6.33 * x);
		return y;
	}
	public static double CubicFunc6(double x) {
		x = Math.clamp(x, 0, 1);
		double y = (7.43 * x * x * x) - (11.2 * x * x) + (4.73 * x);
		return y;
	}
	public static double CubicFunc5(double x) {
		x = Math.clamp(x, 0, 1);
		double y = (5.33 * x * x * x) - (8 * x * x) + (3.67 * x);
		return y;
	}
	public static double Lerp(double a, double b, double x) {
		double y = a + (b - a) * x;
		return y;
	}
}
