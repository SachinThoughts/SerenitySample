package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)

@CucumberOptions(features = { "src/test/resources/features/PRCM-Backend" }, tags = {

		"@428160 or @428161 or @391038" }, glue = {
				"r1.prcmbe.steps.definitions" })
public class RunnerTest13 {

}
