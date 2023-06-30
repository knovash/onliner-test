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

    CatalogPage catalogPage = new CatalogPage();
    public ElementsCollection searchResultsElements;
    public SelenideElement cheapestProductElement;
    public CatalogItem cheapestProduct = new CatalogItem();
    public CatalogItem inBasketProduct = new CatalogItem();

    @Step("input Search Value")
    public void inputSearchValue(String value) {
        log.info("input Search Value: " + value);
        WaitUtils.waitForVisibility(catalogPage.fastSearchInput, 120);
        catalogPage.fastSearchInput.setValue(value);
    }

    @Step("switch To Results Frame")
    public void switchToResultsFrame() {
        log.info("switch To Results Frame");
        SelenideElement frame = catalogPage.frame;
        WaitUtils.waitForVisibility(frame);
        getWebDriver().switchTo().frame(frame);
    }

    @Step("get Search Results")
    public void getSearchResults() {
        log.info("get Search Results");
        searchResultsElements = catalogPage.searchResults;
        WaitUtils.waitForVisibility(searchResultsElements.get(0));
    }

    @Step("iterate In Stream Result Elements and get Cheapest Product Element")
    public void getCheapestProductElement() {
        log.info("iterate In Stream Result Elements and get Cheapest Product Element");

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

    @Step("set Cheapest Product Object")
    public void setCheapestProductObject() {
        log.info("set Cheapest Product Object");
        String title = cheapestProductElement.$(By.xpath(catalogPage.cheapestProductTitle)).getText();
        String price = cheapestProductElement.$(By.xpath(catalogPage.cheapestProductPrice)).getText();
        /** replace used to ruin the test */
//        String title = cheapestProductElement.$(By.xpath(catalogPage.cheapestProductTitle)).getText().replace(" ","");
//        String price = cheapestProductElement.$(By.xpath(catalogPage.cheapestProductPrice)).getText().replace(" ","");
        cheapestProduct.setName(title);
        cheapestProduct.setPrice(price);
    }

    @Step("set In Basket Product Object")
    public void setInBasketProductObject() {
        log.info("set In Basket Product Object");
        WaitUtils.waitForVisibility(catalogPage.inBasketProductTitle);
        String title = catalogPage.inBasketProductTitle.getText();
        String price = catalogPage.inBasketProductPrice.getText();
        inBasketProduct.setName(title);
        inBasketProduct.setPrice(price);
    }

    @Step("go To Cheapest Element Product Page")
    public void goToProductPage() {
        log.info("go To Cheapest Element Product Page");
        SelenideElement link = cheapestProductElement.$(By.xpath(catalogPage.linkToProductPage));
        WaitUtils.waitForVisibility(link);
        link.click();
        log.info("wait for product page title...");
        SelenideElement productTitle = catalogPage.productTitle;
        WaitUtils.waitForVisibility(productTitle);
        log.info("PAGE TITLE: " + productTitle.getText());
    }

    @Step("sort Products On Page")
    public void sortProductsOnPage() {
        log.info("sort Products On Page");
        SelenideElement offers = catalogPage.offers;
        WaitUtils.waitForVisibility(offers);
        //TODO Рома, вопрос, если делать поиск по всей странице то никак ненаходит селектор,
        // я тогда нахожу див в котором все предложения товаров запихиваю его в элемент
        // и уже поиском в этом элементе могу найти селектор. как так? это норм?
        SelenideElement selector = offers.$(By.xpath(catalogPage.selector));
        actions().scrollToElement(selector);
        selector.selectOptionContainingText("возраст");
        selector.click();
        //TODO Рома, вопрос, как это сделать лучше?
        // непонятно зачем но надо, после клика, но без этого не запускается сортировка
        selector.selectOption(2);
        log.info("send keys ENTER to sort selector");
        selector.sendKeys(Keys.ENTER);
    }

    @Step("add Product To Basket")
    public void addProductToBasket() {
        log.info("add Product To Basket");
        SelenideElement offers = catalogPage.offers;
        WaitUtils.waitForVisibility(offers);
        SelenideElement button = offers.$(By.xpath(catalogPage.buttonAddToBasket));
        button.click();
    }

    @Step("go To Basket Page")
    public void goToBasketPage() {
        log.info("go To Basket Page");
        SelenideElement goToBasket = catalogPage.buttonGoToBasket;
        WaitUtils.waitForVisibility(goToBasket);
        goToBasket.click();
    }

    @Step("goToInBasketProductPage")
    public void goToInBasketProductPage() {
        log.info("goToInBasketProductPage");
        SelenideElement title = catalogPage.inBasketProduct;
        WaitUtils.waitForVisibility(title);
        title.click();
    }

    @Step("writeToFileCheapestProductObject")
    public void writeToFileCheapestProductObject() {
        log.info("writeToFileCheapestProductObject");
        //TODO просто потестировать запись объектов в файл
        JsonUtil.setObjectToFile(cheapestProduct, "result.json");
    }
}