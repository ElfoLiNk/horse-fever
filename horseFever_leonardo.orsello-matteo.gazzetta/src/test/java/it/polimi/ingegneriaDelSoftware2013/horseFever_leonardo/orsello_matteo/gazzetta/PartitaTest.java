/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author leonardo
 *
 */
public class PartitaTest {

	/**
	 * Test method for {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#checkArrivati()}.
	 */
	@Test
	public void testCheckArrivati() {

		Partita partita = new Partita();
		for(int i = 0; i<6; i++){
			Giocatore giocatore = new Giocatore();
			giocatore.setPv(4);
			giocatore.setSoldi(12313);
			giocatore.setnomegiocatore("prova");
			partita.getarraygiocatori().add(giocatore);
		}

		String[] colori = { "NERO", "BLU", "VERDE", "ROSSO", "GIALLO",
		"BIANCO" };
		for (int i = 0; i < Parametri.MAX_SCUDERIE; i++) {

			Scuderia scuderia = new Scuderia();
			partita.getScuderie().add(scuderia);
			partita.getScuderie().get(i).setColore(colori[i]);

		}
		partita.getScuderie().get(3).setArrivato(false);

		assertFalse(partita.checkArrivati());

		partita.getarraygiocatori().clear();


	}

	/**
	 * Test method for {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#movimento()}.
	 * @throws IOException 
	 */
	@Test
	public void testMovimento() throws IOException {

		Partita partita = new Partita();

		//creo scuderie con quotazioni dalla più bassa alla più alta
		String[] colori = { "NERO", "BLU", "VERDE", "ROSSO", "GIALLO",
		"BIANCO" };

		for (int i = 0; i < Parametri.MAX_SCUDERIE; i++) {

			Scuderia scuderia = new Scuderia();
			partita.getScuderie().add(scuderia);
			partita.getScuderie().get(i).setColore(colori[i]);
			partita.getScuderie().get(i).setQuotazione(i+2);
		}

		//creo la lista delle carte movimento con una sola carta al suo interno
		String filetxt = "cartemovimentoTest";
		BufferedReader file = null;
		String linea = null;
		try {
			file = new BufferedReader(new FileReader(filetxt));
			linea = file.readLine();
			partita.getlistacartemovimento().add(linea);
		} catch (IOException e) {
			Write.write("ERRORE: Lettura carte movimento");
		}finally {

			if (file != null) {
				file.close();
			}
		}
			partita.movimento();

			int n;
			for (int i = 0; i < Parametri.MAX_SCUDERIE; i++) {
				n = partita.getScuderie().get(i).getMovimento();
				assertEquals(Parametri.MAX_SCUDERIE-i,n);
			}


		}

		/**
		 * Test method for {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#sprint()}.
		 *
		 *creo le scuderie e dopo aver invocato il metodo sprint controllo che in 2
		 *scuderie il valore sprint sia 1 */
		@Test
		public void testSprint() {
			
			Partita partita = new Partita();
			
			String[] colori = { "NERO", "BLU", "VERDE", "ROSSO", "GIALLO",
			"BIANCO" };
			
			for (int i = 0; i < Parametri.MAX_SCUDERIE; i++) {
				Scuderia scuderia = new Scuderia();
				partita.getScuderie().add(scuderia);
				partita.getScuderie().get(i).setColore(colori[i]);
			}
			
			partita.sprint();
			 
			int n = 0;
			
			for (int i = 0; i < Parametri.MAX_SCUDERIE; i++) {
				if(partita.getScuderie().get(i).getSprint() == 1)
					n++;				
			}
			
			assertEquals(2,n);
			
			partita.getarraygiocatori().clear();
						
		}

		/**
		 * Test method for {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#checkFotofinish(java.util.List)}.
		 */
		@Test
		public void testCheckFotofinish() {
			fail("Not yet implemented");
		}

		/**
		 * Test method for {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#trovaVincitore()}.
		 *
		 *inizializzo un vettore di 6 giocatori, metto il primo e l'ultimo con lo stesso 
		 *numero di punti vittoria ma l'ultimo ha più soldi.
		 *deve vincere l'ultimo di nome pietro
		 */
		@Test
		public void testTrovaVincitore() {

			Partita partita = new Partita();
			for(int i = 0; i<6; i++){
				Giocatore giocatore = new Giocatore();
				giocatore.setPv(4);
				giocatore.setSoldi(12313);
				giocatore.setnomegiocatore("prova");
				partita.getarraygiocatori().add(giocatore);
			}

			partita.getarraygiocatori().get(0).setPv(6);
			partita.getarraygiocatori().get(0).setSoldi(100);

			partita.getarraygiocatori().get(5).setnomegiocatore("pietro");
			partita.getarraygiocatori().get(5).setPv(6);
			partita.getarraygiocatori().get(5).setSoldi(300);


			String x = partita.trovaVincitore();

			Assert.assertTrue(x.contains("pietro"));	

			partita.getarraygiocatori().clear();

		}

		/**
		 * Test method for {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#pagascuderie()}.
		 */
		@Test
		public void testPagascuderie() {

			Partita partita = new Partita();
			for(int i = 0; i<6; i++){
				Giocatore giocatore = new Giocatore();
				giocatore.setPv(4);
				giocatore.setSoldi(12313);
				giocatore.setnomegiocatore("prova");
				giocatore.setScuderia("lilla");
				partita.getarraygiocatori().add(giocatore);
			}

			partita.getarraygiocatori().get(2).setScuderia("Giallo");
			partita.getarraygiocatori().get(2).setSoldi(0);
			partita.getarraygiocatori().get(3).setScuderia("Verde");
			partita.getarraygiocatori().get(3).setSoldi(0);
			partita.getarraygiocatori().get(4).setScuderia("Blu");
			partita.getarraygiocatori().get(4).setSoldi(0);

			Scuderia giallo = new Scuderia();
			giallo.setColore("Giallo");
			partita.getclassifica().add(giallo);

			Scuderia verde = new Scuderia();
			verde.setColore("Verde");
			partita.getclassifica().add(verde);

			Scuderia blu = new Scuderia();
			blu.setColore("Blu");
			partita.getclassifica().add(blu);

			partita.pagascuderie();

			assertEquals(600, partita.getarraygiocatori().get(2).getSoldi());
			assertEquals(400, partita.getarraygiocatori().get(3).getSoldi());
			assertEquals(200, partita.getarraygiocatori().get(4).getSoldi());

			partita.getarraygiocatori().clear();
			partita.getclassifica().clear();
		}

		/**
		 * Test method for {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#corsa()}.
		 */
		@Test
		public void testCorsa() {
			fail("Not yet implemented");
		}

		/**
		 * Test method for {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#checkeliminato()}.
		 *
		 *inizializza il vettore giocatori settanto il primo e l'ultimo in modo tale
		 *da essere eliminati
		 */
		@Test
		public void testCheckeliminato() {
			Partita partita = new Partita();
			for(int i = 0; i<6; i++){
				Giocatore giocatore = new Giocatore();
				giocatore.setPv(4);
				giocatore.setSoldi(12313);
				giocatore.setnomegiocatore("prova");
				partita.getarraygiocatori().add(giocatore);
			}

			partita.getarraygiocatori().get(0).setPv(1);
			partita.getarraygiocatori().get(0).setSoldi(0);

			partita.getarraygiocatori().get(5).setPv(2);
			partita.getarraygiocatori().get(5).setSoldi(100);

			partita.checkeliminato();
			assertEquals("devo avere 4", 4, partita.getarraygiocatori().size());

			partita.getarraygiocatori().clear();

		}

	}
