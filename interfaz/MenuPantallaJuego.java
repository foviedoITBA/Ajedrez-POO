
import javafx.scene.Group;
import javafx.scene.control.Button;


public class MenuPantallaJuego extends Group{

	public MenuPantallaJuego(){
		
		super();
		
		Button buttonGuardarPartida = new MyButton("Guardar Partida",30,20,200,50);
		Button buttonDeshacer = new MyButton("Deshacer",250,20,200,50);
		Button buttonSalir = new MyButton("Salir",470,20,200,50);
		TableroPantallaJuego tablero = new TableroPantallaJuego();
	
		this.getChildren().addAll(buttonGuardarPartida,buttonDeshacer,tablero,buttonSalir);
		
		
	}
}
