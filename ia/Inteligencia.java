package ia;

import logica.Juego;
import logica.ColorPieza;
import logica.Jugada;
import logica.PosicionAjedrez;
import logica.PiezaColor;
import logica.NombrePieza;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.Stack;
 
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import excepcion.ImposibleCargarJugadasException;

import java.util.ListIterator;
import java.util.Random;

public class Inteligencia {

	private static final int PUNTAJE_PEON = 1;
	private static final int PUNTAJE_CABALLO = 3;
	private static final int PUNTAJE_ALFIL = 3;
	private static final int PUNTAJE_TORRE = 5;
	private static final int PUNTAJE_DAMA = 9;
	private static final int PUNTAJE_JAQUE_MATE = 100;

	private Juego elJuego;
	private ColorPieza elColor;
	private ColorPieza colorAdversario;
	private String jugadasXML;
	private SAXBuilder jdomBuilder;
	private Document jugadasDocument;
	private Element root, selector;
	private Random randomGenerator;
	private boolean pensando;
	private int jugadasHechas;

	public Inteligencia(Juego elJuego, ColorPieza queColorEs) throws ImposibleCargarJugadasException {
		this.elJuego = elJuego;
		elColor = queColorEs;
		colorAdversario = (elColor == ColorPieza.BLANCO ? ColorPieza.NEGRO : ColorPieza.BLANCO);
		jugadasXML = "src/ia/Jugadas.xml";
		jdomBuilder = new SAXBuilder();
		jugadasDocument = null;
		
		try {
			jugadasDocument = jdomBuilder.build(jugadasXML);
		} catch(JDOMException e) {
			throw new ImposibleCargarJugadasException();
		} catch(IOException e) {
			throw new ImposibleCargarJugadasException();
		}

		root = jugadasDocument.getRootElement();
		selector = root;
		randomGenerator = new Random();
		pensando = false;
		jugadasHechas = 0;
	}

	public void juega() {
		if (pensando == false)
			juegaSinPensar();
		else
			juegaPensando();
	}

	private void juegaSinPensar() {
		// Al entrar en el método, selector refiere a la última jugada (Element) hecha por la IA
		Jugada ultimaJugada;
		List<Element> respuestas = null;
		if (!elJuego.huboUnaJugada()) {
			selector = root;
			respuestas = selector.getChildren();
			selector = respuestas.get(randomGenerator.nextInt(respuestas.size()));
			jugadasHechas = 1;
			hacerJugada(selector);
			return;
		}
		while(elJuego.cuantasJugadasVan() < jugadasHechas + 1) { // Si se revirtió el juego, tiene que volver por el árbol
			selector = selector.getParentElement();
			jugadasHechas--;
		}
		respuestas = selector.getChildren();
		ultimaJugada = elJuego.dameUltimaJugada();
		Element laDelOtro = buscarJugada(respuestas, ultimaJugada);
		if (laDelOtro == null || laDelOtro.getChildren().isEmpty()) { // Si la jugada del otro no está en el árbol o no hay respuesta para esa jugada
			pasarAPensar();
			juegaPensando();
		}
		else { // Si hay alguna respuesta en el árbol de jugadas, elige una al azar
			respuestas = laDelOtro.getChildren();
			ListIterator<Element> iterador = respuestas.listIterator();
			while(iterador.hasNext()) {
				if (iterador.next().getAttributeValue("hacer").equals("false"))
					iterador.remove();
			}
			if (respuestas.isEmpty()) { // Si todas las respuestas son malas jugadas
				pasarAPensar();
				juegaPensando();
			}
			else {
				selector = respuestas.get(randomGenerator.nextInt(respuestas.size()));
				jugadasHechas += 2;
				hacerJugada(selector);
			}
		}

	}

	private void pasarAPensar() {
		root = null;
		jugadasDocument = null;
		jdomBuilder = null;
		pensando = true;
	}

	private void juegaPensando() {
		if (elJuego.sePuedeEnrocarCorto()) {
			elJuego.enrocarCorto();
			return;
		}
		if (elJuego.sePuedeEnrocarLargo()) {
			elJuego.enrocarLargo();
			return;
		}
		Stack<Movida> posiblesMovidas = new Stack<>();
		for (byte fila = 1; fila <= 8; fila++) {
			for (char columna = 'a'; columna <= 'h'; columna++) {
				PosicionAjedrez laPosicion = new PosicionAjedrez(fila, columna);
				if (!elJuego.hayAlgo(laPosicion))
					continue;
				if (elJuego.queHay(laPosicion).dameColor() != this.elColor)
					continue;
				Set<PosicionAjedrez> posicionesPosibles = elJuego.dameMovimientos(laPosicion);
				if (posicionesPosibles.isEmpty())
					continue;
				Iterator<PosicionAjedrez> iterador = posicionesPosibles.iterator();
				while(iterador.hasNext()) {
					Movida unaMovida = new Movida(0, laPosicion, iterador.next());
					analizarMovida(unaMovida);
					posiblesMovidas.push(unaMovida);
				}
			}
		}
		Movida laMejor = posiblesMovidas.pop();
		Movida aux = null;
		while(!posiblesMovidas.empty()) {
			aux = posiblesMovidas.pop();
			if (laMejor.damePuntaje() < aux.damePuntaje())
				laMejor = aux;
		}
		hacerMovida(laMejor);
	}

