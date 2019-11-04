@FinancialInformation
Feature: This feature is to verify the financial Information functionality

  Background: User is able to navigate to R1D Search Page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu

  @391020 @Sprint8 @PRCMUser
  Scenario Outline: Verify that user is able to see "Financial Information" section
    Given user is able to login to sql server and connect to database
    When user executes the query for InvoiceNumber <queryName1>
    And user fetch the InvoiceNumber from DB
    And user enters InvoiceNumber in the InvoiceNumber field and click on submit button
    And user scrolls down till Financial Information Section
    Then User should be able to view following fields
      | Total Balance | Insurance Balance | Patient Balance | Insurance Balance | Unbilled Balance | Total Charges | Expected Payment | Insurance Payments | Patient Payments | Adjustments |

    Examples: 
      | queryName1                                |
      | Financial_Information_Section_391021_SQL1 |

  @391030 @Sprint8 @PRCMUser
  Scenario Outline: Verify the Drilldown detail  for Adjustment >0
    Given user is able to login to sql server and connect to database
    When user runs Financial_Information_Section_SQL5 <queryname5>
    Then user should be able to fetch Invoice Number
    When user enters fetched Invoice Number in the Invoice Number textbox
    And user clicks on Submit button on R1D page
    And user scrolls down till Financial Information Section
    And User Clicks on drill down icon of Adjustment drilldown
    Then User should be able to view following fields:
      | Payment Code        |
      | Payment Description |
      | PayorPlanCode       |
      | PayorPlanName       |
      | Date Posted         |
      | DateOfTransaction   |
      | TypeOfTransaction   |
      | Amount              |
      | GLCode              |
    When user runs Financial_Information_Section_SQL9 <queryname9>
    Then User should be able to view same data in drilldown section of Adjustment as SQL result

    Examples: 
      | queryname5                                | queryname9                                |
      | Financial_Information_Section_391026_SQL5 | Financial_Information_Section_391030_SQL9 |

  @423934 @Sprint8 @PRCMUser
  Scenario Outline: Verify the amount for Total Balance column
    Given user is able to login to sql server and connect to database
    When user runs Financial_Information_Section_SQL3 <queryname3>
    Then user should be able to fetch Invoice Number and Total Balance
    When user enters fetched Invoice Number in the Invoice Number textbox
    And user clicks on Submit button on R1D page
    And user scrolls down till Financial Information Section
    And user moves to Total Balance column
    Then User should be able to view the same amount of Total Balance as SQL result

    Examples: 
      | queryname3                                |
      | Financial_Information_Section_423934_SQL3 |

  @391026 @Sprint8 @PRCMUser
  Scenario Outline: Verify the amount for Adjustment column
    Given user is able to login to sql server and connect to database
    When user executes the query for InvoiceNumber <queryName5>
    And user fetch the InvoiceNumber and "Adjustments" from DB
    And user enters InvoiceNumber in the InvoiceNumber field and click on submit button
    And user scrolls down till Financial Information Section
    Then user should be able to view the same amount in Adjustment column as SQL result

    Examples:   
      | queryName5                                |
      | Financial_Information_Section_391026_SQL5 |
