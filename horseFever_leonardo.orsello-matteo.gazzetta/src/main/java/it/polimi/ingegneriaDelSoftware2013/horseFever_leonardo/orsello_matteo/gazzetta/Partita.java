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
	public List<Scuderia> listascuderie = new ArrayList<Scuderia>();
	public static int tempint;

	public static void main(String[] args) {

		//creazione mazzo personaggi

		listapersonaggi = CartePersonaggio.crealistapersonaggi();

		//richiesta numero giocatori		
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader IN = new BufferedReader(input);
			while(ngiocatori == 0){
			try
			{
				System.out.println("In quanti volete giocare?");
				temp = IN.readLine();
				ngiocatori = Integer.parseInt(temp);
			}
			catch (IOException e1)
			{
				System.out.println ("Errore di flusso");
			}
			catch (NumberFormatException e2)
			{
				System.out.println ("ERRORE: Devi inserire un numero!");
			}
			}



		//inizializzazione giocatori

		for(int i = 0; i < ngiocatori; i++){
			// Costruisco il giocatore
			Giocatore player = new Giocatore();
			
			// Chiedo a terminale il nome del player
			player.setNome(i + 1);
			
			// Seleziono la carta personaggio del player
			Random rnd = new Random();
			tempint = rnd.nextInt(listapersonaggi.size());
			
			// Assegno il nome della carta a interpeta del player
			player.setInterpreta(listapersonaggi.get(tempint).getNome());
			
			// Assegno i corrispondenti soldi al player
			player.setSoldi(listapersonaggi.get(tempint).getSoldi());
			
			// Assegno la corrispettiva Scuderia al player
			player.setScuderia(listapersonaggi.get(tempint).getQuotazione());

			// Aggiungo il player alla lista di giocatori
			arraygiocatori.add(player);
			
			// Elimino la carta giocatore dalla lista
			listapersonaggi.remove(tempint);
		}

		//inizializzo scuderie e le associo ai corrispettivi player
		for(int i = 0; i < ngiocatori; i++){
			Scuderia scuderia = new Scuderia();
			scuderia.setquotazione();
		}


	}

}