/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amministratore
 *
 */
public class Giocatore {

	private String nome;
	private String interpreta;
	private int pv = 1;
	private int soldi = 0;
	private String scuderia;
	private List<CarteAzione> listaazioni = new ArrayList<CarteAzione>();


	
	//setter 

	public void setNome(int i){
		Write.write("Player " +i+ ": Come ti vuoi chiamare?");
		this.nome = Read.readString();
	}
	
	public void setInterpreta(String interpreta){
		this.interpreta = interpreta;
	}
	
	public void setPv(int pv){
		this.pv = pv;
	}

	public void setSoldi(int soldi){
		this.soldi = soldi;
	}
	
	public void aggiornapv(int temp){
		pv +=temp;
	}

	public void setScuderia(int quotazione){
	}	 
	/**
	 * 
	 * @param carta
	 */
	public void setCarteAzione(CarteAzione carta){
		this.listaazioni.add(carta);
	}
	/**
	 * 
	 * @param i
	 */
	public void removeCarteAzione(int i){
		this.listaazioni.remove(i);
	}


	//getter
	public String getNome(){
		return nome;
	}

	public String getInterpreta(){
		return interpreta;
	}

	public int getPv(){
		return pv;
	}
	
	public int getSoldi(){
		return soldi;
	}
	
	public String getScuderia(){
		return scuderia;
	}
	
	public List<CarteAzione> getCarteAzione(){
		return listaazioni;
	}
}
