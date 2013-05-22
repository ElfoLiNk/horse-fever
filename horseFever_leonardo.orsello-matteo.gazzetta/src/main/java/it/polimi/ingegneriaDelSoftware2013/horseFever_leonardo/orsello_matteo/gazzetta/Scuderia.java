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
	in cui ho già salvato il nome del giocatore che sta per effettuarla */
	public void effettuascommessa(){

		int flagsc = 1;
		do{
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
			String stringtemp;
			char chartemp;
			Scommessa.Tiposcommessa tiposcommessatemp;

			i= scommessa.size()-1;
			n= Partita.getarraygiocatori().size()-1;
			nometemp = scommessa.get(i).getnomegiocatore();
			//	tiposcommessatemp = scommessa.get(i).gettiposcommessa();


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

			//int flagsc = 1;
			//do{
			do{
				System.out.println("che tipo di scommessa vuoi giocare?\n" +
						"digita v per vincente\n" +
						"       p per piazzato");

				br = new BufferedReader(new InputStreamReader(System.in));

				try
				{
					stringtemp = br.readLine();

					if (stringtemp.length() > 1)
						throw new NumberFormatException();

					chartemp = stringtemp.charAt(0);
				}
				catch (IOException e1)
				{
					System.out.println ("errore di flusso");
					chartemp = 'e';
				}
				catch (NumberFormatException e2)
				{
					System.out.println ("non hai inserito un carattere valido");
					chartemp = 'e';
				}
			}while(chartemp != 'v'&& chartemp !='p');

			scommessa.get(i).settiposcommessa(chartemp);
			tiposcommessatemp = scommessa.get(i).gettiposcommessa();


			for(int a=0; a<i; a++){
				if(nometemp == Partita.getarraygiocatori().get(a).getNome()){
					if(tiposcommessatemp == scommessa.get(i).gettiposcommessa()){
						System.out.println("questa scommessa è già stata effettuata, non è possibile ripetere" +
								"la stessa scommessa. è possibile fare due scommesse sulla" +
								"stessa scuderia, ma bisogna modificare il tipo" +
								"di scommessa");
						flagsc=0;
					}
				}
			}
		}while(flagsc!=0);
	}


	/**pago le scommesse dopo di che resetto la lista scommessa**/
	
	public void pagascommessa(){

		int i;
		String nometemp;
		i= scommessa.size()-1;
		Scommessa.Tiposcommessa tiposcommessatemp;
		int solditemp;
		int pvtemp;
		int ngiocatori = Partita.getarraygiocatori().size();

		for(int a=i; a>=0; a--){
			nometemp = scommessa.get(a).getnomegiocatore();
			for(int z=0; z <= ngiocatori; z++ ){
				if(nometemp==Partita.getarraygiocatori().get(z).getNome()){
					solditemp = scommessa.get(a).getsoldi();
					tiposcommessatemp = scommessa.get(a).gettiposcommessa();
					if(tiposcommessatemp == Scommessa.Tiposcommessa.VINCENTE){
						if(arrivo == 1){
							Partita.getarraygiocatori().get(z).setSoldi(solditemp*quotazione);
							Partita.getarraygiocatori().get(z).aggiornapv(3);
						}
					}	 
					if(tiposcommessatemp == Scommessa.Tiposcommessa.PIAZZATO){
						Partita.getarraygiocatori().get(z).setSoldi(solditemp*2);
						Partita.getarraygiocatori().get(z).aggiornapv(1);
					}	
				}
			}
		}	
		scommessa.clear();
	}

	//da utilizzare per pulire la lista scommessa delle scuderie perdenti
	
	public void clearscommessa(){
		scommessa.clear();
	}
	

	//aggiorno le quotazioni in base all'arrivo
	
	public void aggiornaquotazioni(int arrivo){
		if(arrivo > quotazione - 1)
			quotazione+=1;
		if(arrivo < quotazione - 1)
			quotazione-=1;	
	}





}
