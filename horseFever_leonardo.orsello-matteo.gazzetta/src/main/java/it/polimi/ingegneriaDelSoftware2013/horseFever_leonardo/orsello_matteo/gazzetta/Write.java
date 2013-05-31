/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.PrintStream;

/**
 * Classe che scrive stringhe e interi su System.out
 * 
 */
final class Write {

	private Write() {
	}

	/**
	 * 
	 * Scrive una stringa su System.out
	 * 
	 * @param Riceve s la stringa da scrivere
	 * @see
	 */
	public static void write(String s) {
		PrintStream out = System.out;
		out.println(s);
	}

	/**
	 * 
	 * Scrive un intero su System.out
	 * 
	 * @param Riceve i l'intero da scrivere
	 * @see
	 */
	public static void write(int i) {
		PrintStream out = System.out;
		out.println(i);
	}
}