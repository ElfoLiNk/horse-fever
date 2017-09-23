/**
 *
 */
package it.polimi.ingsw2013.horsefever.leonardoorsello.matteogazzetta;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Struttura dati del Giocatore, in cui carico i dati da una carta personaggio
 * scelta a caso e salvo 2 carte azione a turno
 */
public class Giocatore {

    private final List<CarteAzione> listaazioni;
    private String nome;
    private String interpreta;
    private int pv;
    private int soldi;
    private int salta;
    private String scuderia;

    Giocatore() {
        this.pv = 1;
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
                + " | PV: " + pv;
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
    public void aggiornapv(final int punti) {
        setPv(getPv() + punti);
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
     * @param interpreta the interpreta to set
     */
    public void setInterpreta(final String interpreta) {
        this.interpreta = interpreta;
    }

    /**
     * @return the pv
     */
    public int getPv() {
        return pv;
    }

    /**
     * @param pv the pv to set
     */
    public void setPv(final int pv) {
        this.pv = pv;
    }

    /**
     * @return the soldi
     */
    public int getSoldi() {
        return soldi;
    }

    /**
     * @param soldi the soldi to set
     */
    public void setSoldi(final int soldi) {
        this.soldi = soldi;
    }

    /**
     * @param newSoldi the soldi to add
     */
    public void aggiornaSoldi(final int newSoldi) {
        this.soldi += newSoldi;
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
            Write.write("Player " + numeroPlayer + ": Come ti vuoi chiamare?");
            this.nome = Read.readString();
        } while (nome == null);
    }

    /**
     * @return the listaazioni
     */
    public List<CarteAzione> getCarteAzione() {
        return listaazioni;
    }

    /**
     * Aggiunge la carta azione passata nella lista
     *
     * @param carta azione da aggiungere
     */
    public void setCarteAzione(final CarteAzione carta) {
        this.listaazioni.add(carta);
    }

    /**
     * @return the salta
     */
    public int getSalta() {
        return salta;
    }

    /**
     * @param salta the salta to set
     */
    public void setSalta(final int salta) {
        this.salta = salta;
    }

    /**
     * Imposta il nome del giocatore con la stringa passata come parametro
     *
     * @param nome il nome da dare al giocatore
     */
    public void setNomeGiocatore(final String nome) {
        this.nome = nome;

    }

}
