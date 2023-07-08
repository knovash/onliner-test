package org.itacademy.onlinertest.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FilterPage {

    //TODO optimize locators
    public ElementsCollection filterResults = $$(By.xpath("//div[@class='schema-product__part schema-product__part_2']"));
    public SelenideElement buttonCatalog = $(By.xpath("//span[@class='b-main-navigation__text' and contains(text(), 'Каталог')]"));
    public SelenideElement buttonElectronic = $(By.xpath("//span[@class='catalog-navigation-classifier__item-title-wrapper' and contains(text(), 'Электроника')]"));
    public SelenideElement buttonMobilePhones = $(By.xpath("//div[@class='catalog-navigation-list__aside-title' and contains(text(), 'Мобильные телефоны')]"));
    public SelenideElement buttonSmartPhones = $(By.xpath("//a[contains(@href,'smartphone')]"));
    public SelenideElement selectorBrands = $(By.xpath("//div[@class='schema-filter-control__item' and contains(text(), 'Все')]"));
    public SelenideElement selectorShops = $(By.xpath("//span[contains(text(), 'Магазины')]"));
    public SelenideElement inputPriceMin = $(By.xpath("//div[@class='schema-filter__label']/span[contains(text(), 'Цена')]/../following-sibling::div/div/div[1]/input"));
    public SelenideElement inputPriceMax = $(By.xpath("//div[@class='schema-filter__label']/span[contains(text(), 'Цена')]/../following-sibling::div/div/div[2]/input"));
    public SelenideElement buttonResults = $(By.xpath("//span[@class='schema-filter-button__sub schema-filter-button__sub_main']"));
}