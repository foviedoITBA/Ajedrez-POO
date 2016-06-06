package interfaz;

import javafx.scene.layout.Pane;
import logica.Color;
import logica.Juego;

public class PantallaJuego extends Pane{

	public PantallaJuego(int cantJugadores, Color color, boolean hayTiempo){
		super();
		this.setPrefSize(700, 800);
		Juego elJuego=new Juego();
		
		this.getChildren().addAll(new MenuPantallaJuego(elJuego));
		this.getChildren().addAll(new TableroPantallaJuego(elJuego));
		
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
	}

}
