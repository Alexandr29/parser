package com.nixsolutions;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {

    private String tagName = "";

    @Override public void startDocument() throws SAXException {
        System.out.println("start parsing...");
    }

    @Override public void endDocument() throws SAXException {
        System.out.println("end parsing");
    }

    @Override public void startElement(String uri, String localName,
            String qName, Attributes attributes) throws SAXException {
        tagName = qName;
        super.startElement(uri, localName, qName, attributes);
    }

    @Override public void endElement(String uri, String localName, String qName)
            throws SAXException {
        tagName = "";
        super.endElement(uri, localName, qName);
    }

    @Override public void characters(char[] ch, int start, int length)
            throws SAXException {

        if (tagName.equals("name")) {
            ch = "Window2".toCharArray();
            start = 0;
            length = ch.length;

        }
        System.out.println(ch);
        super.characters(ch, start, length);
    }
}
