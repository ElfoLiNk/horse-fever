/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.File;
import java.io.FileInputStream;
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
		// return this.id + "\n" + this.nome + "\n" + this.lettera + "\n"
		// + this.effetto + "\n" + this.agisce + "\n\n" + this.descrizione
		// + "\n\n";
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
			XmlParserAzioni parser = new XmlParserAzioni();

			// Parso il file
			carte = parser.parseXml(new FileInputStream(xmlFile));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return carte;
	}

	public int checkCarteAzione(CarteAzione carta, Scuderia scuderia) {
		if (carta.getId() == 15) {
			for (int i = 7; i < 14; i++) {
				scuderia.removeCartaAzione(i);
			}
		} else if (carta.getId() == 17) {
			for (int i = 0; i < 7; i++) {
				scuderia.removeCartaAzione(i);
			}
		} else{return (0);}

		return (1);
	}

	public void usaCarteAzione(CarteAzione carta, Scuderia scuderia) {
		switch (carta.getAgisce()) {
		case "Partenza":
			if (carta.getEffetto() < 0) {
				scuderia.setMovimento(scuderia.getMovimento()
						+ carta.getEffetto());
			} else if (carta.getEffetto() == 0) {
				scuderia.setMovimento(0);
			}
			break;
		case "Movimento":
			
			break;

		case "Sprint":
			break;

		case "Traguardo":
			
			break;
		case "Fotofinish":

			break;
		case "Quotazione":
			if(carta.getEffetto() < 0) {
				if(scuderia.getquotazione() < 4) break;
				else  scuderia.setquotazione(carta.getEffetto() - 2);
			}
			break;
		}

	}

}