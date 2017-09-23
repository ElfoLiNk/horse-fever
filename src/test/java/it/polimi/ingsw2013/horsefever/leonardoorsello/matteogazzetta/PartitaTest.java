package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta;

import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model.Giocatore;
import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model.Parametri;
import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model.Scuderia;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Test per la classe Partita
 *
 * @see Partita
 */
public class PartitaTest {

    private final String failMessage = "Mancano i file";

    /**
     * Test method for
     * {@link Partita#setListe()}
     * .
     */
    @Test
    public void testListapersonaggi() {
        final Partita partita = new Partita();
        try {
            partita.setListe();
        } catch (IOException e) {
            fail(failMessage);
        }
        assertNotNull("", partita.getCartePersonaggio());
    }

    /**
     * Test method for
     * {@link Partita#setListe()}
     * .
     */
    @Test
    public void testListaazioni() {
        final Partita partita = new Partita();
        try {
            partita.setListe();
        } catch (IOException e) {
            fail(failMessage);
        }
        assertNotNull("", partita.getCarteAzione());
    }

    /**
     * Test method for
     * {@link Partita#setListe()}
     * .
     */
    @Test
    public void testCarteMovimento() {
        final Partita partita = new Partita();
        try {
            partita.setListe();
        } catch (IOException e) {
            fail(failMessage);
        }
        assertNotNull("", partita.getCarteMovimento());
    }

    /**
     * Test method for
     * {@link Partita#setSegnalini()}
     * .
     */
    @Test
    public void testSetSegnalini2Giocatori() {
        final Partita partita = new Partita();
        partita.setScuderie();
        // 2 Giocatori
        partita.setNgiocatori(2);
        partita.setSegnalini();
        assertEquals("", 1, partita.getScuderie().get(0).getSegnalino());
    }

    /**
     * Test method for
     * {@link Partita#setSegnalini()}
     * .
     */
    @Test
    public void testSetSegnalini3Giocatori() {
        final Partita partita = new Partita();
        partita.setScuderie();
        // 3 Giocatori
        partita.setNgiocatori(3);
        partita.setSegnalini();
        assertEquals("", 2, partita.getScuderie().get(0).getSegnalino());
    }

    /**
     * Test method for
     * {@link Partita#setSegnalini()}
     * .
     */
    @Test
    public void testSetSegnalini4Giocatori() {
        final Partita partita = new Partita();
        partita.setScuderie();
        // 4 Giocatori
        partita.setNgiocatori(4);
        partita.setSegnalini();
        assertEquals("", 3, partita.getScuderie().get(0).getSegnalino());
    }

    /**
     * Test method for
     * {@link Partita#setSegnalini()}
     * .
     */
    @Test
    public void testSetSegnalini5Giocatori() {
        final Partita partita = new Partita();
        partita.setScuderie();
        // 5 Giocatori
        partita.setNgiocatori(5);
        partita.setSegnalini();
        assertEquals("", 4, partita.getScuderie().get(0).getSegnalino());
    }

    /**
     * Test method for
     * {@link Partita#setSegnalini()}
     * .
     */
    @Test
    public void testSetSegnalini6Giocatori() {
        final Partita partita = new Partita();
        partita.setScuderie();
        // 6 Giocatori
        partita.setNgiocatori(6);
        partita.setSegnalini();
        assertEquals("", 4, partita.getScuderie().get(0).getSegnalino());
    }

    /**
     * Test method for
     * {@link Partita#setScuderie()}
     * .
     */
    @Test
    public void testSetScuderie() {
        final Partita partita = new Partita();
        partita.setScuderie();
        assertNotNull("", partita.getScuderie());
    }

