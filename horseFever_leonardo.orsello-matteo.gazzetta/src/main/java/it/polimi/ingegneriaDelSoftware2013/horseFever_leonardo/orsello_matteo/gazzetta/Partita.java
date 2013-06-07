/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 * 
 * Classe che gestisce tutta la partita: Crea le liste dei mazzi Imposta i
 * giocatori Gestisce i turni e le corse
 * 
 * 
 * 
 * @see
 */
public class Partita {
	/**
	 * Liste delle carte del mazzo
	 */
	private List<CartePersonaggio> listapersonaggi;
	private List<CarteAzione> listaazioni;
	private List<String> listacartemovimento;

	private int turni = 0;
	private int ngiocatori = 0;
	private int primogiocatore = 0;
	private static List<Giocatore> arraygiocatori = new ArrayList<Giocatore>();
	private List<Scuderia> listascuderie = new ArrayList<Scuderia>();

	/**
	 * Gestione Scommesse
	 */
	private int tempint;
	private int flagscommessa = 1;

	private String[] colori = { "NERO", "BLU", "VERDE", "ROSSO", "GIALLO",
			"BIANCO" };

	private List<Scuderia> arrivati = new ArrayList<Scuderia>();
	private List<Scuderia> classifica = new ArrayList<Scuderia>();

	/**
	 * 
	 * Creo liste dei Mazzi Carte
	 * 
	 * @throws IOException
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void setListe() throws IOException {
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

		} while (ngiocatori < Parametri.MIN_GIOCATORI
				|| ngiocatori > Parametri.MAX_GIOCATORI);

		if (ngiocatori == Parametri.DUE || ngiocatori == Parametri.TRE
				|| ngiocatori == Parametri.SEI) {
			turni = Parametri.SEI;
		}
		if (ngiocatori == Parametri.QUATTRO) {
			turni = Parametri.QUATTRO;
		}
		if (ngiocatori == Parametri.CINQUE) {
			turni = Parametri.CINQUE;
		}

	}

	/**
	 * 
	 * Imposto il numero di segnalini per scuderia in base al numero di
	 * giocatori
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void setSegnalini() {
		if (ngiocatori == Parametri.DUE) {
			for (Scuderia scuderia : listascuderie) {
				scuderia.setSegnalino(Parametri.UNO);
			}
		}
		if (ngiocatori == Parametri.TRE) {
			for (Scuderia scuderia : listascuderie) {
				scuderia.setSegnalino(Parametri.DUE);
			}
		}
		if (ngiocatori == Parametri.QUATTRO) {
			for (Scuderia scuderia : listascuderie) {
				scuderia.setSegnalino(Parametri.TRE);
			}
			turni = Parametri.CINQUE;
		}
		if (ngiocatori == Parametri.CINQUE || ngiocatori == Parametri.SEI) {
			for (Scuderia scuderia : listascuderie) {
				scuderia.setSegnalino(Parametri.QUATTRO);
			}
			turni = Parametri.CINQUE;
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
			player.setScuderia(listapersonaggi.get(tempint).getQuotazione(),
					listascuderie);

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

		for (int i = 0; i < Parametri.MAX_SCUDERIE; i++) {

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
		// lista di valori
		final List<Integer> list = new ArrayList<Integer>();
		// Generatore random con seed l'ora
		final Random rnd = new Random(System.currentTimeMillis());

		// Continuo ad aggiungere un numero finche non trovo quello giusto
		while (list.size() < listascuderie.size()) {
			int num = rnd.nextInt(6) + 2;
			if (!list.contains(num)) {
				list.add(num);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			listascuderie.get(i).setQuotazione(list.get(i));
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
				Write.write("\n" + arraygiocatori.get(i).toString()
						+ "\nScegli la carta azione da giocare:");
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
		// Carta movimento
		int[] movimenti = new int[colori.length];

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
			switch (scuderia.getQuotazione()) {
			case (Parametri.DUE):
				scuderia.setMovimento(movimenti[0]);
				break;
			case (Parametri.TRE):
				scuderia.setMovimento(movimenti[1]);
				break;
			case (Parametri.QUATTRO):
				scuderia.setMovimento(movimenti[2]);
				break;
			case (Parametri.CINQUE):
				scuderia.setMovimento(movimenti[3]);
				break;
			case (Parametri.SEI):
				scuderia.setMovimento(movimenti[4]);
				break;
			case (Parametri.SETTE):
				scuderia.setMovimento(movimenti[5]);
				break;
			default:
				break;
			}
		}
		// Chiudo lo scanner
		scannerString.close();

		for (int i = 0; i < listascuderie.size(); i++) {
			int quotazione = listascuderie.get(i).getQuotazione();
			listascuderie.get(i).setMovimento(movimenti[quotazione - 2]);
		}
	}

	/**
	 * 
	 * Imposta lo sprint a 1 di 2 scuderie diverse
	 * 
	 * @exceptions
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
			if (listascuderie.get(i).getPosizione() >= Parametri.TRAGUARDO) {
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
					+ listascuderie.get(i).getQuotazione());
		}
	}

	/**
	 * 
	 * L'utente sceglie su che scuderia scommettere finche la scuderia scelta è
	 * valida e la scommessa non è dello stesso tipo di una gia effettuata dallo
	 * stesso giocatore
	 * 
	 * @param i
	 *            l'indice del array dei giocatori
	 * @exceptions
	 * 
	 * @see
	 */
	public void sceltaScuderiaScommessa(int i) {
		// Valida della scuderia scelta
		int scuderiavalida = 1;
		// Controllo sullo stesso tipo di scommessa
		int check = 1;
		// Indice scuderia
		int s;

		do {
			// L'utente sceglie una scuderia finche non ne sceglie una valida
			do {
				// l'utente sceglie la scuderia su cui scommettere
				s = Read.readScuderiaScommessa(listascuderie);

				// Verifico se la scuderia scelta puo avere ulteriori scommesse
				if (listascuderie.get(s).getSegnalino() == 0) {
					// Imposto la scuderia come non valida
					scuderiavalida = 0;
					Write.write("\nNon è possibile effettuare altre scommesse"
							+ "su questa scuderia. Cambia scuderia!.\n");
				} else {
					// Imposto la scuderia come valida e rimuovo un segnalino
					// dalla scuderia
					scuderiavalida = 1;
					listascuderie.get(s).aggiornaSegnalino(-1);
				}
			} while (scuderiavalida != 1);

			// Creo una nuova scommessa e inizio a impostare il nome del
			// giocatore e la aggiungo alla lista delle scommesse della scuderia
			// scelta
			Scommessa scommessa = new Scommessa();
			scommessa.setNomegiocatore(arraygiocatori.get(i).getNome());
			listascuderie.get(s).getscommessa().add(scommessa);

			// Effettuo la scommessa chiedendo i soldi al giocatore
			check = listascuderie.get(s).effettuaScommessa(i);
			// Rimuovo la scuderia se è dello stesso tipo di una già effettuata
			if (check == 0) {
				listascuderie.get(s).getscommessa()
						.remove(listascuderie.get(s).getscommessa().size() - 1);
			}
		} while (check != 1);
	}

