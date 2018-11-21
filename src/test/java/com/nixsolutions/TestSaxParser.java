package com.nixsolutions;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.IOException;
import static org.junit.Assert.assertTrue;

public class TestSaxParser {
    private RemoveFromXmlSax sax = new RemoveFromXmlSax();

    @Before
    public void setUp() {
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreWhitespace(true);
    }


    @Test
    public void testSaxParserSucces()
            throws IOException, SAXException, ParserConfigurationException,
            TransformerException {
            sax.parse("src/main/resources/source.xml","src/test/resources/resultSax.xml");
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse("src/test/resources/resultSax.xml");
        Document document2 = documentBuilder.parse("src/test/resources/saxParserExpected.xml");
        Diff diff = new Diff(document, document2);
        assertTrue("Sax wrong parse xml", diff.similar());
    }

}
