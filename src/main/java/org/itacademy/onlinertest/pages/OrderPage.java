package org.itacademy.onlinertest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class OrderPage {

    public SelenideElement firstOffer = $(By.xpath("//div[@class='catalog-offers__title']//a[contains(@href,'catalog')]"));
    public SelenideElement buyNow = $(By.xpath("//a[contains(@href,'https://cart.onliner.by') and contains(text(), 'сейчас')]"));
    public SelenideElement street = $(By.xpath("//div[@class='cart-form__label-title' and contains(text(), 'Улица')]/../../../following-sibling::div//input"));
    public SelenideElement building = $(By.xpath("//div[@class='cart-form__label-title' and contains(text(), 'Дом')]/../../../following-sibling::div//input"));
    public SelenideElement entrance = $(By.xpath("//div[@class='cart-form__label-title' and contains(text(), 'Под.')]/../../../following-sibling::div//input"));
    public SelenideElement floor = $(By.xpath("//div[@class='cart-form__label-title' and contains(text(), 'Этаж')]/../../../following-sibling::div//input"));
    public SelenideElement apartment = $(By.xpath("//div[@class='cart-form__label-title' and contains(text(), 'Квартира')]/../../../following-sibling::div//input"));
    public SelenideElement comment = $(By.xpath("//div[contains(text(), 'Комментарий к адресу')]/../../../following-sibling::div//textarea"));
    public SelenideElement firstName = $(By.xpath("//div[@class='cart-form__label-title' and contains(text(), 'Имя')]/../../../following-sibling::div//input"));
    public SelenideElement lastName = $(By.xpath("//div[@class='cart-form__label-title' and contains(text(), 'Фамилия')]/../../../following-sibling::div//input"));
    public SelenideElement email = $(By.xpath("//div[@class='cart-form__label-title' and contains(text(), 'E-mail')]/../../../following-sibling::div//input"));
    public SelenideElement phone = $(By.xpath("//div[contains(text(), 'Телефон')]/following-sibling::div//input"));
}