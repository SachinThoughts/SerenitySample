package r1.prcmbe.steps.definitions;

public class BSODayNightFollowupStepDef {

	public void clickHandOffBtn() {
		addHandOffBtn.click();
	}
	
	public boolean isHandOffPopUpVisible() {
		return handOffPopUp.isVisible();
	}
	
	public void selectHandOfftype(String handOffType) {
		handOffTypeDrpDwn.selectByVisibleText(handOffType);
	}
	
	
	public String getSelectedHandOffType() {
		return handOffTypeDrpDwn.getSelectedVisibleTextValue();
	}
}
