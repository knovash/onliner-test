package org.itacademy.onlinertest.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FilterPage {

    //TODO optimize locators
    public SelenideElement buttonCatalog = $(By.xpath("//span[@class='b-main-navigation__text' and contains(text(), 'Каталог')]"));
    public SelenideElement buttonElectronic = $(By.xpath("//span[@class='catalog-navigation-classifier__item-title-wrapper' and contains(text(), 'Электроника')]"));
    public SelenideElement buttonMobilePhones = $(By.xpath("//div[@class='catalog-navigation-list__aside-title' and contains(text(), 'Мобильные телефоны')]"));
    public SelenideElement buttonSmartPhones = $(By.xpath("//a[contains(@href,'smartphone')]"));
    public SelenideElement manufacturer = $(By.xpath("//span[contains(text(), 'Производитель')]"));

    public SelenideElement checkBoxHuawei = $(By.xpath("//input[@type='checkbox' and @value='huawei']/following-sibling::span"));

    public SelenideElement checkBoxBrand(String brand) {
        $(By.xpath("//input[@type='checkbox' and @value='" + brand + "']/following-sibling::span"));
        return null;
    }

    public SelenideElement priceLow = $(By.xpath("//div[@class='schema-filter__label']/span[contains(text(), 'Цена')]/../following-sibling::div/div/div[1]/input"));
    public SelenideElement priceHi = $(By.xpath("//div[@class='schema-filter__label']/span[contains(text(), 'Цена')]/../following-sibling::div/div/div[2]/input"));
    public ElementsCollection filterResults = $$(By.xpath("//div[@class='schema-product__part schema-product__part_2']"));
}