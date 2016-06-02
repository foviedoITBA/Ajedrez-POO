package interfaz;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OpcionesUnJugador extends Pane{

	public OpcionesUnJugador(){
		
		super();
		
		this.setPrefSize(700, 800);

		Label color = new Label();
		color.setPrefSize(200, 50);
		color.setTranslateX(310);
		color.setTranslateY(50);
		color.setText("Elija un color");


		RadioButton blanca = new RadioButton();
		blanca.setPrefSize(100, 50);
		blanca.setTranslateX(265);
		blanca.setTranslateY(125);

		Label blancaLabel = new Label();
		blancaLabel.setPrefSize(100, 50);
		blancaLabel.setTranslateX(1.5*166.667);
		blancaLabel.setTranslateY(100);
		blancaLabel.setText("Blanca");


		RadioButton negra = new RadioButton();
		negra.setPrefSize(100, 50);
		negra.setTranslateX(425);
		negra.setTranslateY(125);

		Label negraLabel = new Label();
		negraLabel.setPrefSize(100, 50);
		negraLabel.setTranslateX(2.5*166.67);
		negraLabel.setTranslateY(100);
		negraLabel.setText("Negra");


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

		this.getChildren().addAll(color,blancaLabel,blanca,negraLabel,negra,tiempoLabel,tiempo,iniciarJuego);
		
		this.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Scene escena = new Scene(new PantallaJuego());
		escena.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		iniciarJuego.setOnAction(e -> ((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(escena));
	}
}
