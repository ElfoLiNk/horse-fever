package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import static org.junit.Assert.*;

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
	List<CarteAzione> carte = new ArrayList<CarteAzione>();
	List<Scuderia> scuderie = new ArrayList<Scuderia>();

	@Before
	public void beforeCrealistaazioni() {
		Partita partita = new Partita();
		partita.setScuderie();
		scuderie = partita.getScuderie();
		CarteAzione mazzo = new CarteAzione();
		carte = mazzo.crealistaazioni();
		// Ogni scuderia ha tutte le carte del mazzo
		for (Scuderia scuderia : scuderie) {
			for (CarteAzione carta : carte) {
				scuderia.setCarteAzione(carta);
			}
		}
		mazzo.carteAzionePartenza(scuderie);
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.CarteAzione#crealistaazioni()}
	 * .
	 */
	@Test
	public void testCrealistaazioni() {
		assertNotNull("La lista delle carte non deve essere vuota", carte);
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.CarteAzione#carteAzionePartenza(java.util.List)}
	 * .
	 */
	@Test
	public void testCarteAzionePartenza() {
		for (Scuderia scuderia : scuderie) {
			carte = scuderia.getCarteAzione();
			for (CarteAzione carta : carte) {
				if (carta.getAgisce().equals("Partenza")) {
					if (carta.getEffetto() == 0) {
						// Verifico l'effetto
						assertEquals("", 0, scuderia.getMovimento());
					}
					if (carta.getEffetto() == 1) {
						// Verifico l'effetto
						assertEquals("", 1,
								scuderia.getMovimento() + carta.getEffetto());

					}
					if (carta.getEffetto() > 0) {
						// Verifico l'effetto
						assertEquals("", carta.getEffetto(),
								scuderia.getMovimento() + carta.getEffetto());
					}
				}
			}

		}

	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.CarteAzione#carteAzioneMovimento(java.util.List)}
	 * .
	 */
	@Test
	public void testCarteAzioneMovimento() {
		for (Scuderia scuderia : scuderie) {
			carte = scuderia.getCarteAzione();
			for (CarteAzione carta : carte) {
				if (carta.getAgisce().equals("Movimento")) {
					if (carta.getEffetto() == 4 && scuderia.isUltimo()) {
						// Verifico l'effetto
						assertEquals("", 4, scuderia.getMovimento());
					}
					if (carta.getEffetto() == 0 && scuderia.isPrimo()) {
						// Verifico l'effetto
						assertEquals("", 0, scuderia.getMovimento());

					}
				}
			}

		}
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.CarteAzione#carteAzioneSprint(java.util.List)}
	 * .
	 */
	@Test
	public void testCarteAzioneSprint() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.CarteAzione#carteAzioneFotofinish(java.util.List)}
	 * .
	 */
	@Test
	public void testCarteAzioneFotofinish() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.CarteAzione#carteAzioneQuotazione(java.util.List)}
	 * .
	 */
	@Test
	public void testCarteAzioneQuotazione() {
		fail("Not yet implemented");
	}

}
