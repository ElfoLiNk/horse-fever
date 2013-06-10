package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * {Descrizione}
 * 
 * 
 * 
 * @see
 */
public class PartitaTest {

	/**
	 * {Descrizione}
	 * 
	 * @throws java.lang.Exception
	 * @exceptions
	 * 
	 * @see
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#setListe()}
	 * .
	 */
	@Test
	public void testSetListe() {
		Partita partita = new Partita();
		try {
			partita.setListe();
		} catch (IOException e) {
			fail("Mancano i file");
		}
		assertNotNull("", partita.getListapersonaggi());
		assertNotNull("", partita.getListaazioni());
		assertNotNull("", partita.getListacartemovimento());
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#setSegnalini()}
	 * .
	 */
	@Test
	public void testSetSegnalini() {
		Partita partita = new Partita();
		partita.setScuderie();
		partita.setNgiocatori(2);
		partita.setSegnalini();
		assertEquals("", 1, partita.getListascuderie().get(0).getSegnalino());

	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#setScuderie()}
	 * .
	 */
	@Test
	public void testSetScuderie() {
		Partita partita = new Partita();
		partita.setScuderie();
		assertNotNull("", partita.getListascuderie());
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#setQuotazioni()}
	 * .
	 */
	@Test
	public void testSetQuotazioni() {
		Partita partita = new Partita();
		partita.setScuderie();
		assertNotNull("", partita.getScuderie().get(0).getQuotazione());
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#setCarteAzione()}
	 * .
	 */
	@Test
	public void testSetCarteAzione() {
		Partita partita = new Partita();
		try {
			partita.setListe();
		} catch (IOException e) {
			fail("Mancano i file");
		}
		// Costruisco il giocatore
		Giocatore player = new Giocatore();

		// Seleziono la carta personaggio del player
		Random rnd = new Random();
		int tempint = rnd.nextInt(partita.getListapersonaggi().size());

		// Assegno il nome della carta a interpreta del player
		player.setInterpreta(partita.getListapersonaggi().get(tempint)
				.getNome());

		// Assegno i corrispondenti soldi al player
		player.setSoldi(partita.getListapersonaggi().get(tempint).getSoldi());

		// Assegno la corrispettiva Scuderia al player
		player.setScuderia(partita.getListapersonaggi().get(tempint)
				.getQuotazione(), partita.getScuderie());

		// Aggiungo il player alla lista di giocatori
		partita.getarraygiocatori().add(player);

		// Elimino la carta giocatore dalla lista
		partita.getListapersonaggi().remove(tempint);
		partita.setCarteAzione();
		assertNotNull("", partita.getarraygiocatori().get(0).getCarteAzione());
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#randomPrimogiocatore()}
	 * .
	 */
	@Test
	public void testRandomPrimogiocatore() {
		Partita partita = new Partita();
		partita.randomPrimogiocatore();
		assertNotNull("", partita.getprimogiocatore());
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#movimento()}
	 * .
	 */
	@Test
	public void testMovimento() {
		Partita partita = new Partita();
		try {
			partita.setListe();
		} catch (IOException e) {
			fail("Mancano i file");
		}
		partita.movimento();
		for (Scuderia scuderia : partita.getListascuderie()) {
			assertNotNull("", scuderia.getMovimento());
		}

	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#sprint()}
	 * .
	 */
	@Test
	public void testSprint() {
		Partita partita = new Partita();
		partita.setScuderie();
		int sprint = 0;
		partita.sprint();
		for (Scuderia scuderia : partita.getListascuderie()) {
			if (scuderia.getSprint() == 1) {
				sprint++;
			}
		}
		assertEquals("", 2, sprint);
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#posizione()}
	 * .
	 */
	@Test
	public void testPosizione() {
		Partita partita = new Partita();
		try {
			partita.setListe();
		} catch (IOException e) {
			fail("Mancano i file");
		}
		partita.movimento();
		partita.setScuderie();
		List<Scuderia> listascuderie = new ArrayList<Scuderia>();
		listascuderie = partita.getListascuderie();

		listascuderie.get(0).setMovimento(4);
		listascuderie.get(0).setSprint(1);
		listascuderie.get(1).setMovimento(12);
		listascuderie.get(1).setSprint(1);

		partita.posizione();
		assertEquals("", 5, listascuderie.get(0).getPosizione());
		assertEquals("", 13, listascuderie.get(1).getPosizione());
		assertEquals("", true, listascuderie.get(1).isArrivato());

	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#aggiornaprimogiocatore()}
	 * .
	 */
	@Test
	public void testAggiornaprimogiocatore() {
		Partita partita = new Partita();
		List<Giocatore> giocatori = new ArrayList<Giocatore>();
		for (int i = 0; i < 6; i++) {
			Giocatore player = new Giocatore();
			giocatori.add(player);
		}
		partita.setArraygiocatori(giocatori);
		partita.aggiornaprimogiocatore();
		assertEquals("", 1, partita.getprimogiocatore());
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#classifica(java.util.List)}
	 * .
	 */
	@Test
	public void testClassifica() {
		Partita partita = new Partita();
		List<Scuderia> arrivati = new ArrayList<Scuderia>();

		for (int i = 0; i < 3; i++) {
			Scuderia scuderia = new Scuderia();
			arrivati.add(scuderia);
		}
		arrivati.get(0).setPosizione(14);
		arrivati.get(0).setColore("PRIMO");
		arrivati.get(1).setPosizione(12);
		arrivati.get(1).setColore("TERZO");
		arrivati.get(2).setPosizione(13);
		arrivati.get(2).setColore("SECONDO");
		partita.classifica(arrivati);
		assertEquals("", "PRIMO", partita.getClassifica().get(0).getColore());
		assertEquals("", "TERZO", partita.getClassifica().get(2).getColore());
		assertEquals("", "SECONDO", partita.getClassifica().get(1).getColore());

	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#checkFotofinish(java.util.List)}
	 * .
	 */
	@Test
	public void testCheckFotofinish() {
		Partita partita = new Partita();
		List<Scuderia> arrivati = new ArrayList<Scuderia>();

		for (int i = 0; i < 2; i++) {
			Scuderia scuderia = new Scuderia();
			arrivati.add(scuderia);
		}
		arrivati.get(0).setPosizione(14);
		arrivati.get(0).setColore("SECONDO");
		arrivati.get(0).setQuotazione(4);
		arrivati.get(1).setPosizione(14);
		arrivati.get(1).setQuotazione(2);
		arrivati.get(1).setColore("PRIMO");
		partita.checkFotofinish(arrivati);
		assertEquals("", "PRIMO", partita.getClassifica().get(0).getColore());
		assertEquals("", "SECONDO", partita.getClassifica().get(1).getColore());

		// TEST UGUALI QUOTAZIONI CON GIA ALCUNE SCUDERIE IN CLASSIFICA
		Partita partita2 = new Partita();
		List<Scuderia> fotofinish = new ArrayList<Scuderia>();

		for (int i = 0; i < 2; i++) {
			Scuderia scuderia = new Scuderia();
			fotofinish.add(scuderia);
		}
		List<Scuderia> classifica = new ArrayList<Scuderia>();

		for (int i = 0; i < 2; i++) {
			Scuderia scuderia = new Scuderia();
			classifica.add(scuderia);
		}
		partita2.setClassifica(classifica);
		fotofinish.get(0).setPosizione(14);
		fotofinish.get(0).setColore("SECONDO");
		fotofinish.get(0).setQuotazione(4);
		fotofinish.get(1).setPosizione(14);
		fotofinish.get(1).setQuotazione(4);
		fotofinish.get(1).setColore("PRIMO");
		partita2.checkFotofinish(fotofinish);
		assertEquals("", "SECONDO", partita2.getClassifica().get(2).getColore());
		assertEquals("", "PRIMO", partita2.getClassifica().get(3).getColore());

	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Partita#trovaVincitore()}
	 * .
	 */
	@Test
	public void testTrovaVincitore() {
		Partita partita = new Partita();
		List<Giocatore> giocatori = new ArrayList<Giocatore>();

		for (int i = 0; i < 2; i++) {
			Giocatore giocatore = new Giocatore();
			giocatori.add(giocatore);
		}
		giocatori.get(0).setPv(5);
		giocatori.get(0).setNomeGiocatore("VINCE");
		partita.setArraygiocatori(giocatori);
		assertEquals("", "VINCE", partita.trovaVincitore());

		giocatori.get(0).setPv(5);
		giocatori.get(0).setSoldi(3000);
		giocatori.get(0).setNomeGiocatore("VINCE");
		giocatori.get(1).setPv(5);
		giocatori.get(1).setSoldi(1991);
		giocatori.get(1).setNomeGiocatore("PERDE");
		partita.setArraygiocatori(giocatori);
		assertEquals("", "VINCE", partita.trovaVincitore());

	}

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
			giocatore.setNomeGiocatore("prova");
			partita.getarraygiocatori().add(giocatore);
		}

		partita.setScuderie();
		partita.getScuderie().get(3).setArrivato(false);

		assertFalse(partita.checkArrivati());

		partita.getarraygiocatori().clear();


	}

}