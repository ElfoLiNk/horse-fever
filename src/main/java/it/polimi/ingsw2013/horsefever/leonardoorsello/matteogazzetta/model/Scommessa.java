package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model;

/**
 * Struttura dati della Scommessa
 */
public class Scommessa {
    private final String nomeGiocatore;
    private int soldi;
    private Tiposcommessa tipoScommessa;

    public Scommessa(final String newNomeGiocatore) {
        this.nomeGiocatore = newNomeGiocatore;
        this.soldi = 0;
    }

    /**
     * @return the nomeGiocatore
     */
    public String getNomeGiocatore() {
        return nomeGiocatore;
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
     * @return the tipoScommessa
     */
    public Tiposcommessa getTipoScommessa() {
        return tipoScommessa;
    }

    /**
     * @param tipo il tipo di scommessa da impostare v vincente p piazzato
     */
    public void setTipoScommessa(final char tipo) {
        if (tipo == Tiposcommessa.VINCENTE.value) {
            tipoScommessa = Tiposcommessa.VINCENTE;
        }
        if (tipo == Tiposcommessa.PIAZZATO.value) {
            tipoScommessa = Tiposcommessa.PIAZZATO;
        }
    }

    /**
     * Tipo della scommessa
     */
    public enum Tiposcommessa {
        PIAZZATO('p'), VINCENTE('v');

        private final char value;

        Tiposcommessa(final char newValue) {
            this.value = newValue;
        }

        public char getValue() {
            return value;
        }
    }

}
