/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;


/**
 * @author matteo
 *
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
	public void setAgisce(String agisce) {
		this.agisce = agisce;
	}

	@Override
	public String toString() {
		return this.id + ":" + this.nome +  ":" + this.lettera + ":" + this.effetto + ":" + this.agisce + "\n\n" + this.descrizione + "\n\n\n";
	}
	public static List<CarteAzione> crealistaazioni(){
		List<CarteAzione> carte = null;
		try {
			//Locate the file
			File xmlFile = new File("carteAzione.xml");

			//Create the parser instance
			XmlParserAzioni parser = new XmlParserAzioni();

			//Parse the file
			carte = parser.parseXml(new FileInputStream(xmlFile));
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return carte;
		
	}
}