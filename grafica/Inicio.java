package grafica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import grafica.MyButton;
import grafica.MyLabel;
import grafica.OpcionesDosJugadores;
import grafica.OpcionesUnJugador;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logica.ColorPieza;
import logica.Juego;

public class Inicio extends Pane {
	private Juego juego;
	private int cantJugadores;
	private ColorPieza colorElegido;
	
	
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
		buttonCargarPartida.setOnAction(e -> {
			String nombreArchivo = pedirArchivo();
			if(nombreArchivo != null && cargar(nombreArchivo)){
				((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(new Scene(new PantallaJuego(juego,cantJugadores,colorElegido)));
			}
		});
		
		Button buttonSalir = new MyButton("Salir",200,560,300,100);
		buttonSalir.getStyleClass().add("roundedButton");
		buttonSalir.setOnAction(e-> ((Stage)(((Node) e.getSource()).getScene().getWindow())).close());
		
		this.getChildren().addAll(titulo,buttonUnJugador,buttonDosJugadores,buttonCargarPartida,buttonSalir);
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		
	}
	
	private String pedirArchivo(){
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(new Stage());
		if(file != null){
			return file.getName();
		}
		return null;
	}
	
	private boolean cargar(String nombreArchivo){
		
		try {
			FileInputStream file = new FileInputStream(nombreArchivo);
			ObjectInputStream juegoCargado = new ObjectInputStream(file);
			
			juego = (Juego) juegoCargado.readObject();
			cantJugadores = juegoCargado.readInt();
			colorElegido = (ColorPieza) juegoCargado.readObject();
			
			juegoCargado.close();
			
		} catch (FileNotFoundException e){
			
			Alerta.display("Oh oh! Algo salio mal...", "El archivo cargado no se puede leer.");
			return false;
			
		} catch (ClassNotFoundException | IOException e) {
			
			Alerta.display("Oh oh! Algo salio mal...", "Se ha producido un error al intentar cargar el archivo.");
			return false;
			
		}
		return true;
		
	}
}
