package grafica;
/**
 * La clase MyCheckBox hereda de CheckBox con el objetivo de facilitar la creacion de cada instancia.
 * El contructor recibe la posicion en x, la posicion en y, el ancho y alto deseado tambien.
 */
import javafx.scene.control.CheckBox;

public class MyCheckBox extends CheckBox{
	public MyCheckBox(int x, int y, int width, int height){
		super();
		this.setPrefSize(width,height);
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
}
