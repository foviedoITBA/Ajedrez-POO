package grafica;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class Alerta {

    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setHeight(150);
		window.setWidth(400);

        Label label = new Label();
        label.setText(message);
        label.getStyleClass().add("alertaLabel");
        Button cerrar = new Button("Cerrar");
        cerrar.getStyleClass().add("alertaButton");
		cerrar.getStyleClass().add("roundedButton");
        cerrar.setOnAction(e -> window.close());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, cerrar);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("/assets/application.css");
        
        window.setScene(scene);
        window.showAndWait();
    }

}