package com.nixsolutions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class RemoveFromXmlDom {

    private void saveToXML(Document doc) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
            String strTemp = writer.toString();
            File file2 = new File("src/main/resources/myXML2.xml");
            FileWriter fileWriter = new FileWriter(file2);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(strTemp);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (TransformerException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void parsing(Node node) {
        int even = 1;
        if (node.hasChildNodes()) {
            NodeList nodeList = node.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node child = nodeList.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    if (even % 2 == 0) {
                        child.getParentNode().removeChild(child);
                    }
                    if (child.hasChildNodes()) {
                        parsing(child);
                    }
                    even++;
                }
            }
        }
    }

    private void deleteEvenNode(Document doc) {
        try {
            Element root = doc.getDocumentElement();
            parsing(root);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void updateXML(File file) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory
                .newInstance();
        try {
            docFactory.setCoalescing(true);
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            deleteEvenNode(doc);
            saveToXML(doc);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        File file = new File("src/main/resources/source.xml");
        new RemoveFromXmlDom().updateXML(file);
    }
}
