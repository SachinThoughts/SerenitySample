package r1.prcmbe.pages;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class FacilityGroupConfigurationPage extends PageObject {

	@FindBy(xpath = "//*[@id='lstFacilityGroup']/li/div[1]/span")
	private List<WebElementFacade> facilityGroupList;

	@FindBy(xpath = "//h3[contains(text(),'Facility Group Configuration')]")
	private WebElementFacade facilityGroupConfigPageHeader;

	@FindBy(xpath = "//ul[@class='list-table-header FacilityGroup-admin no-counter']/li/preceding-sibling::li")
	private List<WebElementFacade> listOfTableHeader;

	@FindBy(xpath = "//button[contains(@data-target,'#addFacilityGroup')")
	private List<WebElementFacade> listOfAddFacilityBtn;

	@FindBy(xpath = "(//a[@class='btn btn-link lnkEditFacilityGroup'])[1]")
	private WebElementFacade editBtn;
	
	@FindBy(xpath = "//*[@id='lstFacilityGroup']/li/div[2]/span")
	private List<WebElementFacade> listOfFacilities;

	public boolean isFacilityGrpConfigHeaderVisible() {
		return facilityGroupConfigPageHeader.isVisible();
	}

	public String getPRCMFacility() {
		String pRCMFacility = null;
		for (WebElementFacade facility : facilityGroupList) {
			if (facility.getText().trim().contains("PRCM")) {
				pRCMFacility = facility.getText().trim();
			}
		}
		return pRCMFacility;
	}

	public List<String> getTableGridHeaders() {
		List<String> getHeaders = new ArrayList<String>();
		for (WebElementFacade headerElement : listOfTableHeader) {
			getHeaders.add(headerElement.getText().trim());
		}
		return getHeaders;
	}
public boolean IsPRCMFacilityGroupNamePresent(String expectedFacilityGroupName) {
	List<String> getListOfFacilityGroup= new ArrayList<>();
	for(WebElementFacade facilityGrp:facilityGroupList) {
		getListOfFacilityGroup.add(facilityGrp.getText());
	}
	return getListOfFacilityGroup.contains(expectedFacilityGroupName);
}

public boolean isExpectedFacilityPresent(String expectedFacility) {
	List<String>getFacilities= new ArrayList<>();
	for(WebElementFacade facility:listOfFacilities) {
		getFacilities.add(facility.getText());
	}
	return getFacilities.contains(expectedFacility);
}

public boolean AreAddFAcilityBtnPresents() {
	int size=listOfAddFacilityBtn.size();
	for(int i=0;i<size;i++) {
		if(listOfAddFacilityBtn.get(i).isVisible()) {
			return true;
		}
	}
		return false;
		
	
}


}


