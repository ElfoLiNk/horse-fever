package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.*	;

public class giocatore {
	
	private static String nome;
	private String interpreta; //nome carta giocatore
	private int pv = 0;
	private int soldi = 0;
	private String scuderia;
	
	public static class scomessa{
		
		private int soldi=0;
		private String cavallo;
		private String tiposcomessa;
		
		//da fare metodi
		
	}
	
	//getter 
	
	public static String getnome(){
	
		System.out.println("come ti vuoi chiamare?");
		InputStreamReader a = new InputStreamReader(System.in);
		BufferedReader IN = new BufferedReader(a);
		try{
			nome=IN.readLine();
		}
		catch (IOException e)
	      {
	         System.out.println ("errore di flusso");
	      }
	    return(nome);

	}

}
