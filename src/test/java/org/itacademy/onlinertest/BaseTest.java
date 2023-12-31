package org.itacademy.onlinertest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.utils.Config;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static com.codeborne.selenide.Browsers.CHROME;

@Log4j2
public class BaseTest {

    @BeforeClass
    @Parameters({"startType", "browser", "version"})
    public void beforeClass(String startType,
                            @Optional("browser") String browser,
                            @Optional("version") String version) {
        log.info("START TYPE: " + startType);
        log.info("BROWSER: " + browser);
        log.info("VERSION:" + version);
        log.info("BEFORE CLASS config get properties");
        Config.getProperties();

        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .enableLogs(LogType.BROWSER, Level.ALL));

        Configuration.browserSize = "1920x1080";
        /** https://github.com/selenide/selenide/issues/1268 def 30 sec. for mobile connection 90 000 msec */
        Configuration.pageLoadTimeout = 90000;

        log.info("BEFORE CLASS startType run: " + startType);
        if (startType.equals("local")) {
            startLocal();
        } else if (startType.equals("docker")) {
            startDocker(browser, version);
        }
    }

    public static void startLocal() {
        log.info("START TYPE LOCAL");
        Configuration.browser = CHROME;
    }

    public static void startDocker(String browser, String version) {
        log.info("START TYPE DOCKER");
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = browser;
        Map<String, Boolean> options = new HashMap<>();
        options.put("enableVNC", true);
        options.put("enableVideo", false);
        options.put("enableLog", true);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setVersion(version);
        capabilities.setCapability("selenoid:options", options);
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeMethod
    public void beforeMethod() {
        log.info("BEFORE METHOD open home page: " + Config.getHomePage());
        Selenide.open(Config.getHomePage());
    }

    @AfterMethod
    public void afterMethod() {
        Selenide.clearBrowserCookies();
    }
}