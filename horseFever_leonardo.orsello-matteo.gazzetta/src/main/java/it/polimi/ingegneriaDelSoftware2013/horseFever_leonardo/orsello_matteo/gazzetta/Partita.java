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
	public List<Giocatore> arraygiocatori = new ArrayList<Giocatore>();
	public List<Scuderia> listascuderie = new ArrayList<Scuderia>();
	public List<CartePersonaggio> listapersonaggi;
	public List<CarteAzione> listaazioni;
	public static int tempint;

	public void setListe(){
		// Creazione mazzo Personaggi
		listapersonaggi = CartePersonaggio.crealistapersonaggi();
		// Creazione mazzo Azioni
		listaazioni = CarteAzione.crealistaazioni();
	}
	public void setNumGiocatori(){
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
	}

	public void setGiocatori() {
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
			//player.setScuderia(listapersonaggi.get(tempint).getQuotazione());

			// Aggiungo il player alla lista di giocatori
			arraygiocatori.add(player);

			// Elimino la carta giocatore dalla lista
			listapersonaggi.remove(tempint);
		}
	}

	public void setQuotazioni(){
		int n=0;
		int tempint;
		n = listascuderie.size();
		Random rnd = new Random();
		tempint = rnd.nextInt(7-2)+2;
		listascuderie.get(0).setquotazione(tempint);

		int flag=1;
		for(int i=1;i<=n;i++){
			Random rd = new Random();
			tempint = rd.nextInt(7-2)+2;
			for(int a=0;a<i;a++){
				if(tempint==listascuderie.get(a).getquotazione())
					flag=0;
			}
			if(flag==1) listascuderie.get(i).setquotazione(tempint);
			else i--;
		}
	}

	/**
	 * inizializzo scuderie e le associo ai corrispettivi player
	 */
	public void setScuderie() {

		for(int i = 0; i < ngiocatori; i++){
			Scuderia scuderia = new Scuderia();
			//scuderia.setquotazione();
		}
	}
	/**
	 *  Associo a ogni player 2 carte azione
	 */

	public void setCarteAzione(){
		for(int i = 0; i < ngiocatori; i++){
			// Scelgo una carta azione a caso
			Random rnd = new Random();
			tempint = rnd.nextInt(listaazioni.size());

			// Salvo la carta azione nella lista del player i
			arraygiocatori.get(i).setCarteAzione(listaazioni.get(tempint));

			// Elimino la carta dalla lista
			listaazioni.remove(tempint);			
		}
	}

	public void setPrimoGiocatore(){
		Random rnd = new Random();
		tempint = rnd.nextInt(arraygiocatori.size());

		arraygiocatori.get(i).
	}
}