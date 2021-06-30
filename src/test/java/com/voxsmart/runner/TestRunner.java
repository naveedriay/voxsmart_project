package com.voxsmart.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@ContextConfiguration(locations = {"file:/src/test/resources/spring-config.xml"})
@CucumberOptions(

        glue = "com.voxsmart",
        features = "classpath:cucumber",
        tags = {"not @ignore", "@test"}, // skip tests with @ignore
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber-reports",
                "json:target/cucumber-reports/cucumber.json",
                "rerun:target/rerun.txt"} //Creates a text file with failed scenarios
)
/* A nice article about Cucumber.Options is here
 * https://testingneeds.wordpress.com/tag/cucumberoptions/ */
public class TestRunner {
}
