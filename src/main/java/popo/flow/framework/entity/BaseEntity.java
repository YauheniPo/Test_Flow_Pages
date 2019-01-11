package popo.flow.framework.entity;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import popo.flow.framework.driver.Browser;
import popo.flow.framework.driver.DriverManager;

@Log4j2
public class BaseEntity {

    @Parameters(value = "browser")
    @BeforeSuite
    public void before(@Optional(value = "true") String browserName) {
        Browser.getInstance(browserName);
        Browser.openStartPage();
    }

    @AfterSuite
    public void after() {

    }

    protected WebDriver getWebDriver() {
        return DriverManager.getDriver();
    }
}
