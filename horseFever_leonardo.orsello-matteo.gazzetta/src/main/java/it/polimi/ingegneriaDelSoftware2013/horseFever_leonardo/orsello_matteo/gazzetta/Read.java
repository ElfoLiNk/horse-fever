/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author matteo
 * 
 */
public class Read {
	private static BufferedReader br;
	private static String _String;
	private static int _int;

	public static String readString() {
		br = new BufferedReader(new InputStreamReader(System.in));

		try {
			_String = br.readLine();
		} catch (IOException e) {
			System.out.println("errore di flusso");
		}

		return (_String);
	}
	/**
	 * 
	 * @return
	 */
	public static int readInt() {
		br = new BufferedReader(new InputStreamReader(System.in));

		try {
			_String = br.readLine();
			_int = Integer.parseInt(_String);
		} catch (IOException e1) {
			System.out.println("Errore di flusso");
		} catch (NumberFormatException e2) {
			System.out.println("ERRORE: Devi inserire un numero!");
			return (0);
		}

		return (_int);
	}
}