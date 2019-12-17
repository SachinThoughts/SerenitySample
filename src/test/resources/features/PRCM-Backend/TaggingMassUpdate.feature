@TaggingMassUpdate
Feature: Verify Tagging related scenarios in PRCM_BE

  Background: User is able to navigate to Mass Update Screen
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And User clicks on Mass Update link

  @419685 @Sprint103 @PRCMUser
  Scenario: Verify that User is able to add Account Tag to Categorized Account through mass update for Manual Entry
    Given User is on Mass Update screen
    When user clicks on Radio button Professional under Invoice Account Type
    And user clicks on Radio button Manual Entry under Manual Entry or Upload Document?
    And user login to SQL server and connect to facility database
    And user run the query and fetch the Invoice Number to add tag"Tagging_419683_SQL4"
    And user enters query result with FacilityCode prefixed under Enter Values textbox
    And user clicks on Radio button Account Tagging under Mass Tag Update Type
    And user clicks on Add radio button under Account Tag Update Type
    Then User should be able to view Account Tag Category dropdown 
    And User should be able to view Account Tag Name dropdown
    When User selects Account Tag Category from Account Tag Category drop down
    And Users selects Account Tag Name from Account Tag Name drop down
    And User enters the Notes "TestAutomation" in notes textbox
    And User clicks on Submit button
    Then User should be able to view the message "Batch update saved successfully with Batch No"

  @419686 @Sprint103 @PRCMUser
  Scenario: Verify that User is able to Remove Account Tag  through mass update for Manual Entry
    Given User is on Mass Update screen
    When user clicks on Radio button Professional under Invoice Account Type
    And user clicks on Radio button Manual Entry under Manual Entry or Upload Document?
    And user login to SQL server and connect to facility database
    And user run the query and fetch the Invoice Number to add tag"Tagging_419683_SQL4"
    And user enters query result with FacilityCode prefixed under Enter Values textbox
    And user clicks on Radio button Account Tagging under Mass Tag Update Type
    And user clicks on Remove radio button  under Account Tag Update Type
    And User enters the Notes "TestAutomation" in notes textbox
    And User clicks on Submit button
    Then User should be able to view the message "Batch update saved successfully with Batch No"
