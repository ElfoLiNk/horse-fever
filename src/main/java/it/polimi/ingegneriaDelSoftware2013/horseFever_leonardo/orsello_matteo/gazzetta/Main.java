/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.IOException;

/**
 * Main del gioco
 */
public final class Main {
	private Main() {
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Write.write(" ------------------------------------------------");
		Write.write(" |                Horse Fever                   |");
		Write.write(" ------------------------------------------------");
		Audio audio = new Audio("soundtrack.mp3");
		audio.start();
		Partita partita = new Partita();
		partita.setListe();
		partita.setScuderie();
		partita.setQuotazioni();
		partita.setNumGiocatori();
		partita.setGiocatori();
		partita.randomPrimogiocatore();
		partita.turno();

	}

}
