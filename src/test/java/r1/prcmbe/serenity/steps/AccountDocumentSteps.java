package r1.prcmbe.serenity.steps;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import r1.commons.databaseconnection.DatabaseConn;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.AccountDocumentPage;

public class AccountDocumentSteps {

	AccountDocumentPage decisionAccntDocumentPage;
	CommonMethods commonMethods;

	private final String MAROONCOLORRBGACODE = "rgba(204, 51, 0, 1)";
	List<String> listOfVisitNo;

	static String dbFileName = "AccountDocument";

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

	@Step
	public boolean isDocumentDownloadedOnSystem(String fileName, int timeOut) throws InterruptedException {
		String userName = System.getProperty("user.name");
		File folder = new File("\\\\R1-UEM-1.accretivehealth.local\\redirection$\\" + userName + "\\Downloads");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < timeOut; i++) {
			for (File file : listOfFiles) {
				if (file.getName().contains(fileName)) {
					return true;
				}
			}
			Thread.sleep(500);
			listOfFiles = folder.listFiles();
		}
		return false;
	}

	public void deleteFileFromSystem(String fileName) {
		String userName = System.getProperty("user.name");
		File folder = new File("\\\\R1-UEM-1.accretivehealth.local\\redirection$\\" + userName + "\\Downloads");
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.getName().contains(fileName)) {
				file.delete();
			}
		}
	}

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