@ActionTargetConfiguration
Feature: Verify Action Target Configuration related testcases in PRCM-BE

  Background: User is on Action Target Configuration page
    Given user having AHtoDecision Admin role is on R1 Hub page
    When user clicks on setting link
    And user clicks on Settings-R1_Decision
    And user clicks on Action Target Configuration link

  @434419 @AHtoDecisionAdmin @Sprint103
  Scenario: Verify user with AHtoDecision Admin role is able to access 'Action Target configuration'
    Then user should be able to view Action Target Configuration screen

  @434420 @AHtoDecisionAdmin @Sprint103
  Scenario: Verify the UI of 'Search by' filter on the Action target configuration grid
    Given user having AHtoDecision Admin role is on Action Target Configuration page
    Then user should be able to view Search By dropdown
    And user should be able to view Enter Action Target Name textbox
    And user should be able to view Apply button disabled by default
    And user should be able to view Clear All button
    And user should be able to view Add New Action Target button

  @434421 @AHtoDecisionAdmin @Sprint103
  Scenario: Verify values of 'Search By' Drop down on Action Target Configuration Screen
    Given user having AHtoDecision Admin role is on Action Target Configuration page
    When user clicks on Search By dropdown
    Then user should be able to view the following values in search by dropdown
      | Action Target Name | Username |
    And user should be able to view "Action Target Name" as selected value by default in search by dropdown
