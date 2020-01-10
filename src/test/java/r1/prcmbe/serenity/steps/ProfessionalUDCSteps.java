package r1.prcmbe.serenity.steps;

import java.util.List;

import net.thucydides.core.annotations.Step;
import r1.commons.utilities.CommonMethods;

public class ProfessionalUDCSteps {

	private String defectTypeName, defectSubCategoryName;

	/**
	 * This method generates unique defect type name
	 * 
	 * @param value          This parameter contains defect type name
	 * @param defectTypeList This parameter contains list of defect type names
	 * @return String This returns defect type name
	 */
	@Step
	public String getDefectTypeValue(String value, List<String> defectTypeList) {
		int size = defectTypeList.size();
		defectTypeName = value.concat("_" + CommonMethods.getRandom(size));
		while (defectTypeList.contains(defectTypeName)) {
			defectTypeName = value.concat("_" + CommonMethods.getRandom(size));
		}
		return defectTypeName;
	}

	/**
	 * This method generates unique defect sub category name
	 * 
	 * @param value                 This parameter contains defect sub category name
	 * @param defectSubCategoryList This parameter contains list of defect sub
	 *                              category names
	 * @return String This returns defect sub category name
	 */
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
