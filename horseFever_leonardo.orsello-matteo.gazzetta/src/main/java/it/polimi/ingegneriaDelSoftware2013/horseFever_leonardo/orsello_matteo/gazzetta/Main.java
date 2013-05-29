/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;


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
			partita.setQuotazioni();
			partita.setNumGiocatori();
			partita.setGiocatori();
			partita.randomPrimogiocatore();
			partita.turno();

		}

}
