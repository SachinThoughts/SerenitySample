package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)

@CucumberOptions(features = { "src/test/resources/features/PRCM-Backend" }, tags = {

		"@391035 or @391036 or @391037 or @428162 or @428172" }, glue = {
				"r1.prcmbe.steps.definitions" })
public class RunnerTest12 {

}
