/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.util.List;




/**
 * @author amministratore
 *
 */
public class Main {


		/**
		 * @param args
		 */
		public static void main(String[] args) {
			Partita partita = new Partita();
			partita.setListe();
			partita.setScuderie();
			partita.setNumGiocatori();
			partita.setGiocatori();
			partita.setCarteAzione();
			partita.setCarteAzione();
			partita.randomPrimogiocatore();
			partita.scommessa();
			partita.truccoCorsa();
			partita.scommessa();
			partita.corsa();
			//partita.pagascomessa();
			//partita.aggiornaquotazioni();			

		}

}
