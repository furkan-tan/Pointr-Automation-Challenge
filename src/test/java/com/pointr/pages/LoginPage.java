package com.pointr.pages;

import com.pointr.utilities.Driver;
import com.pointr.utilities.WaitUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static com.pointr.utilities.WaitUtils.waitForVisibility;
import static com.pointr.utilities.WaitUtils.waitUntilClickable;


public class LoginPage extends PageBase {

    @FindBy(id = "email")
    private WebElement emailBox;

    @FindBy(id = "password")
    private WebElement passwordBox;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@class='error-message' or (@class='errorText' and text())]")
    private List<WebElement> errorMessages;

    public void enterUsername(String username) {
        logger.info("Entering " + username + " as username");
        waitForVisibility(emailBox);
        emailBox.clear();
        emailBox.sendKeys(username);
    }

    public void enterPassword(String password) {
        logger.info("Entering " + password + " as password");
        waitForVisibility(passwordBox).sendKeys(password);
    }

    public void clickOnLoginButton() {
        logger.info("Click Action on Login Button");
        loginButton.click();
    }

    public boolean isAnyErrorMessageDisplayed() {
        try {
            WaitUtils.waitForPageToLoad(10);
            return errorMessages.size() > 0;
        } catch (Exception e) {
            logger.error("Error Messages are not displayed!");
            return false;
        }
    }

    public List<String> getErrorMessagesAsText() {
        WaitUtils.waitForPageToLoad(10);
        return errorMessages.stream().map(WebElement::getText).collect(Collectors.toList());
    }


}
