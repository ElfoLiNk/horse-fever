/**
 *
 */
package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Struttura dati delle carte movimento
 */
public final class CarteMovimento {
    private static List<String> carteMoviment = new ArrayList<>();

    private CarteMovimento() {
    }

    /**
     * Legge un file txt contenente le carte movimento e le salva in una lista
     *
     * @return la lista delle carte movimento
     * @throws IOException ERRORE: Lettura carte movimento
     */
    public static List<String> setMovimento() throws IOException {
        try (BufferedReader file = new BufferedReader(new InputStreamReader(
                ResourceLoader.load("cartemovimento.txt"), Charset.defaultCharset()))) {
            // Leggo una linea dal file
            String linea = file.readLine();

            while (linea != null) {
                carteMoviment.add(linea);
                linea = file.readLine();
            }

        } catch (IOException e) {
            Write.write("ERRORE: Lettura carte movimento");
        }
        return carteMoviment;
    }

}