/*
 * The MIT License
 *
 * Copyright (c) 2004-2011, Sun Microsystems, Inc., Kohsuke Kawaguchi, Frederik Fromm
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jenkinsci.plugins.viewer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author c031
 * 
 */
public class XPathConfigTest {

    private File jobAconfig;

    private File jobAsnippet;

    @Before
    public void setup() {
        this.jobAconfig = new File("src/test/resources/jobs/jobA/config.xml");
        this.jobAsnippet = new File("src/test/resources/jobs/jobA/configuredTriggers.xml");
    }

    @Test
    public void testGetXpath() {
        XPathConfig config = new XPathConfig("id", "name", "comment", "content");
        assertNotNull(config);
        assertEquals("content", config.getXpath());
    }

    @Test
    public void testGetXmlBlock() {
        XPathConfig config = new XPathConfig(null, null, null, null);
        assertNull(config.getXmlBlock(null));        
        assertNull(config.getXmlBlock(this.jobAconfig));
        
        config.setXpath("//configuredTriggers");

        List<Element> xmlBlocks = config.getXmlBlock(this.jobAconfig);
        assertNotNull("xml block should not be null.", xmlBlocks);
        assertTrue(xmlBlocks.size() > 0);

        try {
            Document snippet = new SAXReader().read(this.jobAsnippet);
            String expectedXml = snippet.asXML();
            expectedXml = expectedXml.substring(expectedXml.indexOf("<con"));
            assertEquals(expectedXml, xmlBlocks.get(0).asXML());
        } catch (DocumentException e) {
            fail("unexpected Exception: " + e.getMessage());
        }
    }
}
