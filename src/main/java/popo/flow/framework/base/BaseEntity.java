package popo.flow.framework.base;

import com.codeborne.selenide.testng.BrowserPerClass;
import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import popo.flow.framework.driver.Browser;
import popo.flow.framework.helpers.listener.TestListener;

@Log4j2
@Listeners({TestListener.class, ScreenShooter.class, BrowserPerTest.class, BrowserPerClass.class})
public class BaseEntity {

    @Parameters(value = {"browser"})
    @BeforeMethod(alwaysRun = true)
    public void before(@Optional(value = "default") String browserName) {
        Browser.getInstance(browserName);
        Browser.openStartPage();
    }

    protected RemoteWebDriver getWebDriver() {
        return Browser.getDriver();
    }
}
