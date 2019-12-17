@PatientInformation
Feature: Verify patient info related test cases in PRCM-BE

  Background: user is able to navigate to R1 Decision Page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu
    When user login to SQL server and connect to database
    And user run the query "patient_info_391254_SQL1" and Fetch Invoice number to verify patient info
    And user select "Invoice Number" from Search By dropdown
    And user enters the query result in Invoice Number search textbox to verify patient info
    And user clicks on submit button

  @391254 @Sprint103 @PRCMUser
  Scenario: TC To Verify that Patient& Facility Info  Panel should be displayed if open via searching an Invoice Number
    Given user is on "R1 Hub Technologies 2.0 - 15 R1_Decision" page
    Then user should be able to view the Patient and Facility Info section on R1D Page
    And user should be able to view the following tabs
      | Patient & Visit Details |
      | Patient Address         |
      | Facility Details        |
    And user should be able to view Patient & Visit Details tab as selected by default

  @391255 @Sprint103 @PRCMUser
  Scenario: TC To Verify "Patient & Facility Info" Panel should be expandable when user click on Drill Down symbol
    Given user is on "R1 Hub Technologies 2.0 - 15 R1_Decision" page
    When User clicks on drilldown icon of Patient & Facility Info panel
    Then user should be able to view Patient Information grid as collapsed

  @391256 @Sprint103 @PRCMUser
  Scenario Outline: TC To Verify headers on patient Info section for all tabs
    Given user is on "R1 Hub Technologies 2.0 - 15 R1_Decision" page
    When User clicks on tab <tabname> tab in Patient & Facility Info panel
    Then user should be able to view the following header fields in all four tabs
      | SSN | DOB | Facility Code | Account # | Invoice # |
    When user login to SQL Server and connect to facility database
    And user runs the patient info query "patient_info_391254_SQL2" and fetch the headers
    Then user should be able to view same data in header fields as in SQL result

    Examples: 
      | tabname                 |
      | Patient Address         |
      | Facility Details        |
      | Patient & Visit Details |

  @391261 @Sprint103 @PRCMUser
  Scenario: Verify Facility Details Tab's and Data Columns Under Patient & Facility Info module
    Given user is on "R1 Hub Technologies 2.0 - 15 R1_Decision" page
    When User clicks on Facility Details tab
    And user runs the patient info query "patient_info_391261_SQL4" and fetch the headers
    And user runs the patient info query to fetch facility data "patient_info_391261_SQL4" query
    Then User should be able to view the following feilds on Facility Details tab
      | Facility Code    |
      | Facility Name    |
      | Location Code    |
      | Location Name    |
      | Location Address |
      | City             |
      | State            |
      | Zip Code         |
      | NPI              |
      | Tax ID #         |
      | PTAN             |
    And User should be able to view the same data in columns as in SQL result 
