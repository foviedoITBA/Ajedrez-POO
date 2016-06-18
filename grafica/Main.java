package grafica;
/**
 * La clase Main es la clase principal, encargada de llamar al metodo start y comenzar el juego.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(new Inicio()));
		primaryStage.setTitle("Ajedrez");
		primaryStage.getIcons().add(new Image("/assets/icon.png"));
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}

}