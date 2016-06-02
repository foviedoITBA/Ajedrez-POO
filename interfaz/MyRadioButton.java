import javafx.scene.control.RadioButton;

public class MyRadioButton extends RadioButton{
	
	public MyRadioButton(int x, int y, int width, int height){
		super();
		this.setPrefSize(width,height);
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
}