	/**
	 * Secondo giro di scommesse in ordine anti orario a partire dall'ultimo
	 * giocatore
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void secondaScommessa() {

		char chartemp = 's';

		boolean finisco = true;
		for (int i = primogiocatore - 1; i > -1 && finisco; i--) {
			Write.write("Tocca al " + arraygiocatori.get(i).toString());

			// Controllo se il giocatore salta il secondo giro di scommesse
			if (arraygiocatori.get(i).getSalta() == 1) {
				Write.write("Il giocatore al primo giro di scommesse ha perso dei punti vittoria"
						+ " pertanto salta anche il secondo giro di scommessa");
			} else {

				int valido = 1;

				// Controllo se ha abbastanza soldi per fare una seconda
				// scommessa
				if (arraygiocatori.get(i).getPv() * 100 > arraygiocatori.get(i)
						.getSoldi()) {
					valido = 0;
					Write.write("Il giocatore non ha abbastanza danari per effettuare la scommessa minima, non puoi effettuare la seconda scommessa!");
				}

				if (valido == 1) {
					// Chiedo se vuole effettuare una seconda scommessa
					chartemp = Read.readSecondaScommessa();

					if (chartemp == 's') {
						// L'utente sceglie la scuderia su cui scommettere
						sceltaScuderiaScommessa(i);
					}
				}
			}
			// Resetto salta del giocatore che ha saltato la scommessa
			if (arraygiocatori.get(i).getSalta() == 1) {
				arraygiocatori.get(i).setSalta(0);
			}

			// Check per ciclare tutti i giocatori in senso anti orario
			if (i == 0 && primogiocatore > 0) {
				i = ngiocatori;
			} else if (i == primogiocatore) {
				finisco = false;
			}

		}
	}

	/**
	 * 
	 * Primo giro di scommesse in senso orario a partire dal primo giocatore
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void primascommessa() {
		int valido;

		// Ciclo tutti i giocatori
		boolean finisco = true;
		for (int i = primogiocatore; i < ngiocatori && finisco; i++) {
			do {
				Write.write("Tocca al " + arraygiocatori.get(i).toString());

				// Verifico la validità della scommessa minima
				if (arraygiocatori.get(i).getPv() * 100 > arraygiocatori.get(i)
						.getSoldi()) {
					Write.write("Il giocatore non ha abbastanza danari per effettuare la scommessa"
							+ " minima, pertanto perde 2 punti vittoria!");
					// Imposto la scommessa come non valida
					valido = 0;
					// Rimuovo 2 punti vittoria
					arraygiocatori.get(i).aggiornapv(-2);
					// Salta la seconda scommessa
					arraygiocatori.get(i).setSalta(1);

				}

				// Imposto a 1 valido perchè il giocatore puo effetturare la
				// scommessa
				valido = 1;

			} while (valido != 1);

			// L'utente sceglie la scuderia su cui scommettere
			sceltaScuderiaScommessa(i);

			// Check per ciclare tutti i giocatori in senso orario
			if (i == ngiocatori - 1 && primogiocatore > 0) {
				i = -1;
			} else if (i == primogiocatore - 1) {
				// Ho ciclato tutti i giocatori
				finisco = false;
			}
		}
	}

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
	public void setflagscommessa(int temp) {
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
		if (primogiocatore == estremo - 1) {
			primogiocatore = 0;
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
	 * Metodo che riempie la lista classifica con le scuderie in ordine di
	 * arrivo
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void classifica(List<Scuderia> scuderie) {
		int max = 0;
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
		for (Scuderia scuderia : classifica) {
			scuderia.setClassifica(i++);
			Write.write(scuderia.getColore() + "   " + scuderia.getClassifica());
		}
	}

	/**
	 * 
	 * Imposto chi vince il fotofinish, confrontando le quotazioni e
	 * controllando se gli è stata applicata una carta azione che modifica il
	 * fotofinish
	 * 
	 * @param fotofinish
	 *            Lista delle scuderie che partecipano al fotofinish
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
					if (fotofinish.get(i).getQuotazione() > max) {
						max = fotofinish.get(i).getQuotazione();
						idmax = i;
					} else if (fotofinish.get(i).getQuotazione() == max) {
						classifica
								.add(classifica.size() + i, fotofinish.get(i));
						fotofinish.remove(i);
					}
				}
				if (classifica.size() > 0) {
					classifica.add(idmax + classifica.size() - 1,
							fotofinish.get(idmax));
					fotofinish.remove(idmax);
				} else {
					classifica.add(idmax + classifica.size(),
							fotofinish.get(idmax));
					fotofinish.remove(idmax);
				}

			}
		}
	}

	/**
	 * Turno di gioco, caratterizzato da una scommessa, truccare la corsa e una
	 * seconda eventuale scommessa
	 * 
	 * @throws IOException
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void turno() throws IOException {

		int turno = 0;
		do {
			Write.write("\nTURNO " + (turno + 1));
			setCarteAzione();
			setCarteAzione();
			setSegnalini();

			primascommessa();
			truccoCorsa();
			secondaScommessa();
			corsa();
			for (int i = 0; i < 3; i++) {
				classifica.get(i).pagascommessa();
			}
			pagascuderie();
			checkeliminato();
			leaderboard();
			// Reset Carte Azione Player
			resetCarteAzione();
			// Reset Scommesse
			resetScommesse();
			// Rimischio il mazzo
			setListe();
			// Primogiocatore in senso orario
			aggiornaprimogiocatore();
			// Reset posizione e arrivo scuderie
			resetScuderie();
			turno++;

			// Controllo se è rimasto un solo giocatore che vince
			// automaticamente la partita
			if (arraygiocatori.size() == 1) {
				turno = turni;
			}
		} while (turno < turni);
		String vincitore = trovaVincitore();
		Write.write("\nVince il giocatore: " + vincitore.toUpperCase());

	}

	/**
	 * Resetta a 0 la posizione e imposta a false l'arrivo di ogni scuderia alla
	 * fine del turno. Svuoto anche le liste di arrivo, classifica e fotofinish.
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	private void resetScuderie() {
		for (Scuderia scuderia : listascuderie) {
			scuderia.setPosizione(0);
			scuderia.setArrivato(false);
			scuderia.setSprint(-1);
			scuderia.setFotofinish(-1);
		}
		arrivati.clear();
		classifica.clear();
	}

	/**
	 * Trova il giocatore con il maggior numero di punti vittoria, se 2
	 * giocatori hanno lo stesso numero di punti vittoria vince quello con piu
	 * soldi
	 * 
	 * @return il nome del giocatore con il maggior numero di punti vittoria
	 * @exceptions
	 * 
	 * @see
	 */
	public String trovaVincitore() {
		int max = 0;
		int idmax = -1;

		if (arraygiocatori.size() > 0) {
			// Ciclo tutti i giocatori rimasti
			for (int i = 0; i < arraygiocatori.size(); i++) {
				// Trovo il giocatore con i pv massimi
				if (arraygiocatori.get(i).getPv() > max) {
					max = arraygiocatori.get(i).getPv();
					idmax = i;

					// Se ci sono due giocatori con gli stessi pv vince quello
					// con più soldi
				} else if (max == arraygiocatori.get(i).getPv()
						&& arraygiocatori.get(i).getSoldi() > arraygiocatori
								.get(idmax).getSoldi()) {
					max = arraygiocatori.get(i).getPv();
					idmax = i;
				}
			}

		}
		return arraygiocatori.get(idmax).getNome();
	}

