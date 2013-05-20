/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.*;
import java.util.*;

/**
 * @author matteo
 *
 */
public class CarteMovimento {
	private int quota;
	private int movimento;
	private List<Integer> movimenti = new ArrayList<Integer>();



	public void setMovimento() {
		try {
			// File per linee > Lista di Stringhe
			FileReader openfile = new FileReader("cartemovimento.txt");
			BufferedReader file = new BufferedReader(openfile);
			List<String> linee = new ArrayList<String>();

			String linea = file.readLine();

			while( linea != null ) {
				linee.add(linea);
				linea = file.readLine();
			}

			// Selezione una linea random dalla Lista
			Random r = new Random();
			int j = r.nextInt(linee.size());
			String randomString = linee.get(j);

			// Elimino la linea dalla lista
			linee.remove(j);

			// Analizzo la stringa e Salvo il movimento corretto
			Scanner scannerString = new Scanner(randomString);


			for(int i=0 ;i < (randomString.length())/2; i++)
			{
				movimenti.add(scannerString.nextInt());
			}

			// Chiudo File e Scanner
			openfile.close();
			file.close();
			scannerString.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getMovimento(int quotazione) {
		quota = quotazione;

		for(int i=0 ;i < quota - 1 ; i++)
		{
			movimento = movimenti.get(i);
		}

		return movimento;
	}
}
