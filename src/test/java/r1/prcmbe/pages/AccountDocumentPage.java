package r1.prcmbe.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.components.FileToUpload;
import r1.commons.utilities.CommonMethods;

public class AccountDocumentPage extends PageObject {

	private final long MEGABYTE = 1024L * 1024L;

	CommonMethods commonMethods;
	List<String> listOfVisitNo;

	private final String documentTypeDefaultVal = "--Select--";
	private final String chooseFileOptionID = "#fUploadDocument";
	private final String documentTitleID = "#txtDocumentTitle";
	private final String showAllDocumentID = "#chkShowAllDoc";

	@FindBy(xpath = "//a[@href='#accountDocs']")
	private WebElementFacade documentLink;

	@FindBy(id = "ddlIDMDocType")
	private WebElementFacade documentTypeDrpDown;

	@FindBy(id = "txtDocumentTitle")
	private WebElementFacade documentTitle;

	@FindBy(id = "fUploadDocument")
	private WebElementFacade chooseFileBtn;

	@FindBy(xpath = "//div[@id='accountDocs']//h3[@class='panel-title']")
	private WebElementFacade accntDocumentPanel;

	@FindBy(id = "btnUpload")
	private WebElementFacade uploadDocumentBtn;

	@FindBy(xpath = "//span[@class='help-block text-danger']")
	private List<WebElementFacade> listOfMsgUnderFileNameField;

	@FindBy(id = "btnNew")
	private WebElementFacade cancelBtn;

	@FindBy(xpath = "//label[@for='chkShowAllDoc']")
	private WebElementFacade showAllDocumentCheckBox;

	@FindBy(id = "msg_success")
	private WebElementFacade documentUploadSuccessMsg;

	@FindBy(xpath = "//ul[@id='docLibrary']//h4")
	private List<WebElementFacade> listOfUploadedDocsTitle;

	@FindBy(xpath = "//ul[@id='docLibrary']//h4/small")
	private List<WebElementFacade> listOfUploadedDocFileName;

	@FindBy(id = "chkMRN")
	private WebElementFacade mrnCheckBox;

	@FindBy(id = "docLibrary")
	private WebElementFacade noDocumentUploadTxt;

	@FindBy(xpath = "//div[@id='DocresultDiv']//span")
	private WebElementFacade uploadErrorMsg;

	/**
	 * This method clicks on Documents Tab
	 */
	public void clickOnDocumentTab() {
		documentLink.click();
	}

	/**
	 * This method select Any DocumentType From DocumentType DropDown
	 * @return selected DocumentType Value
	 */
	public String selectRandomDocumentTypeAndGetSelectedVal() {
		List<String> listOfDocumentDropDwnValues = getDocumentDropDwnValues();
		int randomNumber;
		int size = listOfDocumentDropDwnValues.size();
		randomNumber = CommonMethods.getRandom(size);
		documentTypeDrpDown.selectByIndex(randomNumber);
		while (documentTypeDrpDown.getSelectedVisibleTextValue().equals(documentTypeDefaultVal)) {
			randomNumber = CommonMethods.getRandom(size);
			documentTypeDrpDown.selectByIndex(randomNumber);
		}
		return documentTypeDrpDown.getSelectedVisibleTextValue();

	}

	/**
	 * This method fetch the selected DocumentType Value
	 * @return selected DocumentType Value
	 */
	public String getSelectedDocumentTypeVal() {
		return documentTypeDrpDown.getSelectedVisibleTextValue();
	}

	/**
	 * This method store the DocumentType DropDown Values in List
	 * @return list of DocumentTypeTypeDropDown Values
	 */
	public List<String> getDocumentDropDwnValues() {
		List<String> listOfDocumentDropDwnValues;
		listOfDocumentDropDwnValues = documentTypeDrpDown.getSelectOptions();
		return listOfDocumentDropDwnValues;
	}

	/**
	 * This method enter Text Value in Document Title Field
	 * @param text value to be entered
	 */
	public void enterTextDocTitle(String text) {
		documentTitle.type(text);
	}

	/**
	 * This method fetch the Document Title Value 
	 * @return Document Title Value
	 */
	public String getDocumentTitleVal() {
		return evaluateJavascript("return document.querySelector('" + documentTitleID + "').value").toString();
	}

	/**
	 * This method click On ChooseFile Button
	 */
	public void clickOnChooseFileBtn() {
		chooseFileBtn.click();
	}

	/**
	 * This method fetch the FileName Value From FileName Field
	 * @return FileName Value
	 */
	public String getFileNameVal() {
		return evaluateJavascript("return document.querySelector('" + chooseFileOptionID + "').value").toString();
	}

