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
public class CatalogCheapestProductTest extends BaseTest {

    CheapestSteps cheapestSteps = new CheapestSteps();

    @Description("Find cheapest product and add to basket")
    @Test(testName = "Cheapest product",
            dataProvider = "catalogItems",
            dataProviderClass = DataProviderCatalogItems.class)
    public void cheapestProductTest(CatalogItem item) {
        log.info("TEST CHEAPEST START");
        cheapestSteps.inputValueInSearchField(item.getTitle());
        cheapestSteps.switchToResultsFrame();
        SelenideElement cheapestProductElement = cheapestSteps.getCheapestProductElement();
        CatalogItem cheapestProduct = new CatalogItem();
        cheapestProduct.setTitle(cheapestSteps.getCheapestTitleText(cheapestProductElement));
        cheapestProduct.setPrice(cheapestSteps.getCheapestPriceText(cheapestProductElement));
        cheapestSteps.goToProductPage(cheapestProductElement);
        cheapestSteps.addProductToBasket();
        cheapestSteps.goToBasketPage();
        CatalogItem inBasketProduct = new CatalogItem();
        inBasketProduct.setTitle(cheapestSteps.getInBasketProductTitleText());
        inBasketProduct.setPrice(cheapestSteps.getInBasketProductPriceText());

        Assert.assertTrue(cheapestProduct.equals(inBasketProduct));
    }
}
