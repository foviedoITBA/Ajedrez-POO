package interfaz;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OpcionesDosJugadores extends Pane{

	public OpcionesDosJugadores(){
		
		super();
		
		this.setPrefSize(700, 800);
		
		RadioButton tiempo = new RadioButton();
		tiempo.setPrefSize(100, 50);
		tiempo.setTranslateX(360);
		tiempo.setTranslateY(250);

		Label tiempoLabel = new Label();
		tiempoLabel.setPrefSize(300, 25);
		tiempoLabel.setTranslateX(255);
		tiempoLabel.setTranslateY(225);
		tiempoLabel.setText("Jugar con limite de tiempo");

		Button iniciarJuego = new Button();
		iniciarJuego.setText(" Start Game ");
		iniciarJuego.setPrefSize(200,50);
		iniciarJuego.setTranslateX(270);
		iniciarJuego.setTranslateY(350);

		this.getChildren().addAll(tiempoLabel,tiempo,iniciarJuego);
		
		this.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
		
		Scene escena = new Scene(new PantallaJuego());
		escena.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
		 
		iniciarJuego.setOnAction(e -> ((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(escena));
	}
}
