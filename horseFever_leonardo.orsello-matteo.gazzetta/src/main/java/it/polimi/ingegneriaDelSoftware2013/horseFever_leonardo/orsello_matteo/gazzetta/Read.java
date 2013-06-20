/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Classe per leggere stringe, caratteri e interi da System.in
 */
final class Read {

	private Read() {
	}

	private static BufferedReader br = new BufferedReader(
			new InputStreamReader(System.in, Charset.defaultCharset()));;
	private static String stringa = "temp";
	private static int intero;
	private static char carattere = "e".charAt(0);

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
			} catch (IOException e) {
				Write.write("Errore di flusso");
			} catch(NullPointerException e){
				Write.write("Errore NULL");
			}
		} while (stringa.equals(""));
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
	 * Chiede al giocatore che tipo di scommessa vuole effettuare
	 * 
	 * @return carattere: v per vincente p per piazzato
	 * @exceptions
	 * 
	 * @see
	 */
	public static char readTipoScommessa() {
		do {
			Write.write("Che tipo di scommessa vuoi giocare?\n"
					+ "Digita v per vincente\n" + "       p per piazzato");

			stringa = readString();
			if (stringa != null) {
				if (stringa.length() > 1) {
					carattere = 'e';
				} else {
					carattere = stringa.charAt(0);
				}
			}
			if (carattere != 'v' && carattere != 'p') {
				Write.write("hai sbagliato a digitare, riprova");
			}

		} while (carattere != 'v' && carattere != 'p');
		return carattere;
	}

	/**
	 * Chiede al giocatore se vuole effettuare una seconda scommessa
	 * 
	 * @return carattere: s effettua la seconda scommessa n non effettua la
	 *         scommessa
	 * @exceptions
	 * 
	 * @see
	 */
	public static char readSecondaScommessa() {
		do {
			Write.write("\nIl secondo giro di scommesse è facoltativo, vuoi piazzare "
					+ "una scommessa?\ndigita:\ns per scommettere\nn per saltare la scommmessa");
			stringa = readString();
			if (stringa.length() > 1) {
				carattere = 'e';
			} else {
				carattere = stringa.charAt(0);
			}
			if (carattere != 's' && carattere != 'n') {
				Write.write("Hai sbagliato a digitare, riprova");
			}
		} while (carattere != 's' && carattere != 'n');
		return carattere;
	}

	/**
	 * Chiede al giocatore su che scuderia vuole scommettere
	 * 
	 * @param listascuderie la lista delle scuderie della partita
	 * @return s l'indice della scuderia scelta nella lista
	 * @see Scuderie
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
	/**
	 * 
	 * Chiede al giocatore quale carte azione vuole giocare
	 *
	 * @param carteplayer le carte del giocatore attuale
	 * @return k la carta scelta
	 * @see CarteAzione
	 */
	public static int readCartaAzione(List<CarteAzione> carteplayer) {
		// Print carte azione del player
		for (int j = 0; j < carteplayer.size(); j++) {
			Write.write(j + ") " + carteplayer.get(j).toString());
		}
		// Scelta Carta
		int k = -1;
		do {
			Write.write("Seleziona la Carta Azione da giocare: ");
			k = Read.readInt();
		} while (k < 0 || k > carteplayer.size() - 1);
		return k;
	}

}