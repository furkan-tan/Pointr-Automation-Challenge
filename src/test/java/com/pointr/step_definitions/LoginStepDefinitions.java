package com.pointr.step_definitions;

import com.pointr.pages.HomePage;
import com.pointr.pages.LoginPage;
import com.pointr.utilities.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LoginStepDefinitions {

    private static final Logger logger = LoggerFactory.getLogger(LoginStepDefinitions.class);

    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();

    @When("user navigates to login page")
    public void user_navigates_to_login_page() {
        homePage.navigateToLoginPage();
    }

    @Then("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
        String username = ConfigurationReader.getUsername();
        String password = ConfigurationReader.getPassword();

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
    }

    @Then("log if it is unsuccessful login attempt")
    public void log_if_it_is_unsuccessful_login_attempt() {
        if (Driver.getDriver().getCurrentUrl().contains("giris-yap")) {
            String username = ConfigurationReader.getUsername();
            String password = ConfigurationReader.getPassword();
            String testType = "login with parameters";

            List<String> errorMessages = loginPage.getErrorMessagesAsText();

            for (String errorMessage : errorMessages) {
                String record = String.format("%-30s | %-30s | %-10s | %-50s", testType, username, password, errorMessage);
                LogToFile.saveLogToFile(record, "loginerror.txt");
            }
        }
    }

    @Then("user should should verify that account avatar has initials")
    public void user_should_should_verify_that_account_avatar_has_initials() {
        String actualText = homePage.getAccountAvatarText();
        String expectedText = "TA";
        Assert.assertEquals("Assert account initials matches", expectedText, actualText);
    }

    @When("user clicks logout button")
    public void user_clicks_logout_button() {
        homePage.clickLogoutButton();
    }

    @Then("user should be logged out successfully")
    public void user_should_be_logged_out_successfully() {
        boolean isLoginLinkVisible = homePage.isLoginLinkVisible();
        Assert.assertTrue(isLoginLinkVisible);
    }

    @Then("user enters {string} username and {string} password")
    public void user_enters_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
    }

    @Then("an error message is displayed")
    public void an_error_message_is_displayed() {
        Assert.assertTrue(loginPage.isAnyErrorMessageDisplayed());
    }

    @Then("the error message is logged in {string} as {string} {string} {string}")
    public void the_error_message_is_logged_in_loginerror_txt(String fileName, String testType, String username, String password) {
        List<String> errorMessages = loginPage.getErrorMessagesAsText();

        for (String errorMessage : errorMessages) {
            String record = String.format("%-30s | %-30s | %-10s | %-50s", testType, username, password, errorMessage);
            LogToFile.saveLogToFile(record, fileName);
        }
    }
}
