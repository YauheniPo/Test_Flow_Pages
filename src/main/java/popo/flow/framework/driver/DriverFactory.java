package popo.flow.framework.driver;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.ie.InternetExplorerDriver;

@Log4j2
public final class DriverFactory {

    public static void setUp(Browser.BrowserType browser) {
        setWebDriver(browser);
    }

    private static void setWebDriver(Browser.BrowserType browserType) {
        switch (browserType) {
            case CHROME:
                Configuration.browser = Browsers.CHROME;
                break;
            case FIREFOX:
                Configuration.browser = Browsers.FIREFOX;
                break;
            case EDGE:
                Configuration.browser = Browsers.EDGE;
                break;
            case IE:
                Configuration.browser = Browsers.INTERNET_EXPLORER;
                setIECapabilities();
                break;
            default:
                log.info("Default CHROME Browser");
                Configuration.browser = Browsers.CHROME;
                break;
        }
    }

    private static void setIECapabilities() {
        Configuration.browserCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        Configuration.browserCapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS,false);
        Configuration.browserCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
    }
}
