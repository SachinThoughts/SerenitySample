@R1PRCMExternalSearch
Feature: This is to verify external search functionality in R1 PRCM-BE

  Background: User is able to navigate to R1 Decision search page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu

  @429052 @Sprint8 @PRCMUser
  Scenario: Verify whether Invoice Number is appearing by default selected in Search By dropdown for PRCM enabled site
    Given user is on "R1 Hub Technologies 2.0 - 01 R1_Decision - Search" page
    Then user should be able to view Invoice Number selected by default in Search By drop down

  @429053 @Sprint8 @PRCMUser
  Scenario Outline: Verify the error message displayed when user searches an invalid data in Search textbox with equal operator
    Given user is on "R1 Hub Technologies 2.0 - 01 R1_Decision - Search" page
    When user selects <dropdown> from Search By drop down
    And user enters invalid value in <Invalid Data> textbox 
    And user clicks on Submit Button
    Then user should be able to view error message <ErrorMsg>

    Examples: 
      | dropdown              | Invalid Data | ErrorMsg                                              |
      | Visit Number          | @@$34        | No Record Found!                                      |
      | Invoice Number        | 34@$@$       | No Record Found!                                      |
      | SSN                   | @#$$@43242   | No Record Found!                                      |
      | Last Name/First Name  | @#sf*&       | Special Character are not allowed in Search criteria! |
      | Medical Record Number | %%3424$      | No Record Found!                                      |
      | Claim Number          | $$$cdf5435   | No Record Found!                                      |
