package popo.flow.framework.driver;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.RemoteWebDriver;
import popo.flow.framework.util.ResourcePropertiesManager;

import javax.naming.NamingException;
import java.util.concurrent.TimeUnit;

@Log4j2
public final class Browser {

    private static Browser instance = new Browser();
    private static ThreadLocal<RemoteWebDriver> driverHolder = new ThreadLocal<>();
    private static ResourcePropertiesManager rpStage = new ResourcePropertiesManager("stage.properties");
    private static ResourcePropertiesManager rpBrowser = new ResourcePropertiesManager("browser.properties");
    private static Browsers currentBrowser = Browsers.valueOf((System.getenv("browser") == null
            ? rpBrowser.getProperty("browser")
            : System.getenv("browser")).toUpperCase());

    private static final String BROWSER_URL = String.format(rpStage.getProperty("url"), rpStage.getProperty("stage"));
    private static final Long IMPLICITLY_WAIT = Long.valueOf(rpBrowser.getProperty("browser.timeout"));
    private static final Long PAGE_IMPLICITLY_WAIT = Long.valueOf(rpBrowser.getProperty("browser.pagetimeout"));
    static final boolean IS_BROWSER_HEADLESS = Boolean.valueOf(rpBrowser.getProperty("browser.headless"));

    public static Browser getInstance(String browserName) {
        if (instance == null) {
            synchronized (Browser.class) {
                instance = new Browser();
            }
        }
        setBrowser(browserName);
        return instance;
    }

    private static void setBrowser(String browserName) {
        if (!Browsers.valueOf(browserName.toUpperCase()).equals(Browsers.DEFAULT)) {
            currentBrowser = Browsers.valueOf(browserName.toUpperCase());
        }
    }

    public static RemoteWebDriver getDriver() {
        if (driverHolder.get() == null) {
            driverHolder.set(getNewDriver());
        }
        return driverHolder.get();
    }

    private static RemoteWebDriver getNewDriver() {
        try {
            RemoteWebDriver remoteWebDriver = DriverManager.setUp(currentBrowser);
            remoteWebDriver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT, TimeUnit.SECONDS);
            remoteWebDriver.manage().timeouts().pageLoadTimeout(PAGE_IMPLICITLY_WAIT, TimeUnit.SECONDS);
            return remoteWebDriver;
        } catch (NamingException e) {
            log.debug(e.getMessage());
        }
        return null;
    }

    private static void windowMaximize() {
        getDriver().manage().window().maximize();
    }

    public static void openStartPage() {
        WebDriverRunner.setWebDriver(getDriver());
        Selenide.open(BROWSER_URL);
        windowMaximize();
    }

    public static void refreshPage() {
        Selenide.refresh();
    }

    private static boolean isBrowserAlive() {
        return driverHolder.get() != null;
    }

    public static void exit() {
        try {
            getDriver().quit();
        } catch (Exception e) {
            log.info(e);
        } finally {
            if (isBrowserAlive()) {
                driverHolder.set(null);
            }
        }
    }

    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Browsers {
        FIREFOX("firefox"),
        CHROME("chrome"),
        IE("ie"), //Open Internet Explorer browser. Go to menu View -> Zoom -> Select 100% & Settings -> Security -> Lower & uncheck checkbox,
        DEFAULT("default");

        @Getter
        private final String value;
    }
}
