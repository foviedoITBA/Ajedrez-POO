package grafica;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logica.ColorPieza;

public class OpcionesDosJugadores extends Pane{
	Label titulo;
	Label tiempoLabel;
	CheckBox tiempo;
	Button iniciarJuego;
	
	//private boolean hayTiempo;
	
	public OpcionesDosJugadores(){
		
		super();
		
		this.setPrefSize(700, 800);
		
		titulo = new MyLabel("",100,50,500,100);
		titulo.setAlignment(Pos.TOP_CENTER);
		titulo.setId("labelChess");
		
		tiempoLabel = new MyLabel("Juego con limite de tiempo",180,200,340,60);
		tiempoLabel.setPadding(new Insets(25));
		tiempoLabel.getStyleClass().add("squareButton");
		
		tiempo = new MyCheckBox(470,213,100,50);

		iniciarJuego = new MyButton(" Jugar ",225,300,250,60);
		iniciarJuego.getStyleClass().add("roundedButton");

		this.getChildren().addAll(titulo,tiempoLabel,tiempo,iniciarJuego);
		
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		
		iniciarJuego.setOnAction(e -> {
			
			//hayTiempo = tiempo.isSelected();
			
			Scene escena = new Scene(new PantallaJuego(2,ColorPieza.BLANCO));
			escena.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
			((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(escena);
		});
	}
}
