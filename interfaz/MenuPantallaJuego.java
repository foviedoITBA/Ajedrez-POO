package interfaz;


import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logica.Juego;


public class MenuPantallaJuego extends Group{
	Juego elJuego;
	public MenuPantallaJuego(Juego elJuego){
		
//		super();
		this.elJuego=elJuego;
		
		Label titulo = new MyLabel("",100,20,500,100);
		titulo.setAlignment(Pos.TOP_CENTER);
		titulo.setId("labelChess");
		
		Button buttonGuardarPartida = new MyButton("Guardar",30,700,200,50);
		buttonGuardarPartida.getStyleClass().add("roundedButton");
		buttonGuardarPartida.setOnAction(e->{
			GuardarPartida.display();
		});
		
		
		Button buttonDeshacer = new MyButton("Deshacer",250,700,200,50);
		buttonDeshacer.getStyleClass().add("roundedButton");
		
		
		Button buttonMenu = new MyButton("Menu",470,700,200,50);
		buttonMenu.getStyleClass().add("roundedButton");
		buttonMenu.setOnAction(e ->{
			if(ConfirmacionSalir.display()){
				GuardarPartida.display();//Guardar Patidaaaaa
			}
			((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(new Scene( new Inicio()));
			
		});
		
	
		this.getChildren().addAll(titulo,buttonGuardarPartida,buttonDeshacer,buttonMenu);
		
		
	}
}
