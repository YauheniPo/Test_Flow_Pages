package popo.flow.framework.helpers;

import lombok.extern.log4j.Log4j2;
import popo.flow.framework.driver.Browser;
import popo.flow.framework.util.ResourcePropertiesManager;

@Log4j2
public class Locators {

    private static final ResourcePropertiesManager locators;

    static {
        locators = new ResourcePropertiesManager(Browser.LOCATORS);
    }

    public static String get(String locatorName) {
        return locators.getProperty(locatorName);
    }
}
