/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author matteo
 * 
 */
public class Partita {
	private int round = 0;
	private static int ngiocatori = 0;
	private int primogiocatore = 0;
	private static String temp;
	private static List<Giocatore> arraygiocatori = new ArrayList<Giocatore>();
	private List<Scuderia> listascuderie = new ArrayList<Scuderia>();
	private List<CartePersonaggio> listapersonaggi;
	private List<CarteAzione> listaazioni;
	private static int tempint;
	private static int flagscommessa = 1;
	private int turnoscommessa = 1;
	private String[] colori = { "NERO", "BLU", "VERDE", "ROSSO", "GIALLO",
			"BIANCO" };
	private List<Integer> movimenti = new ArrayList<Integer>();
	private List<String> listacartemovimento = new ArrayList<String>();

	/**
	 * 
	 * Creo liste dei Mazzi Carte
	 * 
	 * @exceptions
	 * 
	 * @see
	 */

	public void setListe() {
		// Creazione mazzo Personaggi
		listapersonaggi = CartePersonaggio.crealistapersonaggi();
		// Creazione mazzo Azioni
		listaazioni = CarteAzione.crealistaazioni();
		// Creazione mazzo Movimento
		listacartemovimento = CarteMovimento.setMovimento();

	}

	/**
	 * 
	 * Richiesta del numero di giocatori a terminale.
	 * 
	 * @exceptions
	 * 
	 * @see Giocatore
	 */
	public void setNumGiocatori() {

		do {
			Write.write("In quanti volete giocare?");
			ngiocatori = Read.readInt();

		} while (ngiocatori < 2 || ngiocatori > 6);
	}

