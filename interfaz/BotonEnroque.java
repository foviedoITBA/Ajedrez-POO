package interfaz;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import logica.Juego;

public class BotonEnroque extends Pane{
	
	private Juego juego;
	private static double PANE_ANCHO = 150;
	private static double PANE_ALTO = 70;
	private static double DESFASAJE_X = 100+500+70;
	private static double DESFASAJE_Y = 150+300+80;
	
	private Button enroqueLargo, enroqueCorto;
	
	public BotonEnroque(Juego juego){
		this.juego = juego;
		this.setVisible(true);
		this.setPrefSize(PANE_ANCHO, PANE_ALTO);
		this.setTranslateX(DESFASAJE_X);
		this.setTranslateY(DESFASAJE_Y);
		this.getStyleClass().add("paneEnroque");
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		
		enroqueLargo = new MyButton("",0,0,70,70);
		enroqueLargo.getStyleClass().add("enroqueButton");
		enroqueLargo.getStyleClass().add("enroqueLargoButton");
		enroqueLargo.setOnAction(e->{
			if(juego.sePuedeEnrocarLargo()){
				juego.enrocarLargo();
			}
		});
		
		enroqueCorto = new MyButton("",80,0,70,70);
		enroqueCorto.getStyleClass().add("enroqueButton");
		enroqueCorto.getStyleClass().add("enroqueCortoButton");
		enroqueCorto.setOnAction(e->{
			if(juego.sePuedeEnrocarCorto()){
				juego.enrocarCorto();
			}
		});
		
		this.getChildren().addAll(enroqueLargo,enroqueCorto);
		actualizarEnroque();
	}
	
	public void actualizarEnroque(){
		if(juego.sePuedeEnrocarLargo()){
			enroqueLargo.getStyleClass().add("show");
		}else{
			enroqueLargo.getStyleClass().remove("show");
		}
		if(juego.sePuedeEnrocarCorto()){
			enroqueCorto.getStyleClass().add("show");
		}else{
			enroqueCorto.getStyleClass().remove("show");
		}
	}
}
