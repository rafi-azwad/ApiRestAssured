package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

    @CucumberOptions(
            features = {"src/test/resources/ApiFeatures"},
            glue = {"StepDefinition"},
            tags ="@getApi",
            monochrome = true,
            dryRun = false, //runs feature file so false to stop
            plugin = {
                    "pretty",
                    "html:build/reports/getFeature.html"
            })
    @Test
    public class GetApiRunnerTest extends AbstractTestNGCucumberTests {
    }

