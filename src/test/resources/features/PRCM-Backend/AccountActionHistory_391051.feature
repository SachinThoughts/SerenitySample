@AccountActionHistory
Feature: Verify Account Action History in PRCM-BE

  Background: user is able to navigate to Account Page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu

  @391051 @AHtoDecisionAdmin @Sprint103
  Scenario: Verify that Account Action History should be visible at Invoice Grain
    Given user is on R1 Decision search page
    When user login to SQL server and connect to database
    And user runs the account action history query "Account_Action_History_391051_SQL1"
    And user selects "Invoice Number" from search by dropdown
    And user enters the result in Invoice Number search textbox
    And user clicks on submit button
    And user scrolls down to reach Account Action History Section
    Then user should be able to view the message "No action history data available for the account" at Account Action History Section
