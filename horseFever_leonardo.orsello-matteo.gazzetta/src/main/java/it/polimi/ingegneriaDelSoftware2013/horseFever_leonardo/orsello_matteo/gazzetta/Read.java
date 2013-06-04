/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * Classe per leggere stringe, caratteri e interi da System.in
 */
final class Read {

	private Read() {
	}

	private static BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in));;
	private static String stringa = "temp";
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
		do {
			try {
				stringa = br.readLine();
				if (!stringa.equals(null)) {
					while (stringa.isEmpty()) {
						stringa = br.readLine();
					}
				}

			} catch (IOException e) {
				Write.write("Errore di flusso");
			}
		} while (stringa.equals("temp"));
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
		try {
			stringa = br.readLine();
			if (!stringa.equals(null)) {
				intero = Integer.parseInt(stringa);
			}
		} catch (IOException e1) {
			Write.write("Errore di flusso");
		} catch (NumberFormatException e2) {
			Write.write("ERRORE: Devi inserire un numero!");
			return (-1);
		}

		return (intero);
	}

	/**
	 * {Descrizione}
	 * 
	 * @return
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public static char readTipoScommessa() {
		do {
			Write.write("Che tipo di scommessa vuoi giocare?\n"
					+ "Digita v per vincente\n" + "       p per piazzato");

			try {
				stringa = br.readLine();
				if (!stringa.equals(null)) {
					if (stringa.length() > 1) {
						throw new NumberFormatException();
					}
					if (stringa.length() < 1) {
						carattere = 'e';
					} else {
						carattere = stringa.charAt(0);
					}
				} else {
					carattere = 'e';
				}
			} catch (IOException e1) {
				Write.write("errore di flusso");
				carattere = 'e';
			} catch (NumberFormatException e2) {
				Write.write("non hai inserito un carattere valido");
				carattere = 'e';
			}
			if (carattere != 'v' && carattere != 'p') {
				Write.write("hai sbagliato a digitare, riprova");
			}

		} while (carattere != 'v' && carattere != 'p');
		return carattere;
	}

	/**
	 * {Descrizione}
	 * 
	 * @return
	 * @exceptions
	 * 
	 * @see
	 */
	public static char readSecondaScommessa() {
		do {
			Write.write("Il secondo giro di scommesse è facoltativo, vuoi piazzare "
					+ "una scommessa?\ndigita:\ns per scommettere\nn per saltare la scommmessa");
			try {
				stringa = br.readLine();
				if (!stringa.equals(null)) {
					if (stringa.length() > 1) {
						throw new NumberFormatException();
					}
					if (stringa.length() < 1) {
						carattere = 'e';
					} else {
						carattere = stringa.charAt(0);
					}
				} else {
					carattere = 'e';
				}
			} catch (IOException e1) {
				Write.write("errore di flusso");
				carattere = 'e';
			} catch (NumberFormatException e2) {
				Write.write("non hai inserito un carattere valido");
				carattere = 'e';
			}
			if (carattere != 's' && carattere != 'n') {
				Write.write("Hai sbagliato a digitare, riprova");
			}
		} while (carattere != 's' && carattere != 'n');
		return carattere;
	}

	/**
	 * {Descrizione}
	 * 
	 * @return
	 * @exceptions
	 * 
	 * @see
	 */
	public static int readScuderiaScommessa(List<Scuderia> listascuderie) {
		int s;
		int n = 0;
		Write.write("\nSu che scuderia vuoi scommettere?");
		for (Scuderia scuderia : listascuderie) {
			Write.write(n + scuderia.toString());
			n++;
		}
		do {
			Write.write("Seleziona scuderia: ");
			s = Read.readInt();
			if (s > listascuderie.size() - 1) {
				Write.write("non hai digitato un valore valido, riprova");
			}
		} while (s < 0 || s > listascuderie.size() - 1);

		return s;
	}

}