/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Struttura dati delle carte movimento
 * 
 */
public class CarteMovimento {
	private static List<String> listacartemovimento = new ArrayList<String>();

	/**
	 * 
	 * Legge un file txt contenente le carte movimento e le salva in una lista
	 * 
	 * @return la lista delle carte movimento
	 * @throws IOException
	 * @exceptions IOException
	 * 
	 * @see
	 */
	public static List<String> setMovimento() throws IOException {
		BufferedReader file = null;
		try {

			file = new BufferedReader(new InputStreamReader(
					ResourceLoader.load("cartemovimento.txt"), Charset.defaultCharset()));

			// Leggo una linea dal file
			String linea = file.readLine();

			while (linea != null) {
				listacartemovimento.add(linea);
				linea = file.readLine();
			}

		} catch (IOException e) {
			Write.write("ERRORE: Lettura carte movimento");
		} finally {

			if (file != null) {
				file.close();
			}

		}
		return listacartemovimento;
	}

}