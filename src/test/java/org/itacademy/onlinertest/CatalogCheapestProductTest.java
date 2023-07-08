package org.itacademy.onlinertest;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.models.CatalogItem;
import org.itacademy.onlinertest.steps.CheapestSteps;
import org.itacademy.onlinertest.utils.DataProviderCheapestItems;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Log4j2
@Listeners
public class CatalogCheapestProductTest extends BaseTest {

    private CheapestSteps cheapestSteps = new CheapestSteps();

    @Description("Find cheapest product and add to basket")
    @Test(testName = "Cheapest test",
            dataProvider = "cheapestItems",
            dataProviderClass = DataProviderCheapestItems.class)
    public void cheapestProductTest(CatalogItem item) {
        log.info("TEST CHEAPEST START");
        cheapestSteps.inputSearchValue(item.getName());
        cheapestSteps.switchToResultsFrame();
        cheapestSteps.getSearchResults();
        cheapestSteps.defineCheapestProductElement();
        cheapestSteps.setCheapestProductObject();
        cheapestSteps.goToProductPage();
        cheapestSteps.sortProductsOnPage();
        cheapestSteps.addProductToBasket();
        cheapestSteps.goToBasketPage();
        cheapestSteps.goToInBasketProductPage();
        cheapestSteps.setInBasketProductObject();
        cheapestSteps.writeToFileCheapestProductObject();
        log.info("Cheapest product: " + cheapestSteps.cheapestProduct);
        log.info("In basket product: " + cheapestSteps.inBasketProduct);
        Assert.assertEquals(cheapestSteps.cheapestProduct, cheapestSteps.inBasketProduct);
    }
}
