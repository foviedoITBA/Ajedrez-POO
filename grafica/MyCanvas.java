package grafica;
/**
 * MyCanvas es una clase creada para simplificar la creacion de una instancia, ya que creamos un nuevo contructor 
 * que recibe los parametros mas utilizados para los canvas de la interfaz.
 * En su constructor toma una imagen para su fondo, posicion en x, posicion en y, ancho y alto.
 */

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
