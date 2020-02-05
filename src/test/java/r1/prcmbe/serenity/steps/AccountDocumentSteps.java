package r1.prcmbe.serenity.steps;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.AccountDocumentPage;

public class AccountDocumentSteps extends PageObject{

	AccountDocumentPage decisionAccntDocumentPage;
	CommonMethods commonMethods;

	private final String MAROONCOLORRBGACODE = "rgba(204, 51, 0, 1)";
	List<String> listOfVisitNo;

	static String dbFileName = "AccountDocument";

	/**
	 * This method verify the ColourCode For Text Message 
	 * @return boolean value based on verification
	 */
	@Step
	public boolean verifyColourCodeForTextMsg() {
		List<String> listOfColourVal;
		listOfColourVal = decisionAccntDocumentPage.getListOfColourValForText();
		for (String value : listOfColourVal) {
			if (!value.equals(MAROONCOLORRBGACODE)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method verify the UploadedDocument Title
	 * @param documentTitle for verification
	 * @return boolean value based on verification
	 */
	@Step
	public boolean verifyUploadedDocsTitle(String documentTitle) {
		String actualTitle;
		int size = decisionAccntDocumentPage.getUploadedDocTitleSize();
		for (int i = 0; i < size; i++) {
			actualTitle = decisionAccntDocumentPage.getListOfUploadedDocTitle().get(i).substring(0,
					decisionAccntDocumentPage.getListOfUploadedDocTitle().get(i).length()
							- decisionAccntDocumentPage.getListOfUploadedFileName().get(i).length());
			if (actualTitle.equals(documentTitle)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method checks whether the Document is downloaded On System
	 * @param Takes two parameter : fileName and timeout
	 * @return boolean value based on presence of file
	 */
	@Step
	public boolean isDocumentDownloadedOnSystem(String fileName, int timeOut){
		String userDir = System.getProperty("user.home")+"\\Downloads";
		File folder = new File(userDir);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < timeOut; i++) {
			for (File file : listOfFiles) {
				if (file.getName().contains(fileName)) {
					return true;
				}
			}
			waitABit(500);
			listOfFiles = folder.listFiles();
		}
		return false;
	}

	/**
	 * This method delete the file from the System
	 * @param Takes fileName as parameter to delete
	 */
	public void deleteFileFromSystem(String fileName) {
		String userDir = System.getProperty("user.home")+"\\Downloads";
		File folder = new File(userDir);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.getName().contains(fileName)) {
				file.delete();
			}
		}
	}

	/**
	 * This method store the database value in List
	 * @param Takes two parameter :queryVal and columnName
	 * @return List of Value from Database
	 */
	@Step("Retrieves List Of value from database for given query")
	public List<String> fetchListOfValFromDb(String queryVal, String columnName)
			throws ClassNotFoundException, SQLException {
		List<String> listOfDbVal = new ArrayList<>();
		DatabaseConn.serverConn(DatabaseConn.serverName, DatabaseConn.databaseName, queryVal);
		while (DatabaseConn.resultSet.next()) {
			listOfDbVal.add(DatabaseConn.resultSet.getString(columnName));
		}
		return listOfDbVal;
	}
}