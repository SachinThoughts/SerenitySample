@InternalSearch
Feature: Verify internal search on R1 Decision page
  This is to verify internal search functionality in R1 decision page

  Background: user is able to navigate to R1 Decision search page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user clicks on R1_Decision link
    When user login to SQL server and connect to database
    And user run the query and fetch the Invoice Number "SearchInternal_391031_SQL1"
    And user select "Invoice Number" from Search By dropdown
    And user enters the query result in Invoice Number search textbox and can view the same invoice number of selected facility or different facility
    Then user navigates on internal search page
 
  @391031 @Sprint101 @PRCMUser
  Scenario: Verify that R1D page for PRCM enabled site Invoice number should be default criteria
    Given user is on "R1 Hub Technologies 2.0 - 01 R1_Decision" page
    Then user should be able to view Invoice Number selected by default in Search By drop down
    
  @391032 @Sprint101 @PRCMUser
  Scenario Outline: Verify the error message displayed when user searches an invalid data in Search textbox with equal operator
    Given user is on "R1 Hub Technologies 2.0 - 01 R1_Decision" page
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

  @391033 @Sprint101 @PRCMUser
  Scenario Outline: Verify that Submit button is disabled for Search textbox for Like Operator if user enters less than five characters
    Given user is on "R1 Hub Technologies 2.0 - 01 R1_Decision" page
    When user selects <dropdown> from Search By drop down
    And user selects "Like" operator from operator dropdown
    And user enters less than 5 characters in <lessThanFivetext> textbox
    Then user should able to view tool-tip message <tooltipMessage>
    And user should be able to view Submit Button in disabled state

    Examples: 
      | dropdown              | lessThanFivetext | tooltipMessage                     |
      | Visit Number          |             1234 | Please add five or more characters |
      | Invoice Number        |             1234 | Please add five or more characters |
      | Medical Record Number |             1234 | Please add five or more characters |
      | Claim Number          |             1234 | Please add five or more characters |
