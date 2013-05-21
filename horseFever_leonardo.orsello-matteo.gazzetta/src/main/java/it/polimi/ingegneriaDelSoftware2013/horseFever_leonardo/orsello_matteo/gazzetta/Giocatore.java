/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.*	;

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


	//setter 

	public void setNome(int i){
		System.out.println("Player " +i+ ": Come ti vuoi chiamare?");
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader IN = new BufferedReader(input);
		try{
			nome = IN.readLine();
		}
		catch (IOException e)
	      {
	         System.out.println ("errore di flusso");
	      }
	}
	
	public void setInterpreta(String interpreta){
		this.interpreta = interpreta;
	}

	public void setSoldi(int soldi){
		this.soldi = soldi;
	}


	public void setScuderia(int quotazione){
		scuderia = //Metodo di scuderia per associare quotazione -> colore scuderia
	}	 



	//getter
	public String getNome(){
		return nome;
	}

	public String getInterpreta(){
		return interpreta;
	}

	public int getSoldi(){
		return soldi;
	}

	
	public String getScuderia(){
		return scuderia;
	}
}
