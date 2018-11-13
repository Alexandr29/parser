package com.nixsolutions;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;

public class WorkWithParsers {
    public static void main(String[] args) {
        SAXParserFactory SAXfactory = SAXParserFactory.newInstance();
        DocumentBuilderFactory DOMfactory = DocumentBuilderFactory
                .newInstance();
        Handler handler = new Handler();
        try {
            SAXParser saxParser = SAXfactory.newSAXParser();
            saxParser.parse(new File("src/main/resources/myXML.xml"), handler);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        try {
            DocumentBuilder builder = DOMfactory.newDocumentBuilder();
            Document document = builder
                    .parse(new File("src/main/resources/myXML.xml"));
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            System.out.println(root.getNodeName());
            NodeList nList = document.getElementsByTagName(root.getNodeName());
            visitChildNodes(nList);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    private static void visitChildNodes(NodeList nList) {
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println(
                        "Node Name = " + node.getNodeName() + "; Value = "
                                + node.getTextContent());
                //Check all attributes
                if (node.hasAttributes()) {
                    node.getAttributes().removeNamedItem("name");
                    // get attributes names and values
                    NamedNodeMap nodeMap = node.getAttributes();
                    for (int i = 0; i < nodeMap.getLength(); i++) {
                        Node tempNode = nodeMap.item(i);
                        System.out.println(
                                "Attr name : " + tempNode.getNodeName()
                                        + "; Value = " + tempNode
                                        .getNodeValue());
                    }
                }
                if (node.hasChildNodes()) {
                    //We got more childs; Let's visit them as well
                    visitChildNodes(node.getChildNodes());
                }
            }
        }
    }
}
