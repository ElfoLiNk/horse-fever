/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author matteo
 * 
 */
public class Partita {
	
	private int turno = 0;
	private static int ngiocatori = 0;
	private int primogiocatore;
	private static List<Giocatore> arraygiocatori = new ArrayList<Giocatore>();
	private static List<Scuderia> listascuderie = new ArrayList<Scuderia>();
	private static List<CartePersonaggio> listapersonaggi;
	private static List<CarteAzione> listaazioni;
	private static int tempint;

	// Creo liste dei Mazzi Carte
	
	/**
	 * 
	 */
	public static void setListe() {
		// Creazione mazzo Personaggi
		listapersonaggi = CartePersonaggio.crealistapersonaggi();
		// Creazione mazzo Azioni
		listaazioni = CarteAzione.crealistaazioni();
	}

	// Richiesta numero giocatori
	public static void setNumGiocatori() {
		
		do
		{
			System.out.println("In quanti volete giocare?");
			ngiocatori = Read.readInt();
			
		}while( ngiocatori < 2 || ngiocatori > 6 );
	}

	// Inizializzazione giocatori

	public static void setGiocatori() {

		for (int i = 0; i < ngiocatori; i++) {
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
	}

	/**
	 * inizializzo scuderie
	 */
	public void setScuderie() {

		for (int i = 0; i < 6; i++) {
			Scuderia scuderia = new Scuderia();
			listascuderie.add(scuderia);

		}
	}

	// Assegno le Scuderie alle Quotazioni in modo Random
	/**
	 * 
	 */
	public void setQuotazioni() {
		int n = 0;
		int tempint;
		n = listascuderie.size();
		Random rnd = new Random();
		tempint = rnd.nextInt(7 - 2) + 2;
		listascuderie.get(0).setquotazione(tempint);

		int flag = 1;
		for (int i = 1; i <= n; i++) {
			Random rd = new Random();
			tempint = rd.nextInt(7 - 2) + 2;
			for (int a = 0; a < i; a++) {
				if (tempint == listascuderie.get(a).getquotazione())
					flag = 0;
			}
			if (flag == 1)
				listascuderie.get(i).setquotazione(tempint);
			else
				i--;
		}
	}

	/**
	 * Associo a ogni player 1 carte azione
	 */
	public static void setCarteAzione() {
		for (int i = 0; i < ngiocatori; i++) {
			// Scelgo una carta azione a caso
			Random rnd = new Random();
			tempint = rnd.nextInt(listaazioni.size());

			// Salvo la carta azione nella lista del player i
			arraygiocatori.get(i).setCarteAzione(listaazioni.get(tempint));

			// Elimino la carta dalla lista
			listaazioni.remove(tempint);
		}
	}

	// Randomizzo il primogiocatore
	public void randomPrimogiocatore() {
		Random rnd = new Random();
		tempint = rnd.nextInt(arraygiocatori.size());
		this.setPrimogiocatore(tempint);
	}

	// Imposto il primo giocatore
	/**
	 * @param primogiocatore
	 *            the primogiocatore to set
	 */
	public void setPrimogiocatore(int primogiocatore) {
		this.primogiocatore = primogiocatore;
	}

	//
	public static void truccoCorsa() {
		for (int i = 0; i < ngiocatori; i++) {
			List<CarteAzione> carteplayer = arraygiocatori.get(i)
					.getCarteAzione();
			System.out.println(carteplayer.size());

			System.out.println("["+arraygiocatori.get(i).getNome().toUpperCase()+ "] scegli la carta da giocare:");
			// Print carte azione del player
			for (int j = 0; j < carteplayer.size(); j++) {

				System.out.println(j + ") " + carteplayer.get(j).toString());
			}

			// Scelta Carta
			int k = 0;
			do {
				System.out.println("Seleziona Carta da giocare: ");
				k = Read.readInt();
			}while(k < 0 || k > carteplayer.size()-1);

			// Rimuovo Carta dalla lista del player
			arraygiocatori.get(i).removeCarteAzione(k);

			// Scelta Scuderia
			System.out.println("A quale corsia vuoi applicarla?");
			int s = 0;
			do {
				System.out.println("Seleziona corsia: ");
				s = Read.readInt();
			}while(s < 0 || s > listascuderie.size()-1);
			
		}
	}

	// getter
	/**
	 * 
	 * @return
	 */
	public static List<Giocatore> getarraygiocatori() {
		return arraygiocatori;
	}

	/**
	 * @return the primogiocatore
	 */
	public int getPrimogiocatore() {
		return primogiocatore;
	}

}