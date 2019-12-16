@PRCMSave
Feature: Verify PRCM Save related test cases in PRCM_BE

  Background: user is able to navigate to on Account Page
    Given user is on R1 Hub page
    And user is able to login to sql server and connect to database
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu
    And user select "Invoice Number" from Search By dropdown
    And user selects "=" operator from operator dropdown
    And user runs the query "Account_Action_History_391051_SQL1" to fetch "Invoice Number"
    And user enters Invoice Number fetched from database in invoice number textbox
    And user clicks on Submit button

  @391058 @Sprint103 @PRCMUser
  Scenario: Verify Verify All Steps taken section
    Given user is on Account Page
    When user clicks on Next button under Defect workflow section
    Then user should be able to view "Verify All Steps Taken" section open and placeholder Triage
    And user should be able to view below fields
      | Previous | Next |
    When user clicks on Previous button
    Then user should be able to view navigation to Override Defect Category section
    When user clicks on Next button
    Then user should be able to view the "Verify All Steps Taken" section
    When user clicks on Next button after selecting any step
    Then user should be able to view "Step(s) Taken" section
    And user should be able to view the bar move to Action

  @391059 @Sprint103 @PRCMUser
  Scenario: Verify A2D save functionality
    Given user is on Account Page
    When user clicks on Next button under Defect workflow section
    Then user should be able to view "Verify All Steps Taken" section open and placeholder Triage
    And user should be able to view below fields
      | Previous | Next |
    When user clicks on Previous button
    Then user should be able to view navigation to Override Defect Category section
    When user clicks on Next button
    Then user should be able to view the "Verify All Steps Taken" section
    When user clicks on Next button after selecting any step
    And user selects checkbox from Steps Taken checkboxes
    And user clicks on A2D Save button
    Then user should be able to view the message "Saved successfully"

  @391060 @Sprint103 @PRCMUser
  Scenario: Verify Account Action History section
    Given user is on Account Page
    When user moves to the Account Action History section
    When user hovers the event circle for newly added Handoff type
    Then user should be able to view the following columns
      | Type:     |
      | Action:   |
      | Added:    |
      | Created:  |
      | Followup: |

  @391061 @Sprint103 @PRCMUser
  Scenario: Verify the entry in DB tables after taking AHtoDecision Save action
    Given user is on Account Page
    When user selects No radio button to Override Subcategory
    And user selects any value from DefectType dropdown and other Than "Uncategorized Defect" value
    And user selects any value from Defectsubcategory dropdown and other Than "Select Sub-Defect Category" value
    And user clicks on Save button
    And user refreshes a page
    And user clicks on Next button
    And user clicks on Next button after selecting any step
    And user selects checkbox from Steps Taken checkboxes
    And user clicks on A2D Save button
    Then user should be able to view the message "Saved successfully"
    When user is able to login to sql server and connect to database
    And user runs the query "PRCM_Save_391061_SQL21" to fetch "Invoice Id"
    And user runs the query "PRCM_Save_391061_SQL8" to fetch "DefectAccountKey"
    And user runs the query "PRCM_Save_391061_SQL6" to fetch "Result Set"
    Then user should be able to view the entries in these tables
    When user runs the query "PRCM_Save_391061_SQL7" to fetch "Result Set"
    Then user should be able to view the entries in these tables
    When user runs the query "PRCM_Save_391061_SQL9" to fetch "Result Set For Created User"
    Then user should be able to view the entries in these tables
    When user runs the query "PRCM_Save_391061_SQL10" to fetch "DefectTypeAttributeId and AttributeVal"
    Then user should be able to view the defect Attributetypeid is 24 and attributevalue is URL

  @391215 @Sprint103 @PRCMUser
  Scenario: Verify the SOPs for Professional Denials
    Given user is on Account Page
    When user login to SQL server and connect to facility database
    And user runs the query "PRCM_Save_391019_SQL2" for fetching DefectTypeId
    And user fetch Defect typeid
    And user runs the query "PRCM_Save_391019_SQL3" with passing by defecttypeid
    And user fetch Defectsubcategoryid
    And user runs the query "PRCM_Save_391215_SQL18" to fetch "SOP List"
    Then user should be able to view the SOP list for the passed defect sub category
    When user checks the SOP steps from DB in UI
    Then user should be able to view same SOPs Step from DB in UI