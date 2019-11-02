@R1PRCMFinancialInformation 
Feature: This feature is to verify the financial Information functionality

  Background: User is able to navigate to R1D Search Page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu

  @391020 @Sprint8 @PRCMUser
  Scenario Outline: Verify that user is able to see "Financial Information" section
    Given user login to SQL server and connect to database
    When user executes the query for InvoiceNumber <queryName1>
    And user fetch the InvoiceNumber from DB
    And user enters InvoiceNumber in the InvoiceNumber field and click on submit button
    And user scrolls down till Financial Information Section
    Then User should be able to view following fields
      | Total Balance | Insurance Balance | Patient Balance | Insurance Balance | Unbilled Balance | Total Charges | Expected Payment | Insurance Payments | Patient Payments | Adjustments |

    Examples: 
      | queryName1                                | dropdown       |
      | Financial_Information_Section_391021_SQL1 | Invoice Number |
