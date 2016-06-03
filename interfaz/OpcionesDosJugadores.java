package interfaz;


import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OpcionesDosJugadores extends Pane{
	Label titulo;
	Label tiempoLabel;
	RadioButton tiempo;
	Button iniciarJuego;
	
	
	
	public OpcionesDosJugadores(){
		
		super();
		
		this.setPrefSize(700, 800);
		
		titulo = new MyLabel("",100,50,500,100);
		titulo.setAlignment(Pos.TOP_CENTER);
		titulo.setId("labelChess");
		
		tiempoLabel = new MyLabel("Jugar con limite de tiempo",200,200,300,50);
		
		tiempo = new MyRadioButton(460,199,100,50);

		iniciarJuego = new MyButton(" Jugar ",225,270,250,60);

		this.getChildren().addAll(titulo,tiempoLabel,tiempo,iniciarJuego);
		
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		
		Scene escena = new Scene(new PantallaJuego());
		escena.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		 
		iniciarJuego.setOnAction(e -> ((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(escena));
	}
}
