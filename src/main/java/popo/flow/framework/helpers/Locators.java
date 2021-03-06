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

    private static final ResourcePropertiesManager LOCATORS = new ResourcePropertiesManager(Browser.LOCATORS);

    public static By get(String locatorName) {
        String locatorProperty = LOCATORS.getProperty(locatorName);
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

    public static String getLocator(String locatorName) {
        log.info(String.format("%s <-- string", locatorName));
        return LOCATORS.getProperty(locatorName);
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

    private static By getByPartialClassTextOfAnyElement(String partialClassText) {
        final String xpathContainsClass = "//%s[contains(@class, '%s')]";
        String locator = String.format(xpathContainsClass, LocatorElement.ANY.getDomElement(), partialClassText);
        log.info(String.format("%s <-- partial class text of any element", locator));
        return byXpath(locator);
    }

    private enum LocatorType {
        id, xPath, text, partText, className, classNamePart
    }

    @AllArgsConstructor()
    @Getter
    public enum LocatorElement {
        ANY("*"), DIV("div");

        private String domElement;
    }
}
