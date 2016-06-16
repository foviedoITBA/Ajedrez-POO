package interfaz;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import logica.ColorPieza;
import logica.Juego;

public class Reloj extends StackPane{
	private static double PANE_ANCHO=100;
	public static double PANE_ALTO=100;
	
	private static double DEFSAJE_X=100+500+60;
	private static double DEFSAJE_Y=100+100+400;

	private Line minutos,segundos;
	private Juego juego;
	private ColorPieza color;
	
	public Reloj(Juego juego, ColorPieza color){
		this.juego = juego;
		this.color = color;
		
		int desfasaje=0;
		if(color == ColorPieza.NEGRO){
			desfasaje = 100;
		}
		
		this.setPrefSize(PANE_ANCHO, PANE_ALTO);
		this.setTranslateX(DEFSAJE_X + desfasaje);
		this.setTranslateY(DEFSAJE_Y);
		this.getStyleClass().add("reloj");
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		setAlignment(Pos.CENTER);
		
		LinearGradient agujas = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop[]{
			new Stop(0, Color.BLACK),
			new Stop(0.6, Color.TRANSPARENT)
		});
		
		minutos = new Line(120, 120, 120, 200);
        minutos.setStrokeWidth(3);
        minutos.setStroke(agujas);
        
        segundos = new Line(120, 120, 120, 200);
        segundos.setStrokeWidth(2);
        segundos.setStroke(agujas);
        
        this.getChildren().addAll(minutos,segundos);
        
        actualizar();
	}
	
	
	public void actualizar(){
		long tiempoInicio = System.nanoTime();
		Timeline timeline = new Timeline();
		
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {

					long tiempoActual = System.nanoTime();
					
					int tiempo;
					if(color == ColorPieza.BLANCO){
						tiempo = juego.dameTiempoBlancas();
					}else{
						tiempo = juego.dameTiempoNegras();
					}
					
					double seg = (tiempoActual - tiempoInicio)/1000000000 + (tiempo % 60);
					double min = (tiempoActual - tiempoInicio)/60000000000L + (tiempo / 60);
					
					if(juego.dameTurno() == color){ // hago andar el reloj solo si es mi turno
						segundos.setRotate( 6 * seg);
						minutos.setRotate( 6 * min);
					}
					
				}
		}));
		timeline.play();
	}
}
