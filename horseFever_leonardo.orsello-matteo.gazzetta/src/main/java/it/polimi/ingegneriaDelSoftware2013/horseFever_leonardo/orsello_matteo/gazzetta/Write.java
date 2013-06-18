/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.PrintStream;
import java.util.List;

/**
 * Classe che scrive stringhe e interi su System.out
 * 
 */
final class Write {

	private Write() {
	}

	/**
	 * 
	 * Scrive una stringa su una nuova linea su System.out 
	 * 
	 * @param Riceve
	 *            s la stringa da scrivere
	 * @see
	 */
	public static void write(String s) {
		PrintStream out = System.out;
		out.println(s);
	}

	/**
	 * 
	 * Layout del titolo della schermata
	 * 
	 * @param s
	 *            Titolo della schermata
	 * @see
	 */
	public static void header(String s) {
		Write.write("------------------------------------------------");
		Write.write("|                  " + s + "                    |");
		Write.write("------------------------------------------------");
	}

	/**
	 * 
	 * Scrive un intero su una nuova linea su System.out
	 * 
	 * @param Riceve
	 *            i l'intero da scrivere
	 * @see
	 */
	public static void write(int i) {
		PrintStream out = System.out;
		out.println(i);
	}

	/**
	 * Pulisco la console
	 */
	public static void clear() {
		for (int i = 0; i < Parametri.CLEAR; i++) {
			Write.write("\n");
		}
	}

	/**
	 * Stampa la classifica di arrivo delle scuderie
	 * 
	 * @param classifica
	 *            lista delle scuderie secondo l'ordine di arrivo
	 * @see Scuderia
	 */
	public static void printClassifica(List<Scuderia> classifica) {
		Write.write("\nCLASSIFICA\n");
		for (Scuderia scuderia : classifica) {
			Write.write(scuderia.getColore() + "\t" + scuderia.getClassifica());
		}
	}

	/**
	 * 
	 * Stampa le quotazioni delle scuderie
	 * 
	 * @param scuderie
	 *            la lista delle scuderie
	 * @see Scuderia
	 */
	public static void printQuotazioni(List<Scuderia> scuderie) {
		Write.write("\nQUOTAZIONI AGGIORNATE\n");
		for (Scuderia scuderia : scuderie) {
			Write.write(scuderia.getColore() + "\tQuotazione: "
					+ scuderia.getQuotazione());
		}
	}

	/**
	 * Stampo la corsa le scuderie sono #
	 * 
	 * @param la
	 *            lista delle scuderie
	 * @see Scuderie
	 */
	public static void printCorsa(List<Scuderia> scuderie) {
		Write.clear();
		for (int i = 0; i < scuderie.size(); i++) {
			// Stampo il colore della scuderia
			Write.simple(scuderie.get(i).getColore()+"\t");
			// Stampo tot # in base alla posizione della scuderia
			for (int j = 0; j < scuderie.get(i).getPosizione(); j++) {
				Write.simple("#");
			}
			Write.simple(" " + scuderie.get(i).getPosizione());
			// Stampo la classifica di arrivo della scuderia
			if (scuderie.get(i).getClassifica() > 0) {
				Write.simple(" " + scuderie.get(i).getClassifica() + "°");
			}
			Write.write("");
		}
		try {
			// Sleep per animazione corsa
			Thread.sleep(Parametri.SLEEP);
		} catch (InterruptedException e) {
			write("Errore nella sleep");
		}
	}
	
	/**
	 * 
	 * Scrive una stringa sulla stessa linea su System.out 
	 * 
	 * @param Riceve
	 *            string la stringa da scrivere
	 * @see
	 */
	private static void simple(String string) {
		PrintStream out = System.out;
		out.print(string);
	}
}