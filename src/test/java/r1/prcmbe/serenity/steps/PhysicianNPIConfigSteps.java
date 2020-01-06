package r1.prcmbe.serenity.steps;

import net.thucydides.core.annotations.Step;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.PhysicianNPIConfigPage;

public class PhysicianNPIConfigSteps {

	PhysicianNPIConfigPage physicianNPIConfigPage;

	/**
	 * This method is used to get a random name of disabled payor from disabled
	 * payor section
	 * 
	 * @return the random disabled payor name
	 */
	@Step
	public String getAnyDisabledPayor() {
		return physicianNPIConfigPage.getListOfDisabledPayorsName()
				.get(CommonMethods.getRandom(physicianNPIConfigPage.getListOfDisabledPayorsName().size() - 1));
	}

	/**
	 * This method is used to get a random name of eligible payor from Eligible
	 * payor section
	 * 
	 * @return the random eligible payor name
	 */
	@Step
	public String getAnyEligiblePayor() {
		return physicianNPIConfigPage.getListOfEligiblePayorsName()
				.get(CommonMethods.getRandom(physicianNPIConfigPage.getListOfEligiblePayorsName().size() - 1));
	}
}
