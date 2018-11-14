package com.nixsolutions;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithParsers {
    int even = 1;
    public static void main(String[] args) {
        final List<String> l = new ArrayList<String>();
        try { DocumentBuilderFactory DOMfactory = DocumentBuilderFactory
                .newInstance();
            DocumentBuilder builder = DOMfactory.newDocumentBuilder();
            Document document = builder
                    .parse(new File("src/main/resources/myXML.xml"));
            Node rootNode = document.getFirstChild();

            WorkWithParsers workWithParsers = new WorkWithParsers();
            workWithParsers.parsing(rootNode);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
    public void parsing(Node node){

        System.out.println("even = " + even);
        if (even % 2 == 0){
           node.setTextContent("hi");
        }
        System.out.println("Имя узла " + node.getNodeName());
        if (node.hasChildNodes()){
            even++;
            NodeList child = node.getChildNodes();
            for (int i = 0; i < child.getLength(); i++) {
                if (child.item(i).getNodeType()==Node.ELEMENT_NODE){
                    parsing(child.item(i));
                }

            }
        }
    }
}
