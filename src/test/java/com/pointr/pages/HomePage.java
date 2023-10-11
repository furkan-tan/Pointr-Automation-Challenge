package com.pointr.pages;

import com.pointr.utilities.BrowserUtils;
import com.pointr.utilities.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.pointr.utilities.WaitUtils.waitForVisibility;

public class HomePage extends PageBase {

    //=========Login/Account Elements==========

    @FindBy(xpath = "//*[text()='Giriş Yap']")
    private WebElement loginLink;

    @FindBy(xpath = "//*[@title='Hesabım']")
    private WebElement accountAvatar;

    @FindBy(xpath = "//*[text()='Çıkış Yap']")
    private WebElement logoutButton;


    //=========Login/Account Methods==========

    public void navigateToLoginPage() {
        logger.info("Navigating to Login Page");
        waitForVisibility(loginLink).click();
    }

    public String getAccountAvatarText() {
        logger.info("Getting text from Account Avatar");
        return waitForVisibility(accountAvatar).getText();
    }

    public void clickLogoutButton() {
        logger.info("Logging Out from the Account");
        waitForVisibility(accountAvatar);
        actions.moveToElement(accountAvatar).pause(1000).moveToElement(logoutButton).click().build().perform();
    }

    public boolean isLoginLinkVisible() {
        logger.info("Checking if Login Link is Visible");
        WaitUtils.waitForVisibility(loginLink, 5);
        return loginLink.isDisplayed();
    }

}
