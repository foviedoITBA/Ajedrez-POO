package ia;

import logica.Juego;
import logica.Color;
import logica.Jugada;
import logica.PosicionAjedrez;
import logica.PiezaColor;
import logica.NombrePieza;

import java.io.IOException;
import java.util.List;
  
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.util.IteratorIterable;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Inteligencia {

	private Juego elJuego;
	private Color elColor;
	private String jugadasXML;
	private SAXBuilder jdomBuilder;
	private Document jugadasDocument;
	private Element root, selector;
	private Random randomGenerator;
	private boolean pensando;
	
	public Inteligencia(Juego elJuego, Color queColorEs) throws ImposibleCargarJugadasException {
		Color elColor = queColorEs;
		jugadasXML = "Jugadas.xml";
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
		List<Element> respuestas;
		if (!elJuego.huboUnaJugada()) {
			respuestas = selector.getChildren();
			selector = respuestas.get(randomGenerator.nextInt(respuestas.size()));
			hacerJugada(selector);
			return;
		}
		ultimaJugada = elJuego.dameUltimaJugada();
		respuestas = selector.getChildren();
		Element laDelOtro = buscarJugada(respuestas, ultimaJugada);
		if (laDelOtro == null || laDelOtro.getChildren().isEmpty()) { // Si la jugada del otro no está en el árbol o no hay respuesta para esa jugada
			/* Pasar a modo pensar */
			selector = null;
			root = null;
			jugadasDocument = null;
			jdomBuilder = null;
			pensando = true;
			// Lo demás...
		}
		else { // Si hay alguna respuesta en el árbol de jugadas, elige una al azar
			respuestas = selector.getChildren();
			selector = respuestas.get(randomGenerator.nextInt(respuestas.size()));
			hacerJugada(selector);
		}

	}

	private void juegaPensando() {

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
		return elElemento;
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
}