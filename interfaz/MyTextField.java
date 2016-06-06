package interfaz;

import javafx.scene.control.TextField;

public class MyTextField extends TextField{
	public MyTextField(String text, int x, int y, int width, int height){
		super();
		this.setText(text);
		this.setPrefSize(width,height);
		this.setTranslateX(x);
		this.setTranslateY(y);
	}

}
