package grafica;
/**
 * La clase CoronacionPieza extiende la clase Pane. Esta clase tiene una variable privada estatica de tipo NombrePieza 
 * llamada seleccionada, la cual representa la pieza a la que se quiere coronar un peon.
 * Por el metodo display() se crea un nuevo Stage, el cual contendra: un label titulo, siendo la imagen del nombre del juego,
 * los botones torre, alfil, caballo y dama, para que el jugador elija a que pieza desear coronar y la variable seleccionada
 * sera tambien variable de retorno de este mismo metodo.
 */
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logica.ColorPieza;
import logica.NombrePieza;

public class CoronacionPiezas extends Pane {
	
	private static NombrePieza seleccionada;
	
	public static NombrePieza display(ColorPieza color){
		
		Stage stage= new Stage();
		
		Label titulo = new MyLabel("",0,40,350,60);
		titulo.setAlignment(Pos.TOP_CENTER);
		titulo.setId("labelChess");

		Button torre = new MyButton("",0,0,90,90);
		Button alfil = new MyButton("",0,0,90,90);
		Button caballo = new MyButton("",0,0,90,90);
		Button dama = new MyButton("",0,0,90,90);
		
		if(color == ColorPieza.BLANCO){
			torre.getStyleClass().add("torreBlanca");
			alfil.getStyleClass().add("alfilBlanca");
			caballo.getStyleClass().add("caballoBlanca");
			dama.getStyleClass().add("damaBlanca");
		}else{
			torre.getStyleClass().add("torreNegro");
			alfil.getStyleClass().add("alfilNegro");
			caballo.getStyleClass().add("caballoNegro");
			dama.getStyleClass().add("damaNegro");
		}
		
		
		torre.setOnAction(e->{
			seleccionada = NombrePieza.TORRE;
			stage.close();
		});
		alfil.setOnAction(e->{
			seleccionada = NombrePieza.ALFIL;
			stage.close();
		});
		caballo.setOnAction(e->{
			seleccionada = NombrePieza.CABALLO;
			stage.close();
		});
		dama.setOnAction(e->{
			seleccionada = NombrePieza.DAMA;
			stage.close();
		});
		
		
		
		HBox hBox= new HBox(30);
		hBox.setAlignment(Pos.CENTER);
		hBox.setTranslateY(60);
		hBox.getChildren().addAll(torre,alfil,caballo,dama);
		
		VBox vBox= new VBox(20);
		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.getChildren().addAll(titulo,hBox);
		
		Scene scene = new Scene(vBox);
		scene.getStylesheets().add("/assets/coronacion.css");
		
		stage.setHeight(300);
		stage.setWidth(500);
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.showAndWait();
		
		return seleccionada;
	}
	
}
