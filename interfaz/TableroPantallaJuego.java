package interfaz;


import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class TableroPantallaJuego extends Pane {
	
	private static double TABLERO_ANCHO=500;
	private static double TABLERO_ALTO=500;
	
	private static double CASILLERO_ALTO=62.5;
	private static double CASILLERO_ANCHO=62.5;
	
	private static double DEFSAJE_X=100;
	private static double DEFSAJE_Y=150;
	private Canvas[][] tablero= new Canvas[8][8];
	
	public TableroPantallaJuego(){
		super();
		this.setPrefSize(TABLERO_ANCHO, TABLERO_ALTO);
		this.setTranslateX(DEFSAJE_X);
		this.setTranslateY(DEFSAJE_Y);

		for(int i=0; i<8;i++){//hay que usar un enum o algo que diga ancho ya alto del tablero en vez de 8
			for(int j=0; j<8;j++){
				tablero[i][j]=new Canvas(CASILLERO_ANCHO,CASILLERO_ALTO);
				tablero[i][j].setTranslateX(CASILLERO_ANCHO*i);
				tablero[i][j].setTranslateY(CASILLERO_ALTO*j);

				this.getChildren().add(tablero[i][j]);
			}
		}
		inicializarPiezas();
		this.setOnMouseClicked(e-> posicionTablero(e.getSceneX(),e.getSceneY()));
		this.getStylesheets().add(getClass().getResource("../assets/application.css").toExternalForm());
		this.setId("tablero");
		
	}
	
	public void  dibujarPieza(Image img, int fila, int col){
		tablero[col][fila].getGraphicsContext2D().drawImage(img, 1.25, 1.25,60,60);//poner variables static
	}
	
	public void posicionTablero(double x, double y){
		System.out.println("x:"+x+" y: "+y);
		int fila= (int) ((y-DEFSAJE_Y)/CASILLERO_ALTO)+1;
		int columna= (int) ((x-DEFSAJE_X)/CASILLERO_ANCHO)+1;
		
		System.out.println("Click en Fila: "+fila+ "Columna: "+columna);
	}
	
	private void inicializarPiezas(){
		for(int i=0; i<8 ;i++){ 
			dibujarPieza(new Image("/assets/peonNegro.png"),1,i);
			dibujarPieza(new Image("/assets/peonBlanco.png"),6,i);
		}
			
		dibujarPieza(new Image("/assets/torreNegro.png"),0,0);
		dibujarPieza(new Image("/assets/torreNegro.png"),0,7);
		
		dibujarPieza(new Image("/assets/torreBlanco.png"),7,0);
		dibujarPieza(new Image("/assets/torreBlanco.png"),7,7);
		
		dibujarPieza(new Image("/assets/caballoBlanco.png"),7,1);
		dibujarPieza(new Image("/assets/caballoBlanco.png"),7,6);
		
		dibujarPieza(new Image("/assets/caballoNegro.png"),0,1);
		dibujarPieza(new Image("/assets/caballoNegro.png"),0,6);
		
		dibujarPieza(new Image("/assets/alfilNegro.png"),0,2);
		dibujarPieza(new Image("/assets/alfilNegro.png"),0,5);
		
		dibujarPieza(new Image("/assets/alfilBlanco.png"),7,2);
		dibujarPieza(new Image("/assets/alfilBlanco.png"),7,5);
		
		dibujarPieza(new Image("/assets/reyBlanco.png"),7,4);
		dibujarPieza(new Image("/assets/damaBlanco.png"),7,3);
		
		dibujarPieza(new Image("/assets/reyNegro.png"),0,4);
		dibujarPieza(new Image("/assets/damaNegro.png"),0,3);
	}
}