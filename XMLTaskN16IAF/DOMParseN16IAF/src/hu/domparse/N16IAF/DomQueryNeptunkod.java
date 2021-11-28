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
		
		// Kiiratom azokat az �gyfelek nev�t akik �zdon laknak
		System.out.println("�zdon el� �gyfelek:");
		
		// Lek�rdezz�k az "ugyfel"-eket, el�sz�r egy list�ba t�ve azokat.
		NodeList nList = doc.getElementsByTagName("ugyfel");
		
		// Majd ezen a list�n v�gigmegy�nk.
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			
			// Ha ez egy elem akkor ki�juk r�luk az adatokat.
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				// N�v lek�rdez�se.
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				// V�rosukat lekerdezzuk
				Node node3 = elem.getElementsByTagName("varos").item(0);
				String adress = node3.getTextContent();
				
				if ("�zd".equals(adress)) {
					// Majd a legv�g�n ki�ratjuk azokat.
					System.out.printf("N�v: %s%n", name);
				}
				
			}
		}
		
		System.out.println("\n");
		
		// ----------------------------------------------------------------------------------
		
		// Ki�ratom azon banksz�mlasz�mokat, melyeken tartoz�s van a bank fel�.
		System.out.println("Negat�v egyenleg� banksz�ml�k:");
		
		nList = doc.getElementsByTagName("bankszamla");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			
			// Ha ez egy elem akkor ki�juk r�luk az adatokat.
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				// Banksz�mlasz�m lek�rdez�s
				String uid = elem.getAttribute("bankszamla_szam");
				
				// Egyenlegeket lek�rdezz�k.
				Node node3 = elem.getElementsByTagName("egyenleg").item(0);
				String money = node3.getTextContent();
				
				if (Integer.valueOf(money) < 0) {
					// Majd a legv�g�n ki�ratjuk azokat.
					System.out.printf("Banksz�mlasz�m: %s%n", uid);
				}
				
			}
		}
		
		System.out.println("\n");
		
		// ----------------------------------------------------------------------------------
		
		// Kiiratom azon banki alkalmazottakat, akik m�r adtak ki bankk�rty�t.
		System.out.println("Szorgalmas dolgoz�k (akik m�r adtak ki bankk�rty�t):");
		
		nList = doc.getElementsByTagName("banki_alkalmazott");
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			
			// Ha ez egy elem akkor ki�juk r�luk az adatokat.
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) nNode;
				
				// N�v lek�rdez�se.
				Node node1 = elem.getElementsByTagName("nev").item(0);
				String name = node1.getTextContent();
				
				// Banksz�mlasz�mokat lek�rdezz�k.
				Node node3 = elem.getElementsByTagName("bankszamla").item(0);
				
				if (node3 != null) {
					// Majd a legv�g�n ki�ratjuk a neveket.
					System.out.printf("N�v: %s%n", name);
				}
				
			}
		}
	}
	
	

}
