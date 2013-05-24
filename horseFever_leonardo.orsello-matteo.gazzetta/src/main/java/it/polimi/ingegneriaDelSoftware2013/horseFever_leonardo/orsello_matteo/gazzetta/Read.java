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
final class Read {

	private static BufferedReader br;
	private static String _String;
	private static int _int;
	private static char _char;

	/**
	 * 
	 * {Descrizione}
	 *
	 * @return
	 * @exceptions
	 *
	 * @see
	 */
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
	 * {Descrizione}
	 *
	 * @return
	 * @exceptions
	 *
	 * @see
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
			return (-1);
		}

		return (_int);
	}

	/**
	 * 
	 * {Descrizione}
	 *
	 * @return
	 * @exceptions
	 *
	 * @see
	 */
	public static char readChar() {
		br = new BufferedReader(new InputStreamReader(System.in));

		try {
			_String = br.readLine();

			if (_String.length() > 1)
				throw new NumberFormatException();

			_char = _String.charAt(0);
		} catch (IOException e1) {
			System.out.println("errore di flusso");
		} catch (NumberFormatException e2) {
			System.out.println("Non hai inserito un carattere valido");
			return (0);
		}

		return (_char);
	}

}