package grafica;
/**
 * La clase MyLabel es una nueva clase descendiente de Label. 
 * En su constructor recibe: un texto, posicion en x, posicion en y, ancho y alto.
 */
import javafx.scene.control.Label;

public class MyLabel extends Label {

	public MyLabel(String text, int x, int y, int width, int height){
		super();
		this.setText(text);
		this.setPrefSize(width,height);
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
}
