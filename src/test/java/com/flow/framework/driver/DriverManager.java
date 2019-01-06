package com.flow.framework.driver;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;

public final class DriverManager {

    public static void setUp(Browser.Browsers browser) {
        setWebDriver(browser);
    }

    private static void setWebDriver(Browser.Browsers browserType) {
        switch (browserType) {
            case CHROME:
                Configuration.browser = Browsers.CHROME;
                break;
            case FIREFOX:
                Configuration.browser = Browsers.FIREFOX;
                break;
        }
    }

    public static WebDriver getDriver() {
        return WebDriverRunner.getWebDriver();
    }
}
