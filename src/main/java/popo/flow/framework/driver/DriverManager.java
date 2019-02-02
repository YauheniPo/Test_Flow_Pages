package popo.flow.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.naming.NamingException;

public final class DriverManager {

    public static RemoteWebDriver setUp(Browser.Browsers browser) throws NamingException {
        return setWebDriver(browser);
    }

    private static RemoteWebDriver setWebDriver(Browser.Browsers browserType) throws NamingException {
        RemoteWebDriver remoteWebDriver;
        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                remoteWebDriver = new ChromeDriver(getChromeOptionsHeadless());
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                remoteWebDriver = new FirefoxDriver(getFirefoxOptionsHeadless());
                break;
            case IE:
                setIECapabilities();
                WebDriverManager.iedriver().setup();
                remoteWebDriver = new InternetExplorerDriver(setIECapabilities());
                break;
            default:
                throw new NamingException("Incorrect Browser name");
        }
        return remoteWebDriver;
    }

    private static InternetExplorerOptions setIECapabilities() {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        internetExplorerOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS,false);
        internetExplorerOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
        return internetExplorerOptions;
    }

    /**
     * Gets chrome options headless.
     *
     * @return the chrome options headless
     */
    private static ChromeOptions getChromeOptionsHeadless() {
        ChromeOptions options = new ChromeOptions();
        if (Browser.IS_BROWSER_HEADLESS) {
            options.setHeadless(true);
        }
        return options;
    }

    /**
     * Gets firefox options headless.
     *
     * @return the firefox options headless
     */
    private static FirefoxOptions getFirefoxOptionsHeadless() {
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        if (Browser.IS_BROWSER_HEADLESS) {
            firefoxBinary.addCommandLineOptions("--headless");
        }
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        return firefoxOptions;
    }
}
