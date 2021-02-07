package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model;

import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.util.ResourceLoader;
import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.util.SystemOut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Struttura dati delle carte movimento
 */
public final class CartaMovimento {
    private static final List<String> CARTE_MOVIMENTO = new ArrayList<>();

    private CartaMovimento() {
    }

    /**
     * Legge un file txt contenente le carte movimento e le salva in una lista
     *
     * @return la lista delle carte movimento
     */
    public static List<String> caricaCarteMovimento() {
        try (BufferedReader file = new BufferedReader(new InputStreamReader(
                ResourceLoader.load("cartemovimento.txt"), Charset.defaultCharset()))) {
            // Leggo una linea dal file
            String linea = file.readLine();

            while (linea != null) {
                CARTE_MOVIMENTO.add(linea);
                linea = file.readLine();
            }

        } catch (IOException e) {
            SystemOut.write("ERRORE: Lettura carte movimento");
        }
        return CARTE_MOVIMENTO;
    }

}
