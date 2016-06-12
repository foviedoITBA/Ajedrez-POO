package interfaz;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(new TableroPantallaJuego(null, null, null, null)));
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}

}