/**
 *
 */
package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.parser;

import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model.CartaPersonaggio;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Parser Handler delle Carte Personaggio
 *
 * @see CartaPersonaggio XmlReader
 */
public class ParserHandlerPersonaggi extends DefaultHandler {
    // Lista da popolare mentre faccio il parsing del file XML
    private final List<CartaPersonaggio> cartaList;

    // Quando leggo un elemento XML faccio una push in questo stack
    private final Stack<String> elementoStack;

    // Quando finisco un blocco XML faccio una push della CartaAzione in questo Stack
    private final Stack<CartaPersonaggio> oggettoStack;

    ParserHandlerPersonaggi() {
        super();
        this.cartaList = new ArrayList<>();
        this.elementoStack = new Stack<>();
        this.oggettoStack = new Stack<>();
    }

    /**
     * Creo la carta
     */
    public void startElement(final String uri, final String localName, final String qName,
                             final Attributes attributes) throws SAXException {
        // Push nello stack degli elementi
        this.elementoStack.push(qName);
        // Se inizia con 'Carta' preparo un oggetto CartaPersonaggio e faccio il
        // push
        // nello stack degli oggetti
        if ("Carta".equals(qName)) {
            // Costruisco una CartaPersonaggio
            final CartaPersonaggio carta = new CartaPersonaggio();

            // Imposto l'attributo id per ogni carta
            if (attributes != null && attributes.getLength() == 1) {
                carta.setIdentifier(Integer.parseInt(attributes.getValue(0)));
            }
            this.oggettoStack.push(carta);
        }
    }

    /**
     * Chiudo la carta
     */
    public void endElement(final String uri, final String localName, final String qName)
            throws SAXException {
        // Rimuovo l'ultima </carta> aggiunta
        this.elementoStack.pop();

        // L'oggetto CartaPersonaggio è costruita quindi faccio il pop dallo
        // stack degli oggetti e faccio il push nella lista delle carte
        if ("Carta".equals(qName)) {
            final CartaPersonaggio oggetto = this.oggettoStack.pop();
            this.cartaList.add(oggetto);
        }
    }

    /**
     * Questo viene chiamato ogni volta che il parser incontra un value node
     */
    public void characters(final char[] ch, final int start, final int length)
            throws SAXException {
        final String stringa = new String(ch, start, length).trim();
        if (stringa.length() == 0) {
            return;
            // Ignoro gli spazi bianchi
        }

        // Gestisco il valore in base a quale elemento appartiene
        if ("Nome".equals(currentElement())) {
            final CartaPersonaggio carta = this.oggettoStack.peek();
            carta.setNome(stringa);
        } else if ("Soldi".equals(currentElement())) {
            final CartaPersonaggio carta = this.oggettoStack.peek();
            final int intero = Integer.parseInt(new String(ch, start, length).trim());
            carta.setSoldi(intero);
        } else if ("Quotazione".equals(currentElement())) {
            final CartaPersonaggio carta = this.oggettoStack.peek();
            final int intero = Integer.parseInt(new String(ch, start, length).trim());
            carta.setQuotazione(intero);
        }
    }

    /**
     * Metodo per ottenere l'elemento corrente
     */
    private String currentElement() {
        return this.elementoStack.peek();
    }

    /**
     * Ritorna la lista delle carte azione
     *
     * @return cartaList
     * @see CartaPersonaggio
     */
    public List<CartaPersonaggio> getCarte() {
        return cartaList;
    }
}
