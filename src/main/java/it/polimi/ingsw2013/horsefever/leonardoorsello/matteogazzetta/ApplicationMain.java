package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta;

import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.util.Audio;
import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.util.SystemOut;

import java.io.IOException;

/**
 * ApplicationMain del gioco
 */
public final class ApplicationMain {
    private ApplicationMain() {
    }

    /**
     * @param args argomenti applicazione
     * @throws IOException quando configuro la lista o il turno
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
