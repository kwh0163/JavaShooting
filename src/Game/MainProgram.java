package Game;

import java.awt.Dimension;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Test.COPY;
import Util.Time;
import Util.Vector2;

public class MainProgram {
	public static String title = "Strike";
	public static int width = 800;
	public static int height = 600;
	public static int defaultPixel = 48;
	
	public static Random random;
	
	public static GamePanel panel;
	public static ColliderManager colliderManager;
	public static GameObjectManager gameObjectManager;
	
	public static int PlayerInt = 0;
	
	public static JFrame frame;
	public static JPanel cpyPanel;
	
	public static void main(String args[]) {
		random = new Random();
		random.setSeed(System.currentTimeMillis());

    	frame = new JFrame(title);
        
        frame.setSize(width, height);
        
        COPY cpy = new COPY();
        cpyPanel = cpy.GetPanel();
        frame.add(cpyPanel);
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // 화면 중앙에 배치
        frame.setVisible(true);
        
	}
	
	public static void StartGame() {
		gameObjectManager = new GameObjectManager();
	    colliderManager = new ColliderManager();
	
	    // 새 패널 생성 및 초기화
	    panel = new GamePanel(new KeyActionHandler());
	    panel.setPreferredSize(new Dimension(800, 600)); // 크기 설정
	    panel.setFocusable(true);
	
	    // 기존 컴포넌트 제거 및 새 패널 추가
	    frame.getContentPane().removeAll();
	    cpyPanel.setVisible(false);
	    frame.add(panel);
	
	    // 레이아웃 다시 계산 및 화면 갱신
	    panel.requestFocusInWindow();
	    frame.setVisible(true);
	    frame.revalidate();
	    frame.repaint();
	
	    // 포커스 강제 요청
	
	    // 게임 매니저 및 시간 관리 시작
	    new GameManager(PlayerInt);
	    Time.StartProgram(gameObjectManager, panel);
		
	}
	
	public static boolean IsOveredWorld(Vector2 _position) {
		if(_position.x <= -50 || _position.x >= 850)
			return true;
		if(_position.y <= -50 || _position.y >= 650)
			return true;
		return false;
	}
	
	public static Random GetRandom() {
		return random;
	}
}
