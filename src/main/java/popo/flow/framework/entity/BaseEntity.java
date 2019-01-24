package popo.flow.framework.entity;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import popo.flow.framework.driver.Browser;
import popo.flow.framework.driver.DriverManager;

@Log4j2
public class BaseEntity {

    @Parameters(value = "browser")
    @BeforeMethod(alwaysRun = true)
    public void before(@Optional(value = "default") String browserName) {
        Browser.getInstance(browserName);
        Browser.openStartPage();
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestFrameworkConfig.class);
//        driver = applicationContext.getBean(WebDriver.class);
    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        Browser.cleanInstance();
    }

    protected WebDriver getWebDriver() {
        return DriverManager.getDriver();
    }
}
