package com.voxsmart.config;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class LocalWebDriver {

    private static final Logger log = (Logger) Logger.getLogger("LocalWebDriver");

    public  static WebDriver REAL_DRIVER;
    private final  Thread    CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            quitGlobalInstance();
        }
    };

    public ProjectProperties properties = new ProjectProperties();

    private enum BrowserType {
        FIREFOX, CHROME
    }

    public LocalWebDriver() {
        log.info("Initialising LocalWebDriver...");
        REAL_DRIVER = getLocalWebDriver(BrowserType.valueOf(properties.getBrowserName()));
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    private void quitGlobalInstance() {
        log.info("Quitting Global Instance...");
        WebDriver driver = REAL_DRIVER;
        REAL_DRIVER = null;
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getLocalWebDriver(BrowserType browser) {
        if (REAL_DRIVER == null) {
            switch (browser) {
                case CHROME:
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/driver_binaries/chrome/chromedriver.exe");
                    REAL_DRIVER = new ChromeDriver();
                    checkBrowserOS();
                    break;
                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", "src/test/resources/driver_binaries/firefox/geckodriver.exe");
                    REAL_DRIVER = new FirefoxDriver();
                    checkBrowserOS();
                    break;
            }
        }
        return REAL_DRIVER;
    }
    private void checkBrowserOS() {
        //Get Browser name and version.
        Capabilities caps = ((RemoteWebDriver) REAL_DRIVER).getCapabilities();
        String browserName = caps.getBrowserName();
        String browserVersion = caps.getVersion();

        //Get OS name.
        String os = System.getProperty("os.name").toLowerCase();
        log.info("STARTING TESTS ON OS: " + os + ", BROWSER: " + browserName + " v_"+ browserVersion);
    }

}
