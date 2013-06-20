/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.util.List;

/**
 * 
 * Struttura dati delle carte azione
 * 
 * @see
 */
public class CarteAzione {
	/**
	 * 
	 * Attributi: id: Numero identificativo della carta lettera: Lettera della
	 * carta agisce: Campo della partita in cui agisce effetto: Numero che
	 * modifica il campo in cui agisce descrizione: Descrizione della carta
	 * azione
	 * 
	 */
	private int id;
	private String lettera;
	private String nome;
	private int effetto;
	private String agisce;
	private String descrizione;

	/**
	 * Override del metodo toString per stampare a video il nome e la
	 * descrizione della carta azione
	 */
	@Override
	public String toString() {
		return this.nome + " :\n\t" + this.descrizione+"\n";
	}

	/**
	 * 
	 * Crea una lista di tipo <CarteAzione> effettuando il parsing di un file
	 * xml contenente le carte azione.
	 * 
	 * @return la lista delle carte azioni lette dal file xml
	 * @exceptions
	 * 
	 * @see
	 */
	public static List<CarteAzione> crealistaazioni() {
		List<CarteAzione> carte = null;

			// Creo l'istanza parser
			XmlParser parser = new XmlParser();

			// Parso il file
			carte = parser.parseXmlAzioni(ResourceLoader.load("carteAzione.xml"));


		return carte;
	}

	/**
	 * 
	 * Applico gli effetti delle carte azione che agiscono alla partenza
	 * 
	 * @param listscuderie
	 *            La lista delle scuderie della partita
	 * @exceptions
	 * 
	 * @see
	 */
	public void carteAzionePartenza(List<Scuderia> listascuderie) {
		// Controllo tutte le scuderie
		for (int i = 0; i < listascuderie.size(); i++) {

			// Prendo le carte azione della scuderia
			List<CarteAzione> carte = listascuderie.get(i).getCarteAzione();

			if (carte != null) {
				// Controllo tutte le carte della scuderia
				for (int j = 0; j < carte.size(); j++) {
					// Analizzo solo le carte azione che influiscono la partenza
					if (carte.get(j).getAgisce().equals("Partenza")) {
						if (carte.get(j).getEffetto() < 0
								&& listascuderie.get(i).getMovimento() > 0) {
							// Applico l'effetto
							listascuderie.get(i).setMovimento(
									listascuderie.get(i).getMovimento()
											+ carte.get(j).getEffetto());
						}
						if (carte.get(j).getEffetto() == 0) {
							// Applico l'effetto
							listascuderie.get(i).setMovimento(0);
						}
						if (carte.get(j).getEffetto() == 1) {
							// Applico l'effetto
							listascuderie.get(i).setMovimento(
									listascuderie.get(i).getMovimento() + 1);

						} else if (carte.get(j).getEffetto() > 0) {
							// Applico l'effetto
							listascuderie.get(i).setMovimento(
									carte.get(j).getEffetto());
						}

						// Rimuovo la carta
						listascuderie.get(i).removeCartaAzione(j);
					}
				}
			}
		}
	}

	/**
	 * 
	 * Applico gli effetti delle carte azione che agiscono sul movimento
	 * 
	 * @param listascuderie
	 *            La lista delle scuderie della partita
	 * @exceptions
	 * 
	 * @see
	 */
	public void carteAzioneMovimento(List<Scuderia> listascuderie) {
		// Controllo tutte le scuderie
		for (int i = 0; i < listascuderie.size(); i++) {

			// Prendo le carte azione della scuderia
			List<CarteAzione> carte = listascuderie.get(i).getCarteAzione();

			// Controllo tutte le carte della scuderia
			for (int j = 0; j < carte.size(); j++) {
				// Analizzo solo le carte azione che influiscono il movimento
				if (carte.get(j).getAgisce().equals("Movimento")) {
					// Se la scuderia è ultima
					if (carte.get(j).getEffetto() == Parametri.QUATTRO
							&& listascuderie.get(i).isUltimo()) {
						// Si muove di 4
						listascuderie.get(i).setMovimento(Parametri.QUATTRO);
					}
					// Se la scuderia è prima
					if (carte.get(j).getEffetto() == 0
							&& listascuderie.get(i).isPrimo()) {
						// Non si muove
						listascuderie.get(i).setMovimento(0);
					}
				}
			}
		}

	}

