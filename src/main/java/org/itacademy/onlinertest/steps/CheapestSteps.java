package org.itacademy.onlinertest.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.models.CatalogItem;
import org.itacademy.onlinertest.pages.CatalogPage;
import org.itacademy.onlinertest.utils.ElementUtils;
import org.itacademy.onlinertest.utils.JsonUtil;
import org.itacademy.onlinertest.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Comparator;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class CheapestSteps {

    private CatalogPage catalogPage = new CatalogPage();
    public static ElementsCollection searchResultsElements;
    public SelenideElement cheapestProductElement;
    public CatalogItem cheapestProduct = new CatalogItem();
    public CatalogItem inBasketProduct = new CatalogItem();

    @Step("input search value")
    public void inputSearchValue(String value) {
        log.info("Input search value: " + value);
        WaitUtils.waitForVisibility(catalogPage.fastSearchInput, 120);
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
    }

    @Step("iterate in stream result elements and get cheapest product element")
    public void defineCheapestProductElement() {
        log.info("iterate in stream result elements and get cheapest product element");

        Comparator<SelenideElement> priceComparator = new Comparator<SelenideElement>() {
            @Override
            public int compare(SelenideElement element1, SelenideElement element2) {
                return ElementUtils.getDouble(element1).compareTo(ElementUtils.getDouble(element2));
            }
        };

        cheapestProductElement = searchResultsElements.stream()
                .peek(element -> log.info("PRICE: " + ElementUtils.getDouble(element)))
                .filter(element -> ElementUtils.getDouble(element) > 0)
                .min(priceComparator)
                .get();
        log.info("cheapest price is: " + ElementUtils.getDouble(cheapestProductElement));
    }

    @Step("set cheapest product object")
    public void setCheapestProductObject() {
        log.info("set cheapest product object");
        String title = cheapestProductElement.$(By.xpath(catalogPage.cheapestProductTitle)).getText();
        String price = cheapestProductElement.$(By.xpath(catalogPage.cheapestProductPrice)).getText();
        cheapestProduct.setName(title);
        cheapestProduct.setPrice(price);
    }

    @Step("set in basket product object")
    public void setInBasketProductObject() {
        log.info("set in basket product object");
        WaitUtils.waitForVisibility(catalogPage.inBasketProductTitle);
        String title = catalogPage.inBasketProductTitle.getText();
        String price = catalogPage.inBasketProductPrice.getText();
        inBasketProduct.setName(title);
        inBasketProduct.setPrice(price);
    }

    @Step("go to product page")
    public void goToProductPage() {
        log.info("go to product page");
        SelenideElement link = cheapestProductElement.$(By.xpath(catalogPage.linkToProductPage));
        WaitUtils.waitForVisibility(link);
        link.click();
    }

    @Step("sort products on page")
    public void sortProductsOnPage() {
        log.info("sort products on page");
        SelenideElement offers = catalogPage.offers;
        WaitUtils.waitForVisibility(offers);
        SelenideElement selector = offers.$(By.xpath(catalogPage.selector));
        actions().scrollToElement(selector);
        selector.selectOptionContainingText("возраст");
        selector.click();
        selector.selectOption(2);
        log.info("send keys e nTER to sort selector");
        selector.sendKeys(Keys.ENTER);
    }

    @Step("add product to basket")
    public void addProductToBasket() {
        log.info("add product to basket");
        WaitUtils.waitForVisibility(1);
        SelenideElement offers = catalogPage.offers;
        WaitUtils.waitForVisibility(offers);
        SelenideElement button = offers.$(By.xpath(catalogPage.buttonAddToBasket));
        button.click();
    }

    @Step("go to basket page")
    public void goToBasketPage() {
        log.info("go to basket page");
        SelenideElement goToBasket = catalogPage.buttonGoToBasket;
        WaitUtils.waitForVisibility(goToBasket);
        goToBasket.click();
    }

    @Step("go to in basket product page")
    public void goToInBasketProductPage() {
        log.info("go to in basket product page");
        SelenideElement title = catalogPage.inBasketProduct;
        WaitUtils.waitForVisibility(title);
        title.click();
    }

    @Step("write to file cheapest product object")
    public void writeToFileCheapestProductObject() {
        log.info("write to file cheapest product object");
        //TODO просто потестировать запись объектов в файл
        JsonUtil.setObjectToFile(cheapestProduct, "result.json");
    }
}