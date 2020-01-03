package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)

@CucumberOptions(features = { "src/test/resources/features/PRCM-Backend" }, tags = {

		"@433695 or @428877 or @428167 or @433692 or @433693" }, glue = {
				"r1.prcmbe.steps.definitions" })
public class RunnerTest14 {

}
