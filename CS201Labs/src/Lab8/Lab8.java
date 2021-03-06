package Lab8;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Lab8{
	private static String stockTicker;
	private static URL url;
	private static InputStream inpStr;
	private static InputStreamReader isr;
	private static File stockTickerDataFile;
	private static String companyName = "Company Name";
	private static String yearHigh = "$$$";
	private static String yearLow = "$";
	private static String marketCap = "$$$$$";

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a Stock Ticker: ");
		boolean isValidTicker = false;
		
		while(!isValidTicker){
			stockTicker = scan.nextLine();
			try {
				//open connection to server with stock info
				String queryURL = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22" +
				stockTicker + "%22)&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
				url = new URL(queryURL);
				URLConnection conn = url.openConnection();
				inpStr = url.openStream();
				isr = new InputStreamReader(inpStr);
				
				
				//read in the XML data, write the data to an xml file (WORKS!!!)
				stockTickerDataFile = new File("stockTickerData.xml");
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder builder = factory.newDocumentBuilder();
		        Document doc = builder.parse(inpStr);

		        TransformerFactory tfactory = TransformerFactory.newInstance();
		        Transformer xform = tfactory.newTransformer();

		        // that’s the default xform; use a stylesheet to get a real one
		        xform.transform(new DOMSource(doc), new StreamResult(stockTickerDataFile));
				
				//parse data from file with DOM (works better than SAX)
				
				doc.getDocumentElement().normalize();
		        // find the tag for (Name) company, (YearLow), (YearHigh), (MarketCapitalization)
//				 
//				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
				
				
				NodeList dataNodeList = doc.getElementsByTagName("*");
				Node currentNode = dataNodeList.item(0);
				if(currentNode.getNodeType() == Node.ELEMENT_NODE){
					Element currentElement = (Element) currentNode; //TODO this is null
					if(currentElement != null && !currentElement.equals("[query: null]")){
//						System.out.println("Current Element: " + currentElement);
						companyName = currentElement.getElementsByTagName("Name").item(0).getTextContent();
						//		System.out.println("Company name: " + companyName);
						yearLow = currentElement.getElementsByTagName("YearLow").item(0).getTextContent();
						//		System.out.println("Year Low: " + yearLow);
						yearHigh = currentElement.getElementsByTagName("YearHigh").item(0).getTextContent();
						//		System.out.println("Year High: " + yearHigh);
						marketCap = currentElement.getElementsByTagName("MarketCapitalization").item(0).getTextContent();
						//		System.out.println("Market Capitalization: " + marketCap);
						System.out.println(companyName + " has a year low of: " + yearLow + " and a year high of: " + yearHigh + 
								". It's current market Capitalization is " + marketCap);
					}


				}
				
//				System.out.println();
//				Node companyNameNode = doc.getElementById("Name");
//				companyName = companyNameNode.getNodeName(); //TODO NullPointerException here
//				System.out.println("Company name: "+ companyName);
//				
//				Node yearLowNode = doc.getElementById("YearLow");
//				yearLow = yearLowNode.getNodeName(); //TODO NullPointerException here
//				System.out.println("Year Low: "+ yearLow);
//				
//				Node yearHighNode = doc.getElementById("YearHigh");
//				yearHigh = yearHighNode.getNodeName(); //TODO NullPointerException here
//				System.out.println("Year High: "+ yearHigh);
//				
//				Node marketCapitalizationNode = doc.getElementById("MarketCapitalization");
//				marketCap = marketCapitalizationNode.getNodeName(); //TODO NullPointerException here
//				System.out.println("Market Capitalization: "+ marketCap);
				
//			 
//				NodeList nList = doc.getElementsByTagName("staff"); 
//			 
//				System.out.println("----------------------------");
//			 
//				for (int temp = 0; temp < nList.getLength(); temp++) {
//			 
//					Node nNode = nList.item(temp);
//			 
//					System.out.println("\nCurrent Element :" + nNode.getNodeName());
//			 
//					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//			 
//						Element eElement = (Element) nNode;
//			 
//						System.out.println("Staff id : " + eElement.getAttribute("id"));
//						System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
//						System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
//						System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
//						System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
//			 
//					}
//				}
				
				//input is valid, file created and read from successfully
				isValidTicker = true;
				break;
			}catch(IOException ioe) {
				// there was some connection problem, or the file did not exist on the server,
				// or URL was not in the right format.
				ioe.printStackTrace();
				System.out.println("ERROR");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
//		System.out.println(companyName + " has a year low of: " + yearLow + " and a year high of: " + yearHigh + 
//				". It's current market Capitalization is " + marketCap);
		
	}
}
