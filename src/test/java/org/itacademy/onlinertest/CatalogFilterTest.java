package org.itacademy.onlinertest;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.models.CatalogItem;
import org.itacademy.onlinertest.steps.FilterSteps;
import org.itacademy.onlinertest.utils.DataProviderFilterItems;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Log4j2
@Listeners
public class CatalogFilterTest extends BaseTest {

    private FilterSteps filterSteps = new FilterSteps();

    @Description("Сheck that the filter results contains the desired product")
    @Test(testName = "Filter results test",
            dataProvider = "filterItems",
            dataProviderClass = DataProviderFilterItems.class)
    public void checkFilterResultsTest(CatalogItem item) {
        log.info("TEST FILTER START");
        log.info("BRAND: " + item.brand);
        log.info("PRICE MIN: " + item.priceMin);
        log.info("PRICE MAX: " + item.priceMax);
        filterSteps.buttonCatalogClick();
        filterSteps.buttonElectronicClick();
        filterSteps.buttonMobilePhonesClick();
        filterSteps.buttonSmartPhonesClick();
        filterSteps.filterSelectBrand(item.brand);
        filterSteps.inputPriceLow(item.priceMin);
        filterSteps.inputPriceHi(item.priceMax);
        filterSteps.collectResultElements();
        filterSteps.checkResults(item.brand).assertAll();
    }
}
