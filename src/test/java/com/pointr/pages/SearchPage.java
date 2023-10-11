package com.pointr.pages;

import com.pointr.utilities.BrowserUtils;
import com.pointr.utilities.WaitUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends PageBase {

    //=========Search Elements==========

    @FindBy(id = "searchData")
    private WebElement searchBox;

    @FindBy(xpath = "//*[@id='listingUl']//h3[@class='productName']")
    private List<WebElement> productNames;

    @FindBy(xpath = "//*[text()='Yazdığın kelimeyi kontrol ederek tekrar arayabilirsin.']")
    private WebElement invalidSearchResultText;

    //=========Search Methods==========

    public void searchForATerm(String term) {
        searchBox.sendKeys(term, Keys.ENTER);
    }

    public List<String> getSearchResultsAsList() {
        try {
            return Collections.singletonList(invalidSearchResultText.getText());
        } catch (Exception e) {
            return productNames.stream().map(WebElement::getText).filter(element -> !element.isEmpty()).collect(Collectors.toList());
        }
    }

    public boolean isAnySearchResultDisplayed() {
        WaitUtils.wait(5);
        return productNames.size() > 0;
    }

    public boolean isInvalidSearchResultDisplayed() {
        WaitUtils.wait(5);
        return invalidSearchResultText.isDisplayed();
    }
}
