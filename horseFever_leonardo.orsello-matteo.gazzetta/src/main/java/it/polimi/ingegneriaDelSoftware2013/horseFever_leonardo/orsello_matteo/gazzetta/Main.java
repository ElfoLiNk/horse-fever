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
			Write.write(partita.getListaazioni().toString());
			List<CarteAzione> lista = CarteAzione.checkLetteraCarteAzione(partita.getListaazioni());
			
			if(!lista.isEmpty())Write.write(lista.toString());
			/*partita.setScuderie();
			partita.setNumGiocatori();
			partita.setGiocatori();
			partita.setCarteAzione();
			partita.setCarteAzione();
			//Scuderia temp = new Scuderia();
			//2temp.effettuascommessa();
			partita.truccoCorsa();
			partita.movimento(2);*/
			//CarteMovimento Prima = new CarteMovimento();
			//Prima.setMovimento();
			
			//Write.write(Prima.getMovimento(5));

			// TODO Auto-generated method stubjjj

		}

}
