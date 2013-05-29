/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

/**
 * @author matteo
 * 
 */
public class Scommessa {
	private String nomegiocatore;
	private int soldi = 0;

	public enum Tiposcommessa {
		PIAZZATO, VINCENTE
	};

	private Tiposcommessa tiposcommessa;

	// setter
	public void setSoldi(int temp) {
		soldi = temp;

	}

	public void setNomegiocatore(String temp) {
		nomegiocatore = temp;

	}

	public void setTiposcommessa(char temp) {
		if (temp == 'v') {
			tiposcommessa = Tiposcommessa.VINCENTE;
		}
		if (temp == 'p') {
			tiposcommessa = Tiposcommessa.PIAZZATO;
		}
	}

	// getter

	public String getNomegiocatore() {
		return nomegiocatore;
	}

	public int getSoldi() {
		return soldi;
	}

	public Tiposcommessa getTiposcommessa() {
		return tiposcommessa;

	}

}