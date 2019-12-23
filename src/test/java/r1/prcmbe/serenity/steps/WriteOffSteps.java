package r1.prcmbe.serenity.steps;

import java.util.List;

import net.thucydides.core.annotations.Step;
import r1.prcmbe.pages.WorkflowConfigurationPage;

public class WriteOffSteps {

	WorkflowConfigurationPage workflowConfigPage;

	@Step
	public boolean verifyActionTypeStatus(String inputValue) {
		List<String> statusList = workflowConfigPage.getActionTypeStatusListText();
		boolean flag = false;
		int size = statusList.size();
		for (int index = 0; index < size; index++) {
			if (statusList.get(index).equals(inputValue)) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}

}
