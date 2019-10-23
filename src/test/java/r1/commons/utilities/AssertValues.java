package r1.commons.utilities;

import java.util.logging.Logger;
import org.junit.Assert;

public class AssertValues {

	private static final org.apache.logging.log4j.Logger LOGGER = (org.apache.logging.log4j.Logger) Logger.getLogger(AssertValues.class.getName());

	private AssertValues() {

	}

	public static void assertFail(Boolean condition, String message) {

		if (!condition) {
			LOGGER.info(message);
			Assert.fail(message);
		}
	}

	/**
	 * 
	 * @param valueName
	 * @param actualValue
	 * @param expectedValue
	 * 
	 *            a. Compares actual and expected values b. have provision to
	 *            provide which field is been verified for better analysis c.
	 *            automatically handles any of the value is null
	 * 
	 *            usage ex.: AssertValues.assertValues("patientId", 243112, 243112);
	 */
	public static void assertValues(String valueName, Object actualValue, Object expectedValue) {

		if (null != expectedValue) {

			Boolean condition = actualValue.equals(expectedValue);

			if (!condition) {
				LOGGER.info(getFailureMessage(valueName, actualValue, expectedValue));
				Assert.fail(getFailureMessage(valueName, actualValue, expectedValue));
			} else {
				LOGGER.info(getPassingMessage(valueName, actualValue, expectedValue));

			}
		} else {
			Assert.assertNull(getFailureMessage(valueName, actualValue, expectedValue));
			LOGGER.info(getFailureMessage(valueName, actualValue, expectedValue));

		}
	}

	private static String getFailureMessage(String valueName, Object actualValue, Object expectedValue) {
		return "FAIL: Actual " + valueName + ": '" + actualValue + "' doesnot match Expected " + valueName + ": '"
				+ expectedValue + "'";
	}

	private static String getPassingMessage(String valueName, Object actualValue, Object expectedValue) {
		return "PASS: Actual " + valueName + ": '" + actualValue + "' matches Expected " + valueName + ": '"
				+ expectedValue + "'";
	}
}
