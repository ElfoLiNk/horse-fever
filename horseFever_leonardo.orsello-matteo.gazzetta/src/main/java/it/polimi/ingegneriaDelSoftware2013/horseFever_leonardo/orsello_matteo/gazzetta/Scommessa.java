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
		private enum Tiposcommessa{PIAZZATO, VINCENTE};
	    private Tiposcommessa tiposcommessa;
	

	//setter
	public void setsoldi(int temp){
		soldi=temp;
		
	}
	
	public void setnomegiocatore(String temp){
		nomegiocatore = temp;
		
	}
	
	public void settiposcommessa(Tiposcommessa temp){
		tiposcommessa = temp;
	}
	
	//getter
	
	public String getnomegiocatore(){
		return nomegiocatore;
	}
	
	public int getsoldi(){
		return soldi;
	}
	
	public Tiposcommessa gettiposcommessa(){
		return tiposcommessa;
		
	}
	
	}