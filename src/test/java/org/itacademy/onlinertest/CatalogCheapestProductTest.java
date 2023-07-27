package org.itacademy.onlinertest;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.models.CatalogItem;
import org.itacademy.onlinertest.steps.CheapestSteps;
import org.itacademy.onlinertest.utils.DataProviderGeneric;
import org.itacademy.onlinertest.utils.DataProviderGeneric.TestData;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Log4j2
@Listeners
public class CatalogCheapestProductTest extends BaseTest {

    private CheapestSteps cheapestSteps = new CheapestSteps();

    @Description("Find the product at the cheapest price. Put it in the basket. " +
            "Check that the desired product is in the basket")
    @TestData(path = "data/", filename = "cheapest_items.json", modelType = "CatalogItem")
    @Test(testName = "Cheapest product test",
            dataProvider = "getData",
            dataProviderClass = DataProviderGeneric.class)
    public void checkCheapestProductTest(CatalogItem item) {
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
