/**
 *
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Parser Handler delle Carte Azione
 *
 * @see CarteAzione XmlReader
 */
public class ParserHandlerAzioni extends DefaultHandler {
    // Lista da popolare mentre faccio il parsing del file XML
    private List<CarteAzione> cartaList = new ArrayList<>();
    // Quando leggo un elemento XML faccio una push in questo stack
    private Stack<String> elementoStack = new Stack<>();
    // Quando finisco un blocco XML faccio una push della CartaAzione in questo
    // Stack
    private Stack<CarteAzione> oggettoStack = new Stack<>();

    /**
     * Creo la carta
     */
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // Push nello stack degli elementi
        this.elementoStack.push(qName);
        // Se inizia con 'Carta' preparo un oggetto CartaAzione e faccio il push
        // nello stack degli oggetti
        if ("Carta".equals(qName)) {
            // Costruisco una CartaAzione
            CarteAzione carta = new CarteAzione();

            // Imposto l'attributo id per ogni carta
            if (attributes != null && attributes.getLength() == 1) {
                carta.setId(Integer.parseInt(attributes.getValue(0)));
            }
            this.oggettoStack.push(carta);
        }
    }

    /**
     * Chiudo la carta
     */
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // Rimuovo l'ultima </carta> aggiunta
        this.elementoStack.pop();

        // L'oggetto CartaAzione è costruita quindi faccio il pop dallo stack
        // degli oggetti e faccio il push nella lista delle carte
        if ("Carta".equals(qName)) {
            CarteAzione oggetto = this.oggettoStack.pop();
            this.cartaList.add(oggetto);
        }
    }

    /**
     * Questo viene chiamato ogni volta che il parser incontra un value node
     */
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String stringa = new String(ch, start, length).trim();
        if (stringa.length() == 0) {
            return;
            // Ignoro gli spazi bianchi
        }

        // Gestisco il valore in base a quale elemento appartiene
        if ("Nome".equals(currentElement())) {
            CarteAzione carta = this.oggettoStack.peek();
            carta.setNome(stringa);
        } else if ("Lettera".equals(currentElement())) {
            CarteAzione carta = this.oggettoStack.peek();
            carta.setLettera(stringa);
        } else if ("Effetto".equals(currentElement())) {
            CarteAzione carta = this.oggettoStack.peek();
            int intero = Integer.parseInt(new String(ch, start, length).trim());
            carta.setEffetto(intero);
        } else if ("Agisce".equals(currentElement())) {
            CarteAzione carta = this.oggettoStack.peek();
            carta.setAgisce(stringa);
        } else if ("Descrizione".equals(currentElement())) {
            CarteAzione carta = this.oggettoStack.peek();
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
     * @see CarteAzione
     */
    public List<CarteAzione> getCarte() {
        return cartaList;
    }
}
