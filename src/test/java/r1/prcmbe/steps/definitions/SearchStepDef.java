package r1.prcmbe.steps.definitions;

import org.junit.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import r1.prcmbe.pages.BillingAndFollowUpPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.SearchPage;

public class SearchStepDef {
	NavigationPage navigationPage;
	BillingAndFollowUpPage billingAndFollowUpPage;
	SearchPage searchPage;

	@When("^user clicks on Billing & Follow-up link$")
	public void user_clicks_on_Billing_Follow_up_link() {
		navigationPage.clickOnBillingAndFollowUpLink();
	}

	@When("^user clicks on R1_Decision link$")
	public void user_clicks_on_R1_Decision_link() {
		billingAndFollowUpPage.clickOnR1DecisionLink();
	}

	@Then("^user should be able to view R1D Search page $")
	public void user_should_be_able_to_view_R1_D_Search_page() {
		Assert.assertTrue("User is not navigated on R1 D Search Page",
				searchPage.getSearchPageTitle().contains("R1 Hub Technologies 2.0 - 01 R1_Decision - Search"));
	}

	@Then("^user should be able to view message \"([^\"]*)\"$")
	public void user_should_be_able_to_view_message(String expectedMessage) {
		Assert.assertTrue("'" + expectedMessage + "' message not visible",
				searchPage.getNoAccountsMessage().replace("\n", "").equals(expectedMessage));
	}
}
