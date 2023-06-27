package org.itacademy.onlinertest.utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class PriceUtils {

    public static Double getDouble(SelenideElement element) {
        String priceText = element.$(By.xpath(".//div[@class='product__price']//span")).getText();
        priceText = priceText
                .replace(" р.", "")
                .replace(" ", "")
                .replace(",", ".");
        return Double.valueOf(priceText);
    }
}
