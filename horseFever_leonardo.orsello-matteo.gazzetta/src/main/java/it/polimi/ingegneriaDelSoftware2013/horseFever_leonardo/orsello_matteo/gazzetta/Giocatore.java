/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Struttura dati del Giocatore, in cui carico i dati da una carta personaggio
 * scelta a caso e salvo 2 carte azione a turno
 * 
 * 
 * @see
 */
public class Giocatore {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Giocatore " + nome.toUpperCase() + " | ( " + interpreta
				+ " ) | Soldi: " + soldi + " | Scuderia: " + scuderia + " | PV: "
				+ pv;
	}

	private String nome;
	private String interpreta;
	private int pv = 1;
	private int soldi = 0;
	private int salta = 0;
	private String scuderia;
	private List<CarteAzione> listaazioni = new ArrayList<CarteAzione>();

	/**
	 * 
	 * Imposta il nome del player, chiedendolo all'utente
	 * 
	 * @param i
	 *            Numero del giocatore
	 * 
	 * @see
	 */
	public void setNome(int i) {
		do {
			Write.write("Player " + i + ": Come ti vuoi chiamare?");
			this.nome = Read.readString();
		} while (nome == null);
	}

	/**
	 * 
	 * Imposta il colore della scuderia in base alla quotazione del personaggio
	 * e la tabella delle quotazioni
	 * 
	 * @param quotazione
	 *            della carta personaggio
	 * @param listascuderie
	 *            della partita
	 * @exceptions
	 * 
	 * @see
	 */
	public void setScuderia(int quotazione, List<Scuderia> listascuderie) {
		for (Scuderia scuderia : listascuderie) {
			if (scuderia.getQuotazione() == quotazione) {
				this.scuderia = scuderia.getColore();
			}
		}
	}

	/**
	 * Aggiunge la carta azione passata nella lista
	 * 
	 * @param carta
	 *            azione da aggiungere
	 */
	public void setCarteAzione(CarteAzione carta) {
		this.listaazioni.add(carta);
	}

	/**
	 * Rimuove la carta azione passata dalla lista
	 * 
	 * @param i
	 *            indice della carta da rimuovere
	 */
	public void removeCarteAzione(int i) {
		this.listaazioni.remove(i);
	}

	/**
	 * 
	 * Aggiorna i punti vittoria del giocatore
	 * 
	 * @param punti
	 *            vittoria vinti
	 * @exceptions
	 * 
	 * @see
	 */
	public void aggiornapv(int punti) {
		setPv(getPv() + punti);
	}

	/**
	 * Svuoto la lista delle carte azione del giocatore
	 * 
	 * @exceptions
	 * 
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
	 * @param interpreta
	 *            the interpreta to set
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
	 * @param pv
	 *            the pv to set
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
	 * @param soldi
	 *            the soldi to add
	 */
	public void aggiornaSoldi(int temp) {
		soldi += temp;
	}

	/**
	 * @param soldi
	 *            the soldi to set
	 */
	public void setSoldi(int soldi) {
		this.soldi = soldi;
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
	 * @return the listaazioni
	 */
	public List<CarteAzione> getCarteAzione() {
		return listaazioni;
	}

	/**
	 * @return the salta
	 */
	public int getSalta() {
		return salta;
	}

	/**
	 * @param salta
	 *            the salta to set
	 */
	public void setSalta(int salta) {
		this.salta = salta;
	}

}
