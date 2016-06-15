package interfaz;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logica.ColorPieza;
import logica.Juego;

public class PantallaJuego extends Pane{
	TableroPantallaJuego tablero;
	TablaJugadas tablaJugadas;
	EstadoDeJuego estadoDeJuego;
	BotonEnroque botonEnroque;
	public PantallaJuego(int cantJugadores, ColorPieza color, boolean hayTiempo){
		super();
		this.setPrefSize(900, 800);
		Juego elJuego=new Juego();
		inicializarMenu();
		tablaJugadas=new TablaJugadas();
		botonEnroque = new BotonEnroque(elJuego);
		estadoDeJuego = new EstadoDeJuego(elJuego,botonEnroque);
		tablero= new TableroPantallaJuego(elJuego,estadoDeJuego,tablaJugadas,color);
		
		this.getChildren().addAll(tablero,estadoDeJuego,tablaJugadas,botonEnroque);
		
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
	}
	
	private void inicializarMenu(){
		Label titulo = new MyLabel("",200,20,500,100);
		titulo.setAlignment(Pos.TOP_CENTER);
		titulo.setId("labelChess");
		
		Button buttonGuardarPartida = new MyButton("Guardar", 130,670,200,50);
		buttonGuardarPartida.getStyleClass().add("roundedButton");
		buttonGuardarPartida.setOnAction(e->{
			GuardarPartida.display();
		});
		
		Button buttonDeshacer = new MyButton("Deshacer",360,670,200,50);
		buttonDeshacer.setOnAction(e->tablero.deshacerJugada());
		buttonDeshacer.getStyleClass().add("roundedButton");
		
		Button buttonMenu = new MyButton("Menu",580,670,200,50);
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
