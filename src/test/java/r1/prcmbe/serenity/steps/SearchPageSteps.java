package r1.prcmbe.serenity.steps;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.thucydides.core.annotations.Step;
import r1.commons.databaseconnection.DatabaseConn;
import r1.prcmbe.pages.SearchPage;

public class SearchPageSteps {

	SearchPage searchPage;
	ResultSetMetaData resultSetMetaData;

	@Step
	public boolean verifyInvoiceIDWithLikeOperator(String dbInvoiceId) {
		if (searchPage.isSearchAccTableVisible()) {
			for (String invoiceID : searchPage.getlistOfInvoiceID()) {
				if (!invoiceID.toLowerCase().contains(dbInvoiceId.toLowerCase())) {
					return false;
				}
			}
			searchPage.clickSearchInvoiceID();
		}
		return searchPage.isPatientAndVisitHeaderVisible()
				&& dbInvoiceId.contains(searchPage.getInvoiceID().toLowerCase());
	}

	@Step
	public List<String> fetchColumnNamesFromDatabaseResult() throws SQLException {
		List<String> dbColumnNames = new ArrayList<>();
		resultSetMetaData = DatabaseConn.resultSet.getMetaData();
		int columnCount = resultSetMetaData.getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			dbColumnNames.add(resultSetMetaData.getColumnLabel(i));
		}
		return dbColumnNames;
	}
}
