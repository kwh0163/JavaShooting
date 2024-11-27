package Game;
import javax.swing.*;
import Object.*;
import Util.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final boolean DEBUG_MODE = false;
	
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	private Composite originalComposite;
	
	public void AddObject(GameObject _object) {
		for(int i = 0;i<objects.size();i++) {
			if(_object.sprite.sortIndex <= objects.get(i).sprite.sortIndex) {
				objects.add(i, _object);
			return;
			}
		}   
		objects.add(_object);
	}
	public void RemoveObject(GameObject _object) {
		int index = objects.indexOf(_object);
		GameObject temp = objects.get(index);
		objects.remove(_object);
		temp = null;
	}
	
	public GamePanel(KeyActionHandler key) {
		key.Initialize(this);
        setFocusable(true);
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D graphics2d = (Graphics2D)g;
        if(originalComposite == null)
        	originalComposite = graphics2d.getComposite();
        
        for(int i = 0;i<objects.size();i++)
        	DrawObject(graphics2d, objects.get(i));
        	
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MainProgram.width, MainProgram.height); // 원하는 패널 크기 반환
    }
    
    public void Start() {
    	JFrame frame = new JFrame(MainProgram.title);
        
        frame.setSize(MainProgram.width, MainProgram.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }
    
	public void Update() {
		repaint();
	}
	
	private void DrawObject(Graphics2D graphics, GameObject _object) {
		if (_object == null || _object.transform == null) return; // null 체크
		if (!_object.isActive) return;
		
		if(!_object.sprite.isVisible) return;

	    if (DEBUG_MODE) {
	    	Debug.Log(String.format("Draw %s in %.2f, %.2f", 
	            _object.name, _object.transform.GetPosition().x, _object.transform.GetPosition().y));
	    }
	    
	    if(_object.sprite.alpha == 0) return;
	    
	    Vector2 position = _object.transform.GetPosition();
	    position = GetPivotPosition(_object);
	    position.y = MainProgram.height - position.y;

	    double angleInRadians = Math.toRadians(-_object.transform.rotation);
	    
	    graphics.rotate(angleInRadians, position.x, position.y);

	    // 사각형 정보
	    Rectangle2D rect = new Rectangle2D.Double(
	    	position.x,
	    	position.y,
	        MainProgram.defaultPixel * _object.transform.scale.x,
	        MainProgram.defaultPixel * _object.transform.scale.y
	    );

	    BufferedImage image = _object.sprite != null ? _object.sprite.image : null;
	    
	    if(_object.sprite.alpha < 0.95) {
	    	graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.clamp(_object.sprite.alpha, 0, 1)));
	    }

	    if (image != null) {
	        // 이미지 그리기
	        graphics.drawImage(image, 
	            (int) position.x, 
	            (int) position.y,
	            (int) (MainProgram.defaultPixel * _object.transform.scale.x), 
	            (int) (MainProgram.defaultPixel * _object.transform.scale.y), 
	            null
	        );
	    } else {
	        // 기본 색상
	        graphics.setColor(_object.sprite.color);
	        graphics.fill(rect);
	    }
	    
	    graphics.rotate(-angleInRadians, position.x, position.y);
        
        graphics.setComposite(originalComposite);
	}
	
	private Vector2 GetPivotPosition(GameObject _object) {
		Vector2 pivotPosition = new Vector2(_object.transform.GetPosition());
		pivotPosition.x -= (MainProgram.defaultPixel * _object.transform.scale.x * _object.transform.pivot.x);
		pivotPosition.y += (MainProgram.defaultPixel * _object.transform.scale.y * _object.transform.pivot.y);
		
		return pivotPosition;
	}
}
