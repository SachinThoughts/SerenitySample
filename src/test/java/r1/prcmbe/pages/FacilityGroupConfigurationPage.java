package r1.prcmbe.pages;

import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class FacilityGroupConfigurationPage extends PageObject {

	@FindBy(xpath = "//*[@id='lstFacilityGroup']/li/div[1]/span")
	private List<WebElementFacade> facilityGroupList;

	public String getPRCMFacility() {
		String prcmFacility = null;
		for (WebElementFacade facility : facilityGroupList) {
			if (facility.getText().trim().equalsIgnoreCase("PRCM")) {
				prcmFacility = facility.getText().trim();
			}
		}
		return prcmFacility;
	}
}
