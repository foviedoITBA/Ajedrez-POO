package grafica;
/**
 * La clase ConfirmacionSalir es la clase de ofrece la posibilidad de abandonar la partida o seguirla.
 * Tiene como unica variable un boolean resp, el cual representa la confirmacion y negaciones de abandonar 
 * la partida. Esta variable privada statica es tambien variable de retorno del metodo display().
 * Los botones si y no son los responsables de la asignacion buleana a la variable reps dependiendo de las acciones del jugador.
 */
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmacionSalir {

	private static boolean resp;
	
	public static boolean display(){
		Stage stage= new Stage();

		Button si= new Button("Si");
		si.setPrefSize(150,50);
		si.getStyleClass().add("alertaButton");
		si.getStyleClass().add("squareButton");
		si.setOnAction(e->{
			resp=true;
			stage.close();
		});

		Button no= new Button("No");
		no.setPrefSize(150,50);
        no.getStyleClass().add("alertaButton");
		no.getStyleClass().add("squareButton");
		no.setOnAction((e->{
			resp=false;
			stage.close();
		}));

		Label label= new Label("¿Desea guardar la partida antes de salir?");
		label.getStyleClass().add("confirmLabel");
		VBox vBox= new VBox(0);
		HBox hBox= new HBox(10);
		hBox.setAlignment(Pos.CENTER);
		vBox.setAlignment(Pos.CENTER);

		hBox.getChildren().addAll(si,no);
		vBox.getChildren().addAll(label,hBox);
		stage.setHeight(150);
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
