
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
		
		RadioButton tiempo = new MyRadioButton(360,250,100,50);

		Label tiempoLabel = new MyLabel("Jugar con limite de tiempo",255,225,300,25);

		Button iniciarJuego = new MyButton(" Jugar ",270,350,200,50);

		this.getChildren().addAll(tiempoLabel,tiempo,iniciarJuego);
		
		this.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Scene escena = new Scene(new PantallaJuego());
		escena.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		 
		iniciarJuego.setOnAction(e -> ((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(escena));
	}
}
