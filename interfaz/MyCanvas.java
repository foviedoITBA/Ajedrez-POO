package interfaz;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

public class MyCanvas extends Canvas {
	public MyCanvas(Image img, double x, double y, double width, double height){
		super(width,height);
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.getGraphicsContext2D().drawImage(img,0,0,width,height);
	}
}
