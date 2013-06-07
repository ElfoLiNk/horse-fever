/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author leonardo
 * 
 */
public class Scuderia {

	private int posizione = 0;
	private int segnalino;
	private int classifica = 0;
	private int quotazione;
	private boolean arrivato = false;
	private List<Scommessa> scommessa = new ArrayList<Scommessa>();
	private String colore;
	private List<CarteAzione> listacarteazione = new ArrayList<CarteAzione>();
	private int movimento = 0;
	// Sprint inizializzato a -1 per controllo carte azione
	private int sprint = -1;
	private boolean ultimo;
	private boolean primo;
	// 1 Vince fotofinish , 0 Perde fotofinish , -1 Controllo le quotazioni
	private int fotofinish = -1;

	// getter

	public int getQuotazione() {
		return quotazione;
	}

	/**
	 * @return the colore
	 */
	public String getColore() {
		return colore;
	}

	/**
	 * @return the movimento
	 */
	public int getMovimento() {
		return movimento;
	}

	public List<Scommessa> getscommessa() {
		return scommessa;
	}

	/**
	 * @param scommessa
	 *            the scommessa to set
	 */
	public void setScommessa(List<Scommessa> scommessa) {
		this.scommessa = scommessa;
	}

	public List<CarteAzione> getCarteAzione() {
		return listacarteazione;
	}

	/**
	 * @return the sprint
	 */
	public int getSprint() {
		return sprint;
	}

	/**
	 * @param sprint
	 *            the sprint to set
	 */
	public void setSprint(int sprint) {
		this.sprint = sprint;
	}

	// setter
	public void setColore(String colore) {
		this.colore = colore;
	}

	public void setQuotazione(int temp) {
		quotazione = temp;
	}

	/**
	 * @param movimento
	 *            the movimento to set
	 */
	public void setMovimento(int movimento) {
		this.movimento = movimento;
	}

	/**
	 * @param carteAzione
	 */
	public void setCarteAzione(CarteAzione carteAzione) {
		listacarteazione.add(carteAzione);
	}

	/**
	 * 
	 * Chiedo i soldi che l'utente vuole scommettere su questa scuderia e il
	 * tipo di scommessa
	 * 
	 * @param tocca
	 *            indice dell'array dei giocatori
	 * @return 0 se la scommessa è dello stesso tipo di una già effettuata 1 se
	 *         è valida
	 * @exceptions
	 * 
	 * @see
	 */
	public int effettuaScommessa(int tocca) {

		int flagsc = 1;
		char chartemp = 'v';
		Scommessa.Tiposcommessa tiposcommessatemp;

		int i = scommessa.size() - 1;
		String nomegiocatore = scommessa.get(i).getNomegiocatore();
		int soldigiocatore = Partita.getarraygiocatori().get(tocca).getSoldi();
		int pvgiocatore = Partita.getarraygiocatori().get(tocca).getPv();
		int soldiscommessa;

		Write.write("Quanti soldi vuoi scommettere?");

		boolean tryAgain;
		do {
			tryAgain = false;
			soldiscommessa = Read.readInt();
			// controllo se il giocatore ha abbastanza soldi per effettuare la
			// scommessa dell'importo da lui scelto
			if (soldiscommessa > soldigiocatore) {
				Write.write("Non puoi scommettere più di quanti soldi possiedi.\n"
						+ " Inserisci un nuovo importo:");
				tryAgain = true;
			}
			// controllo validità scommessa : scommessa>=pv*100
			if (soldiscommessa < pvgiocatore * 100) {
				Write.write("L'importo scommesso non è valido, questo deve essere\n"
						+ "come minimo pari a \"punti vittoria posseduti\" * 100.\n Inserire"
						+ " un nuovo importo :");
				tryAgain = true;
			}
		} while (tryAgain);

		// Salvo i soldi scommessi nella scommessa
		scommessa.get(i).setSoldi(soldiscommessa);

		// Chiedo se scommessa v (vincente) o p (piazzata) e lo imposto nella
		// scommessa
		chartemp = Read.readTipoScommessa();
		scommessa.get(i).setTiposcommessa(chartemp);

		// Verifico che il giocatore non ha gia effettuato questo tipo di
		// scommessa
		tiposcommessatemp = scommessa.get(i).getTiposcommessa();
		for (int a = 0; a < i; a++) {
			if (nomegiocatore.equals(scommessa.get(a).getNomegiocatore())
					&& tiposcommessatemp == scommessa.get(a).getTiposcommessa()) {
				Write.write("\nQuesta scommessa è già stata effettuata, non è possibile ripeterela stessa scommessa."
						+ "\nE' possibile fare due scommesse sulla stessa scuderia, ma bisogna modificare il tipo di scommessa");
				flagsc = 0;
			}
		}

		if (flagsc == 1) {

			// tolgo al giocatore i soldi che ha scommesso
			Partita.getarraygiocatori().get(tocca)
					.aggiornaSoldi(-soldiscommessa);
		}

		return flagsc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " ) " + colore + "    Scommesse: " + scommessa.size()
				+ "  Quotazione: 1:" + quotazione;
	}

