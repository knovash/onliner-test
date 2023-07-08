package org.itacademy.onlinertest.utils;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.pages.CatalogPage;
import org.openqa.selenium.By;

@Log4j2
public class ElementUtils {

    private static CatalogPage catalogPage = new CatalogPage();

    public static Double getDouble(SelenideElement element) {
        String priceText = "0";
        if (element.$(By.xpath(catalogPage.cheapestProductPrice)).exists()) {
            priceText = element.$(By.xpath(catalogPage.cheapestProductPrice)).getText();
            priceText = priceText
                    .replace(" р.", "")
                    .replace(" ", "")
                    .replace(",", ".");
        }
        return Double.valueOf(priceText);
    }

    public static String getTitle(SelenideElement element) {
        String title = element.$(By.xpath(catalogPage.cheapestProductTitle)).getText();
        return title;
    }
}
