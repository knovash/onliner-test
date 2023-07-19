package org.itacademy.onlinertest.steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.pages.CatalogPage;
import org.itacademy.onlinertest.utils.Config;
import org.itacademy.onlinertest.utils.ElementUtils;
import org.itacademy.onlinertest.utils.WaitUtils;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.logging.Level;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class СucumberSearchSteps {

    private CatalogPage catalogPage = new CatalogPage();

    @Before
    public void before() {
        log.info("BEFORE");
        Config.getProperties();
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .enableLogs(LogType.BROWSER, Level.ALL));
        Configuration.browserSize = "1920x1080";
        Configuration.browser = CHROME;
        /** https://github.com/selenide/selenide/issues/1268 def 30 sec. for mobile connection 90 000 msec */
        Configuration.pageLoadTimeout = 90000;
    }

    @Given("I am on the main page")
    public void openpage() {
        log.info("GIVEN I am on the main page");
        log.info("OPEN " + Config.getHomePage());
        Selenide.open(Config.getHomePage());
    }

    @When("I enter the product name {string} in the search field")
    public void inputSearchValue(String value) {
        log.info("WHEN I enter the product name {string} in the search field");
        log.info("value= " + value);
        WaitUtils.waitForVisibility(catalogPage.fastSearchInput, 60);
        catalogPage.fastSearchInput.setValue(value);
    }

    @Then("I check that a frame with a list of found products has appeared")
    public void switchToResultsFrame() {
        log.info("THEN I check that a frame with a list of found products has appeared");
        SelenideElement frame = catalogPage.frame;
        WaitUtils.waitForVisibility(frame);
        Assert.assertTrue(frame.isDisplayed());
        getWebDriver().switchTo().frame(frame);

        CheapestSteps.searchResultsElements = catalogPage.searchResults;
        WaitUtils.waitForVisibility(CheapestSteps.searchResultsElements.get(0));
    }

    @Then("I check that the product name {string} is in the list")
    public void checkItemInResults(String item) {
        log.info("THEN I check that the product name {string} is in the list");
        log.info("item= " + item);
        ElementsCollection results = CheapestSteps.searchResultsElements;
        log.info("results: " + results.size());
        SoftAssert sa = new SoftAssert();
        Assert.assertFalse(results.isEmpty(), "RESULT LIST IS EMPTY");
        results.stream()
                .map(element -> ElementUtils.getTitle(element))
                .peek(title -> sa.assertTrue(title.toLowerCase().contains(item.toLowerCase()), "[" + title + "] NOT CONTAINS [" + item + "]"))
                .forEach(title -> log.info("\nTITLE: " + title + " - contains - " + item + " = " + title.toLowerCase().contains(item.toLowerCase())));
        sa.assertAll();
    }

    @After
    public void after() {
        Selenide.clearBrowserCookies();
    }
}