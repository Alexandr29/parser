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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestDomParser {
RemoveFromXmlDom dom = new RemoveFromXmlDom();

    @Before
    public void setUp() {
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreWhitespace(true);
    }

    @Test
    public void testDomParserSucces() throws
            ParserConfigurationException, IOException, SAXException,
            TransformerException {
        File file = new File("src/main/resources/source.xml");
        File file2 = new File("src/test/resources/resultDom.xml");
        dom.updateXML(file,file2);
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse("src/test/resources/resultDom.xml");
        Document document2 = documentBuilder.parse("src/test/resources/domParserExpected.xml");
        Diff diff = new Diff(document, document2);
        assertTrue("Dom wrong parse xml", diff.similar());
    }

}
