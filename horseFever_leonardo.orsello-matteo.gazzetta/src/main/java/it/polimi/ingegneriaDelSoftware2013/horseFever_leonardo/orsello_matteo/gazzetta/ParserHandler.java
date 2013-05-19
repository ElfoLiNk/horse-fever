/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

/**
 * @author Matteo
 *
 */

import java.util.ArrayList;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ParserHandler extends DefaultHandler
{
	//This is the list which shall be populated while parsing the XML. 
	private ArrayList<CartePersonaggio> cartaList = new ArrayList<CartePersonaggio>();

	//As we read any XML element we will push that in this stack
	private Stack<String> elementoStack = new Stack<String>();

	//As we complete one user block in XML, we will push the User instance in userList 
	private Stack<CartePersonaggio> oggettoStack = new Stack<CartePersonaggio>();

	public void startDocument() throws SAXException
	{
		//System.out.println("start of the document   : ");
	}

	public void endDocument() throws SAXException
	{
		//System.out.println("end of the document document     : ");
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		//Push it in element stack
		this.elementoStack.push(qName);

		//If this is start of 'user' element then prepare a new User instance and push it in object stack
		if ("Carta".equals(qName))
		{
			//New User instance
			CartePersonaggio carta = new CartePersonaggio();

			//Set all required attributes in any XML element here itself
			if(attributes != null && attributes.getLength() == 1)
			{
				carta.setId(Integer.parseInt(attributes.getValue(0)));
			}
			this.oggettoStack.push(carta);
		}
	}

	public void endElement(String uri, String localName, String qName) throws SAXException
	{	
		//Remove last added </user> element
		this.elementoStack.pop();

		//User instance has been constructed so pop it from object stack and push in userList
		if ("Carta".equals(qName))
		{
			CartePersonaggio oggetto = this.oggettoStack.pop();
			this.cartaList.add(oggetto);
		}
	}

	/**
	 * This will be called everytime parser encounter a value node
	 * */
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		String stringa = new String(ch, start, length).trim();
				if (stringa.length() == 0)
		{
			return; // ignore white space
		}

		//handle the value based on to which element it belongs
		if ("Nome".equals(currentElement()))
		{
			CartePersonaggio carta = (CartePersonaggio) this.oggettoStack.peek();
			carta.setNome(stringa);
		}
		else if ("Soldi".equals(currentElement()))
		{
			CartePersonaggio carta = (CartePersonaggio) this.oggettoStack.peek();
			int intero = Integer.parseInt(new String(ch, start, length).trim());
			carta.setSoldi(intero);
		}
		else if ("Quotazione".equals(currentElement())){
			CartePersonaggio carta = (CartePersonaggio) this.oggettoStack.peek();
			int intero = Integer.parseInt(new String(ch, start, length).trim());
			carta.setQuotazione(intero);
		}
	}

	/**
	 * Utility method for getting the current element in processing
	 * */
	private String currentElement()
	{
		return this.elementoStack.peek();
	}

	//Accessor for userList object
	public ArrayList<CartePersonaggio> getCarte()
	{
		return cartaList;
	}
}
