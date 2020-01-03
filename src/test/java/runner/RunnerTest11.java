package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = { "src/test/resources/features/PRCM-Backend" }, tags = {"@391031 or @391032 or @391033 or @391034"}, glue = {
		"r1.prcmbe.steps.definitions" })
public class RunnerTest11 {
}