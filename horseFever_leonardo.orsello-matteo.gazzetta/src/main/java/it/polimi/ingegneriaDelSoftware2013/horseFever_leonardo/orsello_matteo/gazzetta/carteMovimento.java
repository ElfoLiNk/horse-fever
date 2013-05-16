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
public class carteMovimento {
	 private int quota;
	 private int movimento;
	 Vector<Integer> movimenti = new Vector<Integer>();

	 

	public void setMovimento() {
		  try {
				// File per linee > Lista di Stringhe
				 BufferedReader file = new BufferedReader(new FileReader("D:/Dropbox/Università/Ingegneria del Software/HorseFever/FamilyGame/Movimento/cartemovimento.txt"));
				 List<String> linee = new ArrayList<String>();

				 String linea = file.readLine();

				 while( linea != null ) {
				     linee.add(linea);
				     linea = file.readLine();
				 }

				 // Selezione una linea random dalla Lista
				 Random r = new Random();
				 String randomString = linee.get(r.nextInt(linee.size()));
				 
				 // Analizzo la stringa e Salvo il movimento corretto
				 Scanner ScannerString = new Scanner(randomString);
				 
				 
				 for(int i=0 ;i < (randomString.length())/2; i++)
			        {
					 movimenti.add(ScannerString.nextInt());
			        }
				 
				 // Chiudo File e Scanner
				 file.close();
				 ScannerString.close();
							  
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
