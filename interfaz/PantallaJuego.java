package interfaz;

import javafx.scene.layout.Pane;

public class PantallaJuego extends Pane{

	public PantallaJuego(){
		super();
		this.setPrefSize(700, 800);
		this.getChildren().addAll(new MenuPantallaJuego());
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
	}

}
