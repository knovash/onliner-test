package org.itacademy.onlinertest.steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.models.OrderItem;
import org.itacademy.onlinertest.pages.OrderPage;
import org.itacademy.onlinertest.utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class OrderSteps {

    private OrderPage orderPage = new OrderPage();

    @Step("clickFirstOffer")
    public void clickFirstOffer() {
        log.info("clickFirstOffer");
        WaitUtils.waitForVisibility(orderPage.firstOffer);
        orderPage.firstOffer.scrollIntoView(false);
        log.info("DISP: " + orderPage.firstOffer.isDisplayed());
        orderPage.firstOffer.click();
    }

    @Step("clickBuyNow")
    public void clickBuyNow() {
        log.info("clickBuyNow");
        WaitUtils.waitForVisibility(orderPage.buyNow);
        orderPage.buyNow.scrollIntoView(false);
        log.info("DISP: " + orderPage.buyNow.isDisplayed());
        orderPage.buyNow.click();
    }

    @Step("check item in results")
    public void fillForm(OrderItem order) {
        log.info("check item in results");
        orderPage.street.setValue(order.street);
        orderPage.building.setValue(order.building);
        orderPage.entrance.setValue(order.entrance);
        orderPage.floor.setValue(order.floor);
        orderPage.apartment.setValue(order.apartment);
        orderPage.comment.setValue(order.comment);
        orderPage.firstName.setValue(order.firstName);
        orderPage.lastName.setValue(order.lastName);
        orderPage.email.setValue(order.email);
        orderPage.phone.setValue(order.phone);
    }

    @Step("check item in results")
    public SoftAssert checkForm(OrderItem order) {
        log.info("check item in results");
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(orderPage.street.getValue(), order.street);
        sa.assertEquals(orderPage.building.getValue(), order.building);
        sa.assertEquals(orderPage.entrance.getValue(), order.entrance);
        sa.assertEquals(orderPage.floor.getValue(), order.floor);
        sa.assertEquals(orderPage.apartment.getValue(), order.apartment);
        sa.assertEquals(orderPage.comment.getValue(), order.comment);
        sa.assertEquals(orderPage.firstName.getValue(), order.firstName);
        sa.assertEquals(orderPage.lastName.getValue(), order.lastName);
        sa.assertEquals(orderPage.email.getValue(), order.email);
        sa.assertEquals(orderPage.phone.getValue(), order.phone);
        return sa;
    }
}