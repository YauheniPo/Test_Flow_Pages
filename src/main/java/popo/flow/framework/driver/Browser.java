package popo.flow.framework.driver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.RemoteWebDriver;
import popo.flow.framework.util.ResourcePropertiesManager;

import java.util.Locale;

@Log4j2
public final class Browser {

    private volatile static Browser instance = new Browser();
    private static ResourcePropertiesManager rpStage = new ResourcePropertiesManager("stage.properties");
    private static ResourcePropertiesManager rpBrowser = new ResourcePropertiesManager("browser.properties");
    @Getter private static final String BROWSER_URL = String.format(rpStage.getProperty("url"), rpStage.getProperty("stage"));
    private static final Long IMPLICITLY_WAIT = Long.valueOf(rpBrowser.getProperty("browser.timeout"));
    @Getter private static final Long PAGE_LOADING_WAIT = Long.valueOf(rpBrowser.getProperty("browser.pagetimeout"));
    private static final boolean IS_BROWSER_HEADLESS = Boolean.valueOf(rpBrowser.getProperty("browser.headless"));
    public static final String LOCATORS = rpBrowser.getProperty("locators");
    private static BrowserType currentBrowser = BrowserType.valueOf((System.getenv("browser") == null
            ? rpBrowser.getProperty("browser") : System.getenv("browser")).toUpperCase(Locale.ENGLISH));

    public static void getInstance(String browserName) {
        if (instance == null) {
            synchronized (Browser.class) {
                instance = new Browser();
            }
        }
        setBrowser(browserName);
    }

    private static synchronized void setBrowser(String browserName) {
        if (!BrowserType.valueOf(browserName.toUpperCase(Locale.ENGLISH)).equals(BrowserType.DEFAULT)) {
            currentBrowser = BrowserType.valueOf(browserName.toUpperCase(Locale.ENGLISH));
        }
        fetchNewDriver();
    }

    private static void fetchNewDriver() {
        Configuration.timeout = IMPLICITLY_WAIT;
        Configuration.headless = IS_BROWSER_HEADLESS;
        Configuration.baseUrl = BROWSER_URL;
        Configuration.startMaximized = true;
        DriverFactory.setUp(currentBrowser);
        log.info(String.format("*************** %s ***************",
                ((RemoteWebDriver) WebDriverRunner.getAndCheckWebDriver()).getCapabilities().getBrowserName()).toUpperCase(Locale.ENGLISH));
    }

    private static void windowMaximize() {
        getDriver().executeScript("if (window.screen) {window.moveTo(0, 0);window.resizeTo(window.screen.availWidth,window.screen.availHeight);};");
        getDriver().manage().window().maximize();
        log.info(String.format("Final window size: %s", getDriver().manage().window().getSize()));
    }

    public static void openStartPage() {
        Selenide.open("/");
        windowMaximize();
    }

    public static void refreshPage() {
        Selenide.refresh();
    }

    public static RemoteWebDriver getDriver() {
        return (RemoteWebDriver) WebDriverRunner.getWebDriver();
    }

    @AllArgsConstructor()
    public enum BrowserType {
        FIREFOX("firefox"),
        CHROME("chrome"),
        EDGE("edge"),
        IE("ie"), //Open Internet Explorer browser. Go to menu View -> Zoom -> Select 100% & Settings -> Security -> Lower & uncheck checkbox,
        DEFAULT("default");

        @Getter
        private final String value;
    }
}
