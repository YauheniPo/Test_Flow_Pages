package popo.flow.framework.entity;

import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.*;
import popo.flow.framework.config.TestFrameworkConfig;
import popo.flow.framework.driver.Browser;

@Log4j2
@Listeners({ScreenShooter.class, BrowserPerTest.class})
public class BaseEntity {

    private static AnnotationConfigApplicationContext applicationContext;

    @Parameters(value = "browser")
    @BeforeMethod(alwaysRun = true)
    public void before(@Optional(value = "default") String browserName) {
        Browser.getInstance(browserName);
        Browser.openStartPage();
        applicationContext = new AnnotationConfigApplicationContext(TestFrameworkConfig.class);
    }

    @AfterMethod
    public void after() {
        Browser.exit();
    }

    protected RemoteWebDriver getWebDriver() {
        return applicationContext.getBean(RemoteWebDriver.class);
    }
}
