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
