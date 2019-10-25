package r1.prcmbe.steps.definitions;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.util.EnvironmentVariables;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.BillingAndFollowUpPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.PRCMBESearchPage;

public class PRCMBESearchStepDef extends PageObject {
	NavigationPage navigationPage;
	BillingAndFollowUpPage billingAndFollowUpPage;
	CommonMethods commonMethods;
	EnvironmentVariables environmentVariables;
	PRCMBESearchPage pRCMBESearchPage;

	@Given("^user is on R1 Hub page$")
	public void user_is_on_R1_Hub_page() {
		Assert.assertTrue(getDriver().getTitle().contains("R1 Hub Technologies 2.0 - 01 Home"));
	}

	@When("^user clicks on Billing & Follow-up link$")
	public void user_clicks_on_Billing_Follow_up_link() {
		navigationPage.clickOnBillingAndFollowUpLink();
	}

	@When("^user hovers on R1_Decision link$")
	public void user_hovers_on_R1_Decision_link() {
		billingAndFollowUpPage.hoverOnR1DecisionLink();
	}

	@When("^user clicks on search sub menu$")
	public void user_clicks_on_search_sub_menu() {
		billingAndFollowUpPage.clickSearchLink();
	}

	@Given("^user is on \"([^\"]*)\" page$")
	public void user_is_on_page(String searchPagetitle) {
		Assert.assertTrue(searchPagetitle + " page is not displayed",
				pRCMBESearchPage.getsearchPageTitle().contains(searchPagetitle));
	}

	@Then("^user should be able to view Invoice Number selected by default in Search By drop down$")
	public void user_should_be_able_to_view_Invoice_Number_selected_by_default_in_Search_By_drop_down() {
		Assert.assertTrue("Invoice number is not selected by default",
				pRCMBESearchPage.getDefaultSelectedVal().equals("Invoice Number"));
	}

	@When("^user selects (.*) from Search By drop down$")
	public void user_selects_Visit_Number_from_Search_By_drop_down(String dropdown) {
		pRCMBESearchPage.searchBySelectText(dropdown);
	}

	@When("^user enters invalid value in (.*) textbox $")
	public void user_enters_invalid_value_in_$_textbox(String invalidVal) {
		if (pRCMBESearchPage.isVisitTxtFieldVisible()) {
			pRCMBESearchPage.enterVisitNumber(invalidVal);
		} else if (pRCMBESearchPage.isMRNTxtFieldVisible()) {
			pRCMBESearchPage.enterMRN(invalidVal);
		} else if (pRCMBESearchPage.isClaimNumberTxtFieldVisible()) {
			pRCMBESearchPage.enterClaimNumber(invalidVal);
		} else if (pRCMBESearchPage.isInvoiceNumberTxtFieldVisible()) {
			pRCMBESearchPage.enterInvoiceNumber(invalidVal);
		} else if (pRCMBESearchPage.isfirstNameTxtFieldVisible()&&pRCMBESearchPage.islastNameTxtBoxTxtFieldVisible()) {
			pRCMBESearchPage.enterlastNameTxtBox(invalidVal);
			pRCMBESearchPage.enterfirstName(invalidVal);
		} else if (pRCMBESearchPage.isSSNTxtFieldVisible()) {
			pRCMBESearchPage.enterSSN(invalidVal);
		}

		else {
			Assert.assertTrue("Search text box not visible", false);
		}
	}

	@When("^user clicks on Submit Button$")
	public void user_clicks_on_Submit_Button() {
		pRCMBESearchPage.clickSubmitBtn();
	}

	@Then("^user should be able to view error message (.*)$")
	public void user_should_be_able_to_view_error_message(String errorMsg) {
		Assert.assertTrue("'" + errorMsg + "' message is not visible", pRCMBESearchPage.getErrorMsg().equalsIgnoreCase(errorMsg));

	}

}
