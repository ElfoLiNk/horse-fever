package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.parser;

import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model.CartaAzione;
import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model.CartaPersonaggio;
import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.util.SystemOut;
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
 * @see CartaAzione , ParserHandlerPersonaggi, ParserHandlerAzioni
 */
public final class XmlParser {

    private XmlParser() {
    }

    /**
     * Funzione che fa il parsing del file xml delle carte personaggio.
     *
     * @param cartePersonaggio stream di input del file xml
     * @return carte la lista delle carte personaggio lette dal file xml
     * @see CartaAzione , ParserHandlerPersonaggi
     */
    public static List<CartaPersonaggio> parseXmlPersonaggi(final InputStream cartePersonaggio) {
        // Creo una lista vuota delle carte personaggio
        List<CartaPersonaggio> carte = new ArrayList<>();
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
            SystemOut.write("Errore nel parser SAX");
        } catch (IOException e) {
            SystemOut.write("Errore nella lettura del file xml");
        }
        return carte;
    }

    /**
     * Funzione che fa il parsing del file xml delle carte azione.
     *
     * @param carteAzione stream di input del file xml
     * @return carte la lista delle carte azione lette dal file xml
     * @see CartaAzione , ParserHandlerAzioni
     */
    public static List<CartaAzione> parseXmlAzioni(final InputStream carteAzione) {
        // Creo una lista vuota di carte azione
        List<CartaAzione> carte = new ArrayList<>();
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
            SystemOut.write("Errore nel parser SAX");
        } catch (IOException e) {
            SystemOut.write("Errore nella lettura del file xml");
        }
        return carte;
    }
}
