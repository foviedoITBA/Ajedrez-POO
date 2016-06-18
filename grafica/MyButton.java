package grafica;
/**
 * La clase MyButton es una clase que hereda de Button y fue creada con el objetivo de simplicar el manejo de las intancias de la misma.
 * En su constructor toma los parametros mas utlizados: un texto, posicion en x, posicion en y, ancho y alto.
 */


import javafx.scene.control.Button;

public class MyButton extends Button{
	
	public MyButton(String text, int x, int y, int width, int height){
		super();
		this.setText(text);
		this.setPrefSize(width,height);
		this.setTranslateX(x);
		this.setTranslateY(y);
	}
	
}
