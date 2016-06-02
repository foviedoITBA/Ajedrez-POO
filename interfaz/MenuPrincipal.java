package interfaz;



import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class MenuPrincipal extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			
			Label titulo = new Label();
			titulo.setPrefSize(200, 100);
			titulo.setTranslateX(250);
			titulo.setTranslateY(50);
			titulo.setText("CHESS");
			titulo.setAlignment(Pos.TOP_CENTER);
			titulo.setId("labelChess");


			Button buttonUnJugador = new MyButton(" New - 1 Player ", 200);

			buttonUnJugador.setOnAction(e -> primaryStage.setScene(new Scene(new OpcionesUnJugador())));

			Button buttonDosJugadores = new MyButton("New - 2 Players",400);
			buttonDosJugadores.setOnAction(e -> primaryStage.setScene(new Scene(new OpcionesDosJugadores())));

			Button buttonResumirJuego = new MyButton(" Resume Game ",600);

			root.getChildren().addAll(titulo,buttonUnJugador,buttonDosJugadores,buttonResumirJuego);
			Scene scene = new Scene(root,700,800);
			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
