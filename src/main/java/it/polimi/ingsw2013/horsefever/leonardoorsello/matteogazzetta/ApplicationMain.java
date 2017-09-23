/**
 *
 */
package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta;

import java.io.IOException;

/**
 * ApplicationMain del gioco
 */
public final class ApplicationMain {
    private ApplicationMain() {
    }

    /**
     * @param args argomenti applicazione
     */
    public static void main(final String[] args) throws IOException {
        SystemOut.write(" ------------------------------------------------");
        SystemOut.write(" |                Horse Fever                   |");
        SystemOut.write(" ------------------------------------------------");
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
