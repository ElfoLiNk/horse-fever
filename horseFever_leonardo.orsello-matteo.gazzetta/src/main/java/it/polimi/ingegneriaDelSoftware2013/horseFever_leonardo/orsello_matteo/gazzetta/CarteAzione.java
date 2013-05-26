/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author matteo {Descrizione}
 * 
 * 
 * 
 * @see
 */
public class CarteAzione {
	private int id;
	private String lettera;
	private String nome;
	private int effetto;
	private String agisce;
	private String descrizione;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLettera() {
		return lettera;
	}

	public void setLettera(String lettera) {
		this.lettera = lettera;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getEffetto() {
		return effetto;
	}

	public void setEffetto(int effetto) {
		this.effetto = effetto;
	}

	public String getAgisce() {
		return agisce;
	}

	/**
	 * 
	 * @param agisce
	 */
	public void setAgisce(String agisce) {
		this.agisce = agisce;
	}

	@Override
	public String toString() {
		return this.nome + " : " + this.lettera;
	}

	/**
	 * 
	 * Crea una lista di tipo <CarteAzione> effettuando il parsing di un file
	 * xml contenente le carte azione.
	 * 
	 * @return
	 * @exceptions
	 * 
	 * @see
	 */
	public static List<CarteAzione> crealistaazioni() {
		List<CarteAzione> carte = null;
		try {
			// Cerco il file
			File xmlFile = new File("carteAzione.xml");

			// Creo l'istanza parser
			XmlParser parser = new XmlParser();

			// Parso il file
			carte = parser.parseXmlAzioni(new FileInputStream(xmlFile));

		} catch (Exception e) {
			Write.write("Errore nell'apertura e/o parsing del file xml");
		}

		return carte;
	}

	/**
	 * 
	 * Controllo se la scuderia ha le carte azione che rimuovono tutte le carte
	 * azione positive e/o negative assegnate alla scuderia
	 * 
	 * @param carta
	 * @param scuderia
	 * @return
	 * @exceptions
	 * 
	 * @see
	 */
	public int checkCarteAzione(CarteAzione carta, Scuderia scuderia) {
		if (carta.getId() == 15) {
			for (int i = 7; i < 14; i++) {
				scuderia.removeCartaAzioneByID(i);
			}
		} else if (carta.getId() == 17) {
			for (int i = 0; i < 7; i++) {
				scuderia.removeCartaAzioneByID(i);
			}
		} else {
			return (0);
		}

		return (1);
	}

	/**
	 * 
	 * Rimuove le carte azioni con la stessa lettera dalla lista delle carte
	 * azioni della scuderia
	 * 
	 * @param carte
	 * 
	 * @return carte
	 * @exceptions
	 * 
	 * @see
	 */
	public static List<CarteAzione> checkLetteraCarteAzione(
			List<CarteAzione> carte) {
		/* HashMap di tutti gli attributi analizzati */
		HashMap<String, CarteAzione> attributi = new HashMap<String, CarteAzione>();
		/* Lista delle carte con la stessa lettera */
		List<CarteAzione> carteuguali = new ArrayList<CarteAzione>();

		for (CarteAzione x : carte) {
			if (attributi.containsKey(x.getLettera())) {
				carteuguali.add(x);
				carteuguali.add(attributi.get(x.getLettera()));
			}
			attributi.put(x.getLettera(), x);
		}
		/* Rimuovo dalla lista le carte con la stessa lettera */
		carte.removeAll(carteuguali);

		return carte;
	}

	/**
	 * 
	 * Applico gli effetti delle carte azione che agiscono alla partenza
	 * 
	 * @param scuderia
	 * @exceptions
	 * 
	 * @see
	 */
	public void CarteAzionePartenza(List<Scuderia> listascuderie) {
		// Controllo tutte le scuderie
		for (int i = 0; i < listascuderie.size(); i++) {

			// Prendo le carte azione della scuderia
			List<CarteAzione> carte = listascuderie.get(i).getCarteAzione();

			// Controllo tutte le carte della scuderia
			for (int j = 0; j < carte.size(); j++) {
				if (carte.get(j).getAgisce().equals("Partenza")) {
					if (carte.get(j).getEffetto() < 0
							&& listascuderie.get(i).getMovimento() > 0) {
						// Applico l'effetto
						listascuderie.get(i).setMovimento(
								listascuderie.get(i).getMovimento()
										- carte.get(j).getEffetto());
						// Rimuovo la carta
						listascuderie.get(i).removeCartaAzione(j);
					}
					if (carte.get(j).getEffetto() == 0) {
						// Applico l'effetto
						listascuderie.get(i).setMovimento(0);
						// Rimuovo la carta
						listascuderie.get(i).removeCartaAzione(j);
					}
					if (carte.get(j).getEffetto() == 1) {
						// Applico l'effetto
						listascuderie.get(i).setMovimento(
								listascuderie.get(i).getMovimento() + 1);
						// Rimuovo la carta
						listascuderie.get(i).removeCartaAzione(j);
					}
					if (carte.get(j).getEffetto() > 0) {
						// Applico l'effetto
						listascuderie.get(i).setMovimento(
								carte.get(j).getEffetto());
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
	 * @exceptions
	 * 
	 * @see
	 */
	public void CarteAzioneMovimento(List<Scuderia> listascuderie) {
		// Controllo tutte le scuderie
		for (int i = 0; i < listascuderie.size(); i++) {

			// Prendo le carte azione della scuderia
			List<CarteAzione> carte = listascuderie.get(i).getCarteAzione();

			// Controllo tutte le carte della scuderia
			for (int j = 0; j < carte.size(); j++) {
				if (carte.get(j).getAgisce().equals("Movimento")) {
					if (carte.get(j).getEffetto() == 4
							&& listascuderie.get(i).isUltimo()) {
						listascuderie.get(i).setMovimento(4);
					}
					if (carte.get(j).getEffetto() == 0
							&& listascuderie.get(i).isPrimo()) {
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
	 * @exceptions
	 *
	 * @see
	 */
	public void CarteAzioneSprint(List<Scuderia> listascuderie) {
		// Controllo tutte le scuderie
		for (int i = 0; i < listascuderie.size(); i++) {

			// Prendo le carte azione della scuderia
			List<CarteAzione> carte = listascuderie.get(i).getCarteAzione();

			// Controllo tutte le carte della scuderia
			for (int j = 0; j < carte.size(); j++) {
				if (carte.get(j).getAgisce().equals("Sprint")) {
					switch (carte.get(j).getEffetto()) {
					case (-1):
						if (listascuderie.get(i).getSprint() > 0) {
							listascuderie.get(i).setSprint(
									listascuderie.get(i).getSprint() - 1);
						}
						break;
					case (0):
						listascuderie.get(i).setSprint(0);
						break;
					case (+1):
						if (listascuderie.get(i).getSprint() > 0
								&& listascuderie.get(i).getSprint() < 3) {
							listascuderie.get(i).setSprint(
									listascuderie.get(i).getSprint() + 1);
						}
						break;
					case (2):
						if (listascuderie.get(i).getSprint() > 0) {
							listascuderie.get(i).setSprint(3);
						} else {
							listascuderie.get(i).setSprint(3);
						}

						break;

					}
				}
			}
		}

	}

	/**
	 * 
	 * Applico gli effetti delle carte azione che agiscono al traguardo
	 *
	 * @param listascuderie
	 * @exceptions
	 *
	 * @see
	 */
	public void CarteAzioneTraguardo(List<Scuderia> listascuderie) {
		// Controllo tutte le scuderie
		for (int i = 0; i < listascuderie.size(); i++) {

			// Prendo le carte azione della scuderia
			List<CarteAzione> carte = listascuderie.get(i).getCarteAzione();

			// Controllo tutte le carte della scuderia
			for (int j = 0; j < carte.size(); j++) {
				if (carte.get(j).getAgisce().equals("Traguardo")) {
					if (carte.get(j).getEffetto() > 0) {
						listascuderie.get(i).setPosizione(
								listascuderie.get(i).getPosizione()
										+ carte.get(j).getEffetto());
					}
					if (carte.get(j).getEffetto() == 0) {
						// TODO controllare numero posizione del traguardo = 13
						// ?
						listascuderie.get(i).setPosizione(13);
					}
				}
			}
		}

	}

	public void CarteAzioneFotofinish(List<Scuderia> listascuderie) {
		// Controllo tutte le scuderie
		for (int i = 0; i < listascuderie.size(); i++) {

			// Prendo le carte azione della scuderia
			List<CarteAzione> carte = listascuderie.get(i).getCarteAzione();

			// Controllo tutte le carte della scuderia
			for (int j = 0; j < carte.size(); j++) {
				if (carte.get(j).getAgisce().equals("Fotofinish")) {
					// TODO implementare
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
	 * @exceptions
	 *
	 * @see
	 */
	public void CarteAzioneQuotazione(List<Scuderia> listascuderie) {
		// Controllo tutte le scuderie
		for (int i = 0; i < listascuderie.size(); i++) {

			// Prendo le carte azione della scuderia
			List<CarteAzione> carte = listascuderie.get(i).getCarteAzione();

			// Controllo tutte le carte della scuderia
			for (int j = 0; j < carte.size(); j++) {
				if (carte.get(j).getAgisce().equals("Quotazione")) {
					if (carte.get(j).getEffetto() > 0) {
						if (listascuderie.get(i).getquotazione() > carte.get(j)
								.getEffetto() + 1) {
							listascuderie.get(i).setquotazione(
									listascuderie.get(i).getquotazione()
											- carte.get(j).getEffetto());
							//TODO rimuovere carta?
						}
					}
					if (carte.get(j).getEffetto() < 0) {
						if (listascuderie.get(i).getquotazione() < carte.get(j)
								.getEffetto() + 7) {
							listascuderie.get(i).setquotazione(
									listascuderie.get(i).getquotazione()
											- carte.get(j).getEffetto());
							//TODO rimuovere carta?
						}
					}
				}
			}
		}

	}
}