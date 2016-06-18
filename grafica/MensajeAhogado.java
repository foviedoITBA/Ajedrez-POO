package grafica;
/**
 * La clase MensajeAhogado tiene como finalidad comunicar al jugador que la partida finalizo sin ganador alguno
 * ya que no se pueden llevar a cabo mas movimientos. El metodo display() es el encargado de destinar el mensaje 
 * mediante la escena correspondiente.
 */
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MensajeAhogado {
	static boolean resp;
	
	public static boolean display(){
		Stage stage= new Stage();

		Button menu= new Button("Menu Principal");
		menu.getStyleClass().add("ventanaAhogadoButton");
		menu.getStyleClass().add("roundedButton");
		menu.setOnAction((e->{
			resp=true;
			stage.close();
		}));
		
		
		Label label= new Label("");
		label.setPrefSize(300, 180);
		label.getStyleClass().add("ventanaAhogadoTitle");
		VBox vBox= new VBox(0);
		HBox hBox= new HBox(10);
		hBox.setAlignment(Pos.CENTER);
		vBox.setAlignment(Pos.CENTER);

		hBox.getChildren().addAll(menu);
		vBox.getChildren().addAll(label,hBox);
		stage.setHeight(300);
		stage.setWidth(400);
		Scene scene= new Scene(vBox);
		
		scene.getStylesheets().add("/assets/application.css");

		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.showAndWait();

		return resp;
	}
}

