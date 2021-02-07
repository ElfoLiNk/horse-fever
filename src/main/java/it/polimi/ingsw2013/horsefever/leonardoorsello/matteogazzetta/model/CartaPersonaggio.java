package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model;

import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.util.ResourceLoader;
import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.parser.XmlParser;

import java.util.List;

/**
 * Struttura dati delle carte personaggio
 */
public class CartaPersonaggio {
    /**
     * Attributi:
     * identifier: Numero identificativo della carta.
     * nome: Nome del personaggio della carta.
     * soldi: Soldi del personaggio della carta.
     * quotazione: Quotazione del personaggio della carta.
     */
    private int identifier;
    private String nome;
    private int soldi;
    private int quotazione;

    /**
     * Crea una lista di tipo {@link CartaPersonaggio} effettuando il parsing di un file
     * xml contenente le carte azione.
     *
     * @return la lista delle carte personaggio lette dal file xml
     */
    public static List<CartaPersonaggio> caricaCartePersionaggio() {
        // Parso il file
        return XmlParser.parseXmlPersonaggi(ResourceLoader.load("cartePersonaggio.xml"));
    }

    @Override
    public String toString() {
        return this.identifier + ":" + this.nome + ":" + this.soldi + ":"
                + this.quotazione;
    }

    /**
     * @param newIdentifier the identifier to set
     */
    public void setIdentifier(final int newIdentifier) {
        this.identifier = newIdentifier;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param newNome the nome to set
     */
    public void setNome(final String newNome) {
        this.nome = newNome;
    }

    /**
     * @return the soldi
     */
    public int getSoldi() {
        return soldi;
    }

    /**
     * @param newSoldi the soldi to set
     */
    public void setSoldi(final int newSoldi) {
        this.soldi = newSoldi;
    }

    /**
     * @return the quotazione
     */
    public int getQuotazione() {
        return quotazione;
    }

    /**
     * @param newQuotazione the quotazione to set
     */
    public void setQuotazione(final int newQuotazione) {
        this.quotazione = newQuotazione;
    }

}
