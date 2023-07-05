package org.itacademy.onlinertest;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.models.CatalogItem;
import org.itacademy.onlinertest.steps.CheapestSteps;
import org.itacademy.onlinertest.steps.SearchSteps;
import org.itacademy.onlinertest.utils.DataProviderCatalogItems;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Log4j2
@Listeners
public class CatalogSearchTest extends BaseTest {

    private CheapestSteps cheapestSteps = new CheapestSteps();
    private SearchSteps searchSteps = new SearchSteps();

    @Description("Сheck the search results")
    @Test(testName = "Сheck the search results",
            dataProvider = "catalogItems",
            dataProviderClass = DataProviderCatalogItems.class)
    public void checkSearchResultsTest(CatalogItem item) {
        log.info("TEST SEARCH START");
        cheapestSteps.inputSearchValue(item.getName());
        cheapestSteps.switchToResultsFrame();
        cheapestSteps.getSearchResults();
        searchSteps.checkItemInResults(item.getName());
    }
}