    /**
     * Test method for
     * {@link Partita#setCarteAzione()}
     * .
     */
    @Test
    public void testSetCarteAzione() {
        final Partita partita = new Partita();
        try {
            partita.setListe();
        } catch (IOException e) {
            fail(failMessage);
        }
        // Costruisco il giocatore
        final Giocatore player = new Giocatore();

        // Seleziono la carta personaggio del player
        final Random rnd = new Random();
        final int tempint = rnd.nextInt(partita.getCartePersonaggio().size());

        // Assegno il nome della carta a interpreta del player
        player.setInterpreta(partita.getCartePersonaggio().get(tempint)
                .getNome());

        // Assegno i corrispondenti soldi al player
        player.setSoldi(partita.getCartePersonaggio().get(tempint).getSoldi());

        // Assegno la corrispettiva Scuderia al player
        player.setScuderia(partita.getCartePersonaggio().get(tempint)
                .getQuotazione(), partita.getScuderie());

        // Aggiungo il player alla lista di giocatori
        partita.getGiocatori().add(player);

        // Elimino la carta giocatore dalla lista
        partita.getCartePersonaggio().remove(tempint);
        partita.setCarteAzione();
        assertNotNull("", partita.getGiocatori().get(0).getCarteAzione());
    }

    /**
     * Test method for
     * {@link Partita#movimento()}
     * .
     */
    @Test
    public void testMovimento() {
        final Partita partita = new Partita();
        try {
            partita.setListe();
        } catch (IOException e) {
            fail(failMessage);
        }
        partita.setScuderie();
        partita.setQuotazioni();
        partita.movimento();
        // Verifico che ogni scuderie ha un movimento
        for (final Scuderia scuderia : partita.getScuderie()) {
            assertNotEquals(-1, scuderia.getMovimento());
        }

    }

    /**
     * Test method for
     * {@link Partita#sprint()}
     * .
     */
    @Test
    public void testSprint() {
        final Partita partita = new Partita();
        partita.setScuderie();
        partita.sprint();
        final long sprint = partita.getScuderie().stream().filter(scuderia -> scuderia.getSprint() == 1).count();
        assertEquals("Solo due scuderie random possono effettuare uno sprint", 2, sprint);
    }

    /**
     * Test method for
     * {@link Partita#posizione()}
     * .
     */
    @Test
    public void testPosizione() {
        final Partita partita = new Partita();
        try {
            partita.setListe();
        } catch (IOException e) {
            fail(failMessage);
        }
        partita.movimento();
        partita.setScuderie();
        final List<Scuderia> scuderie = partita.getScuderie();

        final Scuderia primaScuderia = scuderie.get(0);
        primaScuderia.setMovimento(4);
        primaScuderia.setSprint(1);
        final Scuderia secondaScuderia = scuderie.get(1);
        secondaScuderia.setMovimento(12);
        secondaScuderia.setSprint(1);

        partita.posizione();
        assertEquals("", 5, primaScuderia.getPosizione());
        assertEquals("", 13, secondaScuderia.getPosizione());
        assertTrue("Arrivato", secondaScuderia.isArrivato());

    }

