package popo.flow.framework.driver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.ResourceBundle;

@Log4j2
public final class Browser {

    private static Browser instance;
    private static ResourceBundle rbStage = ResourceBundle.getBundle("stage");
    private static ResourceBundle rbBrowser = ResourceBundle.getBundle("browser");
    private static Browsers currentBrowser = Browsers.valueOf((System.getenv("browser") == null
            ? rbBrowser.getString("browser")
            : System.getenv("browser")).toUpperCase());

    private static final String BROWSER_URL = String.format(rbStage.getString("url"), rbStage.getString("stage"));
    private static final Long TIMEOUT = Long.valueOf(rbBrowser.getObject("browser.timeout").toString());
    private static final boolean BROWSER_HEADLESS = Boolean.valueOf(rbBrowser.getString("browser.headless"));

    private Browser(Browsers browserName) {
        log.info(String.format("Init %s browser", browserName.name()));
    }

    public static void getInstance(String browserName) {
        if (instance == null) {
            synchronized (Browser.class) {
                if (!Boolean.valueOf(browserName)) {
                    currentBrowser = Browsers.valueOf(browserName.toUpperCase());
                }
                initBrowserProperties();
                instance = new Browser(currentBrowser);
            }
        }
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

    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public enum Browsers {
        FIREFOX("firefox"),
        CHROME("chrome"),
        IE("ie"); //Open Internet Explorer browser. Go to menu View -> Zoom -> Select 100% & Settings -> Security -> Lower & uncheck checkbox

        @Getter
        private final String value;
    }
}
