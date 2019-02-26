package popo.flow.framework.helpers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import popo.flow.framework.driver.Browser;
import popo.flow.framework.util.ResourcePropertiesManager;

import static com.codeborne.selenide.Selectors.*;

@Log4j2
public class Locators {

    private static final ResourcePropertiesManager locators;

    static {
        locators = new ResourcePropertiesManager(Browser.LOCATORS);
    }

    public static By get(String locatorName) {
        String locatorProperty = locators.getProperty(locatorName);
        String[] locatorProperties = locatorProperty.split("=");
        String locator = locatorProperties[1];
        LocatorType locatorType = LocatorType.valueOf(locatorProperties[0]);
        switch (locatorType) {
            case text:
                return byText(locator);
            case partText:
                return withText(locator);
            case id:
                return byId(locator);
            case className:
                return byClassName(locator);
            case xPath:
                return byXpath(locator);
            default:
                throw new IllegalArgumentException(String.format("No suck locator type: %s", locatorType.toString()));
        }
    }

    private enum LocatorType {
        id, css, xPath, text, partText, className
    }
}
