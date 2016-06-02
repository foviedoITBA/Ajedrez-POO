package interfaz;

import javafx.scene.control.Button;

public class MyButton extends Button{

	private final int defaultWidth = 200;
	private final int defaultHeight = 100;
	private final int defaultX = 250;

	public MyButton(String text,int y){
		super();
		this.setText(text);
		this.setPrefSize(defaultWidth,defaultHeight);
		this.setTranslateX(defaultX);
		this.setTranslateY(y);
	}
	
	public MyButton(String text, int x, int y, int width, int height){
		super();
		this.setText(text);
		this.setPrefSize(width,height);
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
	
}
