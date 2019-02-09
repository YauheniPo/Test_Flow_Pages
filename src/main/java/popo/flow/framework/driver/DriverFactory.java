package popo.flow.framework.driver;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.ie.InternetExplorerDriver;

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
            case IE:
                Configuration.browser = Browsers.INTERNET_EXPLORER;
                setIECapabilities();
                break;
        }
    }

    private static void setIECapabilities() {
        Configuration.browserCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        Configuration.browserCapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS,false);
        Configuration.browserCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
    }
}
