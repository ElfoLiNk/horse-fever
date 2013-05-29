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

/**
 * @author leonardo
 * 
 */
public class Scuderia {

	private int posizione = 0;
	private int segnalini;
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

	public int getquotazione() {
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

	public void setquotazione(int temp) {
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

	// metodi inerenti le scommesse

	/*
	 * nel main chiedo su che scuderia vuole puntare e accedo alla classe giusta
	 * dopo faccio partire metodo giusto. una volta che accedo qua ho già creato
	 * un elemento nuovo nella lista scommessa in cui ho già salvato il nome del
	 * giocatore che sta per effettuarla,sarà sicuramente in coda alla lista
	 */
	public void effettuascommessa() {

		int flagsc = 1;
		do {
			int tempint = 0;
			Write.write("quanti soldi vuoi scommettere?");
			BufferedReader br;
			tempint = Read.readInt();

			// controllo se la scommessa è valida
			// controllo importo minimo e se la scommessa è già stata effettuata
			// l'ultima scommessa effetuata sarà sempre all'ultimo posto

			int i;
			int n;
			int pvtemp = 0;
			String nometemp;
			String stringtemp;
			char chartemp;
			Scommessa.Tiposcommessa tiposcommessatemp;

			i = scommessa.size() - 1;
			n = Partita.getarraygiocatori().size() - 1;
			nometemp = scommessa.get(i).getNomegiocatore();
			// tiposcommessatemp = scommessa.get(i).gettiposcommessa();

			// controllo validità scommessa : scommessa>=pv*100
			for (int a = 0; a <= n; a++) {
				if (nometemp == Partita.getarraygiocatori().get(a).getNome()) {
					pvtemp = Partita.getarraygiocatori().get(a).getPv();

					if (tempint >= pvtemp * 100) {
						scommessa.get(i).setSoldi(tempint);
					} else {
						Write.write("l'importo scommesso non è valido, questo deve essere/n"
								+ "come minimo pari a \"punti vittoria posseduti\" * 100, inserire"
								+ "un nuovo importo :)");
						effettuascommessa();
					}
				}
			}

			// controllo se è già stata effettuata la scommessa

			// int flagsc = 1;
			// do{
			do {
				Write.write("che tipo di scommessa vuoi giocare?\n"
						+ "digita v per vincente\n" + "       p per piazzato");

				br = new BufferedReader(new InputStreamReader(System.in));

				try {
					while ((stringtemp = br.readLine()) != null)

						if (stringtemp.length() > 1)
							throw new NumberFormatException();
					{
						chartemp = stringtemp.charAt(0);
					}
				} catch (IOException e1) {
					Write.write("errore di flusso");
					chartemp = 'e';
				} catch (NumberFormatException e2) {
					Write.write("non hai inserito un carattere valido");
					chartemp = 'e';
				}
			} while (chartemp != 'v' && chartemp != 'p');

			scommessa.get(i).setTiposcommessa(chartemp);
			tiposcommessatemp = scommessa.get(i).getTiposcommessa();

			for (int a = 0; a < i; a++) {
				if (Partita.getarraygiocatori().get(a).getNome()
						.equals(nometemp)) {
					if (tiposcommessatemp == scommessa.get(i)
							.getTiposcommessa()) {
						Write.write("questa scommessa è già stata effettuata, non è possibile ripetere"
								+ "la stessa scommessa. è possibile fare due scommesse sulla"
								+ "stessa scuderia, ma bisogna modificare il tipo"
								+ "di scommessa");
						flagsc = 0;
					}
				}
			}
		} while (flagsc != 0);
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

		int i;
		String nometemp;
		i = scommessa.size() - 1;
		Scommessa.Tiposcommessa tiposcommessatemp;
		int solditemp;
		int pvtemp;
		int ngiocatori = Partita.getarraygiocatori().size();

		for (int a = i; a >= 0; a--) {
			nometemp = scommessa.get(a).getNomegiocatore();
			for (int z = 0; z <= ngiocatori; z++) {
				if (Partita.getarraygiocatori().get(z).getNome()
						.equals(nometemp)) {
					solditemp = scommessa.get(a).getSoldi();
					tiposcommessatemp = scommessa.get(a).getTiposcommessa();
					if (tiposcommessatemp == Scommessa.Tiposcommessa.VINCENTE) {
						if (arrivato) {
							Partita.getarraygiocatori().get(z)
									.setSoldi(solditemp * quotazione);
							Partita.getarraygiocatori().get(z).aggiornapv(3);
						}
					}
					if (tiposcommessatemp == Scommessa.Tiposcommessa.PIAZZATO) {
						Partita.getarraygiocatori().get(z)
								.setSoldi(solditemp * 2);
						Partita.getarraygiocatori().get(z).aggiornapv(1);
					}
				}
			}
		}
		scommessa.clear();
	}

	// da utilizzare per pulire la lista scommessa delle scuderie perdenti

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
	public void aggiornaquotazioni(int arrivo) {
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
		if(listacarteazione != null){
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
	 * @return the segnalini
	 */
	public int getSegnalini() {
		return segnalini;
	}

	/**
	 * @param segnalini
	 *            the segnalini to set
	 */
	public void setSegnalini(int segnalini) {
		this.segnalini = segnalini;
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
		if(listacarteazione != null){
		for (int i = 0;i < listacarteazione.size();i++) {
			if (listacarteazione.get(i).getId() == 15) {
				for (int j = 7; j < 14; j++) {
					removeCartaAzioneByID(j);
					// Rimuovo anche la carta stessa
					removeCartaAzioneByID(15);
				}
			} else if (listacarteazione.get(i).getId() == 17) {
				for (int j = 0; j < 7; j++) {
					removeCartaAzioneByID(j);
					// Rimuovo anche la carta stessa
					removeCartaAzioneByID(17);
				}
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
					setPosizione(getPosizione() + listacarteazione.get(j).getEffetto());
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

}
