@CallPayorQueue
Feature: Verify Call Payor Queue functionality

  Background: 
    Given user is on R1 Hub page
    And user is able to login to sql server and connect to database
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu
    And Call Payor Queue user run the query to fetch an Unclassified account "getUnclassifiedAccount"
    And user fetch the InvoiceNumber from DB
    And user enters InvoiceNumber in the InvoiceNumber field and click on submit button

  @427109 @Sprint102 @PRCMUser
  Scenario: Verify that user is able to add only those accounts to Call Queue that have Major Payor Type defined
    Given user is on account page having no payer : "Unclassified Account"
    When user clicks on Add to Call queue icon
    Then The popup to add call to queue should open with title as "Add to Call Queue"
    And user clicks Add without note
    Then user should be able to view message for Call Payor queue as "Account cannot be added to CallPayerQueue, because it has no defect categorization associated with it."
    And user should not be able to view account to Call Queue
