/**
 * 
 */
package it.polimi.ingegneriaDelSoftware2013.horseFever_leonardo.orsello_matteo.gazzetta;

/**
 * @author Matteo
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XmlParserAzioni
{
	public List<CarteAzione> parseXml(InputStream in)
	{
		//Create a empty link of users initially
		List<CarteAzione> carte = new ArrayList<CarteAzione>();
		try 
		{
			//Create default handler instance
			ParserHandlerAzioni handler = new ParserHandlerAzioni();

			//Create parser from factory
			XMLReader parser = XMLReaderFactory.createXMLReader();

			//Register handler with parser
			parser.setContentHandler(handler);

			//Create an input source from the XML input stream
			InputSource sorgente = new InputSource(in);

			//parse the document
			parser.parse(sorgente);

			//populate the parsed users list in above created empty list; You can return from here also.
			carte = handler.getCarte();

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return carte;
	}
}
