package grafica;
/**
 * MyRadioButton hereda de la clase RadioButton con la finalidad de simplificar la contruccion de las intancias
 * necesitadas por la interfaz, tomando: posicion en x, posicion en y, ancho y alto.
 */
import javafx.scene.control.RadioButton;

public class MyRadioButton extends RadioButton{
	
	public MyRadioButton(int x, int y, int width, int height){
		super();
		this.setPrefSize(width,height);
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
}
