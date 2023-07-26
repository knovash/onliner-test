package org.itacademy.onlinertest;

import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.models.OrderItem;
import org.itacademy.onlinertest.steps.OrderSteps;
import org.itacademy.onlinertest.utils.DataProviderOrderItems;
import org.itacademy.onlinertest.utils.WaitUtils;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Log4j2
@Listeners
public class CatalogOrderFormTest extends BaseTest {

    private OrderSteps orderSteps = new OrderSteps();

    @Description("Сheck that the form fields are filled in correctly")
    @Test(testName = "Order form test",
            dataProvider = "orderItems",
            dataProviderClass = DataProviderOrderItems.class)
    public void checkOrderFormTest(OrderItem order) {
        log.info("TEST ORDER START");
        log.info("ORDER: " + order);
        orderSteps.clickFirstOffer();
        orderSteps.clickBuyNow();
        orderSteps.fillForm(order);
        orderSteps.checkForm(order).assertAll();
        WaitUtils.waitForVisibility(2);
    }
}
