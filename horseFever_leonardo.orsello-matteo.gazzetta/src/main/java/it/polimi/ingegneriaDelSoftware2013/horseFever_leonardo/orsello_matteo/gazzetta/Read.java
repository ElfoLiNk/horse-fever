/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *  Classe per leggere stringe, caratteri e interi da System.in
 */
final class Read {

	private Read() {
	}

	private static BufferedReader br;
	private static String stringa;
	private static int intero;
	private static char carattere;

	/**
	 * 
	 * Legge una stringa dal system.in 
	 *
	 * @return La stringa letta
	 * @exceptions IOException
	 *
	 * @see
	 */
	public static String readString() {
		br = new BufferedReader(new InputStreamReader(System.in));

		try {
			stringa = br.readLine();
			if (stringa != null) {
				while (stringa.isEmpty()) {
					stringa = br.readLine();
				}
			}

		} catch (IOException e) {
			Write.write("Errore di flusso");
		}

		return (stringa);
	}

	/**
	 * 
	 * Legge un intero dal system.in
	 *
	 * @return L' intero letto
	 * @exceptions IOException, NumberFormatException
	 *
	 * @see
	 */
	public static int readInt() {
		br = new BufferedReader(new InputStreamReader(System.in));

		try {
			do {
				stringa = br.readLine();
			} while (stringa.isEmpty());
			intero = Integer.parseInt(stringa);
		} catch (IOException e1) {
			Write.write("Errore di flusso");
		} catch (NumberFormatException e2) {
			Write.write("ERRORE: Devi inserire un numero!");
			return (-1);
		}

		return (intero);
	}

	/**
	 * 
	 * Legge un Carattere dal system.in
	 * 
	 * @return Il carattere letto
	 * @exceptions IOException, NumberFormatException
	 * 
	 * @see
	 */
	public static char readChar() {
		br = new BufferedReader(new InputStreamReader(System.in));

		try {

			do {
				stringa = br.readLine();
			} while (stringa.isEmpty());
			if (stringa.length() > 1)
				throw new NumberFormatException();

			carattere = stringa.charAt(0);
		} catch (IOException e1) {
			Write.write("Errore di flusso");
		} catch (NumberFormatException e2) {
			Write.write("ERRORE: Non hai inserito un carattere valido!");
			return (0);
		}

		return (carattere);
	}

}