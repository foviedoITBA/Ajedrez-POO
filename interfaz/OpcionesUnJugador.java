package interfaz;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class OpcionesUnJugador extends OpcionesDosJugadores{

	public OpcionesUnJugador(){
		
		super();
		
		this.setPrefSize(700, 800);

		Label color = new MyLabel("Elija un color ",310,50,200,50);

		RadioButton blanca = new MyRadioButton(265,125,100,50);

		Label blancaLabel = new MyLabel("Blanca",250,100,100,50);

		RadioButton negra = new MyRadioButton(425,125,100,50);
		
		negra.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				negra.setSelected(true);
				blanca.setSelected(false);
			}
		});
		
		blanca.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event){
				negra.setSelected(false);
				blanca.setSelected(true);
			}
		});

		Label negraLabel = new MyLabel("Negra",417,100,100,50);

		Button iniciarJuego = new MyButton(" Jugar ",270,350,200,50);

		this.getChildren().addAll(color,blancaLabel,blanca,negraLabel,negra,iniciarJuego);
		
		this.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
		
		Scene escena = new Scene(new PantallaJuego());
		escena.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
		
		iniciarJuego.setOnAction(e -> ((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(escena));
	}
}
