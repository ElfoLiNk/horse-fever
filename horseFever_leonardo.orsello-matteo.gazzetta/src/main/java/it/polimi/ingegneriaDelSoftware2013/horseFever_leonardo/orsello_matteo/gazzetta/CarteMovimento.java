/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
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
		String filetxt = "cartemovimento.txt";
		BufferedReader file = null;
		try {

			file = new BufferedReader(new FileReader(filetxt));

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