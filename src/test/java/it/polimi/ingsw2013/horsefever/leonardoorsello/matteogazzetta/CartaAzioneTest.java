package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta;

import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model.CartaAzione;
import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model.Scuderia;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * JUnit test per la classe CartaAzione
 *
 * @see CartaAzione
 */
public class CartaAzioneTest {

    private List<Scuderia> scuderie = new ArrayList<>();

    @Before
    public void beforeCrealistaazioni() {
        final Partita partita = new Partita();
        partita.setScuderie();
        partita.setQuotazioni();
        scuderie = partita.getScuderie();

    }

    /**
     * Test method for
     * {@link CartaAzione#caricaCarteAzione()}
     * .
     */
    @Test
    public void testCrealistaazioni() {
        final List<CartaAzione> carte = CartaAzione.caricaCarteAzione();
        assertNotNull("La lista delle carte non deve essere vuota", carte);
    }

    /**
     * Test method for
     * {@link CartaAzione#carteAzionePartenza(java.util.List)}
     * .
     */
    @Test
    public void testCarteAzionePartenzaA() {
        // Testo la carta che fa muovere la scuderia una casella in meno dello
        // spostamento della carta movimento
        final CartaAzione carta = new CartaAzione();
        carta.setAgisce("Partenza");
        carta.setEffetto(-1);
        for (final Scuderia scuderia : scuderie) {
            scuderia.addCarteAzione(carta);
            scuderia.setMovimento(4);
        }
        carta.carteAzionePartenza(scuderie);
        for (Scuderia scuderia : scuderie) {
            // Verifico l'effetto
            assertEquals("", 3, scuderia.getMovimento());
        }

    }

    /**
     * Test method for
     * {@link CartaAzione#carteAzionePartenza(java.util.List)}
     * .
     */
    @Test
    public void testCarteAzionePartenzaB() {
        // Testo la carta che fa muovere la scuderia una casella in piu dello
        // spostamento della carta movimento
        final CartaAzione carta1 = new CartaAzione();
        carta1.setAgisce("Partenza");
        carta1.setEffetto(1);
        for (Scuderia scuderia : scuderie) {
            scuderia.addCarteAzione(carta1);
            scuderia.setMovimento(4);
        }
        carta1.carteAzionePartenza(scuderie);
        for (Scuderia scuderia : scuderie) {
            // Verifico l'effetto
            assertEquals("", 5, scuderia.getMovimento());
        }

    }

    /**
     * Test method for
     * {@link CartaAzione#carteAzionePartenza(java.util.List)}
     * .
     */
    @Test
    public void testCarteAzionePartenzaC() {
        // Testo la carta che fa muovere la scuderia quanto indicato
        // nell'effetto anziche quello scritto sulla carta movimento
        final CartaAzione carta4 = new CartaAzione();
        carta4.setAgisce("Partenza");
        carta4.setEffetto(4);
        for (Scuderia scuderia : scuderie) {
            scuderia.addCarteAzione(carta4);
            scuderia.setMovimento(2);
        }
        carta4.carteAzionePartenza(scuderie);
        for (Scuderia scuderia : scuderie) {
            // Verifico l'effetto
            assertEquals("", 4, scuderia.getMovimento());
        }

    }

