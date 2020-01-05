package r1.prcmbe.serenity.steps;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibm.icu.text.SimpleDateFormat;

import cucumber.api.DataTable;
import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.AccountActionHistoryPage;
import r1.prcmbe.pages.AccountInformationPage;
import r1.prcmbe.pages.NavigationPage;

public class BSODayNightHandoffSteps {

	AccountInformationPage acctInformationPage;
	AccountActionHistoryPage acntActionHistoryPage;
	NavigationPage navigationPage;

	/**
	 * This method fetches the Current Date
	 * @return current Date
	 */
	@Step
	public String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy");
		Date date = new Date();
		return formatter.format(date);
	}

	/**
	 * This method verifies multiple attribute on HandOff PopUp
	 * @param dataTable
	 * @return list of Object 
	 */
	@Step
	public List<Object> verifyMultipleAttributeOnHandoffPopup(DataTable dataTable) {
		List<Object> listOfVal = new ArrayList<>();
		List<String> listOfExpctdVal = dataTable.asList(String.class);

		int count = 0;
		for (String attribute : listOfExpctdVal) {
			switch (attribute) {
			case "Hand Off Type":
				if (acctInformationPage.isHandoffTypeLabelVisible()) {
					count = count + 1;
				} else {
					listOfVal.add(attribute);
				}
				break;
			case "Create":
				if (acctInformationPage.isCreateLabelVisible()) {
					count = count + 1;
				} else {
					listOfVal.add(attribute);
				}
				break;
			case "Note":
				if (acctInformationPage.isNoteLabelVisible()) {
					count = count + 1;
				} else {
					listOfVal.add(attribute);
				}
				break;
			case "Close":
				if (acctInformationPage.isCloseBtnOnHandoffPopupVisible()) {
					count = count + 1;
				} else {
					listOfVal.add(attribute);
				}
				break;
			case "Save":
				if (acctInformationPage.isSaveBtnOnHandoffPopupVisible()) {
					count = count + 1;
				} else {
					listOfVal.add(attribute);
				}
				break;
			case "Save and Move to Next Account":
				if (acctInformationPage.isSaveAndMoveToNxtAccntBtnOnHandoffPopupVisible()) {
					count = count + 1;
				} else {
					listOfVal.add(attribute);
				}
				break;
			}
		}
		if (count == listOfExpctdVal.size()) {
			listOfVal.add(true);
		} else {
			listOfVal.add(false);
		}

		return listOfVal;
	}

	/**
	 * This method verifies Recently Added HandOff Saved Value
	 * @param dataTable
	 * @param handoffType
	 * @param actionValue
	 * @param dispositionValue
	 * @return list of Object
	 */
	@Step
	public List<Object> verifyRecentlyHandoffSavedValue(DataTable dataTable, String handoffType, String actionValue,
			String dispositionValue) {
		List<Object> listOfVal = new ArrayList<>();
		List<String> listOfExpctdVal = dataTable.asList(String.class);

		int count = 0;
		for (String attribute : listOfExpctdVal) {
			switch (attribute) {
			case "Type:":
				if (acntActionHistoryPage
						.getRecentAddedAccountActionHistoryValue(getPositionOfLabelBasedOnAttribute(attribute))
						.equals(handoffType)) {
					count = count + 1;
				} else {
					listOfVal.add(attribute);
				}
				break;
			case "Action:":
				if (acntActionHistoryPage
						.getRecentAddedAccountActionHistoryValue(getPositionOfLabelBasedOnAttribute(attribute))
						.equals(actionValue)) {
					count = count + 1;
				} else {
					listOfVal.add(attribute);
				}
				break;
			case "Disposition:":
				if (acntActionHistoryPage
						.getRecentAddedAccountActionHistoryValue(getPositionOfLabelBasedOnAttribute(attribute))
						.equals(dispositionValue)) {
					count = count + 1;
				} else {
					listOfVal.add(attribute);
				}
				break;
			case "Added:":
				if (acntActionHistoryPage
						.isRecentAddedAccountActionHistoryLabelVisible(getPositionOfLabelBasedOnAttribute(attribute))) {
					count = count + 1;
				} else {
					listOfVal.add(attribute);
				}
				break;
			case "Created:":
				if (acntActionHistoryPage
						.isRecentAddedAccountActionHistoryLabelVisible(getPositionOfLabelBasedOnAttribute(attribute))) {
					count = count + 1;
				} else {
					listOfVal.add(attribute);
				}
				break;
			case "Followup:":
				if (acntActionHistoryPage
						.isRecentAddedAccountActionHistoryLabelVisible(getPositionOfLabelBasedOnAttribute(attribute))) {
					count = count + 1;
				} else {
					listOfVal.add(attribute);
				}
				break;
			}
		}
		if (count == listOfExpctdVal.size()) {
			listOfVal.add(true);
		} else {
			listOfVal.add(false);
		}

		return listOfVal;
	}

	/**
	 * This method fetches Position Of Label Based On Attribute
	 * @param attribute
	 * @return position
	 */
	@Step
	public int getPositionOfLabelBasedOnAttribute(String attribute) {
		int position = 0;
		for (int i = 0; i < acntActionHistoryPage.getListOfRecentAddedAccountActionHistoryLabel().size(); i++) {
			if (acntActionHistoryPage.getListOfRecentAddedAccountActionHistoryLabel().get(i).equals(attribute)) {
				position = i;
				break;
			}
		}
		return position;
	}

	/**
	 * This method verifies whether System User is Mapped with Created User
	 * @return boolean value based on verification
	 */
	@Step
	public boolean verifySystemUserMappedWithCreatedUser() {
		String userNameFromAcctnAcnt = acntActionHistoryPage.getRecentAddedAccountActionHistoryValue(4);
		String[] arrayOfUserName=navigationPage.getUserLoginName().split(" ");
		return userNameFromAcctnAcnt.contains(arrayOfUserName[1].toLowerCase());
	}
}