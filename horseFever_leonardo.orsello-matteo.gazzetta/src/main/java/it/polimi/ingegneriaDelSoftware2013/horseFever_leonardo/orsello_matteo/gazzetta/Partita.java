/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author matteo
 *
 */
public class Partita {
	private int round = 0;
	private static int ngiocatori = 0;
	private int primogiocatore = 0;
	public static String temp;
	public static List<Giocatore> arraygiocatori = new ArrayList<Giocatore>();
	public static List<CartePersonaggio> listapersonaggi;
	public static int tempint;
	
	public static void main(String[] args) {
		
		//creazione mazzo personaggi
		
		listapersonaggi = CartePersonaggio.crealistapersonaggi();
		
		//richiesta numero giocatori
		
		System.out.println("digitare il numero di giocatori e premere invio");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 do{
			 try
			 {
				 temp = br.readLine();
				 ngiocatori = Integer.parseInt(temp);
			 }
			 catch (IOException e1)
			 {
				 System.out.println ("errore di flusso");
			 }
			 catch (NumberFormatException e2)
			 {
				 System.out.println ("errore di input da tastiera");
			 }
		 } while(ngiocatori!=0);
		 
		 
		//inizializzazione giocatori
		 
		 for(int i = ngiocatori; i!=0; i--){
			 Giocatore player = new Giocatore();
			 player.setnome();
			 Random rnd = new Random();
			 tempint = rnd.nextInt(listapersonaggi.size()) + 1;
			 player.setsoldi(listapersonaggi.get(tempint).getSoldi());
			 player.setscuderia(listapersonaggi.get(tempint).)
			 
			 
			 
			 
			 
			 
			 arraygiocatori.add(player);
			 
			 
				 
		 }
		 
          	 
	      

	}

}