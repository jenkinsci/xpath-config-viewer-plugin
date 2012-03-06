package org.jenkinsci.plugins.viewer;

import org.jenkinsci.lib.configprovider.model.Config;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: jet
 * Date: 3/6/12
 * Time: 6:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class XPathConfigComparator implements Comparator<Config> {
    public int compare(Config config1, Config config2) {
        if(config1 instanceof XPathConfig && config2 instanceof XPathConfig) {
            XPathConfig xPathConfig1 = (XPathConfig) config1;
            XPathConfig xPathConfig2 = (XPathConfig) config2;

            return xPathConfig1.getId().compareTo(xPathConfig2.getId());
        }
        return 0;
    }
}
