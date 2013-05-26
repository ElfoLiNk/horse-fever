/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

/**
 * @author Matteo
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * 
 * Xml Parser
 * 
 * 
 * 
 * @see
 */
public class XmlParser {
	
	/**
	 * 
	 * {Descrizione}
	 * 
	 * @param in
	 * @return
	 * @exceptions
	 * 
	 * @see
	 */
	public List<CartePersonaggio> parseXmlPersonaggi(InputStream in) {
		// Creo una lista vuota delle carte personaggio
		List<CartePersonaggio> carte = new ArrayList<CartePersonaggio>();
		try {
			// Creo un instanza del handler per le carte personaggio
			ParserHandlerPersonaggi handler = new ParserHandlerPersonaggi();

			// Creo un parser dalla factory
			XMLReader parser = XMLReaderFactory.createXMLReader();

			// Imposto l'handler delle carte personaggio nel parser
			parser.setContentHandler(handler);

			// Creo un input dal file xml
			InputSource sorgente = new InputSource(in);

			// Faccio il parsing dell'input
			parser.parse(sorgente);

			// Popolo la lista delle carte personaggio
			carte = handler.getCarte();

		} catch (SAXException e) {
			Write.write("Errore nel parser SAX");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Write.write("Errore nella lettura del file xml");
		}
		return carte;
	}

	/**
	 * 
	 * 
	 * 
	 * @param in
	 * @return la carte lista delle carte azioni
	 * @exceptions
	 * 
	 * @see
	 */
	public List<CarteAzione> parseXmlAzioni(InputStream in) {
		// Creo una lista vuota di carte azione
		List<CarteAzione> carte = new ArrayList<CarteAzione>();
		try {
			// Creo un istanza del handler per le carte azioni
			ParserHandlerAzioni handler = new ParserHandlerAzioni();

			// Creo un parser dalla factory
			XMLReader parser = XMLReaderFactory.createXMLReader();

			// Imposto l'handler delle carte azione nel parser
			parser.setContentHandler(handler);

			// Creo un input dal file xml
			InputSource sorgente = new InputSource(in);

			// Faccio il parsing dell'input
			parser.parse(sorgente);

			// Popolo la lista delle carte azione
			carte = handler.getCarte();

		} catch (SAXException e) {
			Write.write("Errore nel parser SAX");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Write.write("Errore nella lettura del file xml");
		}
		return carte;
	}
}
