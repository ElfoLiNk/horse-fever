/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author matteo
 * 
 */
public class Partita {
	private int turni = 0;
	private int ngiocatori = 0;
	private int primogiocatore = 0;
	private static List<Giocatore> arraygiocatori = new ArrayList<Giocatore>();
	private List<Scuderia> listascuderie = new ArrayList<Scuderia>();
	private List<CartePersonaggio> listapersonaggi;
	private List<CarteAzione> listaazioni;
	private int tempint;
	private static int flagscommessa = 1;
	private int turnoscommessa = 1;
	private String[] colori = { "NERO", "BLU", "VERDE", "ROSSO", "GIALLO",
			"BIANCO" };
	private int[] movimenti = new int[colori.length];
	private List<String> listacartemovimento = new ArrayList<String>();
	private List<Scuderia> arrivati = new ArrayList<Scuderia>();
	private List<Scuderia> classifica = new ArrayList<Scuderia>();

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
	 * Richiesta del numero di giocatori a terminale e imposto il numero di
	 * turni necessari
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

		if (ngiocatori == 2 || ngiocatori == 3 || ngiocatori == 6) {
			turni = 6;
		}
		if (ngiocatori == 4) {
			turni = 4;
		}
		if (ngiocatori == 5) {
			turni = 5;
		}
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
		// new list of values
		final List<Integer> list = new ArrayList<Integer>();

		// seed our random generator with the current time
		final Random rnd = new Random(System.currentTimeMillis());

