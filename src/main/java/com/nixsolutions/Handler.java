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

    @Override public void characters(char[] ch, int start, int length)
            throws SAXException {
        System.out.print("Characters:    \"");
        for (int i = start; i < start + length; i++) {
            switch (ch[i]) {
            case '\\':
                System.out.print("\\\\");
                break;
            case '"':
                System.out.print("\\\"");
                break;
            case '\n':
                System.out.print("\\n");
                break;
            case '\r':
                System.out.print("\\r");
                break;
            case '\t':
                System.out.print("\\t");
                break;
            default:
                System.out.print(ch[i]);
                break;
            }
        }
        System.out.print("\"\n");
    }
}
