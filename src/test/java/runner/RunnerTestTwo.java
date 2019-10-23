package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = { "src/test/resources/features" }, tags = {""}, glue = {
		"r1.prcmbe.steps.definitions" })
public class RunnerTestTwo {
}
