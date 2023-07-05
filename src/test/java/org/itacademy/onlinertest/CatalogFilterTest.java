package org.itacademy.onlinertest;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.models.CatalogItem;
import org.itacademy.onlinertest.pages.CatalogPage;
import org.itacademy.onlinertest.steps.CheapestSteps;
import org.itacademy.onlinertest.steps.FilterSteps;
import org.itacademy.onlinertest.steps.SearchSteps;
import org.itacademy.onlinertest.utils.DataProviderCatalogItems;
import org.itacademy.onlinertest.utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
@Listeners
public class CatalogFilterTest extends BaseTest {

    private CheapestSteps cheapestSteps = new CheapestSteps();
    private SearchSteps searchSteps = new SearchSteps();
    private CatalogPage catalogPage = new CatalogPage();
    private FilterSteps filterSteps = new FilterSteps();
    private List<CatalogItem> listcat = new ArrayList<>();

    @Description("Find cheapest product and add to basket")
    @Test(testName = "Search product",
            dataProvider = "catalogItems",
            dataProviderClass = DataProviderCatalogItems.class)
    public void fTest(CatalogItem item) {
        log.info("TEST CHEAPEST START");
//        String brand = item.brand;
        String brand = "samsung";


//        filterSteps.buttonCatalogClick();
//        filterSteps.buttonElectronicClick();
//        filterSteps.buttonMobilePhonesClick();
//        filterSteps.buttonSmartPhonesClick();
        open("https://catalog.onliner.by/mobile?mobile_type%5B0%5D=smartphone&mobile_type%5Boperation%5D=union");

        SelenideElement shops = $(By.xpath("//span[contains(text(), 'Магазины')]"));
        WaitUtils.waitForVisibility(shops);
        shops.scrollIntoView(false);
        log.info("shops " + shops.isDisplayed());

        SelenideElement all = $(By.xpath("//div[@class='schema-filter-control__item' and contains(text(), 'Все')]"));
        WaitUtils.waitForVisibility(all);
        actions().scrollToElement(all);
        log.info("all " + all.isDisplayed());
        all.click();

        WaitUtils.waitForVisibility(1);

        SelenideElement samsung = $(By.xpath("//div[@class='schema-filter-popover__title' and contains(text(), 'Производитель')]/following-sibling::div/div/label/span[contains(text(), 'Apple')]"));
//        SelenideElement samsung = $(By.xpath("//div[@class='schema-filter-popover__title' and contains(text(), 'Производитель')]/following-sibling::div//span/input[@value='apple']"));

        WaitUtils.waitForVisibility(samsung);
        log.info("samsung " + samsung.isDisplayed());
        log.info("samsung " + samsung.getText());
        samsung.click();
        WaitUtils.waitForVisibility(1);
        all.click();

        WaitUtils.waitForVisibility(1);

        ElementsCollection titles = $$(By.xpath("//div[@class='schema-product__title']"));
        WaitUtils.waitForVisibility(titles.get(0));
        log.info("SIZE " + titles.size());

        titles.stream()
                .peek(element -> log.info("TEXT " + element.getText().contains(brand)))
                .forEach(element -> log.info("TEXT " + element.getText()));


        WaitUtils.waitForVisibility(30);

    }
}
