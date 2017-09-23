package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta;

import java.io.InputStream;

/**
 * Utility class per caricare i file dal jar
 */
public final class ResourceLoader {

    private ResourceLoader() {
    }

    /**
     * Restituisce l'input stream della risorsa necessaria
     *
     * @param fileName il percorso del file da restituire l'input stream
     * @return input InputStream della risorsa richiesta
     * @see InputStream, ResourceLoader
     */
    public static InputStream load(final String fileName) {
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("/resources/" + fileName);
        if (input == null) {
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        }
        return input;
    }
}
