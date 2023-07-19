package org.itacademy.onlinertest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = "",
        features = {"src/main/java/org/itacademy/onlinertest/features/Search.feature"},
        glue = {"org.itacademy.onlinertest.steps"}
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
}