	private void analizarMovida(Movida laMovida) {
		Jugada laJugada = elJuego.mover(laMovida.damePosOrigen(), laMovida.damePosDestino());
		if (elJuego.hayAlgoParaCoronar()) {
			elJuego.coronar(NombrePieza.DAMA);
			laMovida.sumarPuntos(PUNTAJE_DAMA - PUNTAJE_PEON);
		}
		PiezaColor laComida = laJugada.damePiezaColorComida();
		if (laComida != null)
			laMovida.sumarPuntos(puntaje(laComida.dameNombre()));
		if (elJuego.hayJaqueMate()) {
			laMovida.sumarPuntos(PUNTAJE_JAQUE_MATE);
			elJuego.revertir();
			return;
		}
		if (elJuego.hayAhogado()) {
			elJuego.revertir();
			return;
		}
		Stack<Movida> lasRespuestas = new Stack<>();
		for (byte fila = 1; fila <= 8; fila++) {
			for (char columna = 'a'; columna <= 'h'; columna++) {
				PosicionAjedrez laPosicion = new PosicionAjedrez(fila, columna);
				if (!elJuego.hayAlgo(laPosicion))
					continue;
				if (elJuego.queHay(laPosicion).dameColor() != colorAdversario)
					continue;
				Set<PosicionAjedrez> posicionesPosibles = elJuego.dameMovimientos(laPosicion);
				if (posicionesPosibles.isEmpty())
					continue;
				Iterator<PosicionAjedrez> iterador = posicionesPosibles.iterator();
				while(iterador.hasNext()) {
					Movida unaRespuesta = new Movida(0, laPosicion, iterador.next());
					Jugada laOtraJugada = elJuego.mover(unaRespuesta.damePosOrigen(), unaRespuesta.damePosDestino());
					if (elJuego.hayAlgoParaCoronar()) {
						elJuego.coronar(NombrePieza.DAMA);
						unaRespuesta.sumarPuntos(PUNTAJE_DAMA - PUNTAJE_PEON);
					}
					PiezaColor laOtraComida = laOtraJugada.damePiezaColorComida();
					if (laOtraComida != null) {
						unaRespuesta.sumarPuntos(puntaje(laOtraComida.dameNombre()));
					}
					lasRespuestas.push(unaRespuesta);
					if (elJuego.hayJaqueMate()) {
						unaRespuesta.sumarPuntos(PUNTAJE_JAQUE_MATE);
					}
					elJuego.revertir();
				}
			}
		}
		Movida laMejorRespuesta = lasRespuestas.pop();
		Movida aux = null;
		while(!lasRespuestas.empty()) {
			aux = lasRespuestas.pop();
			if (laMejorRespuesta.damePuntaje() < aux.damePuntaje())
				laMejorRespuesta = aux;
		}
		laMovida.restarPuntos(laMejorRespuesta.damePuntaje());
		elJuego.revertir();
	}

	private void hacerMovida(Movida laMovida) {
		elJuego.mover(laMovida.damePosOrigen(), laMovida.damePosDestino());
		if (elJuego.hayAlgoParaCoronar()) {
			elJuego.coronar(NombrePieza.DAMA);
		}
	}
	
	private Element buscarJugada(List<Element> jugadas, Jugada laJugada) {
		String filaOrigen = Character.toString((char)(((char) laJugada.damePosicionOrigen().dameFila()) + '0'));
		String columnaOrigen = Character.toString(laJugada.damePosicionOrigen().dameColumna());
		String filaDestino = Character.toString((char)(((char) laJugada.damePosicionDestino().dameFila()) + '0'));
		String columnaDestino = Character.toString(laJugada.damePosicionDestino().dameColumna());
		ListIterator<Element> iterador = jugadas.listIterator();
		boolean encontrado = false;
		Element elElemento = null;
		while (iterador.hasNext() && encontrado == false) {
			elElemento = iterador.next();
			if (elElemento.getAttributeValue("filaOrigen").equals(filaOrigen) && elElemento.getAttributeValue("columnaOrigen").equals(columnaOrigen) && elElemento.getAttributeValue("filaDestino").equals(filaDestino) && elElemento.getAttributeValue("columnaDestino").equals(columnaDestino))
				encontrado = true;
		}
		return (encontrado ? elElemento : null);
	}

	private void hacerJugada(Element e) {
		byte filaOrigen = (byte) (e.getAttributeValue("filaOrigen").charAt(0) - '0');
		char columnaOrigen = e.getAttributeValue("columnaOrigen").charAt(0);
		byte filaDestino = (byte) (e.getAttributeValue("filaDestino").charAt(0) - '0');
		char columnaDestino = e.getAttributeValue("columnaDestino").charAt(0);
		PosicionAjedrez posOrigen = new PosicionAjedrez(filaOrigen, columnaOrigen);
		PosicionAjedrez posDestino = new PosicionAjedrez(filaDestino, columnaDestino);
		elJuego.mover(posOrigen, posDestino);
	}

	private int puntaje(NombrePieza pieza) {
		switch(pieza) {
			case PEON:
				return PUNTAJE_PEON;
			case CABALLO:
				return PUNTAJE_CABALLO;
			case ALFIL:
				return PUNTAJE_ALFIL;
			case TORRE:
				return PUNTAJE_TORRE;
			case DAMA:
				return PUNTAJE_DAMA;
			case REY:
				return PUNTAJE_JAQUE_MATE;
		}
		return 0;
	}

	class Movida {
		private int puntaje;
		private PosicionAjedrez posOrigen;
		private PosicionAjedrez posDestino;

		Movida(int puntaje, PosicionAjedrez posOrigen, PosicionAjedrez posDestino) {
			this.puntaje = puntaje;
			this.posOrigen = posOrigen;
			this.posDestino = posDestino;
		}

		int damePuntaje() {
			return puntaje;
		}

		PosicionAjedrez damePosOrigen() {
			return posOrigen;
		}

		PosicionAjedrez damePosDestino() {
			return posDestino;
		}

		void sumarPuntos(int puntos) {
			puntaje += puntos;
		}

		void restarPuntos(int puntos) {
			puntaje -= puntos;
		}
	}

}
