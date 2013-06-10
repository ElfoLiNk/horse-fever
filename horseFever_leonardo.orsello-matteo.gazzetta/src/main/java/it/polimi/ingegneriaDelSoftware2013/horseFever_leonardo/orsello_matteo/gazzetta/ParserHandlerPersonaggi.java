/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * Parser Handler delle Carte Personaggio
 *
 * @see CartePersonaggio XmlReader
 */
public class ParserHandlerPersonaggi extends DefaultHandler {
	// Lista da popolare mentre faccio il parsing del file XML
	private List<CartePersonaggio> cartaList = new ArrayList<CartePersonaggio>();

	// Quando leggo un elemento XML faccio una push in questo stack
	private Stack<String> elementoStack = new Stack<String>();

	// Quando finisco un blocco XML faccio una push della CartaAzione in questo
	// Stack
	private Stack<CartePersonaggio> oggettoStack = new Stack<CartePersonaggio>();

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// Push nello stack degli elementi
		this.elementoStack.push(qName);
		// Se inizia con 'Carta' preparo un oggetto CartaPersonaggio e faccio il
		// push
		// nello stack degli oggetti
		if ("Carta".equals(qName)) {
			// Costruisco una CartaPersonaggio
			CartePersonaggio carta = new CartePersonaggio();

			// Imposto l'attributo id per ogni carta
			if (attributes != null && attributes.getLength() == 1) {
				carta.setId(Integer.parseInt(attributes.getValue(0)));
			}
			this.oggettoStack.push(carta);
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// Rimuovo l'ultima </carta> aggiunta
		this.elementoStack.pop();

		// L'oggetto CartaPersonaggio è costruita quindi faccio il pop dallo
		// stack degli oggetti e faccio il push nella lista delle carte
		if ("Carta".equals(qName)) {
			CartePersonaggio oggetto = this.oggettoStack.pop();
			this.cartaList.add(oggetto);
		}
	}

	/**
	 * Questo viene chiamato ogni volta che il parser incontra un value node
	 * */
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String stringa = new String(ch, start, length).trim();
		if (stringa.length() == 0) {
			return;
			// Ignoro gli spazi bianchi
		}

		// Gestisco il valore in base a quale elemento appartiene
		if ("Nome".equals(currentElement())) {
			CartePersonaggio carta = (CartePersonaggio) this.oggettoStack
					.peek();
			carta.setNome(stringa);
		} else if ("Soldi".equals(currentElement())) {
			CartePersonaggio carta = (CartePersonaggio) this.oggettoStack
					.peek();
			int intero = Integer.parseInt(new String(ch, start, length).trim());
			carta.setSoldi(intero);
		} else if ("Quotazione".equals(currentElement())) {
			CartePersonaggio carta = (CartePersonaggio) this.oggettoStack
					.peek();
			int intero = Integer.parseInt(new String(ch, start, length).trim());
			carta.setQuotazione(intero);
		}
	}

	/**
	 * Metodo per ottenere l'elemento corrente
	 * */
	private String currentElement() {
		return this.elementoStack.peek();
	}
	/**
	 * 
	 * Ritorna la lista delle carte azione
	 *
	 * @return cartaList

	 * @see CartaPersonaggio
	 */
	public List<CartePersonaggio> getCarte() {
		return cartaList;
	}
}
