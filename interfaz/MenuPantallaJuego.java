package interfaz;


import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class MenuPantallaJuego extends Group{

	public MenuPantallaJuego(){
		
		super();
		
		Label titulo = new MyLabel("",100,20,500,100);
		titulo.setAlignment(Pos.TOP_CENTER);
		titulo.setId("labelChess");
		
		Button buttonGuardarPartida = new MyButton("Guardar",30,700,200,50);
	
		Button buttonDeshacer = new MyButton("Deshacer",250,700,200,50);
		
		
		Button buttonMenuPrincipal = new MyButton("Menu Principal",470,700,200,50);
		buttonMenuPrincipal.setOnAction(e -> ((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(new Scene( new Inicio())));
		
		TableroPantallaJuego tablero = new TableroPantallaJuego();
	
		this.getChildren().addAll(titulo,buttonGuardarPartida,buttonDeshacer,tablero,buttonMenuPrincipal);
		
		
	}
}
