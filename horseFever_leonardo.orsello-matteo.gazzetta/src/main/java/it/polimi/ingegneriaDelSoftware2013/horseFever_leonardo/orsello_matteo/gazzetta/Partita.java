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
	/**
	 * Carta movimento
	 */
	private int[] movimenti = new int[colori.length];

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
		if (ngiocatori == 2) {
			for (Scuderia scuderia : listascuderie) {
				scuderia.setSegnalino(1);
			}
		} else if (ngiocatori == 3) {
			for (Scuderia scuderia : listascuderie) {
				scuderia.setSegnalino(2);
			}

		} else if (ngiocatori == 4) {
			for (Scuderia scuderia : listascuderie) {
				scuderia.setSegnalino(3);
			}
			turni = 4;
		} else if (ngiocatori == 5) {
			for (Scuderia scuderia : listascuderie) {
				scuderia.setSegnalino(4);
			}
			turni = 5;
		} else if (ngiocatori == 6) {
			for (Scuderia scuderia : listascuderie) {
				scuderia.setSegnalino(4);
			}
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
				Write.write("\n[ "
						+ arraygiocatori.get(i).getNome().toUpperCase()
						+ " ] (" + arraygiocatori.get(i).getInterpreta()
						+ ") Scuderia: " + arraygiocatori.get(i).getScuderia()
						+ " Soldi: " + arraygiocatori.get(i).getSoldi()
						+ " Punti Vittoria: " + arraygiocatori.get(i).getPv()
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
			int quotazione = listascuderie.get(i).getQuotazione();
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
					+ listascuderie.get(i).getQuotazione());
		}
	}

	/**
	 * 
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void secondaScommessa() {

		int tocca = primogiocatore;
		if (tocca < 0)
			tocca = arraygiocatori.size() - 1;
		int salta = 0;
		String stemp;
		BufferedReader br;
		String stringtemp;
		char chartemp = 's';

		do {

			stemp = arraygiocatori.get(tocca).getNome();
			Write.write("Tocca al giocatore : " + stemp.toUpperCase()
					+ " Scuderia: " + arraygiocatori.get(tocca).getScuderia());
			salta = arraygiocatori.get(tocca).getSalta();
			if (salta == 1) {
				Write.write("il giocatore al primo giro di scommesse ha perso dei punti vittoria"
						+ "pertanto salta anche il secondo giro di scommessa");
			}
			if (salta != 1) {

				// controllo se ha abbastanza soldi per fare una seconda
				// scommessa

				int soldi;
				int pv;
				int valido = 1;

				soldi = arraygiocatori.get(tocca).getSoldi();
				pv = arraygiocatori.get(tocca).getPv();
				if (pv * 100 > soldi) {
					valido = 0;
					Write.write("Il giocatore non ha abbastanza danari per effettuare la scommessa"
							+ "minima, non puoi effettuare la seconda scommessa!");
				}
				if (valido == 1) {
					do {
						Write.write("il secondo giro di scommesse è facoltativo, vuoi piazzare "
								+ "una scommessa?\ndigita:\ns per scommettere\nn per saltare la scommmessa");
						br = new BufferedReader(
								new InputStreamReader(System.in));

						try {
							stringtemp = br.readLine();
							if (stringtemp.length() > 1) {
								// Write.write("hai sbagliato a digitare");
								throw new NumberFormatException();
							}
							if (stringtemp.length() < 1)
								chartemp = 'e';
							else
								chartemp = stringtemp.charAt(0);
						} catch (IOException e1) {
							Write.write("errore di flusso");
							chartemp = 'e';
						} catch (NumberFormatException e2) {
							Write.write("non hai inserito un carattere valido");
							chartemp = 'e';
						}
						if (chartemp != 's' && chartemp != 'n')
							Write.write("hai sbagliato a digitare, riprova");
					} while (chartemp != 's' && chartemp != 'n');

					int valid = 1;
					int check = 1;
					int s = 0;
					if (chartemp == 's') {
						do {
							do {
								Write.write("Su che scuderia vuoi scommettere?");
								for (int n = 0; n < listascuderie.size(); n++) {
									Write.write(n + " ) "
											+ listascuderie.get(n).getColore());
								}
								do {
									Write.write("Seleziona scuderia: ");
									s = Read.readInt();
									if (s > listascuderie.size() - 1)
										Write.write("non hai digitato un valore valido, riprova");
								} while (s < 0 || s > listascuderie.size() - 1);

								int n;
								n = listascuderie.get(s).getSegnalino();
								if (n == 0) {
									valid = 0;
									Write.write("non è possibile effettuare altre scommesse"
											+ "su questa scuderia. cambia scuderia.");
								} else {
									valid = 1;
									listascuderie.get(s).aggiornaSegnalino(-1);
								}

							} while (valid != 1);

							Scommessa scommessa = new Scommessa();
							scommessa.setNomegiocatore(stemp);
							listascuderie.get(s).getscommessa().add(scommessa);
							check = listascuderie.get(s).effettuaScommessa(
									tocca);
							if (check == 0) {
								int m;
								m = listascuderie.get(s).getscommessa().size();
								listascuderie.get(s).getscommessa()
										.remove(m - 1);
							}
						} while (check != 1);

					}
				}
			}
			if (arraygiocatori.get(tocca).getSalta() == 1)
				arraygiocatori.get(tocca).setSalta(0);
			tocca--;
			if (tocca < 0)
				tocca = arraygiocatori.size() - 1;

		} while (tocca != primogiocatore);
	}

	/**
	 * 
	 * {Descrizione}
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void primascommessa() {
		int eliminato = 0;
		int valido = 1;
		int size;
		int tocca = primogiocatore; // indice array primogiocatore
		String stemp;

		do {

			size = arraygiocatori.size() - 1; // indice array estremo
			do {
				stemp = arraygiocatori.get(tocca).getNome();
				Write.write("Tocca al giocatore : " + stemp.toUpperCase()
						+ " Scuderia: "
						+ arraygiocatori.get(tocca).getScuderia());

				int soldi;
				int pv;
				valido = 1;

				soldi = arraygiocatori.get(tocca).getSoldi();
				pv = arraygiocatori.get(tocca).getPv();
				if (pv * 100 > soldi) {
					valido = 0;
					Write.write("Il giocatore non ha abbastanza danari per effettuare la scommessa"
							+ "minima, pertanto perde 2 punti vittoria!");
					arraygiocatori.get(tocca).aggiornapv(-2);
					arraygiocatori.get(tocca).setSalta(1);

				}

				pv = arraygiocatori.get(tocca).getPv();
				if (pv < 0) {
					Write.write("Il giocatore non ha abbastanza punti vittoria, per tanto"
							+ "perde la partita e viene eliminato"
							+ "CIAO CIAO" + stemp.toUpperCase());
					eliminato = 1;

				}

				if (eliminato == 1) {
					eliminato = 0;
					if (tocca <= primogiocatore)
						primogiocatore--;
					arraygiocatori.remove(tocca);
				}
				if (valido == 0)
					tocca++;

			} while (valido != 1);

			int valid = 1;
			int check = 1;
			int s = 0;

			do {
				do {
					Write.write("Su che scuderia vuoi scommettere?");
					for (int n = 0; n < listascuderie.size(); n++) {
						Write.write(n + " ) "
								+ listascuderie.get(n).getColore()
								+ "     Scommesse: "
								+ listascuderie.get(n).getscommessa().size());
					}
					do {
						Write.write("Seleziona scuderia: ");
						s = Read.readInt();
						if (s > listascuderie.size() - 1)
							Write.write("non hai digitato un valore valido, riprova");
					} while (s < 0 || s > listascuderie.size() - 1);

					int n;
					n = listascuderie.get(s).getSegnalino();
					if (n == 0) {
						valid = 0;
						Write.write("\nNon è possibile effettuare altre scommesse"
								+ "su questa scuderia. Cambia scuderia!.\n");
					} else {
						valid = 1;
						listascuderie.get(s).aggiornaSegnalino(-1);
					}

				} while (valid != 1);

				Scommessa scommessa = new Scommessa();
				scommessa.setNomegiocatore(stemp);
				listascuderie.get(s).getscommessa().add(scommessa);
				check = listascuderie.get(s).effettuaScommessa(tocca);
				if (check == 0) {
					int m;
					m = listascuderie.get(s).getscommessa().size();
					listascuderie.get(s).getscommessa().remove(m - 1);
				}
			} while (check != 1);

			tocca++;
			if (tocca > size) {
				tocca = 0;
			}
		} while (tocca != primogiocatore);
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
			primascommessa();
			truccoCorsa();
			secondaScommessa();
			corsa();
			for (int i = 0; i < 3; i++) {
				classifica.get(i).pagascommessa();
			}
			pagascuderie();
			leaderboard();
			// Reset Carte Azione Player
			resetCarteAzione();
			// Reset Scommesse
			resetScommesse();
			// Rimischio il mazzo
			setListe();
			turno++;
		} while (turno < turni);

		Write.write("Vince il giocatore: " + trovaVincitore());
	}

	/**
	 * {Descrizione}
	 * 
	 * @return
	 * @exceptions
	 * 
	 * @see
	 */
	private String trovaVincitore() {
		int max = 0;
		int idmax = -1;

		while (arraygiocatori.size() > 0) {
			max = 0;
			for (int i = 0; i < arraygiocatori.size(); i++) {
				if (arraygiocatori.get(i).getPv() > max) {
					max = arraygiocatori.get(i).getPv();
					idmax = i;
				} else if (max == arraygiocatori.get(i).getPv()) {
					// gestione stessi pv
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
			if (player.getScuderia() == classifica.get(0).getColore()) {
				player.setSoldi(player.getSoldi() + 600);
			}
			if (player.getScuderia() == classifica.get(1).getColore()) {
				player.setSoldi(player.getSoldi() + 400);
			}
			if (player.getScuderia() == classifica.get(2).getColore()) {
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
			aggiornaQuotazioni();
		} while (checkArrivati() == false);
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

}