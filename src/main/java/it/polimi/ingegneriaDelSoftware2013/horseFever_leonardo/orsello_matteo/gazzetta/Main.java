/**
 *
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.IOException;

/**
 * Main del gioco
 */
public final class Main {
    private Main() {
    }

    /**
     * @param args argomenti applicazione
     */
    public static void main(final String[] args) throws IOException {
        Write.write(" ------------------------------------------------");
        Write.write(" |                Horse Fever                   |");
        Write.write(" ------------------------------------------------");
        final Audio audio = new Audio("soundtrack.mp3");
        audio.start();
        final Partita partita = new Partita();
        partita.setListe();
        partita.setScuderie();
        partita.setQuotazioni();
        partita.setNumGiocatori();
        partita.setGiocatori();
        partita.randomPrimogiocatore();
        partita.turno();

    }

}
