/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * 
 * Struttura dati delle carte personaggio
 * 
 * 
 * @see
 */
public class CartePersonaggio {
	/**
	 * 
	 * Attributi: 
	 * id: Numero identificativo della carta.
	 * nome: Nome del personaggio della carta. 
	 * soldi: Soldi del personaggio della carta.
	 * quotazione: Quotazione del personaggio della carta.
	 * 
	 */
	private int id;
	private String nome;
	private int soldi;
	private int quotazione;

	@Override
	public String toString() {
		return this.id + ":" + this.nome + ":" + this.soldi + ":"
				+ this.quotazione;
	}

	/**
	 * 
	 * Crea una lista di tipo <CartePersonaggio> effettuando il parsing di un file
	 * xml contenente le carte azione.
	 * 
	 * @return
	 * @exceptions
	 * 
	 * @see
	 */
	public static List<CartePersonaggio> crealistapersonaggi() {
		List<CartePersonaggio> carte = null;
		try {
			// Locate the file
			File xmlFile = new File("cartePersonaggio.xml");

			// Create the parser instance
			XmlParser parser = new XmlParser();

			// Parse the file
			carte = parser.parseXmlPersonaggi(new FileInputStream(xmlFile));

		} catch (IOException e) {
			Write.write("Errore nell'apertura e/o parsing del file xml");
		}

		return carte;

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
	 * @return the soldi
	 */
	public int getSoldi() {
		return soldi;
	}

	/**
	 * @param soldi
	 *            the soldi to set
	 */
	public void setSoldi(int soldi) {
		this.soldi = soldi;
	}

	/**
	 * @return the quotazione
	 */
	public int getQuotazione() {
		return quotazione;
	}

	/**
	 * @param quotazione
	 *            the quotazione to set
	 */
	public void setQuotazione(int quotazione) {
		this.quotazione = quotazione;
	}

}
