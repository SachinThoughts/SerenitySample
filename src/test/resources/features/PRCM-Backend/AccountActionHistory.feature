@AccountActionHistory
Feature: Verify Account Action History in PRCM-BE

  Background: user is able to navigate to Account Page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu
    And user login to SQL server and connect to database
    And user runs the account action history query "Account_Action_History_391051_SQL1"
    And user fetch invoice number from database
    And user enters invoice number fetched from database
    And user clicks on Submit button

  @391052 @AHtoDecisionAdmin @Sprint103
  Scenario Outline: Verify that Account Action History should be display for Current Invoice Action when there is no any linked Invoice Associated
    Given user is on Account Information Page
    When user clicks on Handoff button
    And user selects <HandoffType> from Handoff Type dropdown
    And user selects any value from Create dropdown
    And user selects any value from Why dropdown
    And user selects any value from Disposition dropdown
    And user enters any "Test Note" in Notes text area
    And user clicks on Save button on the handoff popup
    Then user should be able to view the appropriate handoff success message: "Handoff Record Saved Successfully."
    Then user should be able to view H under event circle in blue color for newly added Handoff type on Horizontal timeline
    When user hovers the event circle for newly added Handoff type
    Then user should be able to view the following columns
      | Type:        |
      | Action:      |
      | Disposition: |
      | Added:       |
      | Created:     |
      | Followup:    |
    And user should be able to view Handoff type value as newly added handoff type
    And user should be able to view Action with the correct selected data
    And user should be able to view Disposition with the correct selected data
    And user should be able to view Added column value as system current date: "getHandoffDetails"
    And user should be able to view Created column value as Logged in username and userid: "getDisplayName"
    And user should be able to view Followup column value as system current date: "getHandoffDetails"
    When user moves the control on right side of the page and see the Defect Workflow section
    And user selects No radio button to Override Subcategory
    And user selects any value from DefectType dropdown and other Than "Uncategorized Defect" value
    And user selects any value from Defectsubcategory dropdown and other Than "Select Sub-Defect Category" value
    And user clicks on Save button
    When user runs the account action history query "Account_Action_History_391051_SQL1"
    And user fetch invoice number from database
    And user enters invoice number fetched from database
    And user clicks on Submit button
    Then user should be able to view the updated defect category in Defect Classification section
    When user clicks on back and forth arrows
    Then user should be able to view all defect subcategory previously associated with that invoice
    When user clicks on Next button
    And user selects any checkbox in Verify All Steps Taken Section
    And user clicks on Next button
    And user selects any checkbox in steps Taken Section
    And user clicks on Save button
    Then user should be able to view the Blue bubble code display as D on horizontal timeline
    When user hovers the activity bubbles
    Then user should be able to view all fields of that action
      | Type: | Action: | Added: | Created: | Followup: |
    When click on Show Account Action History Notes button
    Then user should be able to view the following columns in Account Action History
      | Type:        |
      | Action:      |
      | Disposition: |
      | Added:       |
      | Created:     |
      | Followup:    |

    Examples: 
      | HandoffType        |
      | CBO/BSO - FollowUp |
