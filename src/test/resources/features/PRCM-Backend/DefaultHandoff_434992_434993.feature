@DefaultHandoff
Feature: Verify default handoff

  Background: user is able to navigate to R1 Decision search page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hover on R1_Decision
    And user clicks on search sub menu

  @434992 @434993 @Sprint103 @PRCMUser
  Scenario Outline: Verify user is able to add newly added handoff type on R1 Decision screen
    Given user is on "R1 Hub Technologies 2.0 - 15 R1_Decision" page
    And user login to SQL server and connect to database
    And user runs the "Defaulthandoff_434990_SQL4" query for default handoff
    And user enters the SQL result in Visit Number Search textbox
    And user clicks on Submit button
    When user clicks on Handoff button
    And user selects <HandoffType> from Handoff Type dropdown
    And user selects any value from Create dropdown
    And user selects any value from Why dropdown
    And user selects any value from Disposition dropdown
    And user enters any "Test Note" in Notes text area
    And user clicks on Save button on the handoff popup
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
    When click on Show Account Action History Notes button
    Then user should be able to view the following columns in Account Action History
      | Type:        |
      | Action:      |
      | Disposition: |
      | Added:       |
      | Created:     |
      | Followup:    |
    And user should be able to view Handoff type value as newly added handoff type in Account Action History
    And user should be able to view Action with the correct selected data in Account Action History
    And user should be able to view Disposition with the correct selected data in Account Action History
    And user should be able to view Added column value as system current date in Account Action History: "getHandoffDetails"
    And user should be able to view Created column value as Logged in username and userid in Account Action History: "getDisplayName"
    And user should be able to view Followup column value as system current date in Account Action History: "getHandoffDetails"

    Examples: 
      | HandoffType        |
      | CBO/BSO - FollowUp |