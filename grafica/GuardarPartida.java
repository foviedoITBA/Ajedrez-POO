package grafica;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GuardarPartida {
	public static void display(){
		Stage stage= new Stage();
		
		Button guardar= new Button("Guardar");
		guardar.getStyleClass().add("squareButton");
		guardar.setOnAction(e->{
			System.out.println("Partdia gurdada!! (mentira)");//guradr partidaaa
			stage.close();
		});
		
		
		Button cancelar= new Button("Cancelar");
		cancelar.getStyleClass().add("squareButton");
		cancelar.setOnAction((e->{
			stage.close();
		}));
		Label label= new Label("Ingrese nombre de archivo");
		TextField text= new TextField();
		text.setMaxWidth(300);
		
		VBox vBox= new VBox(10);
		HBox hBox= new HBox(10);
		hBox.setAlignment(Pos.CENTER);
		vBox.setAlignment(Pos.CENTER);
		
		hBox.getChildren().addAll(guardar,cancelar);
		vBox.getChildren().addAll(label,text,hBox);
		stage.setHeight(150);
		stage.setWidth(400);
		//hBox.getStylesheets().add("assets/application.css");
		//vBox.getStylesheets().add("assets/application.css");
		Scene scene= new Scene(vBox);
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(true);
		stage.showAndWait();
		
		
		
	}
}
//
//package interfaz;
//
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//
//public class GuardarPartida {
//	public static void display(){
//		Stage stage= new Stage();
//		
//		Button guardar= new MyButton("Guardar",100,50,150,70);
//		guardar.getStyleClass().add("squareButton");
//		guardar.setOnAction(e->{
//			System.out.println("Partdia gurdada!! (mentira)");//guradr partidaaa
//			stage.close();
//		});
//		
//		
//		Button cancelar= new MyButton("Cancelar",220,50,200,50);
//		cancelar.getStyleClass().add("squareButton");
//		cancelar.setOnAction((e->{
//			stage.close();
//		}));
//		
//		
//		Label label= new MyLabel("Ingrese nombre de archivo",80,5,300,20);
//		TextField text= new MyTextField("",50,30,300,30);
//		text.setMaxWidth(300);
//		
//		//VBox vBox= new VBox(10);
//		//HBox hBox= new HBox(10);
//		//hBox.setAlignment(Pos.CENTER);
//		//vBox.setAlignment(Pos.CENTER);
//		Pane panel= new Pane();
//		panel.getChildren().addAll(guardar,cancelar,label,text);
//		//hBox.getChildren().addAll(guardar,cancelar);
//		//vBox.getChildren().addAll(label,text,hBox);
//		stage.setHeight(150);
//		stage.setWidth(400);
//		//hBox.getStylesheets().add("assets/application.css");
//		panel.getStylesheets().add("assets/application.css");
//		Scene scene= new Scene(panel);
//		stage.setScene(scene);
//		stage.initModality(Modality.APPLICATION_MODAL);
//		stage.setResizable(true);
//		stage.showAndWait();
//		
//		
//		
//	}
//}
