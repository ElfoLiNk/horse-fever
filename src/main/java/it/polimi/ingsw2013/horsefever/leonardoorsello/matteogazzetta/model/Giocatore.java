/**
 *
 */
package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.model;

import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.util.SystemIn;
import it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta.util.SystemOut;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Struttura dati del Giocatore, in cui carico i dati da una carta personaggio
 * scelta a caso e salvo 2 carte azione a turno
 */
public class Giocatore {

    private final List<CartaAzione> listaazioni;
    private String nome;
    private String interpreta;
    private int puntiVittoria;
    private int soldi;
    private int salta;
    private String scuderia;

    public Giocatore() {
        this.puntiVittoria = 1;
        this.soldi = 0;
        this.salta = 0;
        this.listaazioni = new ArrayList<>();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Giocatore " + nome.toUpperCase(Locale.getDefault()) + " | ( " + interpreta
                + " ) | Soldi: " + soldi + " | Scuderia: " + scuderia
                + " | PV: " + puntiVittoria;
    }

    /**
     * Imposta il colore della scuderia in base alla quotazione del personaggio
     * e la tabella delle quotazioni
     *
     * @param quotazione della carta personaggio
     * @param scuderie   della partita
     */
    public void setScuderia(final int quotazione, final List<Scuderia> scuderie) {
        for (final Scuderia scuderia : scuderie) {
            if (scuderia.getQuotazione() == quotazione) {
                this.scuderia = scuderia.getColore();
            }
        }
    }

    /**
     * Rimuove la carta azione passata dalla lista
     *
     * @param indiceCarta indice della carta da rimuovere
     */
    public void removeCarteAzione(final int indiceCarta) {
        this.listaazioni.remove(indiceCarta);
    }

    /**
     * Aggiorna i punti vittoria del giocatore
     *
     * @param punti vittoria vinti
     */
    public void aggiornaPuntiVittoria(final int punti) {
        setPuntiVittoria(getPuntiVittoria() + punti);
    }

    /**
     * Svuoto la lista delle carte azione del giocatore
     *
     * @exceptions
     * @see
     */
    public void resetCarteAzione() {
        listaazioni.clear();

    }

    /**
     * @return the interpreta
     */
    public String getInterpreta() {
        return interpreta;
    }

    /**
     * @param newInterpreta the interpreta to set
     */
    public void setInterpreta(final String newInterpreta) {
        this.interpreta = newInterpreta;
    }

    /**
     * @return the puntiVittoria
     */
    public int getPuntiVittoria() {
        return puntiVittoria;
    }

    /**
     * @param newPuntiVittoria the puntiVittoria to set
     */
    public void setPuntiVittoria(final int newPuntiVittoria) {
        this.puntiVittoria = newPuntiVittoria;
    }

    /**
     * @return the soldi
     */
    public int getSoldi() {
        return soldi;
    }

    /**
     * @param newSoldi the soldi to set
     */
    public void setSoldi(final int newSoldi) {
        this.soldi = newSoldi;
    }

    /**
     * @param soldiToAdd the soldi to add
     */
    public void aggiornaSoldi(final int soldiToAdd) {
        this.soldi += soldiToAdd;
    }

    /**
     * @return the scuderia
     */
    public String getScuderia() {
        return scuderia;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del player, chiedendolo all'utente
     *
     * @param numeroPlayer Numero del giocatore
     */
    public void setNome(final int numeroPlayer) {
        do {
            SystemOut.write("Player " + numeroPlayer + ": Come ti vuoi chiamare?");
            this.nome = SystemIn.readString();
        } while (nome == null);
    }

    /**
     * @return the listaazioni
     */
    public List<CartaAzione> getCarteAzione() {
        return this.listaazioni;
    }

    /**
     * Aggiunge la carta azione passata nella lista
     *
     * @param carta azione da aggiungere
     */
    public void setCarteAzione(final CartaAzione carta) {
        this.listaazioni.add(carta);
    }

    /**
     * @return the salta
     */
    public int getSalta() {
        return salta;
    }

    /**
     * @param newSalta the salta to set
     */
    public void setSalta(final int newSalta) {
        this.salta = newSalta;
    }

    /**
     * Imposta il nome del giocatore con la stringa passata come parametro
     *
     * @param newNome il nome da dare al giocatore
     */
    public void setNomeGiocatore(final String newNome) {
        this.nome = newNome;

    }

}
