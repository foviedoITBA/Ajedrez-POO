package interfaz;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
		
		Label titulo = new MyLabel("",100,50,500,100);
		titulo.setAlignment(Pos.TOP_CENTER);
		titulo.setId("labelChess");

		Label color = new MyLabel("Elija un color ",290,170,200,50);

		RadioButton blanca = new MyRadioButton(250,239,100,50);

		Label blancaLabel = new MyLabel("Blancas",160,240,100,50);

		RadioButton negra = new MyRadioButton(520,239,100,50);
		
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

		Label negraLabel = new MyLabel("Negras",440,240,100,50);
		

		Label tiempoLabel = new MyLabel("Jugar con limite de tiempo",200,340,300,50);
		
		RadioButton tiempo = new MyRadioButton(460,339,100,50);

		Button iniciarJuego = new MyButton(" Jugar ",225,420,250,60);

		this.getChildren().addAll(titulo,color,blancaLabel,blanca,negraLabel,negra,tiempo,tiempoLabel,iniciarJuego);
		
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		
		Scene escena = new Scene(new PantallaJuego());
		escena.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		
		iniciarJuego.setOnAction(e -> ((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(escena));
	}
}
