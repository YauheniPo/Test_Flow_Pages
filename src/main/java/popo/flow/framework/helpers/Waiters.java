package popo.flow.framework.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import popo.flow.framework.driver.Browser;

import java.util.Objects;

public class Waiters {

    public static final short EXIST_TIMEOUT = 6000;

    public static void waitForPageLoaded() {
        waitForPageLoaded(Browser.getPAGE_LOADING_WAIT());
    }

    public static void waitForPageLoaded(long timeout) {
        new WebDriverWait(Browser.getDriver(), timeout)
                .until((ExpectedCondition<Boolean>) driver -> ((JavascriptExecutor) Objects.requireNonNull(driver))
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }

    public static WebDriverWait getWebDriverWait(long timeout) {
        return new WebDriverWait(Browser.getDriver(), timeout);
    }
}
