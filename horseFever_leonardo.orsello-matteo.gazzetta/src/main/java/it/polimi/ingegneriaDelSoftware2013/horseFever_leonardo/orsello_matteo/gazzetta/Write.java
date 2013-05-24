/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.PrintStream;

/**
 * @author matteo
 * 
 */
final class Write {
	
	private Write() {
	}
	
	/**
	 * 
	 * {Descrizione}
	 *
	 * @param s
	 * @exceptions
	 *
	 * @see
	 */
	public static void write(String s) {
		PrintStream out = System.out;
		out.println(s);
	}

	/**
	 * 
	 * {Descrizione}
	 *
	 * @param i
	 * @exceptions
	 *
	 * @see
	 */
	public static void write(int i) {
		PrintStream out = System.out;
		out.println(i);
	}
}