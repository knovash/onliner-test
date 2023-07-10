package org.itacademy.onlinertest.steps;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.utils.ElementUtils;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

@Log4j2
public class SearchSteps {

    @Step("check item in results")
    public SoftAssert checkItemInResults(String item) {
        log.info("check item in results");
        ElementsCollection results = CheapestSteps.searchResultsElements;
        log.info("results: " + results.size());
        SoftAssert sa = new SoftAssert();
        Assert.assertFalse(results.isEmpty(), "RESULT LIST IS EMPTY");
        results.stream()
                .map(element -> ElementUtils.getTitle(element))
                .peek(title -> sa.assertTrue(title.toLowerCase().contains(item.toLowerCase()), "[" + title + "] NOT CONTAINS [" + item + "]"))
                .forEach(title -> log.info("\nTITLE: " + title + " - contains - " + item + " = " + title.toLowerCase().contains(item.toLowerCase())));
        return sa;
    }
}