package logica;

import java.util.HashSet;
import java.util.Set;

public class Torre extends Pieza{
	
	private Movimiento elMovimiento;
	
	private static int incrementos[][]={ {1,0},{0,1},{-1,0},{0,-1}};
	
	public Torre(boolean color){
		super(color);
	}
	private Set<Posicion> movimientos(Posicion pos){
		Set<Posicion> list = new HashSet<>();
		
		for(int i=0; i<incrementos.length; i++){
			int incX=incrementos[i][0];
			int incY=incrementos[i][1];
			
			for(int posX=pos.getX()+incX, posY=pos.getY()+incY; posX<Tablero.getSize() && posY<Tablero.getSize(); posX+=incX, posY+=incY){
				
				list.add(new Posicion(posX,posY));
				//agregar if para verificar si esta libre el casillero, si tengo una ficha mia o si tengo una ficha del oponente
				//cortar el for con un boolean dependiendo del caso
				
			}
			
		}
			
		return list;
		
	}
s	
	@Override
	public Movimiento damePosiciones() {
		return elMovimiento;
	}
	
}
