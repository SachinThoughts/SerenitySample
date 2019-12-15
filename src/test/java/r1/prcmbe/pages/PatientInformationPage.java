package r1.prcmbe.pages;

import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class PatientInformationPage extends PageObject {

	@FindBy(xpath="//*[@id='patientDetails']//div[1]")
	private WebElementFacade patientDetailsSection;
	
	@FindBy(xpath="//a[text()='Patient & Visit Details']//parent::li[@class='active']")
	private WebElementFacade patientAndVisitDetailsTabSelected;
	
	@FindBy(xpath="//*[@id='patientDetails']/div[1]/ul/li")
	private List <WebElementFacade> listOfPatientInfoHeaders;
	
	@FindBy(xpath="//*[@id='patC']")
	private WebElementFacade drillDownBtn;
	
	@FindBy(xpath="(//h3[@class='panel-title'])[1]/i[contains(@class,'right')]")
	private WebElementFacade collapsedPatientInfoSection;
	
	@FindBy(xpath="//ul[@class='patient-data patient-header']//li/span[1]")
	private List <WebElementFacade> listOfPatientDataHeader;
	
	@FindBy(xpath="//*[@id='patientDetails']//a[text()='Patient & Visit Details']")
	private WebElementFacade patientAndVisitDetailsTab;
	
	@FindBy(xpath="//*[@id='patientDetails']//a[text()='Patient Address']")
	private WebElementFacade patientAddressTab;
	
	@FindBy(xpath="//*[@id='patientDetails']//a[text()='Facility Details']")
	private WebElementFacade facilityDetailsTab;
	
	public boolean isPatientAndFacilityInfoSectionVisible() {
		return patientDetailsSection.isVisible();
	}
	
	public List<String> getlistOfPatientInfoHeaders() {
		List<String> listOfHeaders= new ArrayList<>();
		for (WebElementFacade element : listOfPatientInfoHeaders) {
			listOfHeaders.add(element.getText());
		}
		return listOfHeaders;
	}
	
	public boolean isPatientAndVisitTabVisible() {
		return patientAndVisitDetailsTabSelected.isVisible();
	}
	public void clickDrillDownBtn() {
		drillDownBtn.click();
	}
	public boolean isPatientAndVisitCollapsedSecVisible() {
		return collapsedPatientInfoSection.isVisible();
	}
	public void clickOnPatientAndFacilityInfoPanel() {
		patientDetailsSection.click();
	}
	public List<String> getlistOfPatientDataHeaders() {
		List<String> listOfHeaders= new ArrayList<>();
		for (WebElementFacade element : listOfPatientDataHeader) {
			listOfHeaders.add(element.getText().trim());
		}
		return listOfHeaders;
	}
	public void clickOnPatientAndVisitDetailsTab() {
		patientAndVisitDetailsTab.click();
	}
	public void clickOnPatientAddressTab() {
		patientAddressTab.click();
	}
	public void clickOnFacilityDetailsTab() {
		facilityDetailsTab.click();
	}
}