    /**
     * Test method for
     * {@link CartaAzione#carteAzionePartenza(java.util.List)}
     * .
     */
    @Test
    public void testCarteAzionePartenzaD() {
        // Testo la carta che non fa muovere la scuderia alla partenza
        final CartaAzione carta0 = new CartaAzione();
        carta0.setAgisce("Partenza");
        carta0.setEffetto(0);
        for (Scuderia scuderia : scuderie) {
            scuderia.addCarteAzione(carta0);
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
     * {@link CartaAzione#carteAzioneMovimento(java.util.List)}
     * .
     */
    @Test
    public void testCarteAzioneMovimentoA() {
        // Testo la carta che fa muovere la scuderia di 4 se il cavallo è ultimo
        CartaAzione carta4 = new CartaAzione();
        carta4.setAgisce("Movimento");
        carta4.setEffetto(4);
        for (Scuderia scuderia : scuderie) {
            scuderia.addCarteAzione(carta4);
            scuderia.setUltimo(true);
        }
        carta4.carteAzioneMovimento(scuderie);
        for (Scuderia scuderia : scuderie) {
            // Verifico l'effetto
            assertEquals("", 4, scuderia.getMovimento());
        }

    }

    /**
     * Test method for
     * {@link CartaAzione#carteAzioneMovimento(java.util.List)}
     * .
     */
    @Test
    public void testCarteAzioneMovimentoB() {
        // Testo la carta che fa muovere la scuderia di 0 se il cavallo è primo
        CartaAzione carta0 = new CartaAzione();
        carta0.setAgisce("Movimento");
        carta0.setEffetto(0);
        for (Scuderia scuderia : scuderie) {
            scuderia.addCarteAzione(carta0);
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
     * {@link CartaAzione#carteAzioneSprint(java.util.List)}
     * .
     */
    @Test
    public void testCarteAzioneSprintA() {
        // Testo la carta che fa sprintare una casella in meno
        CartaAzione carta = new CartaAzione();
        carta.setAgisce("Sprint");
        carta.setEffetto(-1);
        for (Scuderia scuderia : scuderie) {
            scuderia.addCarteAzione(carta);
            scuderia.setSprint(2);
        }
        carta.carteAzioneSprint(scuderie);
        for (Scuderia scuderia : scuderie) {
            // Verifico l'effetto
            assertEquals("", 1, scuderia.getSprint());
        }
    }

    /**
     * Test method for
     * {@link CartaAzione#carteAzioneSprint(java.util.List)}
     * .
     */
    @Test
    public void testCarteAzioneSprintB() {
        // Testo la carta che fa sprintare il cavallo di una casella in piu
        CartaAzione carta1 = new CartaAzione();
        carta1.setAgisce("Sprint");
        carta1.setEffetto(1);
        for (Scuderia scuderia : scuderie) {
            scuderia.addCarteAzione(carta1);
            scuderia.setSprint(2);
        }
        carta1.carteAzioneSprint(scuderie);
        for (Scuderia scuderia : scuderie) {
            // Verifico l'effetto
            assertEquals("", 3, scuderia.getSprint());
        }
    }

    /**
     * Test method for
     * {@link CartaAzione#carteAzioneSprint(java.util.List)}
     * .
     */
    @Test
    public void testCarteAzioneSprintC() {
        // Testo la carta che fa sprintare il cavallo di 2 caselle
        final CartaAzione carta2 = new CartaAzione();
        carta2.setAgisce("Sprint");
        carta2.setEffetto(2);
        for (Scuderia scuderia : scuderie) {
            scuderia.addCarteAzione(carta2);
            scuderia.setSprint(0);
        }
        carta2.carteAzioneSprint(scuderie);
        for (Scuderia scuderia : scuderie) {
            // Verifico l'effetto
            assertEquals("", 2, scuderia.getSprint());
        }
    }

    /**
     * Test method for
     * {@link CartaAzione#carteAzioneSprint(java.util.List)}
     * .
     */
    @Test
    public void testCarteAzioneSprintD() {
        // Testo la carta che non fa sprintare il cavallo
        CartaAzione carta0 = new CartaAzione();
        carta0.setAgisce("Sprint");
        carta0.setEffetto(0);
        for (Scuderia scuderia : scuderie) {
            scuderia.addCarteAzione(carta0);
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
     * {@link CartaAzione#carteAzioneFotofinish(java.util.List)}
     * .
     */
    @Test
    public void testCarteAzioneFotofinishA() {
        // Testo la carta che fa perdere il fotofinish
        CartaAzione carta = new CartaAzione();
        carta.setAgisce("Fotofinish");
        carta.setEffetto(0);
        for (Scuderia scuderia : scuderie) {
            scuderia.addCarteAzione(carta);
        }
        carta.carteAzioneFotofinish(scuderie);
        for (Scuderia scuderia : scuderie) {
            // Verifico l'effetto
            assertEquals("", 0, scuderia.getFotofinish());
        }
    }

    /**
     * Test method for
     * {@link CartaAzione#carteAzioneFotofinish(java.util.List)}
     * .
     */
    @Test
    public void testCarteAzioneFotofinishB() {
        // Testo la carta che fa vincere il fotofinish
        CartaAzione carta1 = new CartaAzione();
        carta1.setAgisce("Fotofinish");
        carta1.setEffetto(1);
        for (Scuderia scuderia : scuderie) {
            scuderia.addCarteAzione(carta1);
        }
        carta1.carteAzioneFotofinish(scuderie);
        for (Scuderia scuderia : scuderie) {
            // Verifico l'effetto
            assertEquals("", 1, scuderia.getFotofinish());
        }

    }

    /**
     * Test method for
     * {@link CartaAzione#carteAzioneQuotazione(java.util.List)}
     * .
     */
    @Test
    public void testCarteAzioneQuotazione() {
        for (Scuderia scuderia : scuderie) {
            // Controllo una scuderia alla volta
            final List<Scuderia> lista = new ArrayList<>();
            lista.add(scuderia);
            final CartaAzione mazzo = new CartaAzione();
            // Applico tutte le carte alla scuderia
            mazzo.carteAzioneQuotazione(lista);

            // Le quotazioni devono essere uguali
            assertEquals("", lista.get(0).getQuotazione(), lista.get(0).getQuotazione());

            lista.remove(scuderia);
        }
    }

}
