package Game;
import javax.swing.*;

import Object.*;
import Util.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements IBehavior{
	private static final boolean DEBUG_MODE = false;
	
	private String titleString = "Strike";
	private int width = 800;
	private int height = 600;
	private int defaultPixel = 48;
	
	
	private static ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public static void AddObject(GameObject _object) {
		for(int i = 0;i<objects.size();i++) {
			if(_object.sprite.sortIndex <= objects.get(i).sprite.sortIndex) {
				objects.add(i, _object);
			return;
			}
		}
		objects.add(_object);
	}
	
	public GamePanel(KeyActionHandler key) {
		key.Initialize(this);
        setFocusable(true);
        
        Time.behaviors.add(this);
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D graphics2d = (Graphics2D)g;
        
        for(int i = 0;i<objects.size();i++)
        	DrawObject(graphics2d, objects.get(i));
    }
    
    public void CreateFrame() {
        JFrame frame = new JFrame(titleString);
        
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);
    }
	@Override
	public void Update() {
		repaint();
	}
	@Override
	public void FixedUpdate() {
		// TODO Auto-generated method stub
		
	}
	
	private void DrawObject(Graphics2D graphics, GameObject _object) {
		if (_object == null || _object.transform == null) return; // null 체크

	    if (DEBUG_MODE) {
	        System.out.println(String.format("Draw %s in %.2f, %.2f", 
	            _object.name, _object.transform.GetPosition().x, _object.transform.GetPosition().y));
	    }
	    
	    Vector2 position = _object.transform.GetPosition();
	    position.y = height - position.y;

	    // 사각형 정보
	    Rectangle2D rect = new Rectangle2D.Float(
	    	position.x,
	    	position.y,
	        defaultPixel * _object.transform.scale.x,
	        defaultPixel * _object.transform.scale.y
	    );

	    BufferedImage image = _object.sprite != null ? _object.sprite.image : null;

	    if (image != null) {
	        // 이미지 그리기
	        graphics.drawImage(image, 
	            (int) position.x, 
	            (int) position.y,
	            (int) (defaultPixel * _object.transform.scale.x), 
	            (int) (defaultPixel * _object.transform.scale.y), 
	            null
	        );
	    } else {
	        // 기본 색상
	        graphics.setColor(Color.RED);
	        graphics.fill(rect);
	    }
	}
}
