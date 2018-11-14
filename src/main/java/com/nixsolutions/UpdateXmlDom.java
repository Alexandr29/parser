package com.nixsolutions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

public class UpdateXmlDom {
    int even = 1;
    // Save the updated DOM into the XML back
    private void saveToXML(File file, Document doc) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            DOMSource source = new DOMSource(doc);

            transformer.transform(source, result);

            String strTemp = writer.toString();

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(strTemp);
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch(Exception ex) {
        }
    }

    // Read into the relevant node and update the text value

    public void parsing(Node node){

        System.out.println("even = " + even);
        if (even % 2 == 0){
            node.getParentNode().removeChild(node);
        }
        even++;
        if (node.hasChildNodes()){
            NodeList child = node.getChildNodes();
            for (int i = 0; i < child.getLength(); i++) {
                if (child.item(i).getNodeType()==Node.ELEMENT_NODE){

                    parsing(child.item(i));
                }
                even++;
            }
        }
    }

    private void updateTextValue(Document doc) {
        try {
            Element root = doc.getDocumentElement();
            // Return the NodeList on a given tag name
            NodeList childNodes = root.getElementsByTagName("Rule");

            parsing(root);


//            for(int index = 0; index < childNodes.getLength(); index++) {
//                NodeList subChildNodes = childNodes.item(index).getChildNodes();
//                if(subChildNodes.item(1).getTextContent().equals(oldValue)) {
//                    // Update the relevant node text content. item() position the NodeList.
//                    subChildNodes.item(5).setTextContent(newValue);
//                }
//            }
        }
        catch(Exception ex) {
        }
    }

    private void updateXML(File file) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

        try {
            // Convert CDATA nodes to Text node append
            docFactory.setCoalescing(true);

            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            updateTextValue(doc);
            saveToXML(file, doc);
        }
        catch(Exception ex) {
        }
    }

    public static void main(String[] args) {
        File file = new File("src/main/resources/myXML.xml");
        new UpdateXmlDom().updateXML(file);
    }
}
