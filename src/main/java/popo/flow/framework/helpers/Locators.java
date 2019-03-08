package popo.flow.framework.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import popo.flow.framework.driver.Browser;
import popo.flow.framework.util.ResourcePropertiesManager;

import static com.codeborne.selenide.Selectors.*;

@Log4j2
public class Locators {

    private static final ResourcePropertiesManager locators;
    private static final String XPATH_CONTAINS_CLASS = "//%s[contains(@class, '%s')]";

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
                return getByText(locator);
            case partText:
                return getWithText(locator);
            case id:
                return byId(locator);
            case className:
                return byClassName(locator);
            case classNamePart:
                return getByPartialClassTextOfAnyElement(locator);
            case xPath:
                return getByXpath(locator);
            default:
                throw new IllegalArgumentException(String.format("No suck locator type: %s", locatorType.toString()));
        }
    }

    public static String getString(String locatorName) {
        log.info(String.format("%s <-- string", locatorName));
        return locators.getProperty(locatorName);
    }

    public static By getByText(String locator) {
        log.info(String.format("%s <-- by text", locator));
        return byText(locator);
    }

    public static By getWithText(String locator) {
        log.info(String.format("%s <-- with text", locator));
        return withText(locator);
    }

    public static By getByXpath(String locator) {
        log.info(String.format("%s <-- xpath", locator));
        return byXpath(locator);
    }

    public static By getByPartialClassTextOfElement(LocatorElement locatorElement, String partialClassText) {
        String locator = String.format(XPATH_CONTAINS_CLASS, locatorElement.getDomElement(), partialClassText);
        log.info(String.format("%s <-- partial class text of element", locator));
        return byXpath(locator);
    }

    public static By getByPartialClassTextOfAnyElement(String partialClassText) {
        String locator = String.format(XPATH_CONTAINS_CLASS, LocatorElement.ANY.getDomElement(), partialClassText);
        log.info(String.format("%s <-- partial class text of any element", locator));
        return byXpath(locator);
    }

    private enum LocatorType {
        id, css, xPath, text, partText, className, classNamePart
    }

    @AllArgsConstructor()
    @Getter
    public enum LocatorElement {
        ANY("*"), DIV("div");

        private String domElement;
    }
}
