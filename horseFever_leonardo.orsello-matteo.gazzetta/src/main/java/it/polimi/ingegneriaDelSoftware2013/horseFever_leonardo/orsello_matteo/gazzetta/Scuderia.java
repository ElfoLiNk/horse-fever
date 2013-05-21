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
	
	//setter
	
	public void setquotazione(int temp){
		quotazione = temp;
	}

	//metodi inerenti le scommesse

	/*nel main chiedo su che scuderia vuole puntare e accedo alla classe giusta 
	dopo faccio partire metodo giusto.
	una volta che accedo qua ho già creato un elemento nuovo nella lista scommessa
	in cui ho già salvato il nome del giocatore che sta per effettuarla 
	 */
	public void effettuascommessa(){

		String tempstring;
		int tempint = 0;
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
		//controllo importo minimo e se la scommessa è già stata effettuata
		//l'ultima scommessa effetuata sarà sempre all'ultimo posto  


		int i; int n;
		int pvtemp = 0;
		String nometemp;
		i= scommessa.size()-1;
		n= Partita.getarraygiocatori().size()-1;
		nometemp = scommessa.get(i).getnomegiocatore();


		//controllo validità scommessa : scommessa>=pv*100
		for(int a=0; a<=n; a++){
			if (nometemp == Partita.getarraygiocatori().get(a).getNome()){
				pvtemp = Partita.getarraygiocatori().get(a).getPv();

				if(tempint>=pvtemp*100)
					scommessa.get(i).setsoldi(tempint);
				else{
					System.out.println("l'importo scommesso non è valido, questo deve essere/n" +
							"come minimo pari a \"punti vittoria posseduti\" * 100, inserire" +
							"un nuovo importo :)");
					effettuascommessa();
				}	    	
			}	
		}

		//controllo se è già stata effettuata la scommessa

		for()


	}



	public void pagascommessa(){

	}








}
