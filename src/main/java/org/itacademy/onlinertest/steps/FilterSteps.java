package org.itacademy.onlinertest.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.pages.FilterPage;
import org.itacademy.onlinertest.utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class FilterSteps {

    private FilterPage filterPage = new FilterPage();
    public static ElementsCollection filterResultElements;

    @Step("buttonCatalogClick")
    public void buttonCatalogClick() {
        log.info("buttonCatalogClick");
        WaitUtils.waitForVisibility(filterPage.buttonCatalog);
        filterPage.buttonCatalog.click();
    }

    @Step("buttonElectronicClick")
    public void buttonElectronicClick() {
        log.info("buttonElectronicClick");
        WaitUtils.waitForVisibility(filterPage.buttonElectronic);
        filterPage.buttonElectronic.click();
    }

    @Step("buttonMobilePhonesClick")
    public void buttonMobilePhonesClick() {
        log.info("buttonMobilePhonesClick");
        WaitUtils.waitForVisibility(filterPage.buttonMobilePhones);
        filterPage.buttonMobilePhones.click();
    }

    @Step("buttonSmartPhonesClick")
    public void buttonSmartPhonesClick() {
        log.info("buttonSmartPhonesClick");
        WaitUtils.waitForVisibility(filterPage.buttonSmartPhones);
        filterPage.buttonSmartPhones.click();
    }

    @Step("inputPriceLow")
    public void inputPriceLow(String value) {
        log.info("inputPriceLow" + value);
        WaitUtils.waitForVisibility(filterPage.priceLow);
        filterPage.priceLow.setValue(value);
    }

    @Step("inputPriceHi")
    public void inputPriceHi(String value) {
        log.info("inputPriceHi" + value);
        WaitUtils.waitForVisibility(filterPage.priceHi);
        filterPage.priceHi.setValue(value);
    }

    @Step("filterSelectBrand")
    public void filterSelectBrand(String brand) {
        log.info("filterSelectBrand: " + brand);
        SelenideElement shops = filterPage.selectorShops;
        WaitUtils.waitForVisibility(shops);
        shops.scrollIntoView(false);
        SelenideElement allBrand = filterPage.selectorBrands;
        WaitUtils.waitForVisibility(allBrand);
        actions().scrollToElement(allBrand);
        allBrand.click();
        log.info("RESULTS : " + filterPage.buttonResults.isDisplayed() + "TEXT: " + filterPage.buttonResults.getText());
        SelenideElement brandCheckBox = $(By.xpath("//div[@class='schema-filter-popover__title' and contains(text(), 'Производитель')]/following-sibling::div/div/label/span[contains(text(), '" + brand + "')]"));
        WaitUtils.waitForVisibility(brandCheckBox);
        brandCheckBox.click();
        allBrand.click();
    }

    @Step("collectResultElements")
    public void collectResultElements() {
        log.info("collectResultElements");
        WaitUtils.waitForVisibility(3);
        FilterSteps.filterResultElements = filterPage.filterResults;
        log.info("collectResultElements SIZE " + FilterSteps.filterResultElements.size());
    }

    @Step("checkResults")
    public SoftAssert checkResults(String brand) {
        log.info("checkResults BRAND: " + brand);
        SoftAssert sa = new SoftAssert();
        Assert.assertFalse(FilterSteps.filterResultElements.isEmpty(), "RESULT LIST IS EMPTY");
        FilterSteps.filterResultElements.stream()
                .peek(element -> log.info("TITLE " + element.$(By.xpath(".//div[@class='schema-product__title']")).getText()))
                .peek(element -> log.info("PRICE " + element.$(By.xpath(".//div[@class='schema-product__price']")).getText()))
                .map(element -> element.$(By.xpath(".//div[@class='schema-product__title']")).getText())
                .forEach(element -> sa.assertTrue(element.toLowerCase().contains(brand.toLowerCase()), "NOT CONTAINS"));
        return sa;
    }
}
