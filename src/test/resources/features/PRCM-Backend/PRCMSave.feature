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
    And user runs the "Account_Action_History_391051_SQL1" query to fetch Invoice Number
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