@WriteOff
Feature: Verify write off related testcases in PRCM-BE

  Background: User is able to navigate to R1 Decision page
    Given user having level 1 Approver role is on R1 Hub page
    When user clicks on setting link

  @441253 @Sprint103 @R1DApproval
  Scenario: Verify write off request actions are in open state and have status "Identified" in Workflow Configuration screen
    Given user is on Settings page
    When user clicks on Settings-R1_Decision
    And user clicks on Workflow Configuration link
    And user clicks radio button against Writeoff Handoff
    And user clicks on Continue button on HandOff Tab
    And user clicks on radio button against "WriteOff SubType" Recipient
    And user clicks on Continue button on Recipient tab
    Then user should be able to view Status as "Identified" for all displayed write off request actions
