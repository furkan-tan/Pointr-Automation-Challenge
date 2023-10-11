package com.pointr.step_definitions;

import com.pointr.utilities.ConfigurationReader;
import com.pointr.utilities.Driver;
import com.pointr.utilities.LogToFile;

import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import org.junit.AfterClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.net.SocketException;

public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    WebDriver driver = Driver.getDriver();

    @BeforeAll
    public static void clearLogs() {
        LogToFile.clearAllLogFiles();
        String header = String.format("%-30s | %-30s | %-10s | %-50s", "testType", "username", "password", "errorMessage");
        LogToFile.saveLogToFile(header, "loginerror.txt");
    }

    @Before
    public void testSetup(Scenario scenario) {
        logger.info("=======================================================================================================================================================");
        MDC.put("testName", scenario.getName());
        logger.info("Starting New Test");
        Driver.getDriver().manage().deleteAllCookies();
        Driver.getDriver().manage().window().maximize();
        navigateToHomePage();
    }

    @After
    public void testTearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.error("FAILED SCENARIO:" + scenario.getName());
            TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
            byte[] image = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            scenario.attach(image, "image/png", scenario.getName());
        }
        logger.info("Test Completed");
        MDC.remove("testName");
        logger.info("=======================================================================================================================================================");
        Driver.closeDriver();
    }

    @Given("user is on N11 home page")
    public void user_is_on_n11_home_page() {
        logger.info("Navigating to home page");
        navigateToHomePage();
    }

    public void navigateToHomePage() {
        String currentURL = driver.getCurrentUrl();
        String homePage = ConfigurationReader.getBaseUrl();
        if (!currentURL.equals(homePage)) {
            Driver.getDriver().navigate().to(homePage);
        }
    }
}
