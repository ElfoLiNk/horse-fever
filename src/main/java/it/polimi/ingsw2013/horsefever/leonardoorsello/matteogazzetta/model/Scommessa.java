/**
 *
 */
package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model;

/**
 * Struttura dati della Scommessa
 */
public class Scommessa {
    private String nomegiocatore;
    private int soldi;
    private Tiposcommessa tiposcommessa;

    public Scommessa(final String nomegiocatore) {
        this.nomegiocatore = nomegiocatore;
        this.soldi = 0;
    }

    /**
     * @return the nomegiocatore
     */
    public String getNomeGiocatore() {
        return nomegiocatore;
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
     * @return the tiposcommessa
     */
    public Tiposcommessa getTiposcommessa() {
        return tiposcommessa;
    }

    /**
     * @param tipo il tipo di scommessa da impostare v vincente p piazzato
     */
    public void setTiposcommessa(final char tipo) {
        if (tipo == 'v') {
            tiposcommessa = Tiposcommessa.VINCENTE;
        }
        if (tipo == 'p') {
            tiposcommessa = Tiposcommessa.PIAZZATO;
        }
    }

    /**
     * Tipo della scommessa
     */
    public enum Tiposcommessa {
        PIAZZATO, VINCENTE
    }

}