		// keep trying to add numbers until we have the proper number
		while (list.size() < 6) {
			int num = rnd.nextInt(6) + 2;

			if (!list.contains(num)) {
				list.add(num);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			listascuderie.get(i).setquotazione(list.get(i));
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
	 * @return the turni
	 */
	public int getTurni() {
		return turni;
	}

	/**
	 * @param turni
	 *            the turni to set
	 */
	public void setTurni(int turni) {
		this.turni = turni;
	}

	/**
	 * 
	 * Randomizzo il primogiocatore, impostandolo uguale al indice dell'array
	 * dei giocatori
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void randomPrimogiocatore() {
		Random rnd = new Random();
		tempint = rnd.nextInt(arraygiocatori.size());
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
		int cartedagiocare = ngiocatori * 2;
		do {
			boolean finisco = true;
			for (int i = primogiocatore; i < ngiocatori && finisco; i++) {

				List<CarteAzione> carteplayer = arraygiocatori.get(i)
						.getCarteAzione();
				Write.write("\n["
						+ arraygiocatori.get(i).getNome().toUpperCase()
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
					Write.write(n + " ) " + listascuderie.get(n).getColore()
							+ "    Carte Applicate: "
							+ listascuderie.get(n).getCarteAzione().size());
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

				// Check per ciclare tutti i giocatori
				if (i == ngiocatori - 1 && primogiocatore > 0) {
					i = -1;
				} else if (i == primogiocatore - 1) {
					finisco = false;
				}
				cartedagiocare--;
			}
		} while (cartedagiocare > 0);
	}

	/**
	 * Controlla se tutte le scuderie sono arrivate alla fine della corsa
	 * 
	 * @return true se sono tutte arrivate, false se non sono ancora tutte
	 *         arrivate
	 * @exceptions
	 * 
	 * @see
	 */
	private boolean checkArrivati() {
		int arrivate = 0;
		for (Scuderia scuderia : listascuderie) {
			if (scuderia.isArrivato()) {
				arrivate++;
			}
		}
		if (arrivate == listascuderie.size()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * Leggo una carta movimento e assegno un movimento ad ogni scuderia
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void movimento() {
		// Selezione una linea random dalla Lista delle carte movimento
		Random r = new Random();
		int j = r.nextInt(listacartemovimento.size());
		String randomString = listacartemovimento.get(j);

		// Elimino la linea dalla lista
		listacartemovimento.remove(j);

		// Debug
		Write.write(randomString);

		// Analizzo la stringa e Salvo il movimento corretto
		Scanner scannerString = new Scanner(randomString);
		for (int i = 0; i < 6; i++) {
			movimenti[i] = scannerString.nextInt();
		}

		// Salvo i movimenti nelle scuderie
		for (Scuderia scuderia : listascuderie) {
			switch (scuderia.getquotazione()) {
			case (2):
				scuderia.setMovimento(movimenti[0]);
				break;
			case (3):
				scuderia.setMovimento(movimenti[1]);
				break;
			case (4):
				scuderia.setMovimento(movimenti[2]);
				break;
			case (5):
				scuderia.setMovimento(movimenti[3]);
				break;
			case (6):
				scuderia.setMovimento(movimenti[4]);
				break;
			case (7):
				scuderia.setMovimento(movimenti[5]);
				break;
			default:
				break;
			}
		}
		// Chiudo lo scanner
		scannerString.close();

		for (int i = 0; i < listascuderie.size(); i++) {
			int quotazione = listascuderie.get(i).getquotazione();
			listascuderie.get(i).setMovimento(movimenti[quotazione - 2]);
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
	public void sprint() {
		Random rnd = new Random();
		int i = rnd.nextInt(colori.length);
		int j = rnd.nextInt(colori.length);
		listascuderie.get(i).setSprint(1);
		if (i != j) {
			listascuderie.get(j).setSprint(1);
		}
	}

	/**
	 * 
	 * Controllo se il cavallo è gia arrivato o no, se non è ancora arrivato
	 * sommo la sua posizione col movimento, se gia arrivato applico le carte
	 * traguardo
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void posizione() {
		for (int i = 0; i < listascuderie.size(); i++) {
			if (!listascuderie.get(i).isArrivato()) {

				listascuderie.get(i).setPosizione(
						listascuderie.get(i).getPosizione()
								+ listascuderie.get(i).getMovimento());
				if (listascuderie.get(i).getSprint() > 0) {
					listascuderie.get(i).setPosizione(
							listascuderie.get(i).getPosizione()
									+ listascuderie.get(i).getSprint());
					// Reimposto a -1 lo sprint
					listascuderie.get(i).setSprint(-1);
				}

			}
			if (listascuderie.get(i).getPosizione() >= 12) {
				listascuderie.get(i).setArrivato(true);
				listascuderie.get(i).carteAzioneTraguardo();
				if (listascuderie.get(i).isArrivato()
						&& !classifica.contains(listascuderie.get(i))) {
					arrivati.add(listascuderie.get(i));
				}

			}

			Write.write(listascuderie.get(i).getColore() + " : "
					+ listascuderie.get(i).getPosizione() + " Arrivato  "
					+ listascuderie.get(i).isArrivato()
					+ "         Quotazione: "
					+ listascuderie.get(i).getquotazione());
		}
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
		int eliminato = 1;
		int valido = 1;
		int size = arraygiocatori.size()-1; // indici array
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

			//controllo se il giocatore è ancora in partita da finire
			int soldi;
			int pv;

			soldi = arraygiocatori.get(tocca).getSoldi();
			pv = arraygiocatori.get(tocca).getPv();
			if(pv * 100 > soldi){
				valido=0;
				Write.write("il giocatore non ha abbastanza danari per effettuare la scommessa" +
						"minima, pertanto perde 2 punti vittoria!");
				arraygiocatori.get(tocca).aggiornapv(-2);

			}

			pv = arraygiocatori.get(tocca).getPv();
			if(pv < 0){
				Write.write("il giocatore non ha abbastanza punti vittoria, per tanto" +
						"perde la partita e viene eliminato" +
						"ciao ciao "+stemp);
				eliminato = 0;

			}




			if (valido ==1 && (turnoscommessa == 1 || (turnoscommessa == 2 && chartemp == 's'))) {

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
				scommessa.setNomegiocatore(stemp);
				listascuderie.get(a).getscommessa().add(scommessa);
				listascuderie.get(a).effettuascommessa();
			}
			if(eliminato == 0){
				arraygiocatori.remove(tocca);
				size--;
			}

			tocca++;
			if(tocca > size)
				tocca = 0;
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

	/**
	 * @return the flagscommessa
	 */
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
	/**
	 * @param temp
	 *            the flagscommessa to set
	 */
	public static void setflagscommessa(int temp) {
		flagscommessa = temp;
	}

	/**
	 * 
	 * Aggiorna il primo giocatore in senso orario
	 *
	 * @exceptions
	 *
	 * @see
	 */
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
	 * Metodo che riempie la lista classifica con le scuderie in ordine di arrivo
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void classifica(List<Scuderia> scuderie) {
		int max = 0;
		// int min = scuderie.get(0).getPosizione();
		List<Scuderia> fotofinish = new ArrayList<Scuderia>();
		int idmax = -1;
		
		while (scuderie.size() > 0) {
			max = 0;
			for (int i = 0; i < scuderie.size(); i++) {
				/*
				 * if (scuderie.get(i).getPosizione() < min) { min =
				 * scuderie.get(i).getPosizione(); }
				 */

				if (scuderie.get(i).getPosizione() > max) {
					max = scuderie.get(i).getPosizione();
					idmax = i;
				} else if (max == scuderie.get(i).getPosizione()) {
					// Aggiungo al fotofinish la scuderia con quotazione uguale
					// al massimo
					fotofinish.add(scuderie.get(i));
					// Rimuovo nuova scuderia con quotazione uguale al massimo
					scuderie.remove(scuderie.get(i));
					if (fotofinish.size() == 0) {
						// Aggiungo al fotofinish scuderia precedente con
						// quotazione uguale alla nuova
						fotofinish.add(scuderie.get(idmax));
						// Rimuovo scuderia precedente con quotazione uguale
						// alla nuova
						scuderie.remove(scuderie.get(idmax));
					}

				}
			}
			if (fotofinish.size() > 0) {
				checkFotofinish(fotofinish);
			} else {
				classifica.add(scuderie.get(idmax));
				scuderie.remove(scuderie.get(idmax));
			}
		}
		int i = 1;
		Write.write("\nCLASSIFICA\n");
		for(Scuderia scuderia : classifica){
			scuderia.setClassifica(i++);
			Write.write(scuderia.getColore()+ "   " +scuderia.getClassifica());
		}
	}


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
	public void checkFotofinish(List<Scuderia> fotofinish) {
		int max = 0;
		int idmax = -1;
		
		// Controllo se è stata applicata una carta azione fotofinish
		for (int j = 0; j < fotofinish.size(); j++) {
			if (fotofinish.get(j).getFotofinish() == 1) {
				classifica.add(classifica.size(), fotofinish.get(j));
				fotofinish.remove(j);
			} else if (fotofinish.get(j).getFotofinish() == 0) {
				classifica.add(classifica.size() + fotofinish.size(),
						fotofinish.get(j));
				fotofinish.remove(j);
			}

			while (fotofinish.size() > 0) {
				// Trovo la scuderia quotazione massima
				max = 0;
				for (int i = 0; i < fotofinish.size(); i++) {
					if (fotofinish.get(i).getquotazione() > max) {
						max = fotofinish.get(i).getquotazione();
						idmax = i;
					}else if (fotofinish.get(i).getquotazione() == max) {
						classifica.add(classifica.size() +i,
								fotofinish.get(i));
						fotofinish.remove(i);
					}
				}
				classifica.add(idmax + classifica.size() -1,
						fotofinish.get(idmax));
				fotofinish.remove(idmax);
			}
		}
	}

	/**
	 * Turno di gioco, caratterizzato da una scommessa, truccare la corsa e una
	 * seconda eventuale scommessa
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void turno() {

		int turno = 0;
		do {
			Write.write("\nTURNO " + (turno + 1));
			setCarteAzione();
			setCarteAzione();
			primoGiocatoreOrario();
			// scommessa();
			truccoCorsa();
			// scommessa();
			corsa();
			// pagascomessa();
			// aggiornaquotazioni();

			// Rimischio il mazzo
			setListe();
			turno++;
		} while (turno < turni);
	}

	/**
	 * 
	 * Fase di corsa del gioco:
	 * 
	 *
	 * @exceptions
	 *
	 * @see
	 */
	public void corsa() {
		boolean partenza = true;
		do {
			// Controllo carte
			for (Scuderia scuderia : listascuderie) {
				scuderia.checkCarteAzione();
				scuderia.checkLetteraCarteAzione();
			}
			CarteAzione azioni = new CarteAzione();
			movimento();
			if (partenza == true) {
				azioni.carteAzionePartenza(listascuderie);
				partenza = false;
			} else {
				azioni.carteAzioneMovimento(listascuderie);
			}
			sprint();
			azioni.carteAzioneSprint(listascuderie);
			posizione();
			azioni.carteAzioneFotofinish(listascuderie);
			classifica(arrivati);
		} while (checkArrivati() == false);
	}

	/**
	 * Sposto il primogiocatore in senso orario ogni nuovo turno
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	private void primoGiocatoreOrario() {
		if (primogiocatore < ngiocatori - 1) {
			primogiocatore += 1;
		}
		if (primogiocatore == ngiocatori - 1) {
			primogiocatore = 0;
		}
	}

}