	/**
	 * 
	 * Pago le scommesse dopo di che resetto la lista scommessa
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void pagascommessa() {
		// Ciclo tutte le scommesse
		for (int a = scommessa.size() - 1; a >= 0; a--) {
			// Ciclo tutti i giocatori
			for (int z = 0; z < Partita.getarraygiocatori().size(); z++) {
				// Verifico che il giocatore sia lo stesso che ha effettuato la
				// scommessa
				if (scommessa.get(a).getNomegiocatore()
						.equals(Partita.getarraygiocatori().get(z).getNome())) {

					// Pago le scommesse vincenti
					if (scommessa.get(a).getTiposcommessa() == Scommessa.Tiposcommessa.VINCENTE
							&& classifica == 1) {
						Partita.getarraygiocatori()
								.get(z)
								.aggiornaSoldi(
										scommessa.get(a).getSoldi()
												* quotazione);
						Partita.getarraygiocatori().get(z).aggiornapv(3);

					}
					// Pago le scommesse piazzate
					if (scommessa.get(a).getTiposcommessa() == Scommessa.Tiposcommessa.PIAZZATO) {
						Partita.getarraygiocatori().get(z)
								.aggiornaSoldi(scommessa.get(a).getSoldi() * 2);
						Partita.getarraygiocatori().get(z).aggiornapv(1);
					}
				}
			}
		}
		// Resetto la lista delle scommesse
		scommessa.clear();

	}

	/**
	 * 
	 * Resetto la lista delle scommesse
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void clearscommessa() {
		scommessa.clear();
	}

	/**
	 * 
	 * Aggiorno le quotazioni in base all'arrivo
	 * 
	 * @param arrivo
	 * @exceptions
	 * 
	 * @see
	 */
	public void aggiornaQuotazioni(int arrivo) {
		if (arrivo > quotazione - 1) {
			quotazione += 1;
		}
		if (arrivo < quotazione - 1) {
			quotazione -= 1;
		}
	}

	/**
	 * 
	 * Rimuovo la carta azione dalla lista per ID
	 * 
	 * @param i
	 * @exceptions
	 * 
	 * @see
	 */
	public void removeCartaAzioneByID(int i) {
		if (listacarteazione != null) {
			for (int j = 0; j < listacarteazione.size(); j++) {
				if (listacarteazione.get(j).getId() == i) {
					listacarteazione.remove(j);
				}
			}
		}
	}

	/**
	 * 
	 * Rimuovo la carta azione dalla lista per posizione
	 * 
	 * @param i
	 * @exceptions
	 * 
	 * @see
	 */
	public void removeCartaAzione(int i) {
		listacarteazione.remove(i);

	}

	/**
	 * @return the ultimo
	 */
	public boolean isUltimo() {
		return ultimo;
	}

	/**
	 * @param ultimo
	 *            the ultimo to set
	 */
	public void setUltimo(boolean ultimo) {
		this.ultimo = ultimo;
	}

	/**
	 * @return the primo
	 */
	public boolean isPrimo() {
		return primo;
	}

