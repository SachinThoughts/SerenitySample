@ProfessionalUDC
Feature: Verify ProfessionalUDC related scenarios in PRCM
  This is to verify ProfessionalUDC related scenarios in PRCM application

  Background: user is able to navigate to universal defect configuration screen
    Given user is on R1 Hub page
    When user clicks on setting link
    And user clicks on Settings-R1_Decision
    And user clicks on UDC Admin configuration

  @391392 @PRCMUser @Sprint8
  Scenario: Verify that user having admin role is able to view UDC screen for Technical as well as Professional separately
    Given user is on UDC Admin configuration page
    When user clicks on setting link
    And user clicks on Settings-R1_Decision
    And user clicks on facility group configuration screen
    Then user should be able to view one prcm enable facility group
    When user clicks on setting link
    And user clicks on Settings-R1_Decision
    And user clicks on UDC Admin configuration
    Then user should able to view both application Tabs for technical as well as professional
    When user clicks on R1Decision Config Tab
    Then user should be able to view populated screen to configure at Technical account level
    When user clicks on PRCM R1Decision Config
    Then user should be able to view populated screen to configure at professional account invoice

  @391393 @PRCMUser @Sprint8
  Scenario Outline: Verify that user should able to update UDC at Professional level
    Given user is on UDC Admin configuration page
    Then user having prcm enable facility should be able to view PRCM R1Decision Config as pre-selected
    And user clicks on PRCM R1Decision Config
    Then user should be able to open that application name
    When user clicks on Add defect type button
    Then user should able to view popup window for new defect
    When user fills all the fields <defectTypeName> and clicks active checkbox
    And user clicks on Add Defect Type button on modal popup
    Then user should be able to view success message "Defect Type Added Successfully."
    And user should be able to view newly added defect at professional level
    When user select the radio button against any defect type
    And user clicks on Continue button on defect type page
    And user clicks on add defect subcategory
    And user should be able to add any new <defectSubCategoryName> defectsubcategory and clicks active checkbox
    And user clicks on Add Defect Sub Category button on modal popup
    When user is able to login to sql server and connect to database
    And user runs the "UDC_level_391393_SQL1" query to fetch applicationid
    Then user should be able to view that for all defects which belongs to professional applicationid should be 3

    Examples: 
      | defectTypeName | defectSubCategoryName       |
      | Automation     | AutomationDefectSubcategory |

  @391394 @PRCMUser @Sprint102
  Scenario Outline: Verify that user should able to update UDC at technical  level
    Given user is on UDC Admin configuration page
    When user clicks on R1Decision Config Tab
    Then user should able to open that application name
    And user clicks on Add defect type button
    Then user should able to view popup window for new defect
    And user fills all the fields <defectTypeName> and clicks active checkbox
    And user clicks on Add Defect Type button on modal popup
    Then user should be able to view success message "Defect Type Added Successfully."
    And user should be able to view Add Defect Type pop-up disappear
    And user should be able to view newly added defect at technical level
    When user is able to login to sql server and connect to database
    And user runs the "UDC_level_391394_SQL2" query to fetch applicationid
    Then user should be able to view that for all defects which belongs to technical applicationid should be 1
    When user select the radio button against any defect type
    And user clicks on Continue button on defect type page
    And user clicks on add defect subcategory
    And user should be able to add any new <defectSubCategoryName> defectsubcategory and clicks active checkbox
    And user clicks on Add Defect Sub Category button on modal popup
    Then user should be able to view message as "Defect SubCategory Added Successfully"
    And user should be able to view Add Defect Sub Category pop-up disappear
    When user clicks on Defect Type tab
    And user clicks on edit button on any existing defect
    Then user should be able to view Edit Defect Type modal popup
    When user edits with a valid <defectTypeName> in defect type name
    And user check or uncheck Active checkbox
    And user clicks on Save Defect Type button
    Then user should be able to view message as "Defect Type Updated Successfully"

    Examples: 
      | defectTypeName | defectSubCategoryName       |
      | Automation     | AutomationDefectSubcategory |

  @391395 @NonPRCMUser @Sprint8
  Scenario: Verify that for Professional Config UDC user should have atleast one Facility that have PRCM access
    Given user is on UDC Admin configuration page
    Then user should be able to view R1 Decision UDC screen for technical only
    When user logout from the application
    And user login with the user "prcmBeUsername" who have atleast one facility which is prcm enable
    And user clicks on Login button
    When user clicks on setting link
    And user clicks on Settings-R1_Decision
    And user clicks on UDC Admin configuration
    Then user should able to view both application Tabs for technical as well as professional

  @434958 @PRCMUser @Sprint8
  Scenario: Verify that user is able to add SOP Actions
    Given user having AHtoDecision Admin role is on Universal Defect Configuration  page
    When user select the radio button against any defect type
    And user clicks on the Continue button on defect type page
    And user select the radio button corresponding to a defect subcategory
    And user clicks on the Continue button on defect sub category page
    Then user should be able to view the selected Defect Type Defect SubCategory and default SOP Action in breadcrumb
    And user should be able to view Choose a SOP Action grid
    When user clicks on the Continue button on SOP type page
    And user clicks on the Add New SOP Actions button on SOP Actions screen
    And user enters all the mandatory fields
      | TestActionName        |
      | TestActionDescription |
      | AHtoDecision          |
      |                     2 |
      |                     2 |
      | Complete              |
    And user clicks on Save Changes SOP Actions button
    Then user should be able to view message as "SOP Action Saved Successfully."
    And Add SOP Action pop-up should disappear
    And user should be able to view added SOP Action on SOP Actions screen

  @434931 @Sprint102 @PRCMUser
  Scenario Outline: Verify the functionality of +Add New SOP button in SOP Type grid page and same can be viewed on R1D screen
    Given user having AHtoDecision Admin role is on Universal Defect Configuration  page
    When user select the radio button against any defect type
    And user clicks on the Continue button on defect type page
    And user select the radio button corresponding to a defect subcategory
    And user clicks on the Continue button on defect sub category page
    Then user should be able to view the selected Defect Type, Defect Sub Category and default SOP Type in breadcrumb
    And user should be able to view Choose a SOP Action grid
    When user clicks on +Add New SOP button
    Then user should be able to view Add SOP pop-up
    When user clicks Save Changes button without entering any field
    Then user should be able to view "Please enter SOP Name" validation message
    When user enters SOP name field with any valid value
    And user clicks on Save Changes button on Add SOP popup
    Then user should be able to view "Please enter SOP Description" validation message
    When user enters valid SOP name and SOP description
    And user check or uncheck Active SOP checkbox
    And user clicks on Save Changes button on Add SOP popup
    Then user should be able to view success of added SOP "Defect SOP Type Added Successfully." message
    And user should be able to view Add SOP pop-up should disappear
    And user should be able to view newly added SOP displayed in Choose a SOP grid
    When user clicks on billing & follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu
    And user selects "Invoice Number" from search by dropdown
    And user selects "Like" operator from operator dropdown
    And user enters any <invoice> number
    And user clicks on submit button
    Then user should be able to view account with matching patient detail or patient record page directly if there is only one Search result
    When user selects radio button as No in Do you agree with defect? section
    Then user should be able to view following field displayed
      | Defect Type | Defect Sub category | Save |
    When user selects any value from DefectType dropdown and other Than "Uncategorized Defect" value
    And user selects any value from Defectsubcategory dropdown and other Than "Select Sub-Defect Category" value
    And user clicks on Save button in defect workflow section
    Then user should be able to view changed sub category below current defect under Defect workflow section
    When user selects radio button as Yes in Do you agree with defect? section
    And user clicks on Next button
    When user should be able to select the SOP Type added through UDC section from Verify All Steps Taken section
    When user clicks on Next button on verify all steps screen
    When user should be able to select the SOP Action added through UDC section from Steps Taken section

    Examples: 
      | invoice |
      | 7012345 |

  @439344 @Sprint101 @PRCMUser
  Scenario Outline: Verify the functionality of +Add New SOP button in SOP Type grid page
    Given user having AHtoDecision Admin role is on Universal Defect Configuration  page
    When user select the radio button against any defect type
    And user clicks on the Continue button on defect type page
    And user select the radio button corresponding to a defect subcategory
    And user clicks on the Continue button on defect sub category page
    Then user should be able to view the selected Defect Type Defect SubCategory and default SOP Action in breadcrumb
    And user should be able to view Choose a SOP Action grid
    And user clicks on +Add New SOP button
    Then user should be able to view Add SOP pop-up
    And user clicks on Save Changes button on Add SOP popup
    Then user should be able to view validation message on the Action popup "Please enter SOP Name"
    When user enters valid SOP name
    And user clicks on Save Changes button on Add SOP popup
    Then user should be able to view validation message on the Action popup "Please enter SOP Description"
    When user enters incorrect Numeric or special character data in SOP Name <Incorrect Value>
    And user clicks on Save Changes button on Add SOP popup
    Then user should be able to view validation message on the Action popup "Incorrect data for SOP Name"
    When user enters incorrect Numeric or special character data in SOP Description <Incorrect Value>
    And user enters valid SOP name
    And user clicks on Save Changes button on Add SOP popup
    Then user should be able to view validation message on the Action popup "Incorrect data for SOP Description"

    Examples: 
      | Incorrect Value |
      | !@$%12345       |
