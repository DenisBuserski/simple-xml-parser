package com.simplexmlparser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


@Component
public class XmlReader implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        try {

            File file = new File("src/main/resources/books.xml"); // Creating a constructor of file class and parsing an XML file

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // An instance of factory that gives a document builder

            DocumentBuilder documentBuilder = dbf.newDocumentBuilder(); // An instance of builder to parse the specified xml file
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();

            System.out.println("Root element: " + document.getDocumentElement().getNodeName() + System.lineSeparator());
            NodeList nodeList = document.getElementsByTagName("book");

            for (int i = 0; i < nodeList.getLength(); i++) { // NodeList is not iterable, so we are using for loop
                Node node = nodeList.item(i);

                if (i == 0) {
                    System.out.println("Node Name :" + node.getNodeName() + System.lineSeparator());
                }


                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    System.out.println("Author name: " + eElement.getElementsByTagName("author").item(0).getTextContent());
                    System.out.println("Title: " + eElement.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("Genre: " + eElement.getElementsByTagName("genre").item(0).getTextContent());
                    System.out.println("Price: " + eElement.getElementsByTagName("price").item(0).getTextContent());
                    System.out.println("Publish date: " + eElement.getElementsByTagName("publish_date").item(0).getTextContent());
                    System.out.println("Description: " + eElement.getElementsByTagName("description").item(0).getTextContent());
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
