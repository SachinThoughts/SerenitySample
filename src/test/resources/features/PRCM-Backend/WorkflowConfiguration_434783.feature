@WorkflowConfiguration
Feature: Verify WorkFlowConfiguration related scenarios in PRCM
  To check WorkFlowConfiguration related scenarios in PRCM application

  Background: User is able to navigate to Settings home page
    Given user having AHtoDecision Admin role is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user clicks on R1_Decision link
   When user login to SQL server and connect to database
    And user run the query "434779_WFConfig_R1DHandoffType" and fetch encounterId
    And user select "Visit Number" from Search By dropdown
    And user selects "=" from operator dropdown
    And user enters encounter id
    And user clicks on Submit button

  @434783 @AHtoDecisionAdmin @Sprint103
  Scenario: Verify user is able to view the added Predefined Note for particular Disposition Type associated with respective Handoff Type
    When user clicks on Handoff link button
    And user clicks on Handoff Type dropdown
    And user selects "AR Supervisor" handoff from Handoff Types dropdown
    And user selects "Supervisor Hand Off Request" from Create dropdown
    And user selects "General Request" from Why dropdown
    And user selects "General Request" from Disposition dropdown
    And user clicks on Save button on handoff pop up
    Then Handoff Account modal pop-up window should be closed
    Then user should be able to view the "Handoff Record Saved Successfully." message
    When user should be able to view H under event circle in blue color for newly added Handoff type on Horizontal timeline
    Then user hovers the event circle for newly added Handoff type
    When user navigates to Account Action History section
    And click on Show Account Action History Notes button
    Then user should be able to view the Predefined Note saved at the top under Handoff action related grid