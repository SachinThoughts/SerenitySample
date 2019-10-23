package r1.commons.utilities;

import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.*;
import net.thucydides.core.util.*;
import net.thucydides.core.webdriver.javascript.JavascriptExecutorFacade;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonMethods extends PageObject {
	PageObject page;

	public String getValFromjavaScriptQueryExecutor(String eleString) {
		try {
			JavascriptExecutorFacade executorFacade = new JavascriptExecutorFacade(getDriver());
			return executorFacade.executeScript("return document.querySelector('" + eleString + "').value").toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return eleString;
	}

	// Click on Ok or accept on Alter message box
	public void handleAlertsAccept() {
		page.getAlert().accept();
	}

	// Click on Cancel or dismiss on Alert message box
	public void handleAlertDismiss() {
		page.getAlert().dismiss();
	}

	/* Switch to the child window and return the parent window reference */
	public String handleMultipleWindows() {
		String mainWindow = getDriver().getWindowHandle();
		Set<String> handles = getDriver().getWindowHandles();
		for (String windowHandles : handles)
			if (!mainWindow.equals(windowHandles)) {
				getDriver().switchTo().window(windowHandles);
			}
		return mainWindow;
	}

	// Switch to frame using frame name
	public void frameSwitchUsingName(WebElementFacade frameName) {
		try {
			getDriver().switchTo().frame(frameName);

		} catch (NoSuchFrameException e) {
			System.out.println(e.getMessage());
		}
	}

	// Switch to frame using frameID
	public void frameUsingId(int frameId) {
		try {
			getDriver().switchTo().frame(frameId);
		} catch (NoSuchFrameException e) {
			System.out.println(e.getMessage());
		}
	}

	// Count the number of frames present on the page
	public int getTotalFrameCountInCurrentPage() {
		int ret = 0;
		By byFrameTag = (By) By.tagName("frame");
		List<WebElement> frameList = getDriver().findElements(byFrameTag);
		int frameSize = frameList.size();
		System.out.println("There are " + frameSize + " frame in current web page.");
		By byIFrameTag = (By) By.tagName("iframe");
		List<WebElement> iframeList = getDriver().findElements(byIFrameTag);
		int iframeSize = iframeList.size();
		System.out.println("There are " + iframeSize + " iframe in current web page.");
		ret = frameSize + iframeSize;
		return ret;
	}

	public static String loadProperties(String input) throws IOException {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		return variables.getProperty(input);
	}

	public void enterRandomValueInTextbox(WebElementFacade textbox, List<String> options) {
		int size = options.size();
		int index = getRandom(size);
		textbox.selectByIndex(index);
	}

	public void hoverOverElement(WebElementFacade element) {
		withAction().moveToElement(element).build().perform();
	}

	/*
	 * to fetch random values from list
	 */
	public static int getRandom(int all) {
		Random rnd = new Random();
		return rnd.nextInt(all);
	}

	public void waitForElement(WebElementFacade element) {
		waitFor(ExpectedConditions.elementToBeClickable(element));
	}

	public String loadQuery(String input, String filename) throws Exception {
		String queryValue = "";
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\QueryFile\\"
				+ "" + filename + "" + ".properties");
		prop.load(fis);
		queryValue = prop.getProperty(input.trim());
		return queryValue;
	}

	public String generateTickleDate(String curDate, int numberOfDays) throws ParseException {
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final Date date = format.parse(curDate);
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
		return format.format(calendar.getTime());
	}

	public String fastestRandomStringWithMixedCase(int length) {
		Random random = new Random();
		final int alphabetLength = 'D' - 'A' + 1;
		StringBuilder result = new StringBuilder(length);
		while (result.length() < length) {
			final char charCaseBit = (char) (random.nextInt(2) << 5);
			result.append((char) (charCaseBit | ('A' + random.nextInt(alphabetLength))));
		}
		return result.toString();
	}

	public String generateDateInMMDDYYYYFormat(String currentdate) throws ParseException {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = inputFormat.parse(currentdate);
		return outputFormat.format(date);
	}
}