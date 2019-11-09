package r1.prcmbe.serenity.steps;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.thucydides.core.annotations.Step;

public class WorkflowConfigurationSteps {
	DateFormat outputFormat, inputFormat;

	@Step
	public String formatDbDateFieldWithDateTime(String dateFromDB) throws ParseException {
		outputFormat = new SimpleDateFormat("MM/dd/yyyy");
		inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = inputFormat.parse(dateFromDB);
		return outputFormat.format(date);
	}
}
