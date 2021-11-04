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
		
		// File el�r�s�nek biztos�t�sa, gy�r l�trehoz�sa, DocumentBuilder l�trehoz�sa �s maga a dokumentum l�trehoz�sa.
		File xmlFile = new File("D:\\Programming\\Github\\N16IAF_XMLGyak\\XMLTaskN16IAF\\XMLN16IAF.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		
		// A dokumentum normaliz�l�sa.
		doc.getDocumentElement().normalize();
		
		// Ki�rjuk a gy�k�relemet.
		System.out.println("Gy�k�relem: " + doc.getDocumentElement().getNodeName());
		
		// Lek�rdezz�k az "ugyfel"-eket, el�sz�r egy list�ba t�ve azokat.
		NodeList nList = doc.getElementsByTagName("ugyfel");
		
		// Majd ezen a list�n v�gigmegy�nk.
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			
			// Ki�ratjuk a nev�ket a node-oknak.
			System.out.println("\nJelenlegi elem: " + nNode.getNodeName());
			
			// Ha ez egy elem akkor ki�juk r�luk az adatokat.
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				// Attrib�tum (jellemz�) lek�rdez�se.
				String uid = elem.getAttribute("ugyfel_azonosito");
				
				// Elemek lek�rdez�se.
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				// Itt for ciklus sz�ks�ges, mert t�bb bank is tartozhat egy �gyf�lhez.
				String bank = "";
				Node node2;
				for ( int j = 0; j < elem.getElementsByTagName("bank").getLength() ; j++ ) {
					node2 = elem.getElementsByTagName("bank").item(j);
					bank = bank + " " + node2.getTextContent();
				}
				
				Node node3 = elem.getElementsByTagName("cim").item(0);
				String adress = node3.getTextContent();
				
				// Majd a legv�g�n ki�ratjuk azokat.
				System.out.printf("�gyf�l azonos�t�: %s%n", uid);
				System.out.printf("N�v: %s%n", name);
				System.out.printf("Bank: %s%n", bank);
				System.out.printf("C�m: %s%n", adress);
			}
		}
	}

}
