package com.pointr.utilities;

import com.pointr.step_definitions.Hooks;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    private static final Logger logger = LoggerFactory.getLogger(Driver.class);

    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    public Driver() {

    }

    public synchronized static WebDriver getDriver() {
        if (driverPool.get() == null) {
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();

            if (System.getProperty("browser") != null) {
                browser = System.getProperty("browser");
            }

            switch (browser) {

                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    chromeOptions.setExperimentalOption("prefs", prefs);
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;

                case "chromeheadless":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new");
                    Map<String, Object> prefs2 = new HashMap<String, Object>();
                    options.setExperimentalOption("prefs", prefs2);
                    driverPool.set(new ChromeDriver(options));
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    Map<String, Object> prefsEdge = new HashMap<String, Object>();
                    edgeOptions.setExperimentalOption("prefs", prefsEdge);
                    edgeOptions.addArguments("--remote-allow-origins=*");
                    edgeOptions.addArguments("--start-maximized");
                    edgeOptions.addArguments("--disable-notifications");
                    driverPool.set(new EdgeDriver(edgeOptions));
                    break;

                default:
                    throw new RuntimeException("Wrong browser name!");
            }
        }
        logger.info("DRIVER SETUP");
        return driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}

