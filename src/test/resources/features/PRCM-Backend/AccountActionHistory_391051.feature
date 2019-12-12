@AccountActionHistory
Feature: Verify Account Action History in PRCM-BE

Background: user is able to navigate to Account Page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu

@391051
Scenario: Verify that Account Action History should be visible at Invoice Grain
Given user should be connected to database
And user login to SQL server and connect to database
And user runs the account action history query "Account_Action_History_391051_SQL1"
When user login into application
And user clicks on Billing & Follow-up tab
And user hovers R1_Decision Link
And user clicks on Search sub menu
And user selects invoice number from search by dropdown
And user selects = operator
And user enters Invoice Number fetched from database in invoice number textbox
And user clicks on Submit button
And user scrolls down to reach Account Action History Section
Then user should be able to view the message No action history data available for the account at Account Action History Section