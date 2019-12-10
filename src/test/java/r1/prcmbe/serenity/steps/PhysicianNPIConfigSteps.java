package r1.prcmbe.serenity.steps;

import net.thucydides.core.annotations.Step;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.PhysicianNPIConfigPage;

public class PhysicianNPIConfigSteps {

	PhysicianNPIConfigPage physicianNPIConfigPage;

	@Step
	public String getAnyDisabledPayor() {
		return physicianNPIConfigPage.getListOfDisabledPayorsName()
				.get(CommonMethods.getRandom(physicianNPIConfigPage.getListOfDisabledPayorsName().size() - 1));
	}
}
