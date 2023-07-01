package org.itacademy.onlinertest.utils;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class WaitUtils {

    public static void waitForVisibility(SelenideElement element, int seconds) {
        log.info("wait For Element Visibility sec: " + seconds + " " + element);
        new WebDriverWait(getWebDriver(), Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForVisibility(SelenideElement element) {
        log.info("wait For Element Visibility sec: 30" + " " + element);
        new WebDriverWait(getWebDriver(), Duration.ofSeconds(90))
                .until(ExpectedConditions.visibilityOf(element));
    }

    /** used for debug */
    public static void waitForVisibility(int seconds) {
        log.info("wait sec: " + seconds);
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}