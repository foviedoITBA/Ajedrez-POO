package interfaz;




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
			Scene dosJugadores= new Scene(new OpcionesDosJugadores());
			Scene unJugador= new Scene(new OpcionesUnJugador());
			
			Label titulo = new MyLabel("",100,50,500,100);
			titulo.setAlignment(Pos.TOP_CENTER);
			titulo.setId("labelChess");

			Button buttonUnJugador = new MyButton("Un Jugador", 200,200,300,100);
			buttonUnJugador.setOnAction(e -> primaryStage.setScene(unJugador));

			Button buttonDosJugadores = new MyButton("Dos Jugadores",200,320,300,100);
			buttonDosJugadores.setOnAction(e -> primaryStage.setScene(dosJugadores));

			Button buttonResumirJuego = new MyButton("Resumir el juego",200,440,300,100);

			root.getChildren().addAll(titulo,buttonUnJugador,buttonDosJugadores,buttonResumirJuego);
			
			Scene scene = new Scene(root,700,800);
			
			scene.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
