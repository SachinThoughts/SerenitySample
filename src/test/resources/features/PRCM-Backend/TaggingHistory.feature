@TaggingHistory
Feature: Verify Tagging related scenarios in PRCM-BE

  Background: User is able to navigate to R1 Decision search page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu

  @419683 @Sprint103 @PRCMUser
  Scenario Outline: Verify that  user is able to add tag and tag history is  displayed in Tag History section.
    Given user is on R1 Decision search page
    When user login to SQL server and connect to database
    And user run the query and fetch the Invoice Number to add tag"Tagging_419683_SQL4"
    And user select "Invoice Number" from Search By dropdown
    And user enters the query result in Invoice Number search textbox to add tag on account
    Then user is on "R1 Hub Technologies 2.0 - 15 R1_Decision" page
    When User Clicks on Add Tag link
    And User selects any value from Tag Category dropdown
    And User selects any value from Tag Name dropdown
    And User enters some text "test" in Notes textbox
    And User clicks on Save Changes button
    Then User should be able to view message "Account Tag saved successfully."
    And User should be able to view Tag Name under the title Tag
    When User scrolls till Tag History section
    Then user should be able to view following grid columns under tag history section
      | Tag Category |
      | Tag Name     |
      | Added Date   |
      | Added By     |
      | Removed Date |
      | Removed By   |
    And user should be able to view added Tag Details in the grid
    When user login to SQL server and connect to facility database
    And user run the query to fetch newly added tag in histroy section <query3> query
    Then User should be able to view the newly added Tag in history section in SQL result.

    Examples: 
      | query3              |
      | Tagging_419683_SQL1 |

  @419684  @Sprint103 @PRCMUser
  Scenario Outline: Verify that user is able to edit created tag category by clicking on edit button.
    Given user is on R1 Decision search page
    When user login to SQL server and connect to database
    And user run the query and fetch the Invoice Number to add tag"Tagging_419683_SQL6"
    And user select "Invoice Number" from Search By dropdown
    And user enters the query result in Invoice Number search textbox to add tag on account
    Then user is on "R1 Hub Technologies 2.0 - 15 R1_Decision" page
    When User Clicks on Edit Tag link
    And User edits Tag Category , Tag Name and "editedtest" text in notes textbox and click on save changes button 
    Then User should be able to view message "Account Tag saved successfully."
    And User should be able to view edited Tag Name under the title Tag
    When User scrolls till Tag History section
    Then user should be able to view following grid columns under tag history section
      | Tag Category |
      | Tag Name     |
      | Added Date   |
      | Added By     |
      | Removed Date |
      | Removed By   |
    And user should be able to view edited Tag Details in the grid
    When user login to SQL server and connect to facility database
    And user run the query to fetch newly added tag in histroy section <query3> query
    Then User should be able to view the newly added Tag in history section in SQL result.

    Examples: 
      | query3              |
      | Tagging_419683_SQL1 |
