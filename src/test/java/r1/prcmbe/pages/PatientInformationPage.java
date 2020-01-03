package r1.prcmbe.pages;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class PatientInformationPage extends PageObject {

	@FindBy(id = "patientDetails")
	private WebElementFacade patientAndFacilityInfoSection;

	@FindBy(xpath = "//a[text()='Patient & Visit Details']//parent::li[@class='active']")
	private WebElementFacade patientAndVisitDetailsTabSelected;

	@FindBy(xpath = "//*[@id='patientDetails']/div[@class='panel-heading']//li")
	private List<WebElementFacade> listOfPatientAndFacilityInfoTab;

	@FindBy(id = "patC")
	private WebElementFacade drillDownBtn;

	@FindBy(xpath = "(//h3[@class='panel-title'])[1]/i[contains(@class,'right')]")
	private WebElementFacade collapsedPatientInfoSection;

	@FindBy(xpath = "//ul[@class='patient-data patient-header']//li/span[1]")
	private List<WebElementFacade> listOfPatientDataHeader;

	@FindBy(xpath = "//*[@id='patientDetails']//a[text()='Patient & Visit Details']")
	private WebElementFacade patientAndVisitDetailsTab;

	@FindBy(xpath = "//*[@id='patientDetails']//a[text()='Patient Address']")
	private WebElementFacade patientAddressTab;

	@FindBy(xpath = "//*[@id='patientDetails']//a[text()='Facility Details']")
	private WebElementFacade facilityDetailsTab;

	@FindBy(xpath = "//*[@id='invoiceView']//li//span[1]")
	private List<WebElementFacade> listOfFacilityDetailsSectionHeaders;

	@FindBy(xpath = "//*[@id='invoiceView']//li[1]//span[2]")
	private WebElementFacade facilityCode;

	@FindBy(xpath = "//*[@id='invoiceView']//li[2]//span[2]")
	private WebElementFacade facilityName;

	@FindBy(xpath = "//*[@id='invoiceView']//li[3]//span[2]")
	private WebElementFacade locationCode;

	@FindBy(xpath = "//*[@id='invoiceView']//li[4]//span[2]")
	private WebElementFacade locationName;

	@FindBy(xpath = "//*[@id='invoiceView']//li[5]//span[2]")
	private WebElementFacade locationAddress;

	@FindBy(xpath = "//*[@id='invoiceView']//li[6]//span[2]")
	private WebElementFacade facilityCity;

	@FindBy(xpath = "//*[@id='invoiceView']//li[7]//span[2]")
	private WebElementFacade facilityState;

	@FindBy(xpath = "//*[@id='invoiceView']//li[8]//span[2]")
	private WebElementFacade facilityZipCode;

	@FindBy(xpath = "//*[@id='invoiceView']//li[9]//span[2]")
	private WebElementFacade facilityNpi;

	@FindBy(xpath = "//*[@id='invoiceView']//li[10]//span[2]")
	private WebElementFacade facilityTaxId;

	@FindBy(xpath = "//*[@id='invoiceView']//li[11]//span[2]")
	private WebElementFacade facilityPTan;

	/**
	 * @return Visibility of Patient and Facility Info section
	 */
	public boolean isPatientAndFacilityInfoSectionVisible() {
		return patientAndFacilityInfoSection.isVisible();
	}

	/**
	 * @return List of Patient and Facility Info Tab Headers
	 */
	public List<String> getlistOfPatientAndFacilityInfoTab() {
		List<String> listOfHeaders = new ArrayList<>();
		for (WebElementFacade element : listOfPatientAndFacilityInfoTab) {
			listOfHeaders.add(element.getText());
		}
		return listOfHeaders;
	}

	/**
	 * @return Visibility of Patient And Visit Tab
	 */
	public boolean isPatientAndVisitTabVisible() {
		return patientAndVisitDetailsTabSelected.isVisible();
	}

	/**
	 * Clicking on Drill down Button of Patient and Facility Information
	 */
	public void clickDrillDownBtn() {
		drillDownBtn.click();
	}

	/**
	 * @return Visibility of collapsed Patient Information section
	 */
	public boolean isPatientAndVisitCollapsedSecVisible() {
		return collapsedPatientInfoSection.isVisible();
	}

	/**
	 * Clicking on Patient and Facility Info Panel
	 */
	public void clickOnPatientAndFacilityInfoPanel() {
		patientAndFacilityInfoSection.click();
	}

	/**
	 * @return List of Patient Data Headers
	 */
	public List<String> getlistOfPatientDataHeaders() {
		List<String> listOfHeaders = new ArrayList<>();
		for (WebElementFacade element : listOfPatientDataHeader) {
			listOfHeaders.add(element.getText().trim());
		}
		return listOfHeaders;
	}

	/**
	 * Clicking on Patient and Visit Details Tab
	 */
	public void clickOnPatientAndVisitDetailsTab() {
		patientAndVisitDetailsTab.click();
	}

	/**
	 * Clicking on Patient and Address Tab
	 */
	public void clickOnPatientAddressTab() {
		patientAddressTab.click();
	}

	/**
	 * Clicking on Facility Details Tab
	 */
	public void clickOnFacilityDetailsTab() {
		facilityDetailsTab.click();
	}

	/**
	 * @return List of Facility Information Section labels
	 */
	public List<String> getlistOfFacilityInfoSectionLables() {
		List<String> listOfHeaders = new ArrayList<>();
		for (WebElementFacade element : listOfFacilityDetailsSectionHeaders) {
			listOfHeaders.add(element.getText().trim());
		}
		return listOfHeaders;
	}

	/**
	 * @return Facility code text
	 */
	public String getFacilityCode() {
		return facilityCode.getText();
	}

	/**
	 * @return Facility Name text
	 */
	public String getFacilityName() {
		return facilityName.getText();
	}

	/**
	 * @return Location Code text
	 */
	public String getLocationCode() {
		return locationCode.getText();
	}

	/**
	 * @return Location Name text
	 */
	public String getLocationName() {
		return locationName.getText();
	}

	/**
	 * @return Facility Address text
	 */
	public String getFacilityAddress() {
		return locationAddress.getText();
	}

	/**
	 * @return City of Facility text
	 */
	public String getFacilityCity() {
		return facilityCity.getText();
	}

	/**
	 * @return Facility state text
	 */
	public String getFacilityState() {
		return facilityState.getText();
	}

	/**
	 * @return Facility zip code text
	 */
	public String getFacilityZipCode() {
		return facilityZipCode.getText();
	}

	/**
	 * @return Facility NPI text
	 */
	public String getFacilityNpi() {
		return facilityNpi.getText();
	}

	/**
	 * @return Facility Tax Id text
	 */
	public String getFacilityTaxId() {
		return facilityTaxId.getText();
	}

	/**
	 * @return Facility PTan text
	 */
	public String getFacilityPTan() {
		return facilityPTan.getText();
	}
}
