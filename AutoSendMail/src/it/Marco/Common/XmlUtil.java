package it.Marco.Common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class XmlUtil 
{
	public XmlUtil(){}
	
	/**
	 * Questo metodo pulisce una stringa in formato xml dai caratteri
	 * speciali aggiunti dall' http
	 */
	public String getStringXml(String inString) {
		if (inString == null)
			return inString;

		String stringaXml = searchReplace(inString, "&lt;", "<");
		stringaXml = searchReplace(stringaXml, "&gt;", ">");
		stringaXml = searchReplace(stringaXml, "&amp;", "&");
		stringaXml = searchReplace(stringaXml, "&apos;", "\'");
		stringaXml = searchReplace(stringaXml, "&quot;", "\"");

		return stringaXml;

	}
	/**
	 * Questo metodo prende in input una stringa, una sottostringa
	 *  da sostituire ed una con cui sostituirla e restituisce la
	 *  stringa con la sostituzione
	 */
	public String searchReplace(String inString, String find, String replace) {
		if (inString == null || find == null || replace == null) {
			return inString;
		}
		if (inString.length() == 0) {
			return inString;
		}
		int i = inString.indexOf(find);
		if (i == -1) {
			return inString;
		} else {
			return inString.substring(0, i) + replace + searchReplace(inString.substring(i + find.length()), find, replace);
		}
	}
	
	public  Document getXmlDocument(String xmlString) {
	try{
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
		//System.out.println(domBuilder);
		InputStreamReader inputStreamReader = new InputStreamReader(new java.io.ByteArrayInputStream(xmlString.trim().getBytes()), "ISO-8859-1");
		InputSource inputSource = new InputSource(inputStreamReader);
		//System.out.println("GPL INPUTSOURCE: "+inputSource.toString());
		return domBuilder.parse(inputSource);		
	} catch(Exception exp)
	{
		exp.printStackTrace();	
		return null;
	}
  
	}
  
  	public  Document createXmlDocument() throws Exception{


		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
			// crea DOM per scrivere XML mediante transformer
		Document xmlDocument = builder.newDocument();
		
		return xmlDocument;
}

  	
  	public org.jdom.Document createJdomDocument(File file){
  		try {
  		SAXBuilder builder =  new SAXBuilder();
  		org.jdom.Document document = builder.build(file);
  		return document;
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
  	}
  	/**
  	 * 
  	 * @param xmlDoc
  	 * @param nomeElemento
  	 * @return
  	 * @throws Exception
  	 */
  	public static Element createNodeOnXmlDocument(Document xmlDoc, 
										   String nomeElemento) throws Exception{


      
  Element element = xmlDoc.createElement(nomeElemento);
 
 // setValuesOnXmlDocument(element);
 
  xmlDoc.appendChild(element);

  return element;
}

  	public static Element createNodeOnElement(Document xmlDoc, 
								Element element,
								String nomeElemento) throws Exception{


      
  Element newElement = xmlDoc.createElement(nomeElemento);
  /* chiamo il metodo di setValueOnXmlDocument*/
  // setValuesOnXmlDocument(newElement);
  /* termina la creazione del documento*/
  element.appendChild(newElement);
  return newElement;

}

  	public static void setParameterElement(Element element,
								String name,
								String value)
  {
      
	 element.setAttribute(name, value);
	
	
}
  	/**
	 * @param element
	 * @return
	 */
	public static synchronized String fromXmltoString(Element element){
		
		if(element != null){
					try{		
						TransformerFactory tf = TransformerFactory.newInstance();		
						

						Transformer idTransform = tf.newTransformer();
						idTransform.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
						// Source input = new DOMSource(element);
						DOMSource source = new DOMSource(element);
				
						ByteArrayOutputStream out = new ByteArrayOutputStream();
					   	StreamResult result = new StreamResult(out);
					   	idTransform.transform(source, result);						
						String xmlStr = out.toString();
						return xmlStr;
					}catch(Exception e){  
						try{ 
						
							return generateXMLString(element);
						}catch(Exception e2){
							System.out.println("Non è stato possibile costruire la stringa dal documento XML");	
								
						}
					}	
							
				}
		return "";
	
	}	
//	Generate String out of the XML document object
   private static synchronized String generateXMLString(Element doc)  {
	
	 String xmlString = "";	
	 StringWriter  strWriter    = null;
	 XMLSerializer probeMsgSerializer   = null;
	 OutputFormat  outFormat    = null;

	 try {
	   probeMsgSerializer = new XMLSerializer();
	   strWriter = new StringWriter();
	   outFormat = new OutputFormat();

	   // Setup format settings
	   //outFormat.setEncoding(XML_ENCODING);
	   //outFormat.setVersion(XML_VERSION);
	   outFormat.setIndenting(true);
	   outFormat.setIndent(4);

	   // Define a Writer
	   probeMsgSerializer.setOutputCharStream(strWriter);

		outFormat.setEncoding("ISO-8859-1");

	   // Apply the format settings
	   probeMsgSerializer.setOutputFormat(outFormat);

	   // Serialize XML Document
	   probeMsgSerializer.serialize(doc);
	   xmlString = strWriter.toString();
	   strWriter.close();

		return xmlString;
	 } catch (IOException ioEx) {
		 return null;
	 }
	
   }
   	/**
	 * @param element
	 * @return
	 */
	
   /**
    * Operazioni XML 
    * tutte riferite alle stringhe
    */
   
   public static String fromXmltoString(Document doc) {
		if(doc != null){
			return fromXmltoString(doc.getDocumentElement());
		}
		return "";	
	}		
	/**
	 * Questo metodo aggiunge un nodo (tag xml) ad una stringa
	 */
	public String startNodo(String inString, String nodo) {
		if (inString == null || nodo == null)
			return inString;

		String stringaNuova = new String();
		stringaNuova = inString + "<" + nodo + ">";
		return stringaNuova;

	}
	/**
	 * Questo metodo aggiunge fine nodo (fine tag xml) ad una stringa
	 */
	public String endNodo(String inString, String nodo) {
		if (inString == null || nodo == null)
			return inString;

		String stringaNuova = new String();
		stringaNuova = inString + "</" + nodo + ">";
		return stringaNuova;

	}
	/**
	 * Questo metodo aggiunge una foglia (elemento xml) ad una stringa
	 * la foglia ha un nome ed un valore
	 */
	public String addFoglia(String inString, String nome, String valore) {
		if (inString == null || nome == null || valore == null)
			return inString;

		String stringaNuova = new String();
		stringaNuova = inString + "<" + nome + ">" + valore + "</" + nome + ">";
		return stringaNuova;

	}
	/**
	 * Questo metodo restituisce una foglia (elemento xml) da una stringa
	 * la foglia ha un nome e se ne ritrova il valore
	 */
	public String getFoglia(String inString, String nome) {

		String valore = null;

		if (inString == null || nome == null)
			return valore;

		String inizio = "<" + nome + ">";
		String fine = "</" + nome + ">";
		int lun = inizio.length();
		int i = inString.indexOf(inizio);
		int f = inString.indexOf(fine);
		if (i == -1 || f == -1) {
			return valore;
		} else {
			valore = inString.substring(i + lun, f);
			return valore;
		}

	}
	/**
	 * Questo metodo restituisce una foglia (elemento xml) da una stringa
	 * la foglia ha un nome e se ne ritrova il valore
	 */
	public String getFogliaConAttributi(String inString, String nome, String attributi) {

		String valore = null;

		if (inString == null || nome == null || attributi == null)
			return valore;

		String inizio = "<" + nome ;//+ attributi + ">";
		String fine = "</" + nome + ">";
	
		String completo = "<" + nome +"/>";
		

		int i = inString.indexOf(inizio);
		int ic = inString.indexOf(completo);
		
		if(i==ic) {
			return null;
		}

		if (i != -1) {
			String secParte = inString.substring(i);
			int inizioVero = secParte.indexOf(">");
			int f = inString.indexOf(fine);
			if (i == -1 || f == -1) {
				return valore;
			} else {
				valore = inString.substring(i + inizioVero + 1, f);
				return valore;
			}
		}

		else
			return valore;


}
	/**
	 * Questo metodo prende in input una stringa
	 * e restituisce il numero decimale corrispondente
	 * se la stringa è errata restituisce null
	 */
	public java.math.BigDecimal numeroDecimale(String numeroStringa) {

		java.math.BigDecimal numero = null;
		String numeroFormattato = numeroStringa.replace(',', '.');
		try {
			numero = new java.math.BigDecimal(numeroFormattato);
		} catch (Exception errore) {
			return null;
		}
		return numero;
	}
	
	
	/***********************************************************************************************************
	 * Metodo che converte una stringa xml semplice cioè formata da una radice ed una serie
	 * di tag ad un solo livello in un array a due colonne: 
	 *    colonna 1  nome del tag
	 *    colonna 2  valore del tag 
	 *    
	 *    
	 *    
	 *    Per adesso Non funziona
	 * *********************************************************************************************************
	 */
	
	public String[][] xmlSimpleConvert(String xml, String radice) {
		if (xml == null)
			return null;
		String[][] xmlResult = null;

		xml=getFoglia(xml, radice);
		String[] nodoxml = new String[3];
		Vector<String[]> elenco = new Vector<String[]>();

		while (nodoxml!=null) {
			//nodoxml = trovaPrimoNodo(xml);	
			if (nodoxml!=null) {
				String[] nodo = new String[2];
				nodo[0]=nodoxml[0];
				nodo[1]=nodoxml[1];
				xml=nodoxml[2];
				elenco.addElement(nodo);			 
			}
		}

		int num = elenco.size();
		xmlResult = new String[num][2];
		String[] app = new String[2];
		for (int j=0; j<num; j++) {
			app = elenco.elementAt(j);
			xmlResult[j][0]=app[0];	         	      
			xmlResult[j][1]=app[1];;	         	      
		}       	      
		return xmlResult;
	}

}