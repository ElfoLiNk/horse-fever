/**
 *
 */
package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.util;

import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model.Scuderia;
import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model.CartaAzione;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Classe per leggere stringe, caratteri e interi da System.in
 */
public final class SystemIn {

    private static BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in, Charset.defaultCharset()));
    private static String stringa = "temp";
    ;
    private static int intero;
    private static char carattere = "e".charAt(0);

    private SystemIn() {
    }

    /**
     * Legge una stringa dal system.in
     *
     * @return La stringa letta
     * @exceptions IOException
     * @see
     */
    public static String readString() {
        do {
            try {
                stringa = br.readLine();
            } catch (IOException e) {
                SystemOut.write("Errore di flusso");
            }
        } while ("".equals(stringa));
        return stringa;
    }

    /**
     * Legge un intero dal system.in
     *
     * @return L' intero letto
     * @exceptions IOException, NumberFormatException
     * @see
     */
    public static int readInt() {
        try {
            stringa = br.readLine();
            intero = Integer.parseInt(stringa);
        } catch (IOException e1) {
            SystemOut.write("Errore di flusso");
        } catch (NumberFormatException e2) {
            SystemOut.write("ERRORE: Devi inserire un numero!");
            return -1;
        }

        return intero;
    }

    /**
     * Chiede al giocatore che tipo di scommessa vuole effettuare
     *
     * @return carattere: v per vincente p per piazzato
     */
    public static char readTipoScommessa() {
        do {
            SystemOut.write("Che tipo di scommessa vuoi giocare?\n"
                    + "Digita v per vincente\n" + "       p per piazzato");

            stringa = readString();
            if (stringa != null) {
                if (stringa.length() > 1) {
                    carattere = 'e';
                } else {
                    carattere = stringa.charAt(0);
                }
            }
            if (carattere != 'v' && carattere != 'p') {
                SystemOut.write("hai sbagliato a digitare, riprova");
            }

        } while (carattere != 'v' && carattere != 'p');
        return carattere;
    }

    /**
     * Chiede al giocatore se vuole effettuare una seconda scommessa
     *
     * @return carattere: s effettua la seconda scommessa n non effettua la
     * scommessa
     */
    public static char readSecondaScommessa() {
        do {
            SystemOut.write("\nIl secondo giro di scommesse è facoltativo, vuoi piazzare "
                    + "una scommessa?\ndigita:\ns per scommettere\nn per saltare la scommmessa");
            stringa = readString();
            if (stringa.length() > 1) {
                carattere = 'e';
            } else {
                carattere = stringa.charAt(0);
            }
            if (carattere != 's' && carattere != 'n') {
                SystemOut.write("Hai sbagliato a digitare, riprova");
            }
        } while (carattere != 's' && carattere != 'n');
        return carattere;
    }

    /**
     * Chiede al giocatore su che scuderia vuole scommettere
     *
     * @param scuderie la lista delle scuderie della partita
     * @return s l'indice della scuderia scelta nella lista
     * @see Scuderia
     */
    public static int readScuderiaScommessa(final List<Scuderia> scuderie) {
        int indiceScuderia;
        int numeroScuderie = 0;
        SystemOut.write("\nSu che scuderia vuoi scommettere?");
        SystemOut.write("\n    COLORE\tN.SCOMMESSE\tQUOTAZIONE");
        for (final Scuderia scuderia : scuderie) {
            SystemOut.write(numeroScuderie + scuderia.toString());
            numeroScuderie++;
        }
        do {
            SystemOut.write("Seleziona scuderia: ");
            indiceScuderia = SystemIn.readInt();
            if (indiceScuderia > scuderie.size() - 1) {
                SystemOut.write("non hai digitato un valore valido, riprova");
            }
        } while (indiceScuderia < 0 || indiceScuderia > scuderie.size() - 1);

        return indiceScuderia;
    }

    /**
     * Chiede al giocatore quale carte azione vuole giocare
     *
     * @param carteAzione le carte del giocatore attuale
     * @return k la carta scelta
     * @see CartaAzione
     */
    public static int readCartaAzione(final List<CartaAzione> carteAzione) {
        // Print carte azione del player
        for (int j = 0; j < carteAzione.size(); j++) {
            SystemOut.write(j + ") " + carteAzione.get(j).toString());
        }
        // Scelta Carta
        int indiceCartaScelta;
        do {
            SystemOut.write("Seleziona la Carta Azione da giocare: ");
            indiceCartaScelta = SystemIn.readInt();
        } while (indiceCartaScelta < 0 || indiceCartaScelta > carteAzione.size() - 1);
        return indiceCartaScelta;
    }

}
