package com.simplexmlparser;

import com.simplexmlparser.enums.Genre;
import com.simplexmlparser.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;


@Component
public class XmlReader implements CommandLineRunner {
    private final BookService bookService;

    @Autowired
    public XmlReader(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public void run(String... args) throws Exception {

        // printBookInfo();

        try {
            NodeList nodeList = getNodeList();

            for (int i = 0; i < nodeList.getLength(); i++) { // NodeList is not iterable, so we are using for loop
                Node node = nodeList.item(i);

                if (i == 0) {
                    System.out.println("Node Name :" + node.getNodeName() + System.lineSeparator());
                }


                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    // Book(String id, String title, Genre genre, BigDecimal price, LocalDate publishDate, String description)
                    Element element = (Element) node;
                    Element book = (Element) nodeList.item(0);
                    String bookId = book.getAttribute("id");
                    String title = element.getElementsByTagName("title").item(0).getTextContent();
                    String authorName = element.getElementsByTagName("author").item(0).getTextContent();

                    String genre = element.getElementsByTagName("genre").item(0).getTextContent().toUpperCase();
                    String price = element.getElementsByTagName("price").item(0).getTextContent();
                    String publish_date = element.getElementsByTagName("publish_date").item(0).getTextContent();
                    String description = element.getElementsByTagName("description").item(0).getTextContent();

                    this.bookService.addBook(
                            bookId,
                            title,
                            authorName,
                            Genre.valueOf(genre),
                            BigDecimal.valueOf(Double.parseDouble(price)),
                            LocalDate.parse(publish_date),
                            description);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    private NodeList getNodeList() throws ParserConfigurationException, SAXException, IOException {
        File file = new File("src/main/resources/books.xml"); // Creating a constructor of file class and parsing an XML file

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // An instance of factory that gives a document builder

        DocumentBuilder documentBuilder = dbf.newDocumentBuilder(); // An instance of builder to parse the specified xml file
        Document document = documentBuilder.parse(file);
        document.getDocumentElement().normalize();

        System.out.println("Root element: " + document.getDocumentElement().getNodeName() + System.lineSeparator());
        NodeList nodeList = document.getElementsByTagName("book");
        return nodeList;
    }

    private void printBookInfo() {
        try {
            NodeList nodeList = getNodeList();

            for (int i = 0; i < nodeList.getLength(); i++) { // NodeList is not iterable, so we are using for loop
                Node node = nodeList.item(i);

                if (i == 0) {
                    System.out.println("Node Name :" + node.getNodeName() + System.lineSeparator());
                }
                
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    printBookId(nodeList);
                    System.out.println("Author name: " + element.getElementsByTagName("author").item(0).getTextContent());
                    System.out.println("Title: " + element.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("Genre: " + element.getElementsByTagName("genre").item(0).getTextContent());
                    System.out.println("Price: " + element.getElementsByTagName("price").item(0).getTextContent());
                    System.out.println("Publish date: " + element.getElementsByTagName("publish_date").item(0).getTextContent());
                    System.out.println("Description: " + element.getElementsByTagName("description").item(0).getTextContent());
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printBookId(NodeList nodeList) {
        Element book = (Element) nodeList.item(0);
        String bookId = book.getAttribute("id");
        System.out.println("Book id: " + bookId);
    }
}
