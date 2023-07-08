package org.itacademy.onlinertest;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.steps.FilterSteps;
import org.itacademy.onlinertest.utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
@Listeners
public class CatalogOrderFormTest extends BaseTest {

    private FilterSteps filterSteps = new FilterSteps();

    @Test
    public void checkOrderFormTest() {
        log.info("TEST ORDER START");
        SelenideElement firstLink = $(By.xpath("//div[@class='catalog-offers__title']//a[contains(@href,'catalog')]"));
        firstLink.scrollIntoView(false);
        log.info("DISP: " + firstLink.isDisplayed());
        log.info("TEXT: " + firstLink.getText());
        firstLink.click();


        SelenideElement now = $(By.xpath("//a[contains(@href,'https://cart.onliner.by') and contains(text(), 'сейчас')]"));
        WaitUtils.waitForVisibility(now);
        now.click();


        //div[@class='cart-form__label-title' and contains(text(), 'Улица')]/../../../following-sibling::div//input
        SelenideElement street = $(By.xpath("//div[@class='cart-form__label-title' and contains(text(), 'Улица')]/../../../following-sibling::div//input"));

        WaitUtils.waitForVisibility(street);
        street.setValue("Кульман");
        log.info("street: " + street.getValue());

        SelenideElement building = $(By.xpath("//div[@class='cart-form__label-title' and contains(text(), 'Дом')]/../../../following-sibling::div//input"));
        building.setValue("15");
        log.info("building: " + building.getValue());

        SelenideElement entrance = $(By.xpath("//div[@class='cart-form__label-title' and contains(text(), 'Под.')]/../../../following-sibling::div//input"));
        entrance.setValue("2");
        log.info("entrance: " + entrance.getValue());

        SelenideElement floor = $(By.xpath("//div[@class='cart-form__label-title' and contains(text(), 'Этаж')]/../../../following-sibling::div//input"));
        floor.setValue("7");
        log.info("floor: " + floor.getValue());

        SelenideElement apartment = $(By.xpath("//div[@class='cart-form__label-title' and contains(text(), 'Квартира')]/../../../following-sibling::div//input"));
        apartment.setValue("222");
        log.info("apartment: " + apartment.getValue());

        SelenideElement comment = $(By.xpath("//div[contains(text(), 'Комментарий к адресу')]/../../../following-sibling::div//textarea"));
        comment.setValue("Очень быстро");
        log.info("comment: " + comment.getValue());

        SelenideElement firstName = $(By.xpath("//div[@class='cart-form__label-title' and contains(text(), 'Имя')]/../../../following-sibling::div//input"));
        firstName.setValue("Константин");
        log.info("firstName: " + firstName.getValue());

        SelenideElement lastName = $(By.xpath("//div[@class='cart-form__label-title' and contains(text(), 'Фамилия')]/../../../following-sibling::div//input"));
        lastName.setValue("Новаш");
        log.info("lastName: " + lastName.getValue());

        SelenideElement email = $(By.xpath("//div[@class='cart-form__label-title' and contains(text(), 'E-mail')]/../../../following-sibling::div//input"));
        email.setValue("novash.ki@gmail.com");
        log.info("email: " + email.getValue());

        SelenideElement phone = $(By.xpath("//div[contains(text(), 'Телефон')]/following-sibling::div//input"));
        phone.setValue("293246164");
        log.info("phone: " + phone.getValue());


        WaitUtils.waitForVisibility(30);


    }
}
