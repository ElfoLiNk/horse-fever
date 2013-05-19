/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;



import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;


/**
 * @author amministratore
 *
 */
public class Main {


		/**
		 * @param args
		 */
		public static void main(String[] args) {
			try {
				//Locate the file
				File xmlFile = new File("cartePersonaggio.xml");

				//Create the parser instance
				XmlParser parser = new XmlParser();

				//Parse the file
				ArrayList<CartePersonaggio> carte = parser.parseXml(new FileInputStream(xmlFile));
				
				//Verify the result
				System.out.println(carte);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			//CarteMovimento Prima = new CarteMovimento();
			//Prima.setMovimento();
			
			//System.out.println(Prima.getMovimento(5));

			// TODO Auto-generated method stubjjj

		}

}
