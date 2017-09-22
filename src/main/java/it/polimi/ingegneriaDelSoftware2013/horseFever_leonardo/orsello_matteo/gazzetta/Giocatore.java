/**
 *
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Struttura dati del Giocatore, in cui carico i dati da una carta personaggio
 * scelta a caso e salvo 2 carte azione a turno
 */
public class Giocatore {

    private String nome = null;
    private String interpreta;
    private int pv = 1;
    private int soldi = 0;
    private int salta = 0;
    private String scuderia;
    private List<CarteAzione> listaazioni = new ArrayList<CarteAzione>();

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
     * @param quotazione    della carta personaggio
     * @param listascuderie della partita
     */
    public void setScuderia(int quotazione, List<Scuderia> listascuderie) {
        for (Scuderia cavallo : listascuderie) {
            if (cavallo.getQuotazione() == quotazione) {
                this.scuderia = cavallo.getColore();
            }
        }
    }

    /**
     * Rimuove la carta azione passata dalla lista
     *
     * @param i indice della carta da rimuovere
     */
    public void removeCarteAzione(int i) {
        this.listaazioni.remove(i);
    }

    /**
     * Aggiorna i punti vittoria del giocatore
     *
     * @param punti vittoria vinti
     */
    public void aggiornapv(int punti) {
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
    public void setInterpreta(String interpreta) {
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
    public void setPv(int pv) {
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
    public void setSoldi(int soldi) {
        this.soldi = soldi;
    }

    /**
     * @param newSoldi the soldi to add
     */
    public void aggiornaSoldi(int newSoldi) {
        soldi += newSoldi;
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
     * @param i Numero del giocatore
     */
    public void setNome(int i) {
        do {
            Write.write("Player " + i + ": Come ti vuoi chiamare?");
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
    public void setCarteAzione(CarteAzione carta) {
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
    public void setSalta(int salta) {
        this.salta = salta;
    }

    /**
     * Imposta il nome del giocatore con la stringa passata come parametro
     *
     * @param string il nome da dare al giocatore
     */
    public void setNomeGiocatore(String string) {
        this.nome = string;

    }

}
