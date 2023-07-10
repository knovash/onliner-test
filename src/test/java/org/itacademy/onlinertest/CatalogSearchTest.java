package org.itacademy.onlinertest;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.models.CatalogItem;
import org.itacademy.onlinertest.steps.CheapestSteps;
import org.itacademy.onlinertest.steps.SearchSteps;
import org.itacademy.onlinertest.utils.DataProviderSearchItems;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Log4j2
@Listeners
public class CatalogSearchTest extends BaseTest {

    private CheapestSteps cheapestSteps = new CheapestSteps();
    private SearchSteps searchSteps = new SearchSteps();

    @Description("Сheck that all search results contain the desired product")
    @Test(testName = "Search results test",
            dataProvider = "searchItems",
            dataProviderClass = DataProviderSearchItems.class)
    public void checkSearchResultsTest(CatalogItem item) {
        log.info("TEST SEARCH START");
        log.info("ITEM: " + item.getName());
        cheapestSteps.inputSearchValue(item.getName());
        cheapestSteps.switchToResultsFrame();
        cheapestSteps.getSearchResults();
        searchSteps.checkItemInResults(item.getName()).assertAll();
    }
}