	/**
	 * Pagamento all’eventuale proprietario (le scuderie possono non essere di
	 * nessuno) della scuderia il cui cavallo arriva primo: 600 secondo: 400
	 * terzo: 200
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	private void pagascuderie() {
		for (Giocatore player : arraygiocatori) {
			if (player.getScuderia().equals(classifica.get(0).getColore())) {
				player.setSoldi(player.getSoldi() + 600);
			}
			if (player.getScuderia().equals(classifica.get(1).getColore())) {
				player.setSoldi(player.getSoldi() + 400);
			}
			if (player.getScuderia().equals(classifica.get(2).getColore())) {
				player.setSoldi(player.getSoldi() + 200);
			}
		}

	}

	/**
	 * Svuoto la lista delle carte azione di ogni giocatore e di ogni scuderia
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	private void resetCarteAzione() {
		for (Scuderia scuderia : listascuderie) {
			scuderia.resetCarteAzione();
		}
		for (Giocatore player : arraygiocatori) {
			player.resetCarteAzione();
		}

	}

	/**
	 * 
	 * Reset delle scommesse di tutte le scuderie
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	private void resetScommesse() {
		for (Scuderia scuderia : listascuderie) {
			scuderia.clearscommessa();
		}
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
			if (partenza) {
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
			aggiornaQuotazioni();
		} while (!checkArrivati());
	}

	/**
	 * Stampa la classfica dei giocatori con i dati aggiornati
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	private void leaderboard() {
		Write.write("\nCLASSIFICA GIOCATORI\n");
		for (Giocatore player : arraygiocatori) {
			Write.write(player.getNome().toUpperCase() + " "
					+ player.getScuderia() + " " + player.getSoldi() + " "
					+ player.getPv());
		}

	}

	/**
	 * 
	 * Aggiorna le quotazioni delle scuderie in base all'arrivo
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	private void aggiornaQuotazioni() {
		for (Scuderia scuderia : classifica) {
			scuderia.aggiornaQuotazioni(scuderia.getClassifica());
		}
		Write.write("\nQUOTAZIONI AGGIORNATE\n");
		for (Scuderia scuderia : listascuderie) {
			Write.write(scuderia.getColore() + " Quotazione: "
					+ scuderia.getQuotazione());
		}

	}

	/**
	 * @return la lista delle scuderie
	 * 
	 * @see Scuderia
	 */
	public List<Scuderia> getScuderie() {
		return listascuderie;
	}

	/**
	 * 
	 * Controllo se qualche giocatore non è piu in grado di giocare e lo elimino
	 * dalla partita
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	private void checkeliminato() {
		// Ciclo tutti i giocatori
		for (int i = 0; i < arraygiocatori.size(); i++) {

			int pv = arraygiocatori.get(i).getPv();
			int soldi = arraygiocatori.get(i).getSoldi();
			if (pv <= 2 && soldi < pv * 100) {
				Write.write("\nIl giocatore "
						+ arraygiocatori.get(i).getNome().toUpperCase()
						+ " non ha né abbastanza soldi né abbastanza punti vittoria"
						+ " per proseguire la partita, pertento è eliminato"
						+ "\nCIAO CIAO "
						+ arraygiocatori.get(i).getNome().toUpperCase());
				arraygiocatori.remove(i);
				ngiocatori--;
				i--;
			}
		}
	}

}