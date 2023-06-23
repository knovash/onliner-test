package org.itacademy.onlinertest;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.models.CatalogItem;
import org.itacademy.onlinertest.steps.CheapestSteps;
import org.itacademy.onlinertest.utils.DataProviderCatalogItems;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Log4j2
@Listeners
public class OnlinerCheapestProductTest extends BaseTest {

    CheapestSteps cheapestSteps = new CheapestSteps();

    @Description("Find cheapest product and add to basket")
    @Test(testName = "Cheapest product",
            dataProvider = "catalogItems",
            dataProviderClass = DataProviderCatalogItems.class)
    public void cheapestProductTest(CatalogItem item) {
        log.info("TEST CHEAPEST START");
        cheapestSteps.inputSearchValue(item.getTitle());
        cheapestSteps.switchToResultsFrame();
        SelenideElement cheapestProductElement = cheapestSteps.getCheapestProductElement();
        String cheapestProductTitle = cheapestSteps.getCheapestTitleText(cheapestProductElement);
        String cheapestProductPrice = cheapestSteps.getCheapestPriceText(cheapestProductElement);
        cheapestSteps.goToProductPage(cheapestProductElement);
        cheapestSteps.addProductToBasket();
        cheapestSteps.goToBasketPage();
        String inBasketProductTitle = cheapestSteps.getInBasketProductTitleText();
        String inBasketProductPrice = cheapestSteps.getInBasketProductPriceText();
        log.info("PRICE COMPARE " + cheapestProductPrice + " = " + inBasketProductPrice + "  " + cheapestProductPrice.contains(inBasketProductPrice));
        log.info("TITLE COMPARE " + cheapestProductTitle + " = " + inBasketProductTitle + "  " + cheapestProductTitle.contains(inBasketProductTitle));
        Assert.assertTrue(cheapestProductPrice.contains(inBasketProductPrice) && cheapestProductTitle.contains(inBasketProductTitle));
    }
}
