package com.nixsolutions;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class RemoveFromXmlSax {

    private void saveToXML(Source source) {
        File file = new File("src/main/resources/myXML3.xml");
        try {
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateXML(File file) {

        Handler handler = new Handler();
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        try {
            SAXParser parser = parserFactor.newSAXParser();
            parser.parse(file, handler);
            Source src = new SAXSource(
                    new InputSource("src/main/resources/myXML.xml"));
            saveToXML(src);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        File file = new File("src/main/resources/myXML.xml");
        new RemoveFromXmlSax().updateXML(file);
    }
}
