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
			if (stringa.length() > 1) {
				throw new NumberFormatException();
			}
			carattere = stringa.charAt(0);
		} catch (IOException e1) {
			Write.write("Errore di flusso");
		} catch (NumberFormatException e2) {
			Write.write("ERRORE: Non hai inserito un carattere valido!");
			return (0);
		}

		return (carattere);
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
		String stringtemp;
		char chartemp;
		do {
			Write.write("Che tipo di scommessa vuoi giocare?\n"
					+ "Digita v per vincente\n" + "       p per piazzato");

			br = new BufferedReader(new InputStreamReader(System.in));

			try {
				stringtemp = br.readLine();
				if (stringtemp.length() > 1) {
					throw new NumberFormatException();
				}
				{
					if (stringtemp.length() < 1) {
						chartemp = 'e';
					} else {
						chartemp = stringtemp.charAt(0);
					}
				}
			} catch (IOException e1) {
				Write.write("errore di flusso");
				chartemp = 'e';
			} catch (NumberFormatException e2) {
				Write.write("non hai inserito un carattere valido");
				chartemp = 'e';
			}
			if (chartemp != 'v' && chartemp != 'p') {
				Write.write("hai sbagliato a digitare, riprova");
			}

		} while (chartemp != 'v' && chartemp != 'p');
		return chartemp;
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
		String stringtemp;
		char chartemp;
		do {
			Write.write("Il secondo giro di scommesse è facoltativo, vuoi piazzare "
					+ "una scommessa?\ndigita:\ns per scommettere\nn per saltare la scommmessa");
			try {
				stringtemp = br.readLine();
				if (stringtemp.length() > 1) {
					// Write.write("hai sbagliato a digitare");
					throw new NumberFormatException();
				}
				if (stringtemp.length() < 1) {
					chartemp = 'e';
				} else {
					chartemp = stringtemp.charAt(0);
				}
			} catch (IOException e1) {
				Write.write("errore di flusso");
				chartemp = 'e';
			} catch (NumberFormatException e2) {
				Write.write("non hai inserito un carattere valido");
				chartemp = 'e';
			}
			if (chartemp != 's' && chartemp != 'n') {
				Write.write("Hai sbagliato a digitare, riprova");
			}
		} while (chartemp != 's' && chartemp != 'n');
		return chartemp;
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
		int n=0;
		Write.write("\nSu che scuderia vuoi scommettere?");
		for (Scuderia scuderia: listascuderie) {
			Write.write(n + " ) "
					+ scuderia.getColore()
					+ "     Scommesse: "
					+ scuderia.getscommessa().size()+ "    Quotazione: 1:"+ scuderia.getQuotazione());
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