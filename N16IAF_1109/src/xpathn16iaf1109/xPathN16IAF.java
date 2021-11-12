package xpathn16iaf1109;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class xPathN16IAF {

	public static void main(String[] args) {
		try {
			// File xmlFile = newFile("class.xml");

			// DocumentBuilder l�trehoz�sa
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document document = documentBuilder.parse("src/hallgatokN16IAF.xml");
			document.getDocumentElement().normalize();

			// az XPath k�sz�t�se
			XPath xPath = XPathFactory.newInstance().newXPath();

			// Meg kell adni az el�r�si �t kifejez�s�t �s a csom�pont list�t:
			//String expression = "class";
			//String expression = "/class/student";
			//String expression = "//student[@id='01']";
			//String expression = "//student";
			//String expression = "/class/student[2]";
			//String expression = "/class/student[last()]";
			//String expression = "/class/student[last()-1]";
			//String expression = "/class/student[position()<3]";
			//String expression = "/class/*";
			//String expression = "//student[@*]";
			String expression = "//*";
			
			
			// K�sz�tsunk egy list�t, majd a Path kifejez�st meg kell �rni �s ki kell
			// �rt�kelni
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

			// A for ciklus seg�ts�g�vel a NodeList csom�pontjait v�gig kell iterr�lni
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				System.out.println("\nAktu�lis elem: " + node.getNodeName());

				// Meg kell vizsg�lni a csom�pontot az subelement tesztel�se megt�rt�nt.
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
					Element element = (Element) node;

					// az id attrib�tomot ad vissza
					System.out.println("Hallgat� ID: " + element.getAttribute("id"));

					// keresztnevet, stb. ad vissza
					System.out.println(
							"Keresztn�v: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
					System.out.println(
							"Vezet�kn�v: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					System.out.println("Becen�v: " + element.getElementsByTagName("becenev").item(0).getTextContent());
					System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());

				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
	}

}
