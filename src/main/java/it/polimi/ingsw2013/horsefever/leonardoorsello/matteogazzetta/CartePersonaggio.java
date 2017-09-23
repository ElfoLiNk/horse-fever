/**
 *
 */
package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta;

import java.util.List;

/**
 * Struttura dati delle carte personaggio
 */
public class CartePersonaggio {
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
     * Crea una lista di tipo <CartePersonaggio> effettuando il parsing di un file
     * xml contenente le carte azione.
     */
    public static List<CartePersonaggio> crealistapersonaggi() {
        // Parso il file
        return XmlParser.parseXmlPersonaggi(ResourceLoader.load("cartePersonaggio.xml"));
    }

    @Override
    public String toString() {
        return this.identifier + ":" + this.nome + ":" + this.soldi + ":"
                + this.quotazione;
    }

    /**
     * @return the identifier
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier the identifier to set
     */
    public void setIdentifier(final int identifier) {
        this.identifier = identifier;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(final String nome) {
        this.nome = nome;
    }

    /**
     * @return the soldi
     */
    public int getSoldi() {
        return soldi;
    }

    /**
     * @param soldi the soldi to set
     */
    public void setSoldi(final int soldi) {
        this.soldi = soldi;
    }

    /**
     * @return the quotazione
     */
    public int getQuotazione() {
        return quotazione;
    }

    /**
     * @param quotazione the quotazione to set
     */
    public void setQuotazione(final int quotazione) {
        this.quotazione = quotazione;
    }

}
