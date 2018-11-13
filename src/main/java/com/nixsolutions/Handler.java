package com.nixsolutions;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {

    @Override public void startDocument() throws SAXException {
        System.out.println("start parsing...");
    }

    @Override public void endDocument() throws SAXException {
        System.out.println("end parsing");
    }

    @Override public void startElement(String uri, String localName,
            String qName, Attributes attributes) throws SAXException {
        System.out.println("i am here");
    }

    @Override public void endElement(String uri, String localName, String qName)
            throws SAXException {
        System.out.println("end of element");
    }
}
