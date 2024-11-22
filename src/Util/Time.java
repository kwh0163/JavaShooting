package Util;
import java.util.ArrayList;

public class Time {
	
	private static int FPS = 60;
	private static float deltaTime = 0;
	private static float fixedDeltaTime = 0.02f;
	
	public static float DeltaTime() {
		return deltaTime;
	}
	public static float FixedDeltaTime() {
		return fixedDeltaTime;
	}
	
	public static ArrayList<IBehavior> behaviors = new ArrayList<IBehavior>();
	
	public static void StartProgram() {
		long a = System.currentTimeMillis();
		long b = System.currentTimeMillis();
		long process1 = a;
		long process2 = a;
		
		while(true) {
			process1 = System.currentTimeMillis();
			process2 = System.currentTimeMillis();
			
			if(process1 - a >= (1000.0 / FPS)){
				deltaTime = (float)((process1 - a) / 1000.0000);
				a = process1;
				
				for(int i = 0;i<behaviors.size();i++) {
					IBehavior current = behaviors.get(i);
					if(current.GetIsActive())
						current.Update();
				}
			}
			
			if(process2 - b >= 1000 * fixedDeltaTime) {
				b = process2;
				for(int i = 0;i<behaviors.size();i++) {
					IBehavior current = behaviors.get(i);
					if(current.GetIsActive())
						current.FixedUpdate();
				}
			}
		}
	}
}
