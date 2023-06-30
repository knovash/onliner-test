package org.itacademy.onlinertest.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CatalogPage {

    //TODO optimize locators
    public SelenideElement fastSearchInput = $(By.xpath("//input[@class='fast-search__input']"));
    public SelenideElement frame = $(By.xpath("//iframe[@class='modal-iframe']"));
    public ElementsCollection searchResults = $$(By.xpath("//div[@class='result__item result__item_product']"));
    public SelenideElement productTitle = $(By.xpath("//h1[@class='catalog-masthead__title js-nav-header']"));
    public SelenideElement buttonGoToBasket = $(By.xpath("//a[contains(text(), 'Перейти в корзину')]"));
    public String linkToProductPage = ".//a[@class='button button_orange product__button']";
    public SelenideElement offers = $(By.xpath("//div[@class='offers-list']"));
    public String selector = ".//select";
    public String buttonAddToBasket = ".//div[@class='offers-list__control offers-list__control_default helpers_hide_tablet']//a[contains(text(), 'В корзину')]";
    public SelenideElement inBasketProduct = $(By.xpath("//div[@class='cart-form__description cart-form__description_primary cart-form__description_base-alter cart-form__description_font-weight_semibold cart-form__description_condensed-specific']/a"));
    public SelenideElement inBasketProductTitle = $(By.xpath("//h1[@class='catalog-masthead__title js-nav-header']"));
    public SelenideElement inBasketProductPrice = $(By.xpath("//a[@class='offers-description__link offers-description__link_nodecor js-description-price-link']"));
    public String cheapestProductTitle = ".//div[@class='product__title']/a";
    public String cheapestProductPrice = ".//div[@class='product__price']//span";
}