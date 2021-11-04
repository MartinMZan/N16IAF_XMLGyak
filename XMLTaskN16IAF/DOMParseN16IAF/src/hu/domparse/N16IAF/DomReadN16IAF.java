package hu.domparse.N16IAF;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomReadN16IAF {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		
		// File elérésének biztosítása, gyár létrehozása, DocumentBuilder létrehozása és maga a dokumentum létrehozása.
		File xmlFile = new File("D:\\Programming\\Github\\N16IAF_XMLGyak\\XMLTaskN16IAF\\XMLN16IAF.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		
		// A dokumentum normalizálása.
		doc.getDocumentElement().normalize();
		
		// Kiírjuk a gyökérelemet.
		System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
		
		// Lekérdezzük az "ugyfel"-eket, elõször egy listába téve azokat.
		NodeList nList = doc.getElementsByTagName("ugyfel");
		
		// Majd ezen a listán végigmegyünk.
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			
			// Kiíratjuk a nevüket a node-oknak.
			System.out.println("\nJelenlegi elem: " + nNode.getNodeName());
			
			// Ha ez egy elem akkor kiíjuk róluk az adatokat.
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				// Attribútum (jellemzõ) lekérdezése.
				String uid = elem.getAttribute("ugyfel_azonosito");
				
				// Elemek lekérdezése.
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				// Itt for ciklus szükséges, mert több bank is tartozhat egy ügyfélhez.
				String bank = "";
				Node node2;
				for ( int j = 0; j < elem.getElementsByTagName("bank").getLength() ; j++ ) {
					node2 = elem.getElementsByTagName("bank").item(j);
					bank = bank + " " + node2.getTextContent();
				}
				
				Node node3 = elem.getElementsByTagName("cim").item(0);
				String adress = node3.getTextContent();
				
				// Majd a legvégén kiíratjuk azokat.
				System.out.printf("Ügyfél azonosító: %s%n", uid);
				System.out.printf("Név: %s%n", name);
				System.out.printf("Bank: %s%n", bank);
				System.out.printf("Cím: %s%n", adress);
			}
		}
	}

}
