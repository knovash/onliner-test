package org.itacademy.onlinertest.steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.models.OrderItem;
import org.itacademy.onlinertest.pages.OrderPage;
import org.itacademy.onlinertest.utils.WaitUtils;
import org.testng.asserts.SoftAssert;

@Log4j2
public class OrderSteps {

    private final OrderPage orderPage = new OrderPage();

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
        WaitUtils.waitForVisibility(orderPage.street);
        orderPage.street.click();
        log.info("CLEAR " + orderPage.clearStreet.isDisplayed());
        if (orderPage.clearStreet.isDisplayed()) {
            orderPage.clearStreet.click();
        }
        orderPage.street.clear();
        orderPage.street.setValue(order.street);
        log.info("street disp: " + orderPage.street.isDisplayed() + " value set: " + order.street);
        orderPage.building.setValue(order.building);
        log.info("building disp: " + orderPage.building.isDisplayed() + " value set: " + order.building);
        orderPage.entrance.setValue(order.entrance);
        log.info("entrance disp: " + orderPage.entrance.isDisplayed() + " value set: " + order.entrance);
        orderPage.floor.setValue(order.floor);
        log.info("floor disp: " + orderPage.floor.isDisplayed() + " value set: " + order.floor);
        orderPage.apartment.setValue(order.apartment);
        log.info("apartment disp: " + orderPage.apartment.isDisplayed() + " value set: " + order.apartment);
        orderPage.comment.setValue(order.comment);
        log.info("comment disp: " + orderPage.comment.isDisplayed() + " value set: " + order.comment);
        orderPage.firstName.setValue(order.firstName);
        log.info("firstName disp: " + orderPage.firstName.isDisplayed() + " value set: " + order.firstName);
        orderPage.lastName.setValue(order.lastName);
        log.info("lastName disp: " + orderPage.lastName.isDisplayed() + " value set: " + order.lastName);
        orderPage.email.setValue(order.email);
        log.info("email disp: " + orderPage.email.isDisplayed() + " value set: " + order.email);
        orderPage.phone.setValue(order.phone);
        log.info("phone disp: " + orderPage.phone.isDisplayed() + " value set: " + order.phone);
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
        sa.assertEquals(orderPage.phone.getValue()
                .replace(" ", "")
                .replace("-", ""), order.phone);
        return sa;
    }
}