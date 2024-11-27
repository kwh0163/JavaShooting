package Util;

public class Debug {
	public static <T> void Log(String string) {
		int line = Thread.currentThread().getStackTrace()[2].getLineNumber();
		String tmpClass = Thread.currentThread().getStackTrace()[2].getClassName().toString();
		System.out.println(String.format("%s     | %s in %d", string, tmpClass, line));
	}
}
