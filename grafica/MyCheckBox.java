package grafica;

import javafx.scene.control.CheckBox;

public class MyCheckBox extends CheckBox{
	public MyCheckBox(int x, int y, int width, int height){
		super();
		this.setPrefSize(width,height);
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
}
