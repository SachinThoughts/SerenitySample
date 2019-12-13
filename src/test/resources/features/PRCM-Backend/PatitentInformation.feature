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
