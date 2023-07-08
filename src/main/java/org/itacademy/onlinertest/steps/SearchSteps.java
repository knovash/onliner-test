package org.itacademy.onlinertest.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.pages.CatalogPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

@Log4j2
public class SearchSteps {

    private CatalogPage catalogPage = new CatalogPage();

    @Step("check item in results")
    public void checkItemInResults(String item) {
        log.info("check item in results");
        ElementsCollection results = CheapestSteps.searchResultsElements;
        log.info("results: " + results.size());
        SoftAssert sa = new SoftAssert();
        Assert.assertFalse(results.isEmpty(), "RESULT LIST IS EMPTY");
        results.stream()
                .map(element -> getTitle(element))
                .peek(title -> sa.assertTrue(title.contains(item.toLowerCase()), "[" + title + "] NOT CONTAINS [" + item + "]"))
                .forEach(title -> log.info("\nTITLE: " + title + " - contains - " + item + " = " + title.contains(item)));
        sa.assertAll();
    }

    public String getTitle(SelenideElement element) {
        String title = element.$(By.xpath(catalogPage.cheapestProductTitle)).getText().toLowerCase();
        return title;
    }
}