/**
 *
 */
package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Xml Parser
 *
 * @see CarteAzione, ParserHandlerPersonaggi, ParserHandlerAzioni
 */
public final class XmlParser {

    public XmlParser() {
    }

    /**
     * Funzione che fa il parsing del file xml delle carte personaggio.
     *
     * @param cartePersonaggio stream di input del file xml
     * @return carte la lista delle carte personaggio lette dal file xml
     * @see CarteAzione, ParserHandlerPersonaggi
     */
    public static List<CartePersonaggio> parseXmlPersonaggi(final InputStream cartePersonaggio) {
        // Creo una lista vuota delle carte personaggio
        List<CartePersonaggio> carte = new ArrayList<>();
        try {
            // Creo un instanza del handler per le carte personaggio
            final ParserHandlerPersonaggi handler = new ParserHandlerPersonaggi();

            // Creo un parser dalla factory
            final XMLReader parser = XMLReaderFactory.createXMLReader();

            // Imposto l'handler delle carte personaggio nel parser
            parser.setContentHandler(handler);

            // Creo un input dal file xml
            final InputSource sorgente = new InputSource(cartePersonaggio);

            // Faccio il parsing dell'input
            parser.parse(sorgente);

            // Popolo la lista delle carte personaggio
            carte = handler.getCarte();

        } catch (SAXException e) {
            Write.write("Errore nel parser SAX");
        } catch (IOException e) {
            Write.write("Errore nella lettura del file xml");
        }
        return carte;
    }

    /**
     * Funzione che fa il parsing del file xml delle carte azione.
     *
     * @param carteAzione stream di input del file xml
     * @return carte la lista delle carte azione lette dal file xml
     * @see CarteAzione, ParserHandlerAzioni
     */
    public static List<CarteAzione> parseXmlAzioni(final InputStream carteAzione) {
        // Creo una lista vuota di carte azione
        List<CarteAzione> carte = new ArrayList<>();
        try {
            // Creo un istanza del handler per le carte azioni
            final ParserHandlerAzioni handler = new ParserHandlerAzioni();

            // Creo un parser dalla factory
            final XMLReader parser = XMLReaderFactory.createXMLReader();

            // Imposto l'handler delle carte azione nel parser
            parser.setContentHandler(handler);

            // Creo un input dal file xml
            final InputSource sorgente = new InputSource(carteAzione);

            // Faccio il parsing dell'input
            parser.parse(sorgente);

            // Popolo la lista delle carte azione
            carte = handler.getCarte();

        } catch (SAXException e) {
            Write.write("Errore nel parser SAX");
        } catch (IOException e) {
            Write.write("Errore nella lettura del file xml");
        }
        return carte;
    }
}
