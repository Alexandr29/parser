package com.nixsolutions;

import com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class RemoveFromXmlSax extends DefaultHandler {

    private void updateXML(File file) {
        Handler handler = new Handler();
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxFactory.newSAXParser();
            saxParser.parse(file, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        File file = new File("src/main/resources/myXML.xml");
        new RemoveFromXmlSax().updateXML(file);
    }
}
