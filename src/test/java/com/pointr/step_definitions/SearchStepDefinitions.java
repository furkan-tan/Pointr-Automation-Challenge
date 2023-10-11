package com.pointr.step_definitions;

import com.pointr.pages.SearchPage;
import com.pointr.utilities.BrowserUtils;
import com.pointr.utilities.ConfigurationReader;
import com.pointr.utilities.LogToFile;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class SearchStepDefinitions {

    SearchPage searchPage = new SearchPage();
    String searchTerm;


    @When("user searches for a valid term")
    public void user_searches_for_a_valid_term() {
        searchTerm = ConfigurationReader.getSearchTerm();
        searchPage.searchForATerm(searchTerm);
    }

    @Then("user should see search results")
    public void user_should_see_search_results() {
        Assert.assertTrue(searchPage.isAnySearchResultDisplayed());
    }

    @Then("search term, results and screenshots are logged in {string}")
    public void search_term_results_and_screenshots_are_logged_in(String fileName) {
        String searchTerm = this.searchTerm;
        LogToFile.saveLogToFile("Search Term:" + searchTerm, fileName);
        LogToFile.saveLogToFile("Results:", fileName);

        List<String> productNames = searchPage.getSearchResultsAsList();

        for (String productName : productNames) {
            String record = String.format("    %s", productName);
            LogToFile.saveLogToFile(record, fileName);
        }

        String screenshotPath = BrowserUtils.takeScreenshot(searchTerm);
        LogToFile.saveLogAndScreenshotToFile("Search Result as Screenshot:", screenshotPath, fileName);
    }

    @When("user searches for an invalid term")
    public void user_searches_for_an_invalid_term() {
        searchTerm = "awsd";
        searchPage.searchForATerm(searchTerm);
    }

    @Then("user should see warning message on search results")
    public void user_should_see_warning_message_on_search_results() {
        Assert.assertTrue(searchPage.isInvalidSearchResultDisplayed());
    }
}
