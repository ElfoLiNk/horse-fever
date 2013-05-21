/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @author matteo
 *
 */
public class CartePersonaggio {
	private int id;
	private String nome;
	private int soldi;
	private int quotazione;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSoldi() {
		return soldi;
	}
	public void setSoldi(int soldi) {
		this.soldi = soldi;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuotazione() {
		return quotazione;
	}
	public void setQuotazione(int quotazione) {
		this.quotazione = quotazione;
	}

	

@Override
	public String toString() {
		return this.id + ":" + this.nome +  ":" +this.soldi + ":" + this.quotazione ;
	}

	public static List<CartePersonaggio> crealistapersonaggi(){
		List<CartePersonaggio> carte = null;
		try {
			//Locate the file
			File xmlFile = new File("cartePersonaggio.xml");

			//Create the parser instance
			XmlParserPersonaggi parser = new XmlParserPersonaggi();

			//Parse the file
			carte = parser.parseXml(new FileInputStream(xmlFile));
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return carte;
		
	}

}
