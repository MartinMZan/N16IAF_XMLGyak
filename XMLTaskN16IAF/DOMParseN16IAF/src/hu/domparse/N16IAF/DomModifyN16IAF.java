package hu.domparse.N16IAF;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomModifyN16IAF {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		// TODO Auto-generated method stub
		
		// File el�r�s�nek biztos�t�sa, gy�r l�trehoz�sa, DocumentBuilder l�trehoz�sa �s maga a dokumentum (dom objektum) l�trehoz�sa.
		File xmlFile = new File("D:\\Programming\\Github\\N16IAF_XMLGyak\\XMLTaskN16IAF\\XMLN16IAF.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
				
		// A dokumentum normaliz�l�sa.
		doc.getDocumentElement().normalize();
		
		// Ki�rjuk a gy�k�relemet.
		System.out.println("Gy�k�relem: " + doc.getDocumentElement().getNodeName());
		
		// ----------------------------------------------------------------------------------
		
		// Minden banksz�ml�ra 5% kamatot �runk j�v�/sz�molunk fel
		System.out.println("5% kamat j�v��r�s:");
		
		NodeList nList = doc.getElementsByTagName("bankszamla");
				
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
					
			// Ha ez egy elem akkor ki�juk r�luk az adatokat.
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				// Egyenlegeket lek�rdezz�k.
				Node node3 = elem.getElementsByTagName("egyenleg").item(0);
				String money = node3.getTextContent();
				
				// Majd a legv�g�n ki�ratjuk azokat.
				System.out.printf("Egyenleg j�v��r�s el�tt: %s%n", money);
				
				node3.setTextContent(String.valueOf(Math.round(Double.valueOf(money) * 1.05)));
				
				money = node3.getTextContent();
				System.out.printf("Egyenleg j�v��r�s ut�n: %s%n", money);
				
			}
		}
		
		System.out.println("\n");
		
		// ----------------------------------------------------------------------------------
		
		// A 1343598542375482 banksz�mlasz�m� banksz�mla valut�j�t GBP-re m�dos�tjuk (�s a p�nz�nek �sszeg�t is �tv�ltjuk abba).
		System.out.println("Valuta m�dos�t�sa.");
		
		nList = doc.getElementsByTagName("bankszamla");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			
			// Ha ez egy elem akkor ki�juk r�luk az adatokat.
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				// Banksz�mlasz�m lek�rdez�s
				String uid = elem.getAttribute("bankszamla_szam");
				
				if (uid.equals("1343598542375482")) {
					
					Node node2 = elem.getElementsByTagName("penznem").item(0);
					node2.setTextContent("GBP");
					Node node3 = elem.getElementsByTagName("egyenleg").item(0);
					node3.setTextContent(String.valueOf(Math.round( Double.valueOf(node3.getTextContent()) * 0.85) )); // 1 EUR ~ 0.85 GBP
				}
				
				
			}
		}
		
		System.out.println("\n");
		
		// ----------------------------------------------------------------------------------
		
        // Bank cimenek torlese
		System.out.println("Bank cimenek torlese.");
		
        Node bank = doc.getFirstChild();
        NodeList child = bank.getChildNodes();
        
		for (int i = 0; i < child.getLength(); i++) {
			Node nNode = child.item(i);
			
			// Ha ez egy elem akkor ki�juk r�luk az adatokat.
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				if("cim".equals(elem.getNodeName())) {
					bank.removeChild(elem);
				}
				
			}
		}
		
		System.out.println("\n");
		
		// ----------------------------------------------------------------------------------
		
		// M�dos�tott XML kiiratasa
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        System.out.println("Modositott XML");
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
        
	}

}
