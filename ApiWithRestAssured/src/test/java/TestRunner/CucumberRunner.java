package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

public class CucumberRunner {
    @CucumberOptions(
            features = {"src/test/resources/ApiFeatures"},
            glue = {"StepDefinition"},
            tags ="@getApi or @postApi",
            monochrome = true,
            dryRun = false, //runs feature file so false to stop
            plugin = {
                    "pretty",
                    "html:build/reports/feature.html"
            })
    @Test
    public class CucumberRunnerTests extends AbstractTestNGCucumberTests {
    }
}
