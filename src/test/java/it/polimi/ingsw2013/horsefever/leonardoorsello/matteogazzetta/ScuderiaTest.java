package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test Scuderia
 */
public class ScuderiaTest {

    /**
     * Test method for
     * {@link Scuderia#pagascommessa()}
     * .
     */
    @Test
    public void testPagascommessa() {
        final Partita partita = new Partita();
        partita.setScuderie();
        partita.setQuotazioni();
        final List<Scuderia> scuderie = partita.getScuderie();
        final List<Scommessa> scommesse = new ArrayList<>();
        final List<Giocatore> giocatori = new ArrayList<>();
        final char vincente = "v".charAt(0);
        char piazzata = "p".charAt(0);

        // Scommessa vincente
        final Scommessa scommessa = new Scommessa("vincente");
        scommessa.setSoldi(1000);
        scommessa.setTiposcommessa(vincente);
        scommesse.add(scommessa);

        // Giocatore vincente
        final Giocatore player = new Giocatore();
        player.setSoldi(0);
        player.setNomeGiocatore("vincente");
        player.setPv(1);
        giocatori.add(player);
        partita.setArraygiocatori(giocatori);

        // Scuderia vincente
        scuderie.get(0).setClassifica(1);
        scuderie.get(0).setScommessa(scommesse);
        scuderie.get(0).pagascommessa();

        // Verifico soldi * quotazione
        assertEquals("", 1000 * scuderie.get(0).getQuotazione(), partita.getarraygiocatori().get(0).getSoldi());
        // Vince 3 pv
        assertEquals("", 4, partita.getarraygiocatori().get(0).getPv());

        // Scommessa piazzata
        final Scommessa Spiazzata = new Scommessa("piazzata");
        Spiazzata.setSoldi(1000);
        Spiazzata.setTiposcommessa(piazzata);
        scommesse.add(Spiazzata);

        // Giocatore piazzato
        final Giocatore piazzato = new Giocatore();
        piazzato.setSoldi(0);
        piazzato.setNomeGiocatore("piazzata");
        piazzato.setPv(1);
        giocatori.add(piazzato);
        partita.setArraygiocatori(giocatori);

        // Scuderia piazzata
        scuderie.get(1).setClassifica(3);
        scuderie.get(1).setScommessa(scommesse);
        scuderie.get(1).pagascommessa();

        // Verifico soldi * 2
        assertEquals("", 1000 * 2, partita.getarraygiocatori().get(1)
                .getSoldi());
        // Vince 1 pv
        assertEquals("", 2, partita.getarraygiocatori().get(1).getPv());

    }

    /**
     * Test method for
     * {@link Scuderia#aggiornaQuotazioni(int)}
     * .
     */
    @Test
    public void testAggiornaQuotazioni() {
        Partita partita = new Partita();
        // Test casi limite 1:2 e 1:7 che non superi le quotazioni
        final Scuderia scuderia = new Scuderia(partita);

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
     * {@link Scuderia#removeCartaAzioneByID(int)}
     * .
     */
    @Test
    public void testRemoveCartaAzioneByID() {
        Partita partita = new Partita();
        Scuderia scuderia = new Scuderia(partita);
        final CarteAzione carta = new CarteAzione();
        // Carta ID 4
        carta.setId(4);
        // Salvo la size della lista prima di inserire la carta
        final int size = scuderia.getCarteAzione().size();
        scuderia.setCarteAzione(carta);
        // Rimuovo Carta ID 4
        scuderia.removeCartaAzioneByID(4);
        // Verifico che la carta sia rimossa
        assertEquals("", size, scuderia.getCarteAzione().size());
    }

    /**
     * Test method for
     * {@link Scuderia#removeCartaAzione(int)}
     * .
     */
    @Test
    public void testRemoveCartaAzione() {
        Partita partita = new Partita();
        Scuderia scuderia = new Scuderia(partita);
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
     * {@link Scuderia#aggiornaSegnalino(int)}
     * .
     */
    @Test
    public void testAggiornaSegnalino() {
        Partita partita = new Partita();
        Scuderia scuderia = new Scuderia(partita);
        scuderia.setSegnalino(0);
        scuderia.aggiornaSegnalino(2);
        assertEquals("", 2, scuderia.getSegnalino());
    }

    /**
     * Test method for
     * {@link Scuderia#checkCarteAzione()}
     * .
     */
    @Test
    public void testCheckCarteAzione() {
        Partita partita = new Partita();
        Scuderia scuderia = new Scuderia(partita);
        // Carta ID 15
        CarteAzione carta = new CarteAzione();
        carta.setId(15);

        // Carta ID 8
        final CarteAzione carta8 = new CarteAzione();
        carta8.setId(8);

        scuderia.setCarteAzione(carta);
        scuderia.setCarteAzione(carta8);

        scuderia.checkCarteAzione();
        // Verifico che la carta sia rimossa
        assertEquals("", 0, scuderia.getCarteAzione().size());
    }

    /**
     * Test method for
     * {@link Scuderia#checkLetteraCarteAzione()}
     * .
     */
    @Test
    public void testCheckLetteraCarteAzione() {
        Partita partita = new Partita();
        Scuderia scuderia = new Scuderia(partita);
        // Carta Positiva Lettera A
        final CarteAzione cartapositiva = new CarteAzione();
        cartapositiva.setLettera("A");

        // Carta Negativa Lettera A
        final CarteAzione cartanegativa = new CarteAzione();
        cartanegativa.setLettera("A");

        scuderia.setCarteAzione(cartapositiva);
        scuderia.setCarteAzione(cartanegativa);

        scuderia.checkLetteraCarteAzione();
        // Verifico che le carte siano rimosse
        assertEquals("", 0, scuderia.getCarteAzione().size());
    }

    /**
     * Test method for
     * {@link Scuderia#carteAzioneTraguardo()}
     * .
     */
    @Test
    public void testCarteAzioneTraguardo() {
        Partita partita = new Partita();
        // Testo la carta che fa avanzare la scuderia dopo aver raggiunto il
        // traguardo
        CarteAzione carta = new CarteAzione();
        carta.setAgisce("Traguardo");
        carta.setEffetto(2);
        Scuderia scuderia = new Scuderia(partita);
        scuderia.setPosizione(12);
        scuderia.setCarteAzione(carta);
        scuderia.carteAzioneTraguardo();
        assertEquals("", 14, scuderia.getPosizione());

        // Testo la carta che fa fermare al traguardo
        final CarteAzione cartafermo = new CarteAzione();
        cartafermo.setAgisce("Traguardo");
        cartafermo.setEffetto(0);
        scuderia.setPosizione(14);
        scuderia.setCarteAzione(cartafermo);
        scuderia.carteAzioneTraguardo();
        assertEquals("", 12, scuderia.getPosizione());

    }

}
