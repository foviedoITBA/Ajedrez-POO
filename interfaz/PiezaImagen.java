package interfaz;

import java.util.HashMap;

import javafx.scene.image.Image;
import logica.ColorPieza;
import logica.NombrePieza;
import logica.PiezaColor;

public class PiezaImagen {
	HashMap<PiezaColor,Image> imagenes;
	public PiezaImagen(){
		imagenes=new HashMap<>();
		imagenes.put(new PiezaColor(NombrePieza.PEON,ColorPieza.BLANCO),new Image("/assets/peonBlanco.png"));
		imagenes.put(new PiezaColor(NombrePieza.PEON,ColorPieza.NEGRO),new Image("/assets/peonNegro.png"));
		imagenes.put(new PiezaColor(NombrePieza.ALFIL,ColorPieza.BLANCO),new Image("/assets/alfilBlanco.png"));
		imagenes.put(new PiezaColor(NombrePieza.ALFIL,ColorPieza.NEGRO),new Image("/assets/alfilNegro.png"));
		imagenes.put(new PiezaColor(NombrePieza.CABALLO,ColorPieza.BLANCO),new Image("/assets/caballoBlanco.png"));
		imagenes.put(new PiezaColor(NombrePieza.CABALLO,ColorPieza.NEGRO),new Image("/assets/caballoNegro.png"));
		imagenes.put(new PiezaColor(NombrePieza.TORRE,ColorPieza.BLANCO),new Image("/assets/torreBlanco.png"));
		imagenes.put(new PiezaColor(NombrePieza.TORRE,ColorPieza.NEGRO),new Image("/assets/torreNegro.png"));
		imagenes.put(new PiezaColor(NombrePieza.DAMA,ColorPieza.BLANCO),new Image("/assets/damaBlanco.png"));
		imagenes.put(new PiezaColor(NombrePieza.DAMA,ColorPieza.NEGRO),new Image("/assets/damaNegro.png"));
		imagenes.put(new PiezaColor(NombrePieza.REY,ColorPieza.BLANCO),new Image("/assets/reyBlanco.png"));
		imagenes.put(new PiezaColor(NombrePieza.REY,ColorPieza.NEGRO),new Image("/assets/reyNegro.png"));
			
	}
	public Image dameImagen(PiezaColor pieza){
		return imagenes.get(pieza);	
	}

}
