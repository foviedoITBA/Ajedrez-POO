package grafica;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logica.ColorPieza;
import logica.Juego;
import logica.PosicionAjedrez;

public class PantallaJuego extends Pane implements Dimensiones{
	ControladorJuego controlador;
	Tablero elTablero;
	Juego elJuego;
	EstadoDeJuego estado;
	
	
	public PantallaJuego(int cantJugadores, ColorPieza colorElegido){
		this.setPrefSize(900, 800);
		elJuego= new Juego();
		estado= new EstadoDeJuego(elJuego);
		elTablero = new Tablero(elJuego);
		controlador=new ControladorJuego(cantJugadores,colorElegido,elTablero,elJuego,estado);
		
		inicializarMenu();
	}
	
	private void inicializarMenu(){
		Label titulo = new MyLabel("",200,20,500,100);
			titulo.setAlignment(Pos.TOP_CENTER);
			titulo.setId("labelChess");
		
		Button buttonGuardarPartida = new MyButton("Guardar", 130,670,200,50);
			buttonGuardarPartida.getStyleClass().add("roundedButton");
			buttonGuardarPartida.setOnAction(e->{
				GuardarPartida.display();
			});
		
		Button buttonDeshacer = new MyButton("Deshacer",360,670,200,50);
			buttonDeshacer.setOnAction(e->controlador.deshacerJugada());
			buttonDeshacer.getStyleClass().add("roundedButton");
		
		Button buttonMenu = new MyButton("Menu",580,670,200,50);
			buttonMenu.getStyleClass().add("roundedButton");
			buttonMenu.setOnAction(e ->{
				if(ConfirmacionSalir.display()){
					GuardarPartida.display();
				}
				((Stage)(((Node) e.getSource()).getScene().getWindow())).setScene(new Scene( new Inicio()));
			});
			
		Button enroqueLargo = new MyButton("",680,520,70,70);
			enroqueLargo.setOnAction(e->controlador.enroqueLargo());
			enroqueLargo.getStyleClass().add("enroqueButton");
			enroqueLargo.getStyleClass().add("enroqueLargoButton");
			
			
		Button enroqueCorto = new MyButton("",770,520,70,70);
			enroqueCorto.setOnAction(e->controlador.enroqueCorto());
			enroqueCorto.getStyleClass().add("enroqueButton");
			enroqueCorto.getStyleClass().add("enroqueCortoButton");
			
		this.setOnMouseClicked( e->controlador.jugar( convertirClick( e.getSceneX() , e.getSceneY() ) ) );

		this.getChildren().addAll(titulo,buttonGuardarPartida,buttonDeshacer,buttonMenu,enroqueCorto,enroqueLargo,elTablero,estado);
	}
	
	private PosicionAjedrez convertirClick(double x, double y){
		int fila= (int) ((y-DES_TABLERO_Y)/CASILLERO_ALTO);
		int columna= (int) ((x-DES_TABLERO_X)/CASILLERO_ANCHO);
		return aPosAjedrez(fila, columna);
	}
	
	private PosicionAjedrez aPosAjedrez(int fila, int columna){
		byte laFila = (byte) (8 - fila);
		char laColumna = (char) (columna + 'a');
		return new PosicionAjedrez(laFila, laColumna);
	}
	
}