	/**
	 * 
	 * Applico gli effetti delle carte azione che agiscono sullo sprint
	 * 
	 * @param listascuderie
	 *            La lista delle scuderie della partita
	 * @exceptions
	 * 
	 * @see
	 */
	public void carteAzioneSprint(List<Scuderia> listascuderie) {
		// Controllo tutte le scuderie
		for (int i = 0; i < listascuderie.size(); i++) {

			// Prendo le carte azione della scuderia
			List<CarteAzione> carte = listascuderie.get(i).getCarteAzione();

			// Controllo tutte le carte della scuderia
			for (int j = 0; j < carte.size(); j++) {
				// Analizzo solo le carte azione che influiscono lo sprint
				if (carte.get(j).getAgisce().equals("Sprint")) {
					if (carte.get(j).getEffetto() == -1
							&& listascuderie.get(i).getSprint() > 0) {
						listascuderie.get(i).setSprint(
								listascuderie.get(i).getSprint() - 1);

					}
					if (carte.get(j).getEffetto() == 0) {
						listascuderie.get(i).setSprint(0);
					}
					if (carte.get(j).getEffetto() == 1
							&& listascuderie.get(i).getSprint() > 0
							&& listascuderie.get(i).getSprint() < Parametri.TRE) {
						listascuderie.get(i).setSprint(
								listascuderie.get(i).getSprint() + 1);

					}
					if (carte.get(j).getEffetto() == 2) {
						if (listascuderie.get(i).getSprint() > 0) {
							listascuderie.get(i).setSprint(Parametri.TRE);
						} else {
							listascuderie.get(i).setSprint(2);
						}

					}
				}
			}
		}

	}

	/**
	 * 
	 * Riceve la lista delle scuderie con la stessa posizione e applica le carte
	 * Fotofinish se presenti
	 * 
	 * @param listascuderie
	 *            ricevuta da findFotofinish
	 * @exceptions
	 * 
	 * @see
	 */
	public void carteAzioneFotofinish(List<Scuderia> listascuderie) {
		// Controllo tutte le scuderie
		for (int i = 0; i < listascuderie.size(); i++) {

			// Prendo le carte azione della scuderia
			List<CarteAzione> carte = listascuderie.get(i).getCarteAzione();

			// Controllo tutte le carte della scuderia
			for (int j = 0; j < carte.size(); j++) {
				// Analizzo solo le carte azione che influiscono il fotofinish
				if (carte.get(j).getAgisce().equals("Fotofinish")) {
					if (carte.get(j).getEffetto() == 0) {
						listascuderie.get(i).setFotofinish(0);
					}
					if (carte.get(j).getEffetto() == 1) {
						listascuderie.get(i).setFotofinish(1);
					}

				}
			}
		}
	}

	/**
	 * 
	 * Applico le carte azione che modificano la quotazione della scuderia,
	 * l'algoritmo non si applica se la quotazione non rimane tra (1:1 - 1:7)
	 * 
	 * @param listascuderie
	 *            la lista delle scuderie della partita
	 * @exceptions
	 * 
	 * @see
	 */
	public void carteAzioneQuotazione(List<Scuderia> listascuderie) {
		// Controllo tutte le scuderie
		for (int i = 0; i < listascuderie.size(); i++) {

			// Prendo le carte azione della scuderia
			List<CarteAzione> carte = listascuderie.get(i).getCarteAzione();

			// Controllo tutte le carte della scuderia
			for (int j = 0; j < carte.size(); j++) {
				// Analizzo solo le carte azione che influiscono le quotazioni
				if (carte.get(j).getAgisce().equals("Quotazione")) {
					if (carte.get(j).getEffetto() > 0
							&& listascuderie.get(i).getQuotazione() > carte
									.get(j).getEffetto() + 1) {
						listascuderie.get(i).setQuotazione(
								listascuderie.get(i).getQuotazione()
										- carte.get(j).getEffetto());

					} else if (listascuderie.get(i).getQuotazione() < carte
							.get(j).getEffetto() + Parametri.SETTE) {
						listascuderie.get(i).setQuotazione(
								listascuderie.get(i).getQuotazione()
										- carte.get(j).getEffetto());

					}
				}
			}
		}

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the lettera
	 */
	public String getLettera() {
		return lettera;
	}

	/**
	 * @param lettera
	 *            the lettera to set
	 */
	public void setLettera(String lettera) {
		this.lettera = lettera;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the effetto
	 */
	public int getEffetto() {
		return effetto;
	}

	/**
	 * @param effetto
	 *            the effetto to set
	 */
	public void setEffetto(int effetto) {
		this.effetto = effetto;
	}

	/**
	 * @return the agisce
	 */
	public String getAgisce() {
		return agisce;
	}

	/**
	 * @param agisce
	 *            the agisce to set
	 */
	public void setAgisce(String agisce) {
		this.agisce = agisce;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione
	 *            the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}