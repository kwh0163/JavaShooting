package Util;

import Game.*;

public class Time {
	private static boolean DEBUG_FPS;
	
	private static int FPS = 62;
	private static float deltaTime = 0;
	private static float fixedDeltaTime = 0.02f;
	
	public static float DeltaTime() {
		return deltaTime;
	}
	public static float FixedDeltaTime() {
		return fixedDeltaTime;
	}
	
	public static void StartProgram(GameObjectManager _objectManager, GamePanel _panel) {
		
		long a = System.currentTimeMillis();
		long b = System.currentTimeMillis();
		long process1 = a;
		long process2 = a;
		
		
		while(true) {
			process1 = System.currentTimeMillis();
			process2 = System.currentTimeMillis();
			
			if(process1 - a >= (1000.0 / FPS)){
				if(DEBUG_FPS) {
					double temp = 1000.0 / (process1 - a);
					Debug.Log(String.format("Current FPS : %f", temp));
				}
				deltaTime = (float)((process1 - a) / 1000.0000);
				a = process1;
				
				_objectManager.Awake();
				
				_objectManager.Start();
				
				_objectManager.Update();

				_objectManager.LateUpdate();
				
				_panel.Update();
			}
			
			if(process2 - b >= 1000 * fixedDeltaTime) {
				b = process2;
				
				_objectManager.FixedUpdate();
			}
		}
	}
}
