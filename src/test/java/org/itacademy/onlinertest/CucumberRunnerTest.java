package org.itacademy.onlinertest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import lombok.extern.log4j.Log4j2;
import org.itacademy.onlinertest.utils.Config;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Browsers.CHROME;

@Log4j2
@CucumberOptions(
        features = {"src/main/java/org/itacademy/onlinertest/features/Search.feature"},
        glue = {"org.itacademy.onlinertest.steps.cucumber_steps"},
        plugin = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {

    @BeforeClass
    @Parameters({"startType", "browser", "version"})
    public void beforeClass(String startType,
                            @Optional("browser") String browser,
                            @Optional("version") String version) {
        log.info("STARTTYPE: " + startType);
        log.info("BROWSER: " + browser);
        log.info("VERSION:" + version);
        log.info("BEFORE CLASS config get properties");
        Config.getProperties();

        log.info("BEFORE CLASS startType run: " + startType);
        if (startType.equals("local")) {
            startLocal();
        } else if (startType.equals("docker")) {
            startDocker(browser, version);
        }
    }

    public static void startLocal() {
        log.info("START TYPE LOCAL");
        Configuration.browserSize = "1920x1080";
        Configuration.browser = CHROME;
        /** https://github.com/selenide/selenide/issues/1268 def 30 sec. for mobile connection 90 000 msec */
        Configuration.pageLoadTimeout = 90000;
    }

    public static void startDocker(String browser, String version) {
        log.info("START TYPE DOCKER");
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = browser;
        /** https://github.com/selenide/selenide/issues/1268 def 30 sec. for mobile connection 90 000 msec */
        Configuration.pageLoadTimeout = 90000;

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

    @AfterMethod
    public void afterMethod() {
        Selenide.clearBrowserCookies();
    }
}