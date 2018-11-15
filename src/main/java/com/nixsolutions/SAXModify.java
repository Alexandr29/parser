package com.nixsolutions;

import java.io.*;
import java.util.ArrayList;

import org.xml.sax.*;

import javax.xml.parsers.*;

import org.xml.sax.helpers.DefaultHandler;

public class SAXModify extends DefaultHandler {

    static String displayText[] = new String[1000];
    static int numberLines = 0;
    static String indentation = "";
    private ArrayList<Object> nodeList;
    private ArrayList<Object> rootNode;

    public static void main(String[] args) {

        try {
            File inputFile = new File("src/main/resources/myXML.xml");

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXModify obj = new SAXModify();
            obj.childLoop(inputFile);

            FileWriter filewriter = new FileWriter(
                    "src/main/resources/myXML4.xml");
            for (int loopIndex = 0; loopIndex < numberLines; loopIndex++) {
                filewriter.write(displayText[loopIndex].toCharArray());
                filewriter.write('\n');
                System.out.println(displayText[loopIndex].toString());
            }

            filewriter.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

    }

    public void childLoop(File input) {
        DefaultHandler handler = new SAXModify();
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(input, handler);
        } catch (Throwable t) {
        }
    }

    @Override public void startDocument() {
        nodeList = new ArrayList<>();
        rootNode = new ArrayList<>();

        displayText[numberLines] = indentation;
        displayText[numberLines] +=
                "<?xml version=\"1.0\" encoding=\"" + "UTF-8" + "\"?>";
        numberLines++;
    }

    @Override public void processingInstruction(String target, String data) {
        displayText[numberLines] += indentation;
        displayText[numberLines] += "<?";
        displayText[numberLines] += target;

        if (data != null && data.length() > 0) {
            displayText[numberLines] += ' ';
            displayText[numberLines] += data;
        }
        displayText[numberLines] += "?>";
        //numberLines++;
    }

    public void startElement(String uri, String localName, String qualifiedName,
            Attributes attributes) {
        displayText[numberLines] = indentation;
        indentation += "    ";
        displayText[numberLines] += '<';
        displayText[numberLines] += qualifiedName;

        if (attributes != null) {
            int numberAttributes = attributes.getLength();

            for (int loopIndex = 0; loopIndex < numberAttributes; loopIndex++) {
                displayText[numberLines] += ' ';
                displayText[numberLines] += attributes.getQName(loopIndex);

                displayText[numberLines] += "=\"";
                displayText[numberLines] += attributes.getValue(loopIndex);
                displayText[numberLines] += '"';
            }
        }
        displayText[numberLines] += '>';
        numberLines++;
    }

    @Override public void characters(char characters[], int start, int length) {
        String characterData = (new String(characters, start, length)).trim();

        if (characterData.length() > 0) {
            displayText[numberLines] = indentation;
            displayText[numberLines] += characterData;
            numberLines++;
        }
    }

    @Override public void endElement(String uri, String localName,
            String qualifiedName) {
        indentation = indentation.substring(0, indentation.length() - 4);
        displayText[numberLines] += indentation;
        displayText[numberLines] += "</";
        displayText[numberLines] += qualifiedName;
        displayText[numberLines] += '>';
        numberLines++;
    }

}