	/**
	 * This method fetch the FileName To Upload From TestData Folder available in Current Project
	 * @param Take Two Parameter fileExtension and fileSize
	 * @return fileName to Upload  
	 */
	public String getFileName(String fileExtension, String fileSize) {
		String fileName = "";
		File folder = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\DocsUpload");
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (fileSize.equals("Morethan20MB")) {
				if (file.getName().contains(fileExtension) && file.length() / MEGABYTE > 20) {
					return file.getName();
				}
			} else {
				if (file.getName().contains(fileExtension) && file.length() / MEGABYTE < 20) {
					return file.getName();
				}
			}
		}
		return fileName;
	}

	/**
	 * This method checks whether the AccountDocumentPanel is Visible or not 
	 * @return true or false based on visibility of element
	 */
	public boolean isAccountDocumentPanelVisible() {
		return accntDocumentPanel.isVisible();
	}

	/**
	 * This method upload the file 
	 * @param fileName to Upload
	 */
	public void uploadFile(String fileName) {
		FileToUpload fileToUpload = new FileToUpload(getDriver(),
				System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\DocsUpload\\" + fileName);
		fileToUpload.fromLocalMachine().to(chooseFileBtn);
	}

	/**
	 * This method clicks on UploadFile Button 
	 */
	public void clickOnUploadFileBtn() {
		uploadDocumentBtn.click();
	}

	/**
	 * This method fetch the Message below FileName field 
	 * @return message Value
	 */
	public String getMsgBelowFileName() {
		String msg = "";
		for (WebElementFacade msgUnderFileName : listOfMsgUnderFileNameField) {
			msg = msg.concat(" " + msgUnderFileName.getText());
		}
		String[] arrayOfSplittedMessage = msg.split("\n");
		msg = arrayOfSplittedMessage[0] + " " + arrayOfSplittedMessage[1];
		return msg.trim();
	}

	/**
	 * This method store the Color Value For Text in List 
	 * @return list of Color Value for Text
	 */
	public List<String> getListOfColourValForText() {
		List<String> listOfColourValForText = new ArrayList<>();
		for (WebElementFacade msgUnderFileName : listOfMsgUnderFileNameField) {
			listOfColourValForText.add(msgUnderFileName.getCssValue("color"));
		}
		return listOfColourValForText;
	}

	/**
	 * This method clicks on Cancel Button 
	 */
	public void clickOnCancelBtn() {
		cancelBtn.click();
	}

	/**
	 * This method clicks on ShowAllDocument CheckBox 
	 */
	public void clickOnShowAllDocumentCheckBox() {
		waitForAngularRequestsToFinish();
		evaluateJavascript("arguments[0].click();", showAllDocumentCheckBox);
	}

	/**
	 * This method fetch Success Message when Document is Uploaded
	 * @return successMessage Text Value 
	 */
	public String getDocumentUploadSuccessMsg() {
		return documentUploadSuccessMsg.getText();
	}

	/**
	 * This method fetch size of Uploaded Document Title
	 * @return size of Document Title 
	 */
	public int getUploadedDocTitleSize() {
		return listOfUploadedDocsTitle.size();
	}

	/**
	 * This method store the Uploaded Document Title in List
	 * @return list of Uploaded Document Title Values
	 */
	public List<String> getListOfUploadedDocTitle() {
		List<String> listOfUploadedDocTitle = new ArrayList<>();
		for (WebElementFacade element : listOfUploadedDocsTitle) {
			listOfUploadedDocTitle.add(element.getText());
		}
		return listOfUploadedDocTitle;
	}

	/**
	 * This method store the Uploaded FileName in List
	 * @return list of Uploaded FileName Values
	 */
	public List<String> getListOfUploadedFileName() {
		List<String> listOfOfUploadedFileName = new ArrayList<>();
		for (WebElementFacade uploadedDocFileName : listOfUploadedDocFileName) {
			listOfOfUploadedFileName.add(uploadedDocFileName.getText());
		}
		return listOfOfUploadedFileName;
	}

	/**
	 * This method clicks on Document Uploaded
	 */
	public void clickOnUploadedDocument(String document) {
		for (WebElementFacade uploadedDocTitle : listOfUploadedDocsTitle) {
			String[] array = uploadedDocTitle.getText().split(document);
			if (array.length != 0 && array[0].isEmpty()) {
				withAction().moveToElement(uploadedDocTitle).click().build().perform();
				break;
			}
		}
	}

	/**
	 * This method clicks on MRN CheckBox 
	 */
	public void clickOnMRNCheckBox() {
		mrnCheckBox.click();
	}

	/**
	 * This method checks whether the MRN CheckBox is selected or not
	 * @return selected status of checkbox 
	 */
	public boolean isMRNCheckBoxSelected() {
		return mrnCheckBox.isSelected();
	}

	/**
	 * This method fetch ShowAllDocument CheckBox Selected Status
	 * @return CheckBox Selected Status
	 */
	public String getShowAllDocumentCheckBoxSelectedStatus() {
		return evaluateJavascript("return document.querySelector('" + showAllDocumentID + "').checked").toString();
	}

	/**
	 * This method fetch NoDocumentUpload  Text Value
	 * @return Text Value  
	 */
	public String getNoDocumentUploadTxtValue() {
		return noDocumentUploadTxt.getText();
	}

	/**
	 * This method fetch Error Message on Upload
	 * @return error Message Text Value 
	 */
	public String getUploadErrorMsg() {
		String errorMsg = "";
		errorMsg = uploadErrorMsg.getText();
		String[] arrayVal = errorMsg.split("\n");
		errorMsg = arrayVal[0] + " " + arrayVal[1];
		return errorMsg;
	}
}