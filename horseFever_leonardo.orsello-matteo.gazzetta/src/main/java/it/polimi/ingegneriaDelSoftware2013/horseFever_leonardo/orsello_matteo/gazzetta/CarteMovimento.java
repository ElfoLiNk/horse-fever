/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.*;
import java.util.*;

/**
 * @author matteo
 * 
 */
public class CarteMovimento {

	/**
	 * 
	 * Legge un file txt contenente le carte movimento e le salva in una lista
	 *
	 * @return la lista delle carte movimento
	 * @exceptions
	 *
	 * @see
	 */
	public static List<String> setMovimento() {
		List<String> linee = new ArrayList<String>();
		try {
			// File per linee to Lista di Stringhe
			FileReader openfile = new FileReader("cartemovimento.txt");
			BufferedReader file = new BufferedReader(openfile);
			

			String linea = file.readLine();

			while (linea != null) {
				linee.add(linea);
				linea = file.readLine();
			}

			// Chiudo File
			openfile.close();
			file.close();
			
			return linee;			

		} catch (IOException e) {
			Write.write("ERRORE: Lettura carte movimento");
		}
		return linee;
	}
	
}