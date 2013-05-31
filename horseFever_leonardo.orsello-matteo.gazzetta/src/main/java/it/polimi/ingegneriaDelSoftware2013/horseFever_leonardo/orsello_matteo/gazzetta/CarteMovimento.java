/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.*;
import java.util.*;

/**
 * 
 *  Struttura dati delle carte movimento
 * 
 */
public class CarteMovimento {
	private static List<String> listacartemovimento = new ArrayList<String>();
	
	/**
	 * 
	 * Legge un file txt contenente le carte movimento e le salva in una lista
	 *
	 * @return la lista delle carte movimento
	 * @exceptions IOException
	 *
	 * @see
	 */
	public static List<String> setMovimento() {
		try {
			String filetxt = "cartemovimento.txt";
			FileReader fr = new FileReader(filetxt);
			BufferedReader file = new BufferedReader(fr);
			
			// Leggo una linea dal file
			String linea = file.readLine();

			while (linea != null) {
				listacartemovimento.add(linea);
				linea = file.readLine();
			}

			// Chiudo File
			fr.close();
			file.close();
			
			return listacartemovimento;			

		} catch (IOException e) {
			Write.write("ERRORE: Lettura carte movimento");
		}
		return listacartemovimento;
	}
	
}