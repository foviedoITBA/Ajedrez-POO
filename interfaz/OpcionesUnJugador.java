package interfaz;


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
import logica.Color;

public class OpcionesUnJugador extends Pane{
	
	Label titulo,tiempoLabel,blancaLabel,negraLabel;
	CheckBox tiempo;
	Button iniciarJuego;
	RadioButton blanca,negra;

	private Color color;
	private boolean hayTiempo;
	
	public OpcionesUnJugador(){
		
		this.setPrefSize(700, 800);
		
		titulo = new MyLabel("",100,50,500,100);
		titulo.setAlignment(Pos.TOP_CENTER);
		titulo.setId("labelChess");

		blanca = new MyRadioButton(280,223,100,50);
		blanca.setSelected(true);
		blancaLabel = new MyLabel("Blancas",160,210,170,60);
		blancaLabel.setPadding(new Insets(25,25,25,35));
		blancaLabel.getStyleClass().add("curvedButton");

		negra = new MyRadioButton(485,223,100,50);
		negraLabel = new MyLabel("Negras",370,210,170,60);
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
		

		tiempoLabel = new MyLabel("Juego con limite de tiempo",180,340,340,60);
		tiempoLabel.setPadding(new Insets(25));
		tiempoLabel.getStyleClass().add("squareButton");
		
		tiempo = new MyCheckBox(470,353,100,50);

		iniciarJuego = new MyButton(" Jugar ",225,490,250,60);
		iniciarJuego.getStyleClass().add("roundedButton");

		this.getChildren().addAll(titulo,blancaLabel,blanca,negraLabel,negra,tiempoLabel,tiempo,iniciarJuego);
		
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		
		
		
		iniciarJuego.setOnAction(e -> {
			
			if(blanca.isSelected()){
				color=Color.BLANCO;
			}else{
				color=Color.NEGRO;
			}
			
			hayTiempo = tiempo.isSelected();
			
			Scene escena = new Scene(new PantallaJuego(1,color,hayTiempo));
			escena.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
			((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(escena);
		});
	}
}
