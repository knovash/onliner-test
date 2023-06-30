package org.itacademy.onlinertest.utils;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

@Log4j2
public class ElementUtils {

    public static Double getDouble(SelenideElement element) {
        String priceText = "0";
        if (
        element.$(By.xpath(".//div[@class='product__price']//span")).exists()){
        priceText = element.$(By.xpath(".//div[@class='product__price']//span")).getText();
        priceText = priceText
                .replace(" р.", "")
                .replace(" ", "")
                .replace(",", ".");}
        return Double.valueOf(priceText);
    }
}
