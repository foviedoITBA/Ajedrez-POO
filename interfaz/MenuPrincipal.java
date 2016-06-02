


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class MenuPrincipal extends Application {
	
	public void start(Stage primaryStage) {
		
			Pane root = new Pane();
			
			Label titulo = new MyLabel("CHESS",250,50,200,100);
			titulo.setAlignment(Pos.TOP_CENTER);
			titulo.setId("labelChess");

			Button buttonUnJugador = new MyButton(" Un Jugador ", 250,200,200,100);
			buttonUnJugador.setOnAction(e -> primaryStage.setScene(new Scene(new OpcionesUnJugador())));

			Button buttonDosJugadores = new MyButton("Dos Jugadores",250,400,200,100);
			buttonDosJugadores.setOnAction(e -> primaryStage.setScene(new Scene(new OpcionesDosJugadores())));

			Button buttonResumirJuego = new MyButton(" Resumir el juego ",250,600,200,100);

			root.getChildren().addAll(titulo,buttonUnJugador,buttonDosJugadores,buttonResumirJuego);
			
			Scene scene = new Scene(root,700,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
