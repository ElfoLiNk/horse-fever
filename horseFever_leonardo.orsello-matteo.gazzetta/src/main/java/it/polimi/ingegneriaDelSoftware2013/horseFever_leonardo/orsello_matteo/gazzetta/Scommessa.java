/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

/**
 * 
 * Struttura dati della Scommessa
 * 
 */
public class Scommessa {
	private String nomegiocatore;
	private int soldi = 0;
	
	/**
	 *  Tipo della scommessa
	 */
	public enum Tiposcommessa {
		PIAZZATO, VINCENTE
	};

	private Tiposcommessa tiposcommessa;

	/**
	 * @return the nomegiocatore
	 */
	public String getNomeGiocatore() {
		return nomegiocatore;
	}

	/**
	 * @param nomegiocatore
	 *            the nomegiocatore to set
	 */
	public void setNomeGiocatore(String nomegiocatore) {
		this.nomegiocatore = nomegiocatore;
	}

	/**
	 * @return the soldi
	 */
	public int getSoldi() {
		return soldi;
	}

	/**
	 * @param soldi
	 *            the soldi to set
	 */
	public void setSoldi(int soldi) {
		this.soldi = soldi;
	}

	/**
	 * @return the tiposcommessa
	 */
	public Tiposcommessa getTiposcommessa() {
		return tiposcommessa;
	}

	/**
	 * @param tipo
	 *            il tipo di scommessa da impostare v vincente p piazzato
	 */
	public void setTiposcommessa(char tipo) {
		if (tipo == 'v') {
			tiposcommessa = Tiposcommessa.VINCENTE;
		}
		if (tipo == 'p') {
			tiposcommessa = Tiposcommessa.PIAZZATO;
		}
	}

}