	/**
	 * @param primo
	 *            the primo to set
	 */
	public void setPrimo(boolean primo) {
		this.primo = primo;
	}

	/**
	 * @return the posizione
	 */
	public int getPosizione() {
		return posizione;
	}

	/**
	 * @param posizione
	 *            the posizione to set
	 */
	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}

	/**
	 * @return the fotofinish
	 */
	public int getFotofinish() {
		return fotofinish;
	}

	/**
	 * @param fotofinish
	 *            the fotofinish to set
	 */
	public void setFotofinish(int fotofinish) {
		this.fotofinish = fotofinish;
	}

	/**
	 * @return the segnalino
	 */
	public int getSegnalino() {
		return segnalino;
	}

	/**
	 * @param segnalino
	 *            the segnalino to set
	 */
	public void setSegnalino(int segnalino) {
		this.segnalino = segnalino;
	}

	/**
	 * @param segnalino
	 *            the segnalino to add
	 */
	public void aggiornaSegnalino(int temp) {
		this.segnalino += temp;
	}

	/**
	 * @return
	 */
	public boolean isArrivato() {
		return arrivato;
	}

	/**
	 * @param arrivato
	 *            the arrivato to set
	 */
	public void setArrivato(boolean arrivato) {
		this.arrivato = arrivato;
	}

	/**
	 * 
	 * Controllo se la scuderia ha le carte azione che rimuovono tutte le carte
	 * azione positive e/o negative assegnate alla scuderia
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void checkCarteAzione() {
		if (listacarteazione != null) {
			for (int i = 0; i < listacarteazione.size(); i++) {
				if (listacarteazione.get(i).getId() == 15) {
					for (int j = 7; j < 14; j++) {
						removeCartaAzioneByID(j);
					}
					// Rimuovo anche la carta stessa
					removeCartaAzioneByID(15);
				} else if (listacarteazione.get(i).getId() == 17) {
					for (int j = 0; j < 7; j++) {
						removeCartaAzioneByID(j);
					}
					// Rimuovo anche la carta stessa
					removeCartaAzioneByID(17);
				}
			}
		}
	}

	/**
	 * 
	 * Rimuove le carte azioni con la stessa lettera dalla lista delle carte
	 * azioni della scuderia
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void checkLetteraCarteAzione() {
		/* HashMap di tutti gli attributi analizzati */
		HashMap<String, CarteAzione> attributi = new HashMap<String, CarteAzione>();
		/* Lista delle carte con la stessa lettera */
		List<CarteAzione> carteuguali = new ArrayList<CarteAzione>();

		for (CarteAzione x : listacarteazione) {
			if (attributi.containsKey(x.getLettera())) {
				carteuguali.add(x);
				carteuguali.add(attributi.get(x.getLettera()));
			}
			attributi.put(x.getLettera(), x);
		}
		/* Rimuovo dalla lista le carte con la stessa lettera */
		listacarteazione.removeAll(carteuguali);
	}

	/**
	 * 
	 * Applico gli effetti delle carte azione che agiscono al traguardo
	 * 
	 * @param list
	 * @exceptions
	 * 
	 * @see
	 */
	public void carteAzioneTraguardo() {
		// Controllo tutte le carte della scuderia
		for (int j = 0; j < listacarteazione.size(); j++) {
			if (listacarteazione.get(j).getAgisce().equals("Traguardo")) {
				if (listacarteazione.get(j).getEffetto() > 0) {
					setPosizione(getPosizione()
							+ listacarteazione.get(j).getEffetto());
				}
				if (listacarteazione.get(j).getEffetto() == 0) {
					setPosizione(12);
				}
			}
		}
	}

	/**
	 * @return the classifica
	 */
	public int getClassifica() {
		return classifica;
	}

	/**
	 * @param classifica
	 *            the classifica to set
	 */
	public void setClassifica(int classifica) {
		this.classifica = classifica;
	}

	/**
	 * 
	 * Svuoto la lista delle carte azione
	 * 
	 * @exceptions
	 * 
	 * @see
	 */
	public void resetCarteAzione() {
		listacarteazione.clear();
	}
}
