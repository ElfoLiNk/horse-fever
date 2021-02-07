package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.util;

import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model.Giocatore;
import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model.Parametri;
import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model.Scuderia;

import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

/**
 * Classe che scrive stringhe e interi su System.out
 */
public final class SystemOut {

    private SystemOut() {
    }

    /**
     * Scrive una stringa su una nuova linea su System.out
     *
     * @param stringa la stringa da scrivere
     */
    public static void write(final String stringa) {
        final PrintStream out = System.out;
        out.println(stringa);
    }

    /**
     * Layout del titolo della schermata
     *
     * @param header Titolo della schermata
     */
    public static void header(final String header) {
        SystemOut.write("|----------------------------------------------|");
        SystemOut.write("|                " + header + "                |");
        SystemOut.write("|----------------------------------------------|");
    }

    /**
     * Scrive un intero su una nuova linea su System.out
     *
     * @param intero l'intero da scrivere
     */
    public static void write(final int intero) {
        PrintStream out = System.out;
        out.println(intero);
    }

    /**
     * Pulisco la console
     */
    public static void clear() {
        for (int i = 0; i < Parametri.CLEAR; i++) {
            SystemOut.write("\n");
        }
    }

    /**
     * Stampa la classifica di arrivo delle scuderie
     *
     * @param classifica lista delle scuderie secondo l'ordine di arrivo
     * @see Scuderia
     */
    public static void printClassifica(final List<Scuderia> classifica) {
        SystemOut.write("\nCLASSIFICA\n");
        for (final Scuderia scuderia : classifica) {
            SystemOut.write(scuderia.getColore() + "\t" + scuderia.getClassifica());
        }
    }

    /**
     * Stampa le quotazioni delle scuderie
     *
     * @param scuderie la lista delle scuderie
     * @see Scuderia
     */
    public static void printQuotazioni(final List<Scuderia> scuderie) {
        SystemOut.write("\nQUOTAZIONI AGGIORNATE\n");
        for (final Scuderia scuderia : scuderie) {
            SystemOut.write(scuderia.getColore() + "\tQuotazione: "
                    + scuderia.getQuotazione());
        }
    }

    /**
     * Stampo la corsa le scuderie sono #
     *
     * @param scuderie la lista delle scuderie
     * @see Scuderia
     */
    public static void printCorsa(final List<Scuderia> scuderie) {
        SystemOut.clear();
        for (final Scuderia scuderia : scuderie) {
            // Stampo il colore della scuderia
            SystemOut.simple(scuderia.getColore() + "\t");
            // Stampo tot # in base alla posizione della scuderia
            for (int j = 0; j < scuderia.getPosizione(); j++) {
                SystemOut.simple("#");
            }
            SystemOut.simple(" " + scuderia.getPosizione());
            // Stampo la classifica di arrivo della scuderia
            if (scuderia.getClassifica() > 0) {
                SystemOut.simple(" " + scuderia.getClassifica() + "°");
            }
            SystemOut.write("");
        }
        try {
            // Sleep per animazione corsa
            Thread.sleep(Parametri.SLEEP);
        } catch (InterruptedException e) {
            write("Errore nella sleep");
        }
    }

    /**
     * Stampa la classifica dei giocatori con i dati aggiornati
     *
     * @param giocatori la lista dei giocatori di questa partita da stampare
     * @see Giocatore
     */
    public static void leaderboard(final List<Giocatore> giocatori) {
        write("\nCLASSIFICA GIOCATORI\n");
        write("Nome\tScuderia\tSoldi\tPV");
        for (final Giocatore player : giocatori) {
            // Informazioni sul giocatore: Nome scuderia soldi pv
            write(player.getNome().toUpperCase(Locale.getDefault())
                    + "\t" + player.getScuderia() + "\t\t" + player.getSoldi()
                    + "\t" + player.getPuntiVittoria() + "\n");
        }
    }

    /**
     * Scrive una stringa sulla stessa linea su System.out
     *
     * @param string la stringa da scrivere
     *
     */
    private static void simple(final String string) {
        PrintStream out = System.out;
        out.print(string);
    }
}
