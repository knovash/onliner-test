package org.itacademy.onlinertest.steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.pages.CatalogPage;
import org.itacademy.onlinertest.utils.PriceUtils;
import org.itacademy.onlinertest.utils.WaitUtils;
import org.openqa.selenium.By;

import java.util.Comparator;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class CheapestSteps {

    CatalogPage catalogPage = new CatalogPage();

    @Step("input Value In SearchField")
    public void inputValueInSearchField(String value) {
        log.info("input Value In SearchField: " + value);
        WaitUtils.waitForVisibility(catalogPage.fastSearchInput, 60);
        catalogPage.fastSearchInput.setValue(value);
    }

    @Step("switch To Results Frame")
    public void switchToResultsFrame() {
        log.info("switch To Results Frame");
        SelenideElement frame = catalogPage.frame;
        WaitUtils.waitForVisibility(frame, 60);
        getWebDriver().switchTo().frame(frame);
    }

    @Step("iterate Result Elements and get Cheapest Product Element")
    public SelenideElement getCheapestProductElement() {
        log.info("iterate Result Elements and get Cheapest Product Element");

        Comparator<SelenideElement> priceComparator = new Comparator<SelenideElement>() {
            @Override
            public int compare(SelenideElement element1, SelenideElement element2) {
                return PriceUtils.getDouble(element1).compareTo(PriceUtils.getDouble(element2));
            }
        };

        WaitUtils.waitForVisibility(catalogPage.searchResults.get(0), 60);
        log.info("RESULT LIST SIZE: " + catalogPage.searchResults.size());
        SelenideElement result = catalogPage.searchResults
                .stream()
                .peek(element -> log.info("PRICE: " + PriceUtils.getDouble(element)))
                .min(priceComparator)
                .get();
        return result;
    }

    @Step("get Cheapest Element Title Text")
    public String getCheapestTitleText(SelenideElement minPriceElement) {
        log.info("get Cheapest Element Title Text");
        String cheapestTitle = minPriceElement.$(By.xpath(".//div[@class='product__title']")).getText();
        log.info("CHEAPEST TITLE: " + cheapestTitle);
        return cheapestTitle;
    }

    @Step("get Cheapest Element Price Text")
    public String getCheapestPriceText(SelenideElement minPriceElement) {
        log.info("get Cheapest Element Price Text");
        String cheapestPrice = minPriceElement.$(By.xpath(".//div[@class='product__price']//span")).getText();
        log.info("CHEAPEST PRICE: " + cheapestPrice);
        return cheapestPrice;
    }

    @Step("go To Cheapest Element Product Page")
    public void goToProductPage(SelenideElement minPriceElement) {
        log.info("go To Cheapest Element Product Page");
        SelenideElement link = minPriceElement.$(By.xpath(".//a[@class='product__title-link']"));
        WaitUtils.waitForVisibility(link, 60);
        link.click();
        log.info("wait for product page title...");
        SelenideElement productTitle = catalogPage.productTitle;
        WaitUtils.waitForVisibility(productTitle, 60);
        log.info("PAGE TITLE: " + productTitle.getText());
    }

    @Step("add Cheapest Product To Basket")
    public void addProductToBasket() {
        log.info("add Cheapest Product To Basket");
        SelenideElement addToBasket = catalogPage.buttonToBasket;
        WaitUtils.waitForVisibility(addToBasket, 60);
        log.info("button add To Basket is Displayed: " + addToBasket.isDisplayed());
        addToBasket.click();
    }

    @Step("go To Basket Page")
    public void goToBasketPage() {
        log.info("go To Basket Page");
        SelenideElement goToBasket = catalogPage.buttonGoToBasket;
        WaitUtils.waitForVisibility(goToBasket, 60);
        log.info("button go To Basket is Displayed: " + goToBasket.isDisplayed());
        goToBasket.click();
    }

    @Step("get In Basket Product Title")
    public String getInBasketProductTitleText() {
        log.info("get In Basket Product Title");
        return catalogPage.inBasketItems.get(0).$(By.xpath(".//a[contains(@class,'cart-form__link_base-alter')]")).getText();
    }

    @Step("get Basket In Product Price")
    public String getInBasketProductPriceText() {
        log.info("get In Basket Product Price");
        return catalogPage.inBasketItems.get(0).$(By.xpath(".//div[contains(@class,'cart-form__offers-part_price_specific')]")).getText();
    }
}
