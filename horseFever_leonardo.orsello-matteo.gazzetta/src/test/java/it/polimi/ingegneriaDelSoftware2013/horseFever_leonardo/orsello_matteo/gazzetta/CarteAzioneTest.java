package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test per la classe CarteAzione
 * 
 * 
 * 
 * @see CarteAzione
 */
public class CarteAzioneTest {

	List<Scuderia> scuderie = new ArrayList<Scuderia>();

	@Before
	public void beforeCrealistaazioni() {
		Partita partita = new Partita();
		partita.setScuderie();
		partita.setQuotazioni();
		scuderie = partita.getScuderie();

	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.CarteAzione#crealistaazioni()}
	 * .
	 */
	@Test
	public void testCrealistaazioni() {
		List<CarteAzione> carte = new ArrayList<CarteAzione>();
		CarteAzione mazzo = new CarteAzione();
		carte = mazzo.crealistaazioni();
		assertNotNull("La lista delle carte non deve essere vuota", carte);
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.CarteAzione#carteAzionePartenza(java.util.List)}
	 * .
	 */
	@Test
	public void testCarteAzionePartenza() {
		// Testo la carta che fa muovere la scuderia una casella in meno dello
		// spostamento della carta movimento
		CarteAzione carta = new CarteAzione();
		carta.setAgisce("Partenza");
		carta.setEffetto(-1);
		for (Scuderia scuderia : scuderie) {
			scuderia.setCarteAzione(carta);
			scuderia.setMovimento(4);
		}
		carta.carteAzionePartenza(scuderie);
		for (Scuderia scuderia : scuderie) {
			// Verifico l'effetto
			assertEquals("", 3, scuderia.getMovimento());
		}

		// Testo la carta che fa muovere la scuderia una casella in piu dello
		// spostamento della carta movimento
		CarteAzione carta1 = new CarteAzione();
		carta1.setAgisce("Partenza");
		carta1.setEffetto(1);
		for (Scuderia scuderia : scuderie) {
			scuderia.setCarteAzione(carta1);
			scuderia.setMovimento(4);
		}
		carta1.carteAzionePartenza(scuderie);
		for (Scuderia scuderia : scuderie) {
			// Verifico l'effetto
			assertEquals("", 5, scuderia.getMovimento());
		}

		// Testo la carta che fa muovere la scuderia quanto indicato
		// nell'effetto anziche quello scritto sulla carta movimento
		CarteAzione carta4 = new CarteAzione();
		carta4.setAgisce("Partenza");
		carta4.setEffetto(4);
		for (Scuderia scuderia : scuderie) {
			scuderia.setCarteAzione(carta4);
			scuderia.setMovimento(2);
		}
		carta4.carteAzionePartenza(scuderie);
		for (Scuderia scuderia : scuderie) {
			// Verifico l'effetto
			assertEquals("", 4, scuderia.getMovimento());
		}

		// Testo la carta che non fa muovere la scuderia alla partenza
		CarteAzione carta0 = new CarteAzione();
		carta0.setAgisce("Partenza");
		carta0.setEffetto(0);
		for (Scuderia scuderia : scuderie) {
			scuderia.setCarteAzione(carta0);
			scuderia.setMovimento(4);
		}
		carta0.carteAzionePartenza(scuderie);
		for (Scuderia scuderia : scuderie) {
			// Verifico l'effetto
			assertEquals("", 0, scuderia.getMovimento());
		}

	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.CarteAzione#carteAzioneMovimento(java.util.List)}
	 * .
	 */
	@Test
	public void testCarteAzioneMovimento() {
		// Testo la carta che fa muovere la scuderia di 4 se il cavallo è ultimo
		CarteAzione carta4 = new CarteAzione();
		carta4.setAgisce("Movimento");
		carta4.setEffetto(4);
		for (Scuderia scuderia : scuderie) {
			scuderia.setCarteAzione(carta4);
			scuderia.setUltimo(true);
		}
		carta4.carteAzioneMovimento(scuderie);
		for (Scuderia scuderia : scuderie) {
			// Verifico l'effetto
			assertEquals("", 4, scuderia.getMovimento());
		}

		// Testo la carta che fa muovere la scuderia di 0 se il cavallo è primo
		CarteAzione carta0 = new CarteAzione();
		carta0.setAgisce("Movimento");
		carta0.setEffetto(0);
		for (Scuderia scuderia : scuderie) {
			scuderia.setCarteAzione(carta0);
			scuderia.setPrimo(true);
		}
		carta0.carteAzioneMovimento(scuderie);
		for (Scuderia scuderia : scuderie) {
			// Verifico l'effetto
			assertEquals("", 0, scuderia.getMovimento());
		}

	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.CarteAzione#carteAzioneSprint(java.util.List)}
	 * .
	 */
	@Test
	public void testCarteAzioneSprint() {
		// Testo la carta che fa sprintare una casella in meno
		CarteAzione carta = new CarteAzione();
		carta.setAgisce("Sprint");
		carta.setEffetto(-1);
		for (Scuderia scuderia : scuderie) {
			scuderia.setCarteAzione(carta);
			scuderia.setSprint(2);
		}
		carta.carteAzioneSprint(scuderie);
		for (Scuderia scuderia : scuderie) {
			// Verifico l'effetto
			assertEquals("", 1, scuderia.getSprint());
		}

		// Testo la carta che fa sprintare il cavallo di una casella in piu
		CarteAzione carta1 = new CarteAzione();
		carta1.setAgisce("Sprint");
		carta1.setEffetto(1);
		for (Scuderia scuderia : scuderie) {
			scuderia.setCarteAzione(carta1);
			scuderia.setSprint(2);
		}
		carta1.carteAzioneSprint(scuderie);
		for (Scuderia scuderia : scuderie) {
			// Verifico l'effetto
			assertEquals("", 2, scuderia.getSprint());
		}

		// Testo la carta che fa sprintare il cavallo di 2 caselle
		CarteAzione carta2 = new CarteAzione();
		carta2.setAgisce("Sprint");
		carta2.setEffetto(2);
		for (Scuderia scuderia : scuderie) {
			scuderia.setCarteAzione(carta2);
			scuderia.setSprint(0);
		}
		carta2.carteAzioneSprint(scuderie);
		for (Scuderia scuderia : scuderie) {
			// Verifico l'effetto
			assertEquals("", 2, scuderia.getSprint());
		}

		// Testo la carta che non fa sprintare il cavallo
		CarteAzione carta0 = new CarteAzione();
		carta0.setAgisce("Sprint");
		carta0.setEffetto(0);
		for (Scuderia scuderia : scuderie) {
			scuderia.setCarteAzione(carta0);
			scuderia.setSprint(2);
		}
		carta0.carteAzioneSprint(scuderie);
		for (Scuderia scuderia : scuderie) {
			// Verifico l'effetto
			assertEquals("", 0, scuderia.getSprint());
		}

	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.CarteAzione#carteAzioneFotofinish(java.util.List)}
	 * .
	 */
	@Test
	public void testCarteAzioneFotofinish() {
		// Testo la carta che fa perdere il fotofinish
		CarteAzione carta = new CarteAzione();
		carta.setAgisce("Fotofinish");
		carta.setEffetto(0);
		for (Scuderia scuderia : scuderie) {
			scuderia.setCarteAzione(carta);
		}
		carta.carteAzioneFotofinish(scuderie);
		for (Scuderia scuderia : scuderie) {
			// Verifico l'effetto
			assertEquals("", 0, scuderia.getFotofinish());
		}
		// Testo la carta che fa vincere il fotofinish
		CarteAzione carta1 = new CarteAzione();
		carta1.setAgisce("Fotofinish");
		carta1.setEffetto(1);
		for (Scuderia scuderia : scuderie) {
			scuderia.setCarteAzione(carta1);
		}
		carta1.carteAzioneFotofinish(scuderie);
		for (Scuderia scuderia : scuderie) {
			// Verifico l'effetto
			assertEquals("", 1, scuderia.getFotofinish());
		}

	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.CarteAzione#carteAzioneQuotazione(java.util.List)}
	 * .
	 */
	@Test
	public void testCarteAzioneQuotazione() {
		for (Scuderia scuderia : scuderie) {
			// Controllo una scuderia alla volta
			List<Scuderia> lista = new ArrayList<Scuderia>();
			lista.add(scuderia);
			CarteAzione mazzo = new CarteAzione();
			// Applico tutte le carte alla scuderia
			mazzo.carteAzioneQuotazione(lista);

			// Le quotazioni devono essere uguali
			assertEquals("", lista.get(0).getQuotazione(), lista.get(0)
					.getQuotazione());

			lista.remove(scuderia);
		}
	}

}
