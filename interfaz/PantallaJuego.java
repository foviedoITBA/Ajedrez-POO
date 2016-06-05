package interfaz;

import javafx.scene.layout.Pane;
import logica.Color;

public class PantallaJuego extends Pane{

	public PantallaJuego(int cantJugadores, Color color, boolean hayTiempo){
		super();
		this.setPrefSize(700, 800);
		this.getChildren().addAll(new MenuPantallaJuego());
		this.getChildren().addAll(new TableroPantallaJuego());
		
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
	}

}
