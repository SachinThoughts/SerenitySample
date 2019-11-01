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

	@FindBy(xpath = "//a[@href='#accountDocs']//h4")
	private WebElementFacade documentLink;

	@FindBy(id = "ddlIDMDocType")
	private WebElementFacade documentTypeDrpDown;

	@FindBy(id = "txtDocumentTitle")
	private WebElementFacade documentTitle;

	@FindBy(id = "fUploadDocument")
	private WebElementFacade chooseFileBtn;

	@FindBy(xpath = "//*[@id='accountDocs']//h3[@class='panel-title']")
	private WebElementFacade accntDocumentPanel;

	@FindBy(xpath = "//input[@id='btnUpload']")
	private WebElementFacade uploadDocumentBtn;

	@FindBy(xpath = "//span[@class='help-block text-danger']")
	private List<WebElementFacade> listOfMsgUnderFileNameField;

	@FindBy(xpath = "//*[@id='accountDocs']/div[1]/h3")
	private WebElementFacade accDocHeader;

	@FindBy(xpath = "//input[@value='Cancel']")
	private WebElementFacade cancelBtn;

	@FindBy(xpath = "//label[@for='chkShowAllDoc']")
	private WebElementFacade showAllDocumentCheckBox;

	@FindBy(xpath = "//div[@class='alert alert-success']//div[@id='msg_success']")
	private WebElementFacade documentUploadSuccessMsg;

	@FindBy(xpath = "//*[@id='docLibrary']/li//following-sibling::div[2]//a/h4")
	private List<WebElementFacade> listOfUploadedDocsTitle;

	@FindBy(xpath = "//*[@id='docLibrary']/li//following-sibling::div[2]//a/h4/small")
	private List<WebElementFacade> listOfUploadedDocFileName;

	@FindBy(id = "chkMRN")
	private WebElementFacade mrnCheckBox;

	@FindBy(id = "docLibrary")
	private WebElementFacade noDocumentUploadTxt;

	@FindBy(xpath = "//div[@id='DocresultDiv']//strong/ancestor::div[@id='DocresultDiv']//span")
	private WebElementFacade uploadErrorMsg;

	public void clickOnDocumentLink() {
		documentLink.click();
	}

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

	public String getSelectedDocumentTypeVal() {
		return documentTypeDrpDown.getSelectedVisibleTextValue();
	}

	public List<String> getDocumentDropDwnValues() {
		List<String> listOfDocumentDropDwnValues;
		listOfDocumentDropDwnValues = documentTypeDrpDown.getSelectOptions();
		return listOfDocumentDropDwnValues;
	}

	public void enterTextDocTitle(String text) {
		documentTitle.type(text);
	}

	public String getDocumentTitleVal() {
		return evaluateJavascript("return document.querySelector('" + documentTitleID + "').value").toString();
	}

	public void clickOnChooseFileBtn() {
		chooseFileBtn.click();
	}

	public String getFileNameVal() {
		return evaluateJavascript("return document.querySelector('" + chooseFileOptionID + "').value").toString();
	}

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

	public boolean isAccountDocumentPanelVisible() {
		return accntDocumentPanel.isVisible();
	}

	public void uploadFile(String fileName) {
		FileToUpload fileToUpload = new FileToUpload(getDriver(),
				System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\DocsUpload\\" + fileName);
		fileToUpload.fromLocalMachine().to(chooseFileBtn);
	}

	public void clickOnUploadFileBtn() {
		evaluateJavascript("arguments[0].click();",uploadDocumentBtn);
		//uploadDocumentBtn.click();
	}

	public String getMsgBelowFileName() {
		String msg = "";
		for (WebElementFacade element : listOfMsgUnderFileNameField) {
			msg = msg.concat(" " + element.getText());
		}
		String[] arrayOfSplittedMessage = msg.split("\n");
		msg = arrayOfSplittedMessage[0] + " " + arrayOfSplittedMessage[1];
		return msg.trim();
	}

	public List<String> getListOfColourValForText() {
		List<String> listOfColourValForText = new ArrayList<>();
		for (WebElementFacade element : listOfMsgUnderFileNameField) {
			listOfColourValForText.add(element.getCssValue("color"));
		}
		return listOfColourValForText;
	}

	public boolean isAccountHeaderVisible() {
		return accDocHeader.isVisible();
	}

	public void clickOnCancelBtn() {
		cancelBtn.click();
	}

	public void clickOnShowAllDocumentCheckBox() {
		waitForAngularRequestsToFinish();
		evaluateJavascript("arguments[0].click();", showAllDocumentCheckBox);
	}

	public String getDocumentUploadSuccessMsg() {
		return documentUploadSuccessMsg.getText();
	}

	public int getUploadedDocTitleSize() {
		return listOfUploadedDocsTitle.size();
	}

	public List<String> getListOfUploadedDocTitle() {
		List<String> listOfUploadedDocTitle = new ArrayList<>();
		for (WebElementFacade element : listOfUploadedDocsTitle) {
			listOfUploadedDocTitle.add(element.getText());
		}
		return listOfUploadedDocTitle;
	}

	public List<String> getListOfUploadedFileName() {
		List<String> listOfOfUploadedFileName = new ArrayList<>();
		for (WebElementFacade element : listOfUploadedDocFileName) {
			listOfOfUploadedFileName.add(element.getText());
		}
		return listOfOfUploadedFileName;
	}

	public void clickOnUploadedDocument(String document) {
		for (WebElementFacade element : listOfUploadedDocsTitle) {
			String[] array = element.getText().split(document);
			if (array.length != 0 && array[0].isEmpty()) {
				withAction().moveToElement(element).click().build().perform();
				break;
			}
		}
	}

	public void clickOnMRNCheckBox() {
		mrnCheckBox.click();
	}

	public boolean isMRNCheckBoxSelected() {
		return mrnCheckBox.isSelected();
	}

	public String getShowAllDocumentCheckBoxSelectedStatus() {
		return evaluateJavascript("return document.querySelector('" + showAllDocumentID + "').checked").toString();
	}

	public String getNoDocumentUploadTxtValue() {
		return noDocumentUploadTxt.getText();
	}

	public String getUploadErrorMsg() {
		String errorMsg = "";
		errorMsg = uploadErrorMsg.getText();
		String[] arrayVal = errorMsg.split("\n");
		errorMsg = arrayVal[0] + " " + arrayVal[1];
		return errorMsg;
	}
}