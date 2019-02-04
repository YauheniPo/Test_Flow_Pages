package popo.flow.framework.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import popo.flow.framework.driver.Browser;

import java.util.Objects;

public class Waiters {

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
