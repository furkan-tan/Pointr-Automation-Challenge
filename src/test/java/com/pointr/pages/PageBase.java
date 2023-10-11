package com.pointr.pages;

import com.pointr.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class PageBase {
    static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    WebDriver driver = Driver.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    Actions actions = new Actions(driver);

    public PageBase() {
        PageFactory.initElements(driver, this);
    }
}
