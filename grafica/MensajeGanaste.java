package grafica;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logica.ColorPieza;

public class MensajeGanaste {
	
	final static int STAGE_ANCHO = 400;
	final static int STAGE_ALTO = 300;
	
	static boolean resp;
	
	public static boolean display(ColorPieza turno){
		Stage stage= new Stage();

		Button menu= new Button("Menu Principal");
		menu.getStyleClass().add("ventanaGanarButton");
		menu.getStyleClass().add("roundedButton");
		menu.setOnAction((e->{
			resp=true;
			stage.close();
		}));

		Label label= new Label("");
		label.setPrefSize(300, 180);
		
		if(turno==ColorPieza.BLANCO){
			label.getStyleClass().add("ventanaGanarNegrasTitle");
		}else{
			label.getStyleClass().add("ventanaGanarBlancasTitle");
		}
		
		VBox vBox= new VBox(0);
		HBox hBox= new HBox(10);
		hBox.setAlignment(Pos.CENTER);
		vBox.setAlignment(Pos.CENTER);

		hBox.getChildren().addAll(menu);
		vBox.getChildren().addAll(label,hBox);
		stage.setHeight(STAGE_ALTO);
		stage.setWidth(STAGE_ANCHO);
		Scene scene= new Scene(vBox);
		
		scene.getStylesheets().add("/assets/application.css");

		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.showAndWait();

		return resp;
	}
}
