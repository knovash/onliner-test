package org.itacademy.onlinertest.steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.pages.CatalogPage;
import org.itacademy.onlinertest.pages.FilterPage;
import org.itacademy.onlinertest.utils.WaitUtils;

import java.util.List;

import static com.codeborne.selenide.Selenide.actions;

@Log4j2
public class FilterSteps {

    CatalogPage catalogPage = new CatalogPage();
    FilterPage filterPage = new FilterPage();

    @Step("buttonCatalogClick")
    public void buttonCatalogClick() {
        log.info("buttonCatalogClick");
        WaitUtils.waitForVisibility(filterPage.buttonCatalog, 60);
        filterPage.buttonCatalog.click();
    }


    @Step("catalogPage.buttonElectronic.click")
    public void buttonElectronicClick() {
        log.info("catalogPage.buttonElectronic.click");
        WaitUtils.waitForVisibility(filterPage.buttonElectronic, 60);
        filterPage.buttonElectronic.click();
    }

    @Step("catalogPage.buttonMobilePhones.click")
    public void buttonMobilePhonesClick() {
        log.info("catalogPage.buttonMobilePhones.click");
        WaitUtils.waitForVisibility(filterPage.buttonMobilePhones, 60);
        filterPage.buttonMobilePhones.click();
    }

    @Step("icatalogPage.buttonSmartPhones.click")
    public void buttonSmartPhonesClick() {
        log.info("catalogPage.buttonSmartPhones.click");
        WaitUtils.waitForVisibility(filterPage.buttonSmartPhones, 60);
        filterPage.buttonSmartPhones.click();
    }

    @Step("input Value In SearchField")
    public void checkBoxHuaweiClick() {
        log.info("catalogPage.buttonSmartPhones.click");
        WaitUtils.waitForVisibility(filterPage.manufacturer, 60);
        actions().scrollToElement(filterPage.manufacturer);
        log.info("manufacturer " + filterPage.manufacturer.isDisplayed());
        WaitUtils.waitForVisibility(filterPage.checkBoxHuawei, 60);

//        actions().scrollFromOrigin()

        filterPage.checkBoxHuawei.click();
    }

    @Step("catalogPage.checkBoxBrand(brand).click")
    public void checkBoxBrandClick(String brand) {
        log.info("catalogPage.checkBoxBrand(brand).click");
        WaitUtils.waitForVisibility(filterPage.checkBoxHuawei, 60);
        actions().scrollToElement(filterPage.checkBoxHuawei);
        filterPage.checkBoxBrand(brand).click();
    }

    @Step("catalogPage.priceLow.setValue(value)")
    public void inputPriceLow(String value) {
        log.info("catalogPage.priceLow.setValue: " + value);
        WaitUtils.waitForVisibility(filterPage.priceLow, 60);
        filterPage.priceLow.setValue(value);
    }

    @Step("catalogPage.priceHi.setValue(value)")
    public void inputPriceHi(String value) {
        log.info("catalogPage.priceHi.setValue: " + value);
        WaitUtils.waitForVisibility(filterPage.priceHi, 60);
        filterPage.priceHi.setValue(value);
    }

    @Step("catalogPage.filterResults")
    public List<SelenideElement> filterResults() {
        log.info("catalogPage.filterResults");
        WaitUtils.waitForVisibility(filterPage.filterResults.get(0), 60);
        return filterPage.filterResults;
    }


}