    /**
     * Test method for
     * {@link Partita#aggiornaprimogiocatore()}
     * .
     */
    @Test
    public void testAggiornaprimogiocatore() {
        final Partita partita = new Partita();
        final List<Giocatore> giocatori = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            final Giocatore player = new Giocatore();
            giocatori.add(player);
        }
        partita.setGiocatori(giocatori);
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
        final Partita partita = new Partita();
        final List<Scuderia> arrivati = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            final Scuderia scuderia = new Scuderia(partita, partita.COLORI[i]);
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
        final List<Scuderia> arrivati = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            final Scuderia scuderia = new Scuderia(partita, partita.COLORI[i]);
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
        final Partita partita2 = new Partita();
        final List<Scuderia> fotofinish = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Scuderia scuderia = new Scuderia(partita, partita.COLORI[i]);
            fotofinish.add(scuderia);
        }
        final List<Scuderia> classifica = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Scuderia scuderia = new Scuderia(partita, partita.COLORI[i]);
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
        final List<Giocatore> giocatori = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            final Giocatore giocatore = new Giocatore();
            giocatori.add(giocatore);
        }
        giocatori.get(0).setPuntiVittoria(5);
        giocatori.get(0).setNomeGiocatore("VINCE");
        partita.setGiocatori(giocatori);
        assertEquals("", "VINCE", partita.trovaVincitore());

        giocatori.get(0).setPuntiVittoria(5);
        giocatori.get(0).setSoldi(3000);
        giocatori.get(0).setNomeGiocatore("VINCE");
        giocatori.get(1).setPuntiVittoria(5);
        giocatori.get(1).setSoldi(1991);
        giocatori.get(1).setNomeGiocatore("PERDE");
        partita.setGiocatori(giocatori);
        assertEquals("", "VINCE", partita.trovaVincitore());

    }

    /**
     * Test method for
     * {@link Partita#checkArrivati()}
     * .
     */
    @Test
    public void testCheckArrivatiFalse() {
        final Partita partita = new Partita();
        for (int i = 0; i < 6; i++) {
            Giocatore giocatore = new Giocatore();
            giocatore.setPuntiVittoria(4);
            giocatore.setSoldi(12313);
            giocatore.setNomeGiocatore("prova");
            partita.getGiocatori().add(giocatore);
        }
        // Controllo il caso in cui non tutte sono arrivate
        partita.setScuderie();
        partita.getScuderie().get(3).setArrivato(false);
        assertFalse("Arrivati", partita.checkArrivati());
    }

    /**
     * Test method for
     * {@link Partita#checkArrivati()}
     * .
     */
    @Test
    public void testCheckArrivatiTrue() {
        final Partita partita = new Partita();
        for (int i = 0; i < 6; i++) {
            Giocatore giocatore = new Giocatore();
            giocatore.setPuntiVittoria(4);
            giocatore.setSoldi(12313);
            giocatore.setNomeGiocatore("prova");
            partita.getGiocatori().add(giocatore);
        }
        // Controllo il caso in cui tutte le scuderie sono arrivate
        for (final Scuderia scuderia : partita.getScuderie()) {
            scuderia.setArrivato(true);
        }
        assertTrue("Arrivati", partita.checkArrivati());
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
        final List<Scuderia> scuderie = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Scuderia scuderia = new Scuderia(partita, partita.COLORI[i]);
            scuderia.setQuotazione(i + 1);
            scuderie.add(scuderia);
        }

        partita.setScuderie(scuderie);

        final List<Scuderia> classifica = new ArrayList<>();

        final Scuderia primo = new Scuderia(partita, partita.COLORI[1]);
        primo.setColore("GIALLO");
        primo.setQuotazione(5);
        classifica.add(primo);

        final Scuderia secondo = new Scuderia(partita, partita.COLORI[2]);
        secondo.setColore("VERDE");
        secondo.setQuotazione(3);
        classifica.add(secondo);

        final Scuderia terzo = new Scuderia(partita, partita.COLORI[3]);
        terzo.setColore("BLU ");
        terzo.setQuotazione(2);
        classifica.add(terzo);

        partita.setClassifica(classifica);

        final List<Giocatore> giocatori = new ArrayList<>();
        for (int i = 0; i < Parametri.MAX_GIOCATORI; i++) {
            Giocatore giocatore = new Giocatore();
            giocatore.setNomeGiocatore(String.format("%d", i));
            giocatore.setSoldi(0);
            giocatore.setScuderia(i + 1, partita.getScuderie());
            giocatori.add(giocatore);
        }
        partita.setGiocatori(giocatori);

        partita.pagascuderie();

        assertTrue("Assegna le vincinte correttamente", Parametri.SOLDI_PRIMA_SCUDERIA == partita.getGiocatori().get(4).getSoldi()
                && Parametri.SOLDI_SECONDA_SCUDERIA == partita.getGiocatori().get(2).getSoldi()
                && Parametri.SOLDI_TERZA_SCUDERIA == partita.getGiocatori().get(1).getSoldi());
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
            giocatore.setPuntiVittoria(4);
            giocatore.setSoldi(12313);
            giocatore.setNomeGiocatore("prova");
            giocatori.add(giocatore);
        }

        giocatori.get(0).setPuntiVittoria(1);
        giocatori.get(0).setSoldi(0);

        giocatori.get(5).setPuntiVittoria(2);
        giocatori.get(5).setSoldi(100);

        partita.setGiocatori(giocatori);
        partita.checkeliminato();
        assertEquals("devo avere 4", 4, partita.getGiocatori().size());
    }
}
