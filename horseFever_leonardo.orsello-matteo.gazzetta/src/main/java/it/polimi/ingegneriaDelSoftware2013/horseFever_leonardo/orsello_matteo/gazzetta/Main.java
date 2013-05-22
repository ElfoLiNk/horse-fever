/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;



import java.io.File;
import java.io.FileInputStream;
import java.util.List;



/**
 * @author amministratore
 *
 */
public class Main {


		/**
		 * @param args
		 */
		public static void main(String[] args) {
			Partita.setListe();
			Partita.setNumGiocatori();
			Partita.setGiocatori();
			Partita.setCarteAzione();
			Partita.setCarteAzione();
			Partita.truccoCorsa();
		/*	try {
				//Locate the file
				File xmlFile = new File("carteAzione.xml");

				//Create the parser instance
				XmlParserAzioni parser = new XmlParserAzioni();

			//Parse the file
			List<CarteAzione> carte = parser.parseXml(new FileInputStream(xmlFile));
				
				//Verify the result
			Write.write(carte.toString());
			}
			catch(Exception e){
				e.printStackTrace();
			}*/
			
			//CarteMovimento Prima = new CarteMovimento();
			//Prima.setMovimento();
			
			//System.out.println(Prima.getMovimento(5));

			// TODO Auto-generated method stubjjj

		}

}
