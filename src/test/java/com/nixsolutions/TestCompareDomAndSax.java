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
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class TestCompareDomAndSax {
    RemoveFromXmlDom dom = new RemoveFromXmlDom();
    RemoveFromXmlSax sax = new RemoveFromXmlSax();

    @Before
    public void setUp() {
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreWhitespace(true);
    }

    @Test
    public void testCompareDomAndSax() throws ParserConfigurationException,
            IOException, SAXException, TransformerException {
        File source = new File("src/main/resources/source.xml");
        File resut = new File("src/test/resources/resultDom.xml");
        dom.updateXML(source,resut);
        sax.parse("src/main/resources/source.xml","src/test/resources/resultSax.xml");
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document resultDom = documentBuilder.parse("src/test/resources/resultDom.xml");
        Document resultSax = documentBuilder.parse("src/test/resources/resultSax.xml");
        Diff diff = new Diff(resultDom, resultSax);
        assertTrue("XMLs are different", diff.similar());
    }
}
