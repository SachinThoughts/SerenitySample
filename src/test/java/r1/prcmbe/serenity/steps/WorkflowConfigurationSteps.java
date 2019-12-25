package r1.prcmbe.serenity.steps;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import r1.commons.utilities.CommonMethods;
import r1.prcmbe.pages.AccountInformationPage;
import r1.prcmbe.pages.LoginPage;
import r1.prcmbe.pages.NavigationPage;
import r1.prcmbe.pages.SettingsPage;

public class WorkflowConfigurationSteps {
	DateFormat outputFormat, inputFormat;
	LoginPage userLoginPage;
	NavigationPage navigationPage;
	SettingsPage settingsPage;
	AccountInformationPage accInfoPage;

	@Steps
	LoginSteps loginStep;

	/**
	 * 
	 * @param dateFromDB
	 *            takes the paramter from the DB
	 * @return the formatted date required
	 * @throws ParseException
	 */
	@Step
	public String formatDbDateFieldWithDateTime(String dateFromDB) throws ParseException {
		outputFormat = new SimpleDateFormat("MM/dd/yyyy");
		inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = inputFormat.parse(dateFromDB);
		return outputFormat.format(date);
	}

	/**
	 * Method clears the cache and relogins again
	 * 
	 * @throws IOException
	 */

	@Step
	public void clearCacheAndLogin() throws IOException {
		navigationPage.clickSettings();
		settingsPage.hoverITToolsLink();
		settingsPage.clickCacheLink();
		settingsPage.clickClearAllBtn();
		accInfoPage.logOut();
		String accountuser = CommonMethods.loadProperties("prcmBeUsername");
		String passwd = CommonMethods.loadProperties("prcmBePassword");
		loginStep.userEntersCredentials(accountuser, passwd);
		userLoginPage.loginBtnClick();
		if (userLoginPage.isProceedLinkVisible()) {
			userLoginPage.clickOnProceedFurther();
		}
	}
}