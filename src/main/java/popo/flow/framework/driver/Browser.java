package popo.flow.framework.driver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import popo.flow.framework.util.ResourcePropertiesManager;

@Log4j2
public final class Browser {

    private static Browser instance;
    private static ResourcePropertiesManager rpStage = new ResourcePropertiesManager("stage.properties");
    private static ResourcePropertiesManager rpBrowser = new ResourcePropertiesManager("browser.properties");
    private static Browsers currentBrowser = Browsers.valueOf((System.getenv("browser") == null
            ? rpBrowser.getProperty("browser")
            : System.getenv("browser")).toUpperCase());

    private static final String BROWSER_URL = String.format(rpStage.getProperty("url"), rpStage.getProperty("stage"));
    private static final Long TIMEOUT = Long.valueOf(rpBrowser.getProperty("browser.timeout"));
    private static final boolean BROWSER_HEADLESS = Boolean.valueOf(rpBrowser.getProperty("browser.headless"));

    private Browser(Browsers browserName) {
        log.info(String.format("Init %s browser", browserName.name()));
    }

    public static Browser getInstance(String browserName) {
        if (instance == null) {
            synchronized (Browser.class) {
                if (!Browsers.valueOf(browserName.toUpperCase()).equals(Browsers.DEFAULT)) {
                    currentBrowser = Browsers.valueOf(browserName.toUpperCase());
                }
                initBrowserProperties();
                instance = new Browser(currentBrowser);
            }
        }
        return instance;
    }

    private static void initBrowserProperties() {
        Configuration.timeout = TIMEOUT;
        Configuration.headless = BROWSER_HEADLESS;

        DriverManager.setUp(currentBrowser);
    }

    private static void windowMaximize() {
        DriverManager.getDriver().manage().window().maximize();
    }

    public static void openStartPage() {
        Selenide.open(BROWSER_URL);
        windowMaximize();
    }

    public static void refreshPage() {
        Selenide.refresh();
    }

    public static void cleanInstance() {
        instance = null;
    }

    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public enum Browsers {
        FIREFOX("firefox"),
        CHROME("chrome"),
        IE("ie"), //Open Internet Explorer browser. Go to menu View -> Zoom -> Select 100% & Settings -> Security -> Lower & uncheck checkbox,
        DEFAULT("default");

        @Getter
        private final String value;
    }
}
