package org.itacademy.onlinertest.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.pages.CatalogPage;
import org.itacademy.onlinertest.utils.AllureListener;
import org.itacademy.onlinertest.utils.WaitUtils;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class BaseSteps {

    private final CatalogPage catalogPage = new CatalogPage();
    public static ElementsCollection searchResultsElements;

    @Step("input search value")
    public void inputSearchValue(String value) {
        log.info("Input search value: " + value);
        WaitUtils.waitForVisibility(catalogPage.fastSearchInput, 60);
        catalogPage.fastSearchInput.setValue(value);
    }

    @Step("switch to results frame")
    public void switchToResultsFrame() {
        log.info("switch to results frame");
        SelenideElement frame = catalogPage.frame;
        WaitUtils.waitForVisibility(frame);
        getWebDriver().switchTo().frame(frame);
    }

    @Step("get search results")
    public void getSearchResults() {
        log.info("get search results");
        searchResultsElements = catalogPage.searchResults;
        WaitUtils.waitForVisibility(searchResultsElements.get(0));
        AllureListener.screenShot();
    }
}