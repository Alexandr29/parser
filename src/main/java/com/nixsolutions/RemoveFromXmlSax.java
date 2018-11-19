package com.nixsolutions;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RemoveFromXmlSax {
    Handler handler = new Handler();

    public void parse(String pathToSourceXml, String pathToResultXml) {
        SAXParser parser;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
            parser.parse(new File(pathToSourceXml), handler);
            copyToXml(pathToResultXml);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private void copyToXml(String pathToResultXml) throws IOException {
        FileWriter filewriter = new FileWriter(pathToResultXml);
        filewriter.write(handler.getStringBuilder().toString());
        filewriter.close();
    }

    public static void main(String[] args) throws Exception {
                RemoveFromXmlSax parser = new RemoveFromXmlSax();
                parser.parse("src/main/resources/source.xml",
                        "src/main/resources/result.xml");
        //Sax sax = new Sax();
        //sax.parse();
    }
}