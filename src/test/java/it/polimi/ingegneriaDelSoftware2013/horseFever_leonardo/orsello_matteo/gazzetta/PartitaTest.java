package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Test per la classe Partita
 *
 * @see Partita
 */
public class PartitaTest {

    /**
     * Test method for
     * {@link Partita#setListe()}
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
     * {@link Partita#setSegnalini()}
     * .
     */
    @Test
    public void testSetSegnalini() {
        Partita partita = new Partita();
        partita.setScuderie();
        // 2 Giocatori
        partita.setNgiocatori(2);
        partita.setSegnalini();
        assertEquals("", 1, partita.getListascuderie().get(0).getSegnalino());
        // 3 Giocatori
        partita.setNgiocatori(3);
        partita.setSegnalini();
        assertEquals("", 2, partita.getListascuderie().get(0).getSegnalino());
        // 4 Giocatori
        partita.setNgiocatori(4);
        partita.setSegnalini();
        assertEquals("", 3, partita.getListascuderie().get(0).getSegnalino());
        // 5 Giocatori
        partita.setNgiocatori(5);
        partita.setSegnalini();
        assertEquals("", 4, partita.getListascuderie().get(0).getSegnalino());
        // 6 Giocatori
        partita.setNgiocatori(6);
        partita.setSegnalini();
        assertEquals("", 4, partita.getListascuderie().get(0).getSegnalino());

    }

    /**
     * Test method for
     * {@link Partita#setScuderie()}
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
     * {@link Partita#setQuotazioni()}
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
     * {@link Partita#setCarteAzione()}
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
     * {@link Partita#movimento()}
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
        partita.setScuderie();
        partita.setQuotazioni();
        partita.movimento();
        // Verifico che ogni scuderie ha un movimento
        for (final Scuderia scuderia : partita.getListascuderie()) {
            switch (scuderia.getQuotazione()) {
                case Parametri.DUE:
                    assertNotEquals(0, scuderia.getMovimento());
                    break;
                case Parametri.TRE:
                    assertNotEquals(0, scuderia.getMovimento());
                    break;
                case Parametri.QUATTRO:
                    assertNotEquals(0, scuderia.getMovimento());
                    break;
                case Parametri.CINQUE:
                    assertNotEquals(0, scuderia.getMovimento());
                    break;
                case Parametri.SEI:
                    assertNotEquals(0, scuderia.getMovimento());
                    break;
                case Parametri.SETTE:
                    assertNotEquals(0, scuderia.getMovimento());
                    break;
                default:
                    break;
            }

        }

    }

    /**
     * Test method for
     * {@link Partita#sprint()}
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
        assertEquals(2, sprint);
    }

    /**
     * Test method for
     * {@link Partita#posizione()}
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
        final List<Scuderia> listascuderie = partita.getListascuderie();

        final Scuderia primaScuderia = listascuderie.get(0);
        primaScuderia.setMovimento(4);
        primaScuderia.setSprint(1);
        final Scuderia secondaScuderia = listascuderie.get(1);
        secondaScuderia.setMovimento(12);
        secondaScuderia.setSprint(1);

        partita.posizione();
        assertEquals(5, primaScuderia.getPosizione());
        assertEquals(13, secondaScuderia.getPosizione());
        assertTrue(secondaScuderia.isArrivato());

    }

    /**
     * Test method for
     * {@link Partita#aggiornaprimogiocatore()}
     * .
     */
    @Test
    public void testAggiornaprimogiocatore() {
        Partita partita = new Partita();
        final List<Giocatore> giocatori = new ArrayList<>();
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
     * {@link Partita#creaClassifica(java.util.List)}
     * .
     */
    @Test
    public void testClassifica() {
        Partita partita = new Partita();
        final List<Scuderia> arrivati = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Scuderia scuderia = new Scuderia(partita);
            arrivati.add(scuderia);
        }
        arrivati.get(0).setPosizione(14);
        arrivati.get(0).setColore("PRIMO");
        arrivati.get(1).setPosizione(12);
        arrivati.get(1).setColore("TERZO");
        arrivati.get(2).setPosizione(13);
        arrivati.get(2).setColore("SECONDO");
        partita.creaClassifica(arrivati);
        assertEquals("", "PRIMO", partita.getClassifica().get(0).getColore());
        assertEquals("", "TERZO", partita.getClassifica().get(2).getColore());
        assertEquals("", "SECONDO", partita.getClassifica().get(1).getColore());
    }

    /**
     * Test method for
     * {@link Partita#checkFotofinish(java.util.List)}
     * .
     */
    @Test
    public void testCheckFotofinish() {
        // Test con quotazioni diverse vince la quotazione piu alta
        Partita partita = new Partita();
        List<Scuderia> arrivati = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Scuderia scuderia = new Scuderia(partita);
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
        List<Scuderia> fotofinish = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Scuderia scuderia = new Scuderia(partita);
            fotofinish.add(scuderia);
        }
        List<Scuderia> classifica = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Scuderia scuderia = new Scuderia(partita);
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
        assertEquals("", "PRIMO", partita2.getClassifica().get(2).getColore());
        assertEquals("", "SECONDO", partita2.getClassifica().get(3).getColore());

    }

