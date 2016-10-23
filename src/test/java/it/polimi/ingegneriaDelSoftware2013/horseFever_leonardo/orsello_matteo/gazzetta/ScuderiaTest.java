package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Scuderia
 * 
 * 
 * 
 * @see
 */
public class ScuderiaTest {

	List<Scuderia> scuderie = new ArrayList<Scuderia>();

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

		Partita partita = new Partita();
		partita.setScuderie();
		partita.setQuotazioni();
		scuderie = partita.getScuderie();
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Scuderia#pagascommessa()}
	 * .
	 */
	@Test
	public void testPagascommessa() {
		List<Scommessa> scommesse = new ArrayList<Scommessa>();
		List<Giocatore> giocatori = new ArrayList<Giocatore>();
		char vincente = "v".charAt(0);
		char piazzata = "p".charAt(0);

		// Scommessa vincente
		Scommessa scommessa = new Scommessa();
		scommessa.setSoldi(1000);
		scommessa.setTiposcommessa(vincente);
		scommessa.setNomeGiocatore("vincente");
		scommesse.add(scommessa);

		// Giocatore vincente
		Giocatore player = new Giocatore();
		player.setSoldi(0);
		player.setNomeGiocatore("vincente");
		player.setPv(1);
		giocatori.add(player);
		Partita.setArraygiocatori(giocatori);

		// Scuderia vincente
		scuderie.get(0).setClassifica(1);
		scuderie.get(0).setScommessa(scommesse);
		scuderie.get(0).pagascommessa();

		// Verifico soldi * quotazione
		assertEquals("", 1000 * scuderie.get(0).getQuotazione(), Partita
				.getarraygiocatori().get(0).getSoldi());
		// Vince 3 pv
		assertEquals("", 4, Partita.getarraygiocatori().get(0).getPv());

		// Scommessa piazzata
		Scommessa Spiazzata = new Scommessa();
		Spiazzata.setSoldi(1000);
		Spiazzata.setTiposcommessa(piazzata);
		Spiazzata.setNomeGiocatore("piazzata");
		scommesse.add(Spiazzata);

		// Giocatore piazzato
		Giocatore piazzato = new Giocatore();
		piazzato.setSoldi(0);
		piazzato.setNomeGiocatore("piazzata");
		piazzato.setPv(1);
		giocatori.add(piazzato);
		Partita.setArraygiocatori(giocatori);

		// Scuderia piazzata
		scuderie.get(1).setClassifica(3);
		scuderie.get(1).setScommessa(scommesse);
		scuderie.get(1).pagascommessa();

		// Verifico soldi * 2
		assertEquals("", 1000 * 2, Partita.getarraygiocatori().get(1)
				.getSoldi());
		// Vince 1 pv
		assertEquals("", 2, Partita.getarraygiocatori().get(1).getPv());

	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Scuderia#aggiornaQuotazioni(int)}
	 * .
	 */
	@Test
	public void testAggiornaQuotazioni() {
		// Test casi limite 1:2 e 1:7 che non superi le quotazioni
		Scuderia scuderia = new Scuderia();

		scuderia.setQuotazione(2);
		scuderia.aggiornaQuotazioni(1);
		// Verifico
		assertEquals("", 2, scuderia.getQuotazione());

		scuderia.setQuotazione(7);
		scuderia.aggiornaQuotazioni(6);
		// Verifico
		assertEquals("", 7, scuderia.getQuotazione());

	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Scuderia#removeCartaAzioneByID(int)}
	 * .
	 */
	@Test
	public void testRemoveCartaAzioneByID() {
		Scuderia scuderia = new Scuderia();
		CarteAzione carta = new CarteAzione();
		// Carta ID 4
		carta.setId(4);
		// Salvo la size della lista prima di inserire la carta
		int size = scuderia.getCarteAzione().size();
		scuderia.setCarteAzione(carta);
		// Rimuovo Carta ID 4
		scuderia.removeCartaAzioneByID(4);
		// Verifico che la carta sia rimossa
		assertEquals("", size, scuderia.getCarteAzione().size());
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Scuderia#removeCartaAzione(int)}
	 * .
	 */
	@Test
	public void testRemoveCartaAzione() {
		Scuderia scuderia = new Scuderia();
		CarteAzione carta = new CarteAzione();
		// Salvo la size della lista prima di inserire la carta
		int size = scuderia.getCarteAzione().size();
		scuderia.setCarteAzione(carta);
		// Rimuovo carta posizione 0
		scuderia.removeCartaAzione(0);
		// Verifico che la carta sia rimossa
		assertEquals("", size, scuderia.getCarteAzione().size());
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Scuderia#aggiornaSegnalino(int)}
	 * .
	 */
	@Test
	public void testAggiornaSegnalino() {
		Scuderia scuderia = new Scuderia();
		scuderia.setSegnalino(0);
		scuderia.aggiornaSegnalino(2);
		assertEquals("", 2, scuderia.getSegnalino());
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Scuderia#checkCarteAzione()}
	 * .
	 */
	@Test
	public void testCheckCarteAzione() {
		Scuderia scuderia = new Scuderia();
		// Carta ID 15
		CarteAzione carta = new CarteAzione();
		carta.setId(15);

		// Carta ID 8
		CarteAzione carta8 = new CarteAzione();
		carta8.setId(8);

		scuderia.setCarteAzione(carta);
		scuderia.setCarteAzione(carta8);

		scuderia.checkCarteAzione();
		// Verifico che la carta sia rimossa
		assertEquals("", 0, scuderia.getCarteAzione().size());
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Scuderia#checkLetteraCarteAzione()}
	 * .
	 */
	@Test
	public void testCheckLetteraCarteAzione() {
		Scuderia scuderia = new Scuderia();
		// Carta Positiva Lettera A
		CarteAzione cartapositiva = new CarteAzione();
		cartapositiva.setLettera("A");

		// Carta Negativa Lettera A
		CarteAzione cartanegativa = new CarteAzione();
		cartanegativa.setLettera("A");

		scuderia.setCarteAzione(cartapositiva);
		scuderia.setCarteAzione(cartanegativa);

		scuderia.checkLetteraCarteAzione();
		// Verifico che le carte siano rimosse
		assertEquals("", 0, scuderia.getCarteAzione().size());
	}

	/**
	 * Test method for
	 * {@link it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta.Scuderia#carteAzioneTraguardo()}
	 * .
	 */
	@Test
	public void testCarteAzioneTraguardo() {
		// Testo la carta che fa avanzare la scuderia dopo aver raggiunto il
		// traguardo
		CarteAzione carta = new CarteAzione();
		carta.setAgisce("Traguardo");
		carta.setEffetto(2);
		Scuderia scuderia = new Scuderia();
		scuderia.setPosizione(12);
		scuderia.setCarteAzione(carta);
		scuderia.carteAzioneTraguardo();
		assertEquals("", 14, scuderia.getPosizione());

		// Testo la carta che fa fermare al traguardo
		CarteAzione cartafermo = new CarteAzione();
		cartafermo.setAgisce("Traguardo");
		cartafermo.setEffetto(0);
		scuderia.setPosizione(14);
		scuderia.setCarteAzione(cartafermo);
		scuderia.carteAzioneTraguardo();
		assertEquals("", 12, scuderia.getPosizione());

	}

}
