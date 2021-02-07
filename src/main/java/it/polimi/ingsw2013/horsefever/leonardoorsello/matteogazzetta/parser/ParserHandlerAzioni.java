package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.parser;

import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model.CartaAzione;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Parser Handler delle Carte Azione
 *
 * @see CartaAzione XmlReader
 */
public class ParserHandlerAzioni extends DefaultHandler {
    // Lista da popolare mentre faccio il parsing del file XML
    private final List<CartaAzione> cartaList;
    // Quando leggo un elemento XML faccio una push in questo stack
    private final Stack<String> elementoStack;
    // Quando finisco un blocco XML faccio una push della CartaAzione in questo Stack
    private final Stack<CartaAzione> oggettoStack;

    ParserHandlerAzioni() {
        super();
        this.cartaList = new ArrayList<>();
        this.elementoStack = new Stack<>();
        this.oggettoStack = new Stack<>();
    }

    /**
     * Creo la carta
     */
    public void startElement(final String uri, final String localName, final String qName,
                             final Attributes attributes) {
        // Push nello stack degli elementi
        this.elementoStack.push(qName);
        // Se inizia con 'Carta' preparo un oggetto CartaAzione e faccio il push
        // nello stack degli oggetti
        if ("Carta".equals(qName)) {
            // Costruisco una CartaAzione
            final CartaAzione carta = new CartaAzione();

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
    public void endElement(final String uri, final String localName, final String qName) {
        // Rimuovo l'ultima </carta> aggiunta
        this.elementoStack.pop();

        // L'oggetto CartaAzione è costruita quindi faccio il pop dallo stack
        // degli oggetti e faccio il push nella lista delle carte
        if ("Carta".equals(qName)) {
            final CartaAzione oggetto = this.oggettoStack.pop();
            this.cartaList.add(oggetto);
        }
    }

    /**
     * Questo viene chiamato ogni volta che il parser incontra un value node
     */
    public void characters(final char[] ch, final int start, final int length) {
        final String stringa = new String(ch, start, length).trim();
        if (stringa.length() == 0) {
            return;
            // Ignoro gli spazi bianchi
        }

        // Gestisco il valore in base a quale elemento appartiene
        if ("Nome".equals(currentElement())) {
            final CartaAzione carta = this.oggettoStack.peek();
            carta.setNome(stringa);
        } else if ("Lettera".equals(currentElement())) {
            final CartaAzione carta = this.oggettoStack.peek();
            carta.setLettera(stringa);
        } else if ("Effetto".equals(currentElement())) {
            CartaAzione carta = this.oggettoStack.peek();
            final int intero = Integer.parseInt(stringa);
            carta.setEffetto(intero);
        } else if ("Agisce".equals(currentElement())) {
            CartaAzione carta = this.oggettoStack.peek();
            carta.setAgisce(stringa);
        } else if ("Descrizione".equals(currentElement())) {
            CartaAzione carta = this.oggettoStack.peek();
            carta.setDescrizione(stringa);
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
     * @see CartaAzione
     */
    public List<CartaAzione> getCarte() {
        return cartaList;
    }
}