    /**
     * Test method for
     * {@link Partita#trovaVincitore()}
     * .
     */
    @Test
    public void testTrovaVincitore() {
        Partita partita = new Partita();
        List<Giocatore> giocatori = new ArrayList<>();

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
     * Test method for
     * {@link Partita#checkArrivati()}
     * .
     */
    @Test
    public void testCheckArrivati() {

        Partita partita = new Partita();
        for (int i = 0; i < 6; i++) {
            Giocatore giocatore = new Giocatore();
            giocatore.setPv(4);
            giocatore.setSoldi(12313);
            giocatore.setNomeGiocatore("prova");
            partita.getarraygiocatori().add(giocatore);
        }
        // Controllo il caso in cui non tutte sono arrivate
        partita.setScuderie();
        partita.getScuderie().get(3).setArrivato(false);
        assertFalse(partita.checkArrivati());
        // Controllo il caso in cui tutte le scuderie sono arrivate
        for (Scuderia scuderia : partita.getScuderie()) {
            scuderia.setArrivato(true);
        }
        assertTrue(partita.checkArrivati());
    }

    /**
     * Test method for
     * {@link Partita#pagascuderie()}
     * .
     */
    @Test
    public void testPagascuderie() {

        Partita partita = new Partita();
        partita.setQuotazioni();
        List<Scuderia> scuderie = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Scuderia scuderia = new Scuderia(partita);
            scuderie.add(scuderia);
        }
        scuderie.get(0).setQuotazione(2);
        scuderie.get(0).setColore("Giallo");
        scuderie.get(1).setQuotazione(3);
        scuderie.get(1).setColore("Verde");
        scuderie.get(2).setQuotazione(4);
        scuderie.get(2).setColore("Blu");
        scuderie.get(3).setQuotazione(5);
        scuderie.get(3).setColore("Nero");
        scuderie.get(4).setQuotazione(6);
        scuderie.get(4).setColore("Bianco");
        scuderie.get(5).setQuotazione(7);
        scuderie.get(5).setColore("Rosso");

        partita.setListascuderie(scuderie);

        List<Scuderia> classifica = new ArrayList<>();

        Scuderia giallo = new Scuderia(partita);
        giallo.setColore("Giallo");
        giallo.setQuotazione(2);
        classifica.add(giallo);

        Scuderia verde = new Scuderia(partita);
        verde.setColore("Verde");
        verde.setQuotazione(3);
        classifica.add(verde);

        Scuderia blu = new Scuderia(partita);
        blu.setColore("Blu");
        blu.setQuotazione(4);
        classifica.add(blu);

        partita.setClassifica(classifica);

        List<Giocatore> giocatori = new ArrayList<>();
        for (int i = 0; i < Parametri.MAX_GIOCATORI; i++) {
            Giocatore giocatore = new Giocatore();
            giocatore.setScuderia(i + 2, partita.getScuderie());
            giocatori.add(giocatore);
        }
        partita.setArraygiocatori(giocatori);

        partita.pagascuderie();

        assertEquals(600, partita.getarraygiocatori().get(0).getSoldi());
        assertEquals(400, partita.getarraygiocatori().get(1).getSoldi());
        assertEquals(200, partita.getarraygiocatori().get(2).getSoldi());
    }

    /**
     * Test method for
     * {@link Partita#checkeliminato()}
     * .
     * <p>
     * inizializza il vettore giocatori settando il primo e l'ultimo in modo
     * tale da essere eliminati
     */
    @Test
    public void testCheckeliminato() {
        List<Giocatore> giocatori = new ArrayList<>();
        Partita partita = new Partita();
        for (int i = 0; i < 6; i++) {
            Giocatore giocatore = new Giocatore();
            giocatore.setPv(4);
            giocatore.setSoldi(12313);
            giocatore.setNomeGiocatore("prova");
            giocatori.add(giocatore);
        }

        giocatori.get(0).setPv(1);
        giocatori.get(0).setSoldi(0);

        giocatori.get(5).setPv(2);
        giocatori.get(5).setSoldi(100);

        partita.setArraygiocatori(giocatori);
        partita.checkeliminato();
        assertEquals("devo avere 4", 4, partita.getarraygiocatori().size());
    }
}
