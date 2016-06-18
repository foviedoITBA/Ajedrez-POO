package grafica;
/**
 * La clase OpcionesUnJugador entiende de Pane y tiene como variables privadas: 
 * un label titulo, reprensentante de la foto con el titulo del juego;
 * un label tiempoLabel y un checkBox tiempo, encargados de brindar la posibilidad de jugar con o sin tiempo;
 * un label blancaLabel, un label negraLabel, un RadioButton blanca y un RadioButton negro, 
 * que dan la posibilidad de elegir ser jugador blanco o negro.
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logica.ColorPieza;

public class OpcionesUnJugador extends Pane{
	
	private Label titulo,tiempoLabel,blancaLabel,negraLabel;
	private CheckBox tiempo;
	private Button iniciarJuego;
	private RadioButton blanca,negra;

	private ColorPieza color;
	
	public OpcionesUnJugador(){
		
		this.setPrefSize(700, 800);
		
		titulo = new MyLabel("",100,50,500,100);
		titulo.setAlignment(Pos.TOP_CENTER);
		titulo.setId("labelChess");

		blanca = new MyRadioButton(280,283,100,50);
		blanca.setSelected(true);
		blancaLabel = new MyLabel("Blancas",160,270,170,60);
		blancaLabel.setPadding(new Insets(25,25,25,35));
		blancaLabel.getStyleClass().add("curvedButton");

		negra = new MyRadioButton(485,283,100,50);
		negraLabel = new MyLabel("Negras",370,270,170,60);
		negraLabel.setPadding(new Insets(25,25,25,35));
		negraLabel.getStyleClass().add("curvedButton");
		
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

		iniciarJuego = new MyButton(" Jugar ",225,490,250,60);
		iniciarJuego.getStyleClass().add("roundedButton");

		this.getChildren().addAll(titulo,blancaLabel,blanca,negraLabel,negra,iniciarJuego);
		
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		
		iniciarJuego.setOnAction(e -> {
			if(blanca.isSelected()){
				color=ColorPieza.BLANCO;
			}else{
				color=ColorPieza.NEGRO;
			}
			
			Scene escena = new Scene(new PantallaJuego(1,color));
			escena.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
			((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(escena);
		});
	}
}
