package com.flow.framework.entity;

import com.flow.framework.driver.Browser;
import com.flow.framework.driver.DriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Log4j2
public class BaseEntity {

    @Parameters(value = "browser")
    @BeforeSuite
    public void before(@Optional(value = "true") String browserName) {
        Browser.getInstance(browserName);
        Browser.openStartPage();
    }

    protected WebDriver getWebDriver() {
        return DriverManager.getDriver();
    }
}
