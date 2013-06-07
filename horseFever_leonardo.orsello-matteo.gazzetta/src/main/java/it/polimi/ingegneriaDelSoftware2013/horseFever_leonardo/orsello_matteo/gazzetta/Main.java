/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.IOException;


/**
 * @author amministratore
 *
 */
public class Main {


		/**
		 * @param args
		 * @throws IOException 
		 */
		public static void main(String[] args) throws IOException {
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
