package org.jenkinsci.plugins.viewer;

import hudson.ExtensionList;
import junit.framework.TestCase;
import org.jenkinsci.lib.configprovider.ConfigProvider;
import org.jenkinsci.lib.configprovider.model.Config;
import org.jvnet.hudson.test.HudsonTestCase;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: jet
 * Date: 3/6/12
 * Time: 6:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class XPathConfigProviderTest extends HudsonTestCase {
    public void testGetAllConfigs() throws Exception {
        ExtensionList<ConfigProvider> extensionList = this.hudson.getExtensionList(ConfigProvider.class);
        
        assertNotNull(extensionList);
        assertEquals(1, extensionList.size());

        ConfigProvider configProvider = extensionList.get(0);
        XPathConfig xPathConfigA = new XPathConfig("a", "a", "", "a");
        XPathConfig xPathConfigB = new XPathConfig("b", "b", "", "b");
        XPathConfig xPathConfigC = new XPathConfig("c", "c", "", "c");
        
        Collection<Config> expectedCollection = new ArrayList<Config>();
        expectedCollection.add(xPathConfigA);
        expectedCollection.add(xPathConfigB);
        expectedCollection.add(xPathConfigC);

        configProvider.save(xPathConfigB);
        configProvider.save(xPathConfigA);
        configProvider.save(xPathConfigC);

        Collection<Config> allConfigs = configProvider.getAllConfigs();

        assertEquals(expectedCollection, allConfigs);
    }
}
