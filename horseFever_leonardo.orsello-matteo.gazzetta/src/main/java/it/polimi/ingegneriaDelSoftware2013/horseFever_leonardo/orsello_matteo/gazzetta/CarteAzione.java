/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

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
		return this.nome + " : " + this.descrizione;
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

		} catch (IOException e) {
			Write.write("Errore nell'apertura e/o parsing del file xml");
		}

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
	public void carteAzionePartenza(List<Scuderia> listascuderie) {
		// Controllo tutte le scuderie
		for (int i = 0; i < listascuderie.size(); i++) {

			// Prendo le carte azione della scuderia
			List<CarteAzione> carte = listascuderie.get(i).getCarteAzione();

			if (carte != null) {
				// Controllo tutte le carte della scuderia
				for (int j = 0; j < carte.size(); j++) {
					if (carte.get(j).getAgisce().equals("Partenza")) {
						if (carte.get(j).getEffetto() < 0
								&& listascuderie.get(i).getMovimento() > 0) {
							// Applico l'effetto
							listascuderie.get(i).setMovimento(
									listascuderie.get(i).getMovimento()
											- carte.get(j).getEffetto());
						}
						if (carte.get(j).getEffetto() == 0) {
							// Applico l'effetto
							listascuderie.get(i).setMovimento(0);
						}
						if (carte.get(j).getEffetto() == 1) {
							// Applico l'effetto
							listascuderie.get(i).setMovimento(
									listascuderie.get(i).getMovimento() + 1);

						}
						if (carte.get(j).getEffetto() > 0) {
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
	public void carteAzioneSprint(List<Scuderia> listascuderie) {
		// Controllo tutte le scuderie
		for (int i = 0; i < listascuderie.size(); i++) {

			// Prendo le carte azione della scuderia
			List<CarteAzione> carte = listascuderie.get(i).getCarteAzione();

			// Controllo tutte le carte della scuderia
			for (int j = 0; j < carte.size(); j++) {
				if (carte.get(j).getAgisce().equals("Sprint")) {
					if (carte.get(j).getEffetto() == -1) {
						if (listascuderie.get(i).getSprint() > 0) {
							listascuderie.get(i).setSprint(
									listascuderie.get(i).getSprint() - 1);
						}
					}
					if (carte.get(j).getEffetto() == 0) {
						listascuderie.get(i).setSprint(0);
					}
					if (carte.get(j).getEffetto() == +1) {
						if (listascuderie.get(i).getSprint() > 0
								&& listascuderie.get(i).getSprint() < 3) {
							listascuderie.get(i).setSprint(
									listascuderie.get(i).getSprint() + 1);
						}
					}
					if (carte.get(j).getEffetto() == 2) {
						if (listascuderie.get(i).getSprint() > 0) {
							listascuderie.get(i).setSprint(3);
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
				if (carte.get(j).getAgisce().equals("Quotazione")) {
					if (carte.get(j).getEffetto() > 0) {
						if (listascuderie.get(i).getquotazione() > carte.get(j)
								.getEffetto() + 1) {
							listascuderie.get(i).setquotazione(
									listascuderie.get(i).getquotazione()
											- carte.get(j).getEffetto());
							// TODO rimuovere carta?
						}
					}
					if (carte.get(j).getEffetto() < 0) {
						if (listascuderie.get(i).getquotazione() < carte.get(j)
								.getEffetto() + 7) {
							listascuderie.get(i).setquotazione(
									listascuderie.get(i).getquotazione()
											- carte.get(j).getEffetto());
							// TODO rimuovere carta?
						}
					}
				}
			}
		}

	}
}