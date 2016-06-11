package interfaz;

import interfaz.MyButton;
import interfaz.MyLabel;
import interfaz.OpcionesDosJugadores;
import interfaz.OpcionesUnJugador;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import logica.Color;

public class Inicio extends Pane {
	public Inicio(){
		super();
		this.setPrefSize(700, 800);
		Label titulo = new MyLabel("",100,50,500,100);
		titulo.setAlignment(Pos.TOP_CENTER);
		titulo.setId("labelChess");

		Button buttonUnJugador = new MyButton("Un Jugador", 200,200,300,100);
		buttonUnJugador.getStyleClass().add("roundedButton");
		buttonUnJugador.setOnAction(e -> ((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(new Scene(new OpcionesUnJugador())));

		Button buttonDosJugadores = new MyButton("Dos Jugadores",200,320,300,100);
		buttonDosJugadores.getStyleClass().add("roundedButton");
		buttonDosJugadores.setOnAction(e -> ((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(new Scene(new OpcionesDosJugadores())));

		Button buttonCargarPartida = new MyButton("Cargar Partida",200,440,300,100);
		buttonCargarPartida.getStyleClass().add("roundedButton");
		
		Button buttonSalir = new MyButton("Salir",200,560,300,100);
		buttonSalir.getStyleClass().add("roundedButton");
		buttonSalir.setOnAction(e-> ((Stage)(((Node) e.getSource()).getScene().getWindow())).close());
		
		this.getChildren().addAll(titulo,buttonUnJugador,buttonDosJugadores,buttonCargarPartida,buttonSalir);
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		
	}
}
