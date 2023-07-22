package org.itacademy.onlinertest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/main/java/org/itacademy/onlinertest/features/Search.feature"},
        glue = {"org.itacademy.onlinertest.steps.cucumber_search_steps"},
        plugin = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
}