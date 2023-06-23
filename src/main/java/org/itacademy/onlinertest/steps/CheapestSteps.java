package org.itacademy.onlinertest.steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.pages.OnlinerPage;
import org.itacademy.onlinertest.utils.PriceUtils;
import org.itacademy.onlinertest.utils.WaitUtils;
import org.openqa.selenium.By;

import java.util.Comparator;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class CheapestSteps {

    OnlinerPage onlinerPage = new OnlinerPage();

    @Step("input Search Value")
    public void inputSearchValue(String text) {
        log.info("input Search Value: " + text);
        WaitUtils.waitForVisibility(onlinerPage.fastSearchInput, 60);
        onlinerPage.fastSearchInput.setValue(text);
    }

    @Step("switch To Frame")
    public void switchToResultsFrame() {
        log.info("switch To Results Frame");
        SelenideElement frame = onlinerPage.frame;
        WaitUtils.waitForVisibility(frame, 60);
        getWebDriver().switchTo().frame(frame);
    }

    @Step("get Cheapest Product Element")
    public SelenideElement getCheapestProductElement() {

        Comparator<SelenideElement> priceComparator = new Comparator<SelenideElement>() {
            @Override
            public int compare(SelenideElement element1, SelenideElement element2) {
                return PriceUtils.getDouble(element1).compareTo(PriceUtils.getDouble(element2));
            }
        };

        log.info("get Cheapest Product Element");
        WaitUtils.waitForVisibility(onlinerPage.searchResults.get(0), 60);
        log.info("RESULT LIST SIZE: " + onlinerPage.searchResults.size());
        SelenideElement result = onlinerPage.searchResults
                .stream()
                .peek(element -> log.info("PRICE: " + PriceUtils.getDouble(element)))
                .min(priceComparator)
                .get();
        return result;
    }

    @Step("get Cheapest Title Text")
    public String getCheapestTitleText(SelenideElement minPriceElement) {
        log.info("get Cheapest Title Text");
        String cheapestTitle = minPriceElement.$(By.xpath(".//div[@class='product__title']")).getText();
        log.info("CHEAPEST TITLE: " + cheapestTitle);
        return cheapestTitle;
    }

    @Step("get Cheapest Price Text")
    public String getCheapestPriceText(SelenideElement minPriceElement) {
        log.info("get Cheapest Price Text");
        String cheapestPrice = minPriceElement.$(By.xpath(".//div[@class='product__price']//span")).getText();
        log.info("CHEAPEST PRICE: " + cheapestPrice);
        return cheapestPrice;
    }

    @Step("go To Product Page")
    public void goToProductPage(SelenideElement minPriceElement) {
        log.info("go To Product Page");
        SelenideElement link = minPriceElement.$(By.xpath(".//*[@class='product__title-link']"));
        WaitUtils.waitForVisibility(link, 60);
        link.click();
        log.info("wait for product page title...");
        SelenideElement productTitle = onlinerPage.productTitle;
        WaitUtils.waitForVisibility(productTitle, 60);
        log.info("PAGE TITLE: " + productTitle.getText());
    }

    @Step("add Product To Basket")
    public void addProductToBasket() {
        log.info("add Product To Basket");
        SelenideElement addToBasket = onlinerPage.buttonToBasket;
        WaitUtils.waitForVisibility(addToBasket, 60);
        log.info("button add To Basket is Displayed: " + addToBasket.isDisplayed());
        addToBasket.click();
    }

    @Step("go To Basket Page")
    public void goToBasketPage() {
        log.info("go To Basket Page");
        SelenideElement goToBasket = onlinerPage.buttonGoToBasket;
        WaitUtils.waitForVisibility(goToBasket, 60);
        log.info("button go To Basket is Displayed: " + goToBasket.isDisplayed());
        goToBasket.click();
    }

    @Step("get Basket Product Title")
    public String getInBasketProductTitleText() {
        log.info("get Basket Product Title");
        return onlinerPage.inBasketItems.get(0).$(By.xpath(".//a[contains(@class,'cart-form__link_base-alter')]")).getText();
    }

    @Step("get Basket Product Price")
    public String getInBasketProductPriceText() {
        log.info("get Basket Product Price");
        return onlinerPage.inBasketItems.get(0).$(By.xpath(".//div[contains(@class,'cart-form__offers-part_price_specific')]")).getText();
    }
}
