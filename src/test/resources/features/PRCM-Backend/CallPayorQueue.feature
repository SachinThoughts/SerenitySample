@CallPayorQueue
Feature: Verify Call Payor Queue functionality

  Background: user is able to navigate to Account Page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu
    And user selects "Invoice Number" from search by dropdown
    And user selects "=" option
    And user login to SQL server and connect to facility database
    And user runs the "PRCM_Fetch_Classified_Invoices" query to fetch invoice number
    And user enters the query result in Invoice Number search textbox
    And user clicks on submit button

  @427108 @Sprint102 @PRCMUser
  Scenario: Verify that user is able to delete individual accounts from the Call Queue.
    Given user is on R1 Decision Account page
    When user clicks on Toggle Call Queue button
    And user expands the call queue section to view a list of all the added accounts
    Then user should be able to view X button against each account in Call Queue
    When user clicks on remove X button
    Then user should be able to view the deleted account from Call Queue
    And user should be able to view the count of accounts is decreased by 1 in Call Queue
