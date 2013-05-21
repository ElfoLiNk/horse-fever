/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author leonardo
 *
 */
public class Scuderia {

	private int posizione;
	private int quotazione;
	private int arrivo;
	private List<Scommessa> scommessa;
	private String colore;
	private List<CarteAzione> listacarteazione;


	//getter

	public int getquotazione(){
		return quotazione;
	}

	public int getarrivo(){
		return arrivo;
	}

	public String getcolore(){
		return colore;
	}

	public int getposizione(){
		return posizione;
	}

	//metodi inerenti le scommesse

	/*nel main chiedo su che scuderia vuole puntare e accedo alla classe giusta 
	dopo faccio partire metodo giusto.
	una volta che accedo qua ho già creato un elemento nuovo nella lista scommessa
	in cui ho già salvato il nome del giocatore che sta per effettuarla 
	 */
	public void effettuascommessa(){

		String tempstring;
		int tempint;
		System.out.println("quanti soldi vuoi scommettere?");
		BufferedReader br;
		br = new BufferedReader(new InputStreamReader(System.in));

		try
		{
			tempstring = br.readLine();
			tempint = Integer.parseInt(tempstring);
		}
		catch (IOException e1)
		{
			System.out.println ("errore di flusso");
		}
		catch (NumberFormatException e2)
		{
			System.out.println ("errore di input da tastiera");
			effettuascommessa();
		}

		//controllo se la scommessa è valida
		//controllo importo minimo
		//l'ultima scommessa effetuata sarà sempre all'ultimo posto  


		int i;
		int pvtemp;
		i= scommessa.size();

	}
public void pagascommessa(){

}








}
