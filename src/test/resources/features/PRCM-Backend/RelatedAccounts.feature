@RelatedAccounts
Feature: Verify Related Account testcases in PRCM-BE

  Background: User is able to navigate to R1D Page
    Given user having AHtoDecision Admin role is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user clicks on R1_Decision link

  @439227 @PRCMQueueUser @Sprint103
  Scenario Outline: Verify the User is able to view Related Invoices pop up with pagination and Search box for PRCM enabled sites
    Given user is on R1 Decision Account information page
    When user clicks on Related Accounts under Patient & Facility Info Section
    Then user should able to view the pop up title as "Related Accounts"
    And user should be able to view Search button
    And user should be able to view First button
    And user should be able to view Previous button
    And user should be able to view Next Button
    And user should be able to view Last Button
    And user should be able to view 1 button
    And user should be able to view following grid columns
      | Visit # | Invoice # | Facility Code | Admit Date | Discharge Date | Patient Type | PayerPlan Code | Insurance Balance | Patient Balance | Defect Type | Defect SubCategory |
    And user should be able to view maximum 20 Accounts under Related Accounts grid
    When user login to SQL Server and connect to facility database
    And user runs query to fetch Related Accounts <query1>
    Then user should be able to view same list of accounts in grid as in SQL result

    Examples: 
      | query1                      |
      | RelatedInvoices_439227_SQL1 |

  @439231 @PRCMQueueUser @Sprint103
  Scenario Outline: Verify the Grid data for Related Accounts for PRCM sites
    Given user is on R1 Decision Account information page
    When user clicks on Related Accounts under Patient & Facility Info Section
    Then user should be able to view InvoiceNumber as hyperlink
    And user should be able to view Visit Number as hyperlink for records having InvoiceNumber as NA
    When user login to SQL server and connect to facility database
    And user runs query to fetch all details of Related Accounts <query3>
    Then user should be able to view the same grid data as in SQL result

    Examples: 
      | query3                      |
      | RelatedInvoices_439237_SQL3 |

  @439232 @PRCMQueueUser @Sprint103
  Scenario: Verify when User clicks on any account from Related Accounts section then user is navigated to That Account
    Given user is on R1 Decision Account information page
    When user clicks on Related Accounts under Patient & Facility Info Section
    And user clicks on Any InvoiceNumber from the grid
    Then user should be able to view the R1D screen for that InvoiceNumber
    When user clicks on Related Accounts under Patient & Facility Info Section
    Then user should be able to view the previous Invoice in Related Accounts grid

  @439261 @PRCMQueueUser @Sprint103
  Scenario: Verify when User clicks on any visit number from Related Accounts section then user is navigated to That Account
    Given user is on R1 Decision Account information page
    When user clicks on Related Accounts under Patient & Facility Info Section
    And user clicks on any Visit Number from the grid where InvoiceNumber is NA
    Then user should be able to view the R1D screen for that Visit Number
    When user clicks on Related Accounts under Patient & Facility Info Section
    Then User should be able to view the previous Account in Related Accounts grid
