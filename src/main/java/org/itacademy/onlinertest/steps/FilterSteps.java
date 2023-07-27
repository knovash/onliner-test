package org.itacademy.onlinertest.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.pages.FilterPage;
import org.itacademy.onlinertest.utils.AllureListener;
import org.itacademy.onlinertest.utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class FilterSteps extends BaseSteps{

    private final FilterPage filterPage = new FilterPage();
    public static ElementsCollection filterResultElements;

    @Step("button catalog click")
    public void buttonCatalogClick() {
        log.info("button catalog click");
        WaitUtils.waitForVisibility(filterPage.buttonCatalog);
        AllureListener.screenShot();
        filterPage.buttonCatalog.click();
        WaitUtils.waitForVisibility(filterPage.buttonElectronic);
        AllureListener.screenShot();
    }

    @Step("button electronic click")
    public void buttonElectronicClick() {
        log.info("button electronic click");
        WaitUtils.waitForVisibility(filterPage.buttonElectronic);
        filterPage.buttonElectronic.click();
        WaitUtils.waitForVisibility(filterPage.buttonMobilePhones);
        AllureListener.screenShot();
    }

    @Step("button mobile phones click")
    public void buttonMobilePhonesClick() {
        log.info("button mobile phones click");
        WaitUtils.waitForVisibility(filterPage.buttonMobilePhones);
        filterPage.buttonMobilePhones.click();
        WaitUtils.waitForVisibility(filterPage.buttonSmartPhones);
        AllureListener.screenShot();
    }

    @Step("button smart phones click")
    public void buttonSmartPhonesClick() {
        log.info("button smart phones click");
        WaitUtils.waitForVisibility(filterPage.buttonSmartPhones);
        filterPage.buttonSmartPhones.click();
        WaitUtils.waitForVisibility(filterPage.inputPriceMin);
        AllureListener.screenShot();
    }

    @Step("input price low")
    public void inputPriceLow(String value) {
        log.info("input price low" + value);
        WaitUtils.waitForVisibility(filterPage.inputPriceMin);
        filterPage.inputPriceMin.setValue(value);
        WaitUtils.waitForVisibility(filterPage.inputPriceMax);
        AllureListener.screenShot();
    }

    @Step("input price hi")
    public void inputPriceHi(String value) {
        log.info("input price hi" + value);
        WaitUtils.waitForVisibility(filterPage.inputPriceMax);
        filterPage.inputPriceMax.setValue(value);
        AllureListener.screenShot();
    }

    @Step("filter select brand")
    public void filterSelectBrand(String brand) {
        log.info("filter select brand: " + brand);
        SelenideElement shops = filterPage.selectorShops;
        WaitUtils.waitForVisibility(shops);
        shops.scrollIntoView(false);
        SelenideElement allBrand = filterPage.selectorBrands;
        WaitUtils.waitForVisibility(allBrand);
        actions().scrollToElement(allBrand);
        allBrand.click();
        AllureListener.screenShot();
        log.info("RESULTS : " + filterPage.buttonResults.isDisplayed() + "TEXT: " + filterPage.buttonResults.getText());
        SelenideElement brandCheckBox = $(By.xpath("//div[@class='schema-filter-popover__title' and contains(text(), 'Производитель')]/following-sibling::div/div/label/span[contains(text(), '" + brand + "')]"));
        WaitUtils.waitForVisibility(brandCheckBox);
        brandCheckBox.click();
        AllureListener.screenShot();
        allBrand.click();
    }

    @Step("collect result elements")
    public void collectResultElements() {
        log.info("collect result elements");
        WaitUtils.waitForVisibility(3);
        FilterSteps.filterResultElements = filterPage.filterResults;
        log.info("collect result elements SIZE " + FilterSteps.filterResultElements.size());
    }

    @Step("check results")
    public SoftAssert checkResults(String brand) {
        log.info("check results BRAND: " + brand);
        SoftAssert sa = new SoftAssert();
        Assert.assertFalse(FilterSteps.filterResultElements.isEmpty(), "RESULT LIST IS EMPTY");
        FilterSteps.filterResultElements.asDynamicIterable().stream()
                .peek(element -> log.info("TITLE " + element.$(By.xpath(".//div[@class='schema-product__title']")).getText()))
                .map(element -> element.$(By.xpath(".//div[@class='schema-product__title']")).getText())
                .forEach(element -> sa.assertTrue(element.toLowerCase().contains(brand.toLowerCase()), "NOT CONTAINS"));
        return sa;
    }
}
