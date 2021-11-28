package hu.domparse.N16IAF;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryNeptunkod {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
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
		
		// Kiiratom azokat az ügyfelek nevét akik Ózdon laknak
		System.out.println("Ózdon elõ ügyfelek:");
		
		// Lekérdezzük az "ugyfel"-eket, elõször egy listába téve azokat.
		NodeList nList = doc.getElementsByTagName("ugyfel");
		
		// Majd ezen a listán végigmegyünk.
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			
			// Ha ez egy elem akkor kiíjuk róluk az adatokat.
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				// Név lekérdezése.
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				// Városukat lekerdezzuk
				Node node3 = elem.getElementsByTagName("varos").item(0);
				String adress = node3.getTextContent();
				
				if ("Ózd".equals(adress)) {
					// Majd a legvégén kiíratjuk azokat.
					System.out.printf("Név: %s%n", name);
				}
				
			}
		}
		
		System.out.println("\n");
		
		// ----------------------------------------------------------------------------------
		
		// Kiíratom azon bankszámlaszámokat, melyeken tartozás van a bank felé.
		System.out.println("Negatív egyenlegû bankszámlák:");
		
		nList = doc.getElementsByTagName("bankszamla");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			
			// Ha ez egy elem akkor kiíjuk róluk az adatokat.
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				// Bankszámlaszám lekérdezés
				String uid = elem.getAttribute("bankszamla_szam");
				
				// Egyenlegeket lekérdezzük.
				Node node3 = elem.getElementsByTagName("egyenleg").item(0);
				String money = node3.getTextContent();
				
				if (Integer.valueOf(money) < 0) {
					// Majd a legvégén kiíratjuk azokat.
					System.out.printf("Bankszámlaszám: %s%n", uid);
				}
				
			}
		}
		
		System.out.println("\n");
		
		// ----------------------------------------------------------------------------------
		
		// Kiiratom azon banki alkalmazottakat, akik már adtak ki bankkártyát.
		System.out.println("Szorgalmas dolgozók (akik már adtak ki bankkártyát):");
		
		nList = doc.getElementsByTagName("banki_alkalmazott");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			
			// Ha ez egy elem akkor kiíjuk róluk az adatokat.
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				// Név lekérdezése.
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				// Bankszámlaszámokat lekérdezzük.
				Node node3 = elem.getElementsByTagName("bankszamla").item(0);
				
				if (node3 != null) {
					// Majd a legvégén kiíratjuk a neveket.
					System.out.printf("Név: %s%n", name);
				}
				
			}
		}
	}
	
	

}
