package r1.prcmbe.serenity.steps;

import java.util.List;

import net.thucydides.core.annotations.Step;
import r1.commons.utilities.CommonMethods;

public class ProfessionalUDCSteps {

	private String defectTypeName, defectSubCategoryName;

	@Step
	public String getDefectTypeValue(String value, List<String> defectTypeList) {
		int size = defectTypeList.size();
		defectTypeName = value.concat("_" + CommonMethods.getRandom(size));
		while (defectTypeList.contains(defectTypeName)) {
			defectTypeName = value.concat("_" + CommonMethods.getRandom(size));
		}
		return defectTypeName;
	}

	@Step
	public String getDefectSubTypeValue(String value, List<String> defectSubCategoryList) {
		int size = defectSubCategoryList.size();
		if (size == 0) {
			defectSubCategoryName = value.concat("_01");
			return defectSubCategoryName;
		} else {
			defectSubCategoryName = value.concat("_" + CommonMethods.getRandom(size));
			while (defectSubCategoryList.contains(defectSubCategoryName)) {
				defectSubCategoryName = value.concat("_" + CommonMethods.getRandom(20));
			}
		}
		return defectSubCategoryName;
	}
}
