@CallPayerQueue
Feature: Verify Call Payer Queue functionality

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

  @427114 @Sprint102 @PRCMUser
  Scenario Outline: Verify that added account in call payer queue after taking Handoff Action
    Given user is on R1 Decision Account page
    When user clicks on Add to Queue button
    Then user should be able to view the account added to Call Queue
    When user clicks on Handoff button
    And user selects any value from Handoff Types dropdown <handoffType>
    And user selects any value from Create dropdown <create>
    And user selects "General Request" from Why dropdown
    And user selects "General Request" from Disposition dropdown
    And user enters any value in Note Text Area "test"
    And user clicks on Save button on handoff pop up
    Then user should be able to view the "Handoff Record Saved Successfully." message
    And user should be able to view the saved Action in Action History
    And user should be able to view the deleted account from the Call Queue on navigating to the Next Account

    Examples: 
      | handoffType   | create                      |
      | AR Supervisor | Supervisor Hand Off Request |

  @427115 @Sprint102 @PRCMUser
  Scenario Outline: Verify Call Queue Indicator pop-up message is displayed to user 2 while adding an account already in Call Queue of User 1
    Given user is on R1 Decision Account page
    When user clicks on Add to Queue button
    Then user should be able to view the account added to Call Queue
    When user logout from the application
    Then user should be able to view that user successfully logout from application
    When user login with <user2>
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu
    And user selects "Invoice Number" from search by dropdown
    And user selects "=" option
    And user login to SQL server and connect to facility database
    And user runs the "PRCM_Fetch_Classified_Invoices" query to fetch invoice number
    And user enters the query result in Invoice Number search textbox
    And user clicks on submit button
    Then user should be able to view the message Account already added in Call Queue of "<Name of User 1>"
    And user should be able to view the Add to call payer button disable

    Examples: 
      | user2    |
      | rsingh85 |
