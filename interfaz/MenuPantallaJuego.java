package interfaz;


import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class MenuPantallaJuego extends Group{

	public MenuPantallaJuego(){
		
		super();
		
		Label titulo = new MyLabel("",100,20,500,100);
		titulo.setAlignment(Pos.TOP_CENTER);
		titulo.setId("labelChess");
		
		Button buttonGuardarPartida = new MyButton("Guardar",30,700,200,50);
		Button buttonDeshacer = new MyButton("Deshacer",250,700,200,50);
		Button buttonSalir = new MyButton("Salir",470,700,200,50);
		TableroPantallaJuego tablero = new TableroPantallaJuego();
	
		this.getChildren().addAll(titulo,buttonGuardarPartida,buttonDeshacer,tablero,buttonSalir);
		
		
	}
}
