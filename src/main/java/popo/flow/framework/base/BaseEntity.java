package popo.flow.framework.base;

import com.codeborne.selenide.testng.BrowserPerClass;
import com.codeborne.selenide.testng.BrowserPerTest;
import com.codeborne.selenide.testng.ScreenShooter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.SkipException;
import org.testng.annotations.*;
import popo.flow.framework.driver.Browser;
import popo.flow.framework.helpers.config.TestFrameworkConfig;
import popo.flow.framework.helpers.listener.TestListener;
import popo.flow.framework.util.OSValidator;

import java.lang.reflect.Method;

import static popo.flow.framework.Contants.LOGGER_THREAD_CONTEXT;

@Log4j2
@Listeners({TestListener.class, ScreenShooter.class, BrowserPerTest.class, BrowserPerClass.class})
public class BaseEntity {

    @BeforeSuite
    public void beforeSuite() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestFrameworkConfig.class);
        log.info(String.format("---------------- %s ----------------", OSValidator.OS));

        if (!applicationContext.getBean(Boolean.class)) {
            String massageSkipTests = "Skipping tests because resource was not available.";
            log.info(massageSkipTests);
            throw new SkipException(massageSkipTests);
        }
    }

    @Parameters(value = {"browser"})
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method m, @Optional(value = "default") String browserName) {
        try {
            ThreadContext.put(LOGGER_THREAD_CONTEXT, m.getName() + "-" + Thread.currentThread().getId());

            Browser.getInstance(browserName);
            Browser.openStartPage();
        } catch (Throwable throwable) {
            log.fatal(ExceptionUtils.getStackTrace(throwable));
        }
    }

    protected RemoteWebDriver getWebDriver() {
        try {
            return Browser.getDriver();
        } catch (Throwable throwable) {
            log.fatal(ExceptionUtils.getStackTrace(throwable));
        }
        return null;
    }
}
