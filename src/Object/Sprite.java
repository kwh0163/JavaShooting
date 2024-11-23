package Object;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Sprite {
	public BufferedImage image;
	public Color color = Color.WHITE;
	
	public boolean isVisible;
	public int sortIndex = 0;
	
	public Sprite(BufferedImage _image) {
		image = _image;
		isVisible = true;
	}
}
