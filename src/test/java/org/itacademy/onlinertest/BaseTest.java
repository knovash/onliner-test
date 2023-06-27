package org.itacademy.onlinertest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.utils.Config;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class BaseTest {

    @BeforeClass
    public void beforeClass() {
        log.info("BEFORE CLASS config get properties");
        Config.getProperties();
        log.info("BEFORE CLASS add allure listener");
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true)
        );
        Configuration.pageLoadTimeout = 90000;
        /** https://github.com/selenide/selenide/issues/1268 def 30 sec. for mobile connection 90 000 msec */
    }

    @BeforeMethod
    public void beforeMethod() {
        log.info("BEFORE METHOD open home page: " + Config.getHomePage());
        open(Config.getHomePage());
    }
}
