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
		
		// File elérésének biztosítása, gyár létrehozása, DocumentBuilder létrehozása és maga a dokumentum (dom objektum) létrehozása.
		File xmlFile = new File("D:\\Programming\\Github\\N16IAF_XMLGyak\\XMLTaskN16IAF\\XMLN16IAF.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
				
		// A dokumentum normalizálása.
		doc.getDocumentElement().normalize();
		
		// Kiírjuk a gyökérelemet.
		System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
		
		// ----------------------------------------------------------------------------------
		
		// Minden bankszámlára 5% kamatot írunk jóvá/számolunk fel
		System.out.println("5% kamat jóváírás:");
		
		NodeList nList = doc.getElementsByTagName("bankszamla");
				
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
					
			// Ha ez egy elem akkor kiíjuk róluk az adatokat.
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				// Egyenlegeket lekérdezzük.
				Node node3 = elem.getElementsByTagName("egyenleg").item(0);
				String money = node3.getTextContent();
				
				// Majd a legvégén kiíratjuk azokat.
				System.out.printf("Egyenleg jóváírás elõtt: %s%n", money);
				
				node3.setTextContent(String.valueOf(Math.round(Double.valueOf(money) * 1.05)));
				
				money = node3.getTextContent();
				System.out.printf("Egyenleg jóváírás után: %s%n", money);
				
			}
		}
		
		System.out.println("\n");
		
		// ----------------------------------------------------------------------------------
		
		// A 1343598542375482 bankszámlaszámú bankszámla valutáját GBP-re módosítjuk (és a pénzének összegét is átváltjuk abba).
		System.out.println("Valuta módosítása.");
		
		nList = doc.getElementsByTagName("bankszamla");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			
			// Ha ez egy elem akkor kiíjuk róluk az adatokat.
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				// Bankszámlaszám lekérdezés
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
			
			// Ha ez egy elem akkor kiíjuk róluk az adatokat.
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				if("cim".equals(elem.getNodeName())) {
					bank.removeChild(elem);
				}
				
			}
		}
		
		System.out.println("\n");
		
		// ----------------------------------------------------------------------------------
		
		// Módosított XML kiiratasa
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        System.out.println("Modositott XML");
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
        
	}

}
