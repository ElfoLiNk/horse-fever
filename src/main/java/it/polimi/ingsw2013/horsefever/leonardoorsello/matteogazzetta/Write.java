/**
 *
 */
package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta;

import java.io.PrintStream;
import java.util.List;
import java.util.Locale;

/**
 * Classe che scrive stringhe e interi su System.out
 */
final class Write {

    private Write() {
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
        Write.write("|----------------------------------------------|");
        Write.write("|                " + header + "                |");
        Write.write("|----------------------------------------------|");
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
            Write.write("\n");
        }
    }

    /**
     * Stampa la classifica di arrivo delle scuderie
     *
     * @param classifica lista delle scuderie secondo l'ordine di arrivo
     * @see Scuderia
     */
    public static void printClassifica(final List<Scuderia> classifica) {
        Write.write("\nCLASSIFICA\n");
        for (final Scuderia scuderia : classifica) {
            Write.write(scuderia.getColore() + "\t" + scuderia.getClassifica());
        }
    }

    /**
     * Stampa le quotazioni delle scuderie
     *
     * @param scuderie la lista delle scuderie
     * @see Scuderia
     */
    public static void printQuotazioni(final List<Scuderia> scuderie) {
        Write.write("\nQUOTAZIONI AGGIORNATE\n");
        for (final Scuderia scuderia : scuderie) {
            Write.write(scuderia.getColore() + "\tQuotazione: "
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
        Write.clear();
        for (final Scuderia scuderia : scuderie) {
            // Stampo il colore della scuderia
            Write.simple(scuderia.getColore() + "\t");
            // Stampo tot # in base alla posizione della scuderia
            for (int j = 0; j < scuderia.getPosizione(); j++) {
                Write.simple("#");
            }
            Write.simple(" " + scuderia.getPosizione());
            // Stampo la classifica di arrivo della scuderia
            if (scuderia.getClassifica() > 0) {
                Write.simple(" " + scuderia.getClassifica() + "°");
            }
            Write.write("");
        }
        try {
            // Sleep per animazione corsa
            Thread.sleep(Parametri.SLEEP);
        } catch (InterruptedException e) {
            write("Errore nella sleep");
        }
    }

    /**
     * Stampa la classfica dei giocatori con i dati aggiornati
     *
     * @see Giocatore
     */
    public static void leaderboard(final List<Giocatore> giocatori) {
        write("\nCLASSIFICA GIOCATORI\n");
        write("Nome\tScuderia\tSoldi\tPV");
        for (final Giocatore player : giocatori) {
            // Informazioni sul giocatore: Nome scuderia soldi pv
            write(player.getNome().toUpperCase(Locale.getDefault())
                    + "\t" + player.getScuderia() + "\t\t" + player.getSoldi()
                    + "\t" + player.getPv() + "\n");
        }
    }

    /**
     * Scrive una stringa sulla stessa linea su System.out
     *
     * @param string la stringa da scrivere
     * @see
     */
    private static void simple(final String string) {
        PrintStream out = System.out;
        out.print(string);
    }
}