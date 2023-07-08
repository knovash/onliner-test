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
import org.itacademy.onlinertest.utils.DataProviderFilterItems;
import org.itacademy.onlinertest.utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
            dataProvider = "filterItems",
            dataProviderClass = DataProviderFilterItems.class)
    public void fTest(CatalogItem item) {
        log.info("TEST CHEAPEST START");
        log.info("BRAND: " + item.brand);
        String brand = item.brand;

//        filterSteps.buttonCatalogClick();
//        filterSteps.buttonElectronicClick();
//        filterSteps.buttonMobilePhonesClick();
//        filterSteps.buttonSmartPhonesClick();
        open("https://catalog.onliner.by/mobile?mobile_type%5B0%5D=smartphone&mobile_type%5Boperation%5D=union");

        SelenideElement shops = $(By.xpath("//span[contains(text(), 'Магазины')]"));
        WaitUtils.waitForVisibility(shops);
        shops.scrollIntoView(false);
        log.info("shops " + shops.isDisplayed());

        SelenideElement allBrand = $(By.xpath("//div[@class='schema-filter-control__item' and contains(text(), 'Все')]"));
        WaitUtils.waitForVisibility(allBrand);
        actions().scrollToElement(allBrand);
        log.info("all " + allBrand.isDisplayed());
        allBrand.click();

        WaitUtils.waitForVisibility(1);

        SelenideElement brandCheckBox = $(By.xpath("//div[@class='schema-filter-popover__title' and contains(text(), 'Производитель')]/following-sibling::div/div/label/span[contains(text(), 'Samsung')]"));
//        SelenideElement brandCheckBox = $(By.xpath("//div[@class='schema-filter-popover__title' and contains(text(), 'Производитель')]/following-sibling::div//span/input[@value='Samsung']"));

        WaitUtils.waitForVisibility(brandCheckBox);
        log.info("samsung " + brandCheckBox.isDisplayed());
        log.info("samsung " + brandCheckBox.getText());
        brandCheckBox.click();
        WaitUtils.waitForVisibility(1);
        allBrand.click();

        WaitUtils.waitForVisibility(1);

        ElementsCollection titles = $$(By.xpath("//div[@class='schema-product__title']"));
        WaitUtils.waitForVisibility(titles.get(0));
        log.info("SIZE " + titles.size());


        SoftAssert sa = new SoftAssert();
        Assert.assertFalse(titles.isEmpty(), "RESULT LIST IS EMPTY");
        titles.stream()
                .peek(element -> log.info("TEXT " + element.getText().contains(brand)))
                .peek(element -> sa.assertTrue(element.getText().contains(brand), "NOT CONTAINS"))
                .forEach(element -> log.info("TEXT " + element.getText()));
        sa.assertAll();
        WaitUtils.waitForVisibility(10);
    }
}
