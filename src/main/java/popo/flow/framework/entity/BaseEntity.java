package popo.flow.framework.entity;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.*;
import popo.flow.framework.config.TestFrameworkConfig;
import popo.flow.framework.driver.Browser;

@Log4j2
public class BaseEntity {

    private static AnnotationConfigApplicationContext applicationContext;

    @Parameters(value = "browser")
    @BeforeMethod(alwaysRun = true)
    public void before(@Optional(value = "default") String browserName) {
        Browser.getInstance(browserName);
        Browser.openStartPage();
        applicationContext = new AnnotationConfigApplicationContext(TestFrameworkConfig.class);
    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        Browser.cleanInstance();
    }

    protected WebDriver getWebDriver() {
        return applicationContext.getBean(WebDriver.class);
    }
}
