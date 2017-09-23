/**
 *
 */
package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta;

/**
 * Struttura dati della Scommessa
 */
public class Scommessa {
    private String nomegiocatore;
    private int soldi;
    private Tiposcommessa tiposcommessa;

    Scommessa(final String nomegiocatore) {
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
     * @param nomegiocatore the nomegiocatore to set
     */
    public void setNomeGiocatore(final String nomegiocatore) {
        this.nomegiocatore = nomegiocatore;
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
     * @param tipo il tipo di scommessa da impostare v vincente p piazzato
     */
    public Tiposcommessa getTiposcommessa(final char tipo) {
        if (tipo == 'v') {
            return Tiposcommessa.VINCENTE;
        }
        if (tipo == 'p') {
            return Tiposcommessa.PIAZZATO;
        }
        throw new RuntimeException();
    }

    /**
     * Tipo della scommessa
     */
    public enum Tiposcommessa {
        PIAZZATO, VINCENTE
    }

}
