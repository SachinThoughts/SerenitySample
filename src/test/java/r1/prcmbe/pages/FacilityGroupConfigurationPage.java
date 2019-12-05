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

	@FindBy(xpath = "(//button[contains(@data-target,'#addFacilityGroup')])[1]")
	private WebElementFacade addFacilityBtn;

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

	public List<Object> verifyControlsOnFacilityConfigPage(List<String> listOfFields) {
		int count = 0;
		int size = listOfTableHeader.size();
		List<Object> listOfVal = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			if (listOfTableHeader.get(i).getText().equals(listOfFields.get(i))) {
				count = count + 1;
			} else {
				listOfVal.add(listOfFields.get(i));
			}
			if (addFacilityBtn.getText().equals(listOfFields.get(2))) {
				count = count + 1;
			} else {
				listOfVal.add("Add New Facility Group");

			}
			if (editBtn.getText().equals(listOfFields.get(3))) {
				count = count + 1;
			} else {
				listOfVal.add("Edit");
			}
		}
			if (count == 4) {
				listOfVal.add(true);
			} else {
				listOfVal.add(false);
			}
			return listOfVal;
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





}