	/**
	 * 
	 * Costruisco il giocatore chiedendo a terminale il nome del player, gli
	 * assegno una carta personaggio e modifico i soldi di conseguenza; Assegno
	 * la scuderia associata alla quotazione del personaggio.
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void setGiocatori() {

		for (int i = 0; i < ngiocatori; i++) {
			// Costruisco il giocatore
			Giocatore player = new Giocatore();

			// Chiedo a terminale il nome del player
			player.setNome(i + 1);

			// Seleziono la carta personaggio del player
			Random rnd = new Random();
			tempint = rnd.nextInt(listapersonaggi.size());

			// Assegno il nome della carta a interpreta del player
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
	 * 
	 * Inizializza le Scuderie.
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void setScuderie() {

		for (int i = 0; i < 6; i++) {

			Scuderia scuderia = new Scuderia();
			listascuderie.add(scuderia);
			listascuderie.get(i).setColore(colori[i]);

		}
	}

	/**
	 * 
	 * Assegno le Scuderie alle Quotazioni in modo Random
	 * 
	 * @exceptions
	 * 
	 * @see
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
				if (tempint == listascuderie.get(a).getquotazione()) {
					flag = 0;
				}
			}
			if (flag == 1) {
				listascuderie.get(i).setquotazione(tempint);
			} else {
				i--;
			}
		}
	}

	/**
	 * 
	 * Associo a ogni player 1 carte azione
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void setCarteAzione() {
		for (int i = 0; i < ngiocatori; i++) {
			// Scelgo una carta azione a caso
			Random rnd = new Random();
			tempint = rnd.nextInt(getListaazioni().size());

			// Salvo la carta azione nella lista del player i
			arraygiocatori.get(i).setCarteAzione(getListaazioni().get(tempint));

			// Elimino la carta dalla lista
			getListaazioni().remove(tempint);
		}
	}

	/**
	 * 
	 * Randomizzo il primogiocatore
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void randomPrimogiocatore() {
		Random rnd = new Random();
		tempint = rnd.nextInt(arraygiocatori.size() - 1) + 1;
		primogiocatore = tempint;
	}

	/**
	 * 
	 * Trucco la corsa con le carte azione
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void truccoCorsa() {
		for (int i = 0; i < ngiocatori; i++) {
			List<CarteAzione> carteplayer = new ArrayList<CarteAzione>();
			carteplayer = arraygiocatori.get(i).getCarteAzione();
			Write.write("[" + arraygiocatori.get(i).getNome().toUpperCase()
					+ "] Scegli la carta azione da giocare:");
			// Print carte azione del player
			for (int j = 0; j < carteplayer.size(); j++) {
				Write.write(j + ") " + carteplayer.get(j).toString());
			}
			// Scelta Carta
			int k = -1;
			do {
				Write.write("Seleziona la Carta Azione da giocare: ");
				k = Read.readInt();
			} while (k < 0 || k > carteplayer.size() - 1);
			// Scelta Scuderia
			Write.write("A quale corsia vuoi applicarla?");
			for (int n = 0; n < listascuderie.size(); n++) {
				Write.write(n + " ) " + listascuderie.get(n).getColore());
			}
			int s = 0;
			do {
				Write.write("Seleziona corsia: ");
				s = Read.readInt();
			} while (s < 0 || s > listascuderie.size() - 1);

			// Salvo la carta nella scuderia corrispondente
			listascuderie.get(s).setCarteAzione(carteplayer.get(k));

			// Rimuovo Carta dalla lista del player
			arraygiocatori.get(i).removeCarteAzione(k);

		}
	}

	// CORSA WOrk in progress
	public void corsa() {
		/*
		 * do{ movimento(listascuderie); sprint(listascuderie);
		 * posizione(listascuderie); }while(tutti arrivati);
		 */
	}

	/**
	 * 
	 * Riceve la lista delle scuderie, leggo una carta movimento e assegno un
	 * movimento ad ogni scuderia
	 * 
	 * @param listascuderie
	 * @exceptions
	 * 
	 * @see
	 */
	public void movimento(List<Scuderia> listascuderie) {
		// Selezione una linea random dalla Lista delle carte movimento
		Random r = new Random();
		int j = r.nextInt(listacartemovimento.size());
		String randomString = listacartemovimento.get(j);

		// Elimino la linea dalla lista
		listacartemovimento.remove(j);

		// Analizzo la stringa e Salvo il movimento corretto
		Scanner scannerString = new Scanner(randomString);
		for (int i = 0; i < (randomString.length()) / 2; i++) {
			movimenti.add(scannerString.nextInt());
		}
		
		// Chiudo lo scanner
		scannerString.close();

		for (int i = 0; i < listascuderie.size(); i++) {
			int quotazione = listascuderie.get(i).getquotazione();
			listascuderie.get(i).setMovimento(
					movimenti.get(quotazione -2));
		}
	}

	/**
	 * 
	 * Riceve la lista delle scuderie e imposta lo sprint a 1 di 2 scuderie
	 * diverse
	 * 
	 * @param listascuderie
	 * @exceptions
	 * 
	 * @see
	 */
	public void sprint(List<Scuderia> listascuderie) {
		Random rnd = new Random();
		int i = rnd.nextInt(colori.length);
		int j = rnd.nextInt(colori.length);
		listascuderie.get(i).setSprint(1);
		if (i != j) {
			listascuderie.get(j).setSprint(1);
		}
	}

	// Posizione
	public void posizione(List<Scuderia> listascuderie) {

	}

	/**
	 * Questo metodo mi permette di effettuare le scommesse. riconosce da solo
	 * che giro di scommessa è se il primo o il secondo.
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void scommessa() {

		int size = arraygiocatori.size(); // indici array
		int tocca = primogiocatore - 1; // indice array primogiocatore
		String stemp;
		do {
			stemp = arraygiocatori.get(tocca).getNome();
			Write.write("tocca al giocatore" + tocca + 1);

			BufferedReader br;
			String stringtemp;
			char chartemp = 's';

			if (turnoscommessa == 2) {
				do {
					Write.write("il secondo giro di scommesse è facoltativo, vuoi piazzare "
							+ "una scommessa?\ndigita:\ns per scommettere\nn per saltare la scommmessa");
					br = new BufferedReader(new InputStreamReader(System.in));

					try {
						stringtemp = br.readLine();
						if (stringtemp.length() > 1) {
							Write.write("hai sbaglito a digitare");
							throw new NumberFormatException();
						}
						chartemp = stringtemp.charAt(0);
					} catch (IOException e1) {
						Write.write("errore di flusso");
						chartemp = 'e';
					} catch (NumberFormatException e2) {
						Write.write("non hai inserito un carattere valido");
						chartemp = 'e';
					}
				} while (chartemp != 's' && chartemp != 'n');

			}
			if (turnoscommessa == 1 || (turnoscommessa == 2 && chartemp == 's')) {

				Write.write("\n su che scuderia vuoi scommettere?"
						+ "digita il colore della scuderia e premi invio\n\n"
						+ "nero-verde-blu-giallo-bianco-rosso");
				/*
				 * potrei inserire un "queste sono le quotazioni attuali" e
				 * faccio uno stampa a video delle scuderie associate alla loro
				 * quotazione
				 */
				String string;
				do {
					string = Read.readString();
				} while (string.equals("nero") || string.equals("verde")
						|| string.equals("blu") || string.equals("rosso")
						|| string.equals("giallo") || string.equals("bianco"));

				int a = 0;
				int trovato = 0;
				do {
					if (string == listascuderie.get(a).getColore()) {
						trovato = 1;
					} else {
						a++;
					}
				} while (trovato == 0);

				Scommessa scommessa = new Scommessa();
				scommessa.setnomegiocatore(stemp);
				listascuderie.get(a).getscommessa().add(scommessa);
				listascuderie.get(a).effettuascommessa();
			}
			tocca++;
			chartemp = 's';
		} while (tocca == primogiocatore - 1);
		if (turnoscommessa == 1) {
			turnoscommessa = 2;
		}
		if (turnoscommessa == 2) {
			turnoscommessa = 1;
		}
	}

	// getter
	/**
	 * @return the arraygiocatori
	 */
	public static List<Giocatore> getarraygiocatori() {
		return arraygiocatori;
	}

	public int getflagscommessa() {
		return flagscommessa;
	}

	/**
	 * @return the primogiocatore
	 */
	public int getprimogiocatore() {
		return primogiocatore;
	}

	// setter

	public static void setflagscommessa(int temp) {
		flagscommessa = temp;
	}

	public void aggiornaprimogiocatore() {
		int estremo;
		estremo = arraygiocatori.size();
		if (primogiocatore == estremo) {
			primogiocatore = 1;
		} else {
			primogiocatore += 1;
		}
	}

	/**
	 * @return the listaazioni
	 */
	public List<CarteAzione> getListaazioni() {
		return listaazioni;
	}

	/**
	 * @param listaazioni
	 *            the listaazioni to set
	 */
	public void setListaazioni(List<CarteAzione> listaazioni) {
		this.listaazioni = listaazioni;
	}

	/**
	 * 
	 * Metodo che trova le scuderie in posizione prima e ultima
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void checkPosizioni() {
		// Trovo la prima e l'utlima scuderia
		int max = listascuderie.get(0).getPosizione();
		int min = listascuderie.get(0).getPosizione();
		for (int i = 1; i < listascuderie.size(); i++) {
			if (listascuderie.get(i).getPosizione() < min) {
				min = listascuderie.get(i).getPosizione();
			}

			if (listascuderie.get(i).getPosizione() > max) {
				max = listascuderie.get(i).getPosizione();
			}
		}
		for (int j = 0; j < listascuderie.size(); j++) {
			if (listascuderie.get(j).getPosizione() == max) {
				listascuderie.get(j).setPrimo(true);
			}
			if (listascuderie.get(j).getPosizione() == min) {
				listascuderie.get(j).setUltimo(true);
			}
		}
	}

	/**
	 * 
	 * Cerco le Scuderie che hanno la stessa posizione dopo il traguardo
	 * 
	 * @return
	 * @exceptions
	 * 
	 * @see
	 */
	public List<Scuderia> findFotofinish() {

		/* HashMap di tutti le posizioni analizzate */
		HashMap<Integer, Scuderia> posizioni = new HashMap<Integer, Scuderia>();
		/* Lista delle scuderie con la stessa posizione dopo il traguardo */
		List<Scuderia> fotofinish = new ArrayList<Scuderia>();
		for (Scuderia scuderia : listascuderie) {
			if (posizioni.containsKey(scuderia.getPosizione())) {
				fotofinish.add(scuderia);
				fotofinish.add(posizioni.get(scuderia.getPosizione()));
			}
			posizioni.put(scuderia.getPosizione(), scuderia);

		}

		return fotofinish;
	}

	
	//TODO SISTEMARE INSIME AL CONTROLLO DELL'ARRIVO
	/**
	 * 
	 * Imposto chi vince il fotofinish, confrontando le quotazioni e
	 * controllando se gli è stata applicata una carta azione che modifica il
	 * fotofinish
	 * 
	 * @param fotofinish
	 * @exceptions
	 * 
	 * @see
	 */
	public int checkFotofinish(List<Scuderia> fotofinish) {
		// Controllo se è stata applicata una carta azione
		for (int j = 0; j < fotofinish.size(); j++) {
			if (fotofinish.get(j).getFotofinish() == 1) {
				return (1);
			}
		}
		// Trovo la quotazione massima
		int max = fotofinish.get(0).getquotazione();
		int min = fotofinish.get(0).getquotazione();
		for (int i = 1; i < fotofinish.size(); i++) {
			if (fotofinish.get(i).getPosizione() < min) {
				min = fotofinish.get(i).getquotazione();
			}

			if (fotofinish.get(i).getquotazione() > max) {
				max = fotofinish.get(i).getquotazione();
			}
		}

		for (int j = 0; j < fotofinish.size(); j++) {
			if ((fotofinish.get(j).getquotazione() == max)
					&& (fotofinish.get(j).getFotofinish() == -1)) {
				fotofinish.get(j).setFotofinish(1);
			} else if ((fotofinish.get(j).getquotazione() == max - 1)
					&& (fotofinish.get(j).getFotofinish() == -1)) {
				fotofinish.get(j).setFotofinish(1);
			}
		}
		
		return(0);

	}
}