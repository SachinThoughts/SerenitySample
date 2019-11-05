@ExternalSearch
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

  @429054 @Sprint8 @PRCMUser
  Scenario Outline: Verify that Submit button is disabled for Search textbox for Like Operator if user enters less than five characters
    Given user is on "R1 Hub Technologies 2.0 - 01 R1_Decision - Search" page
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

  @429996 @PRCMUser
  Scenario Outline: Verify that user is able to see the search result grid for Last Name/First Name
    Given user is on R1 Decision search page
    When user selects "Last Name/First Name" from Search By dropdown
    And user enters <lastName> text in Last Name textbox
    And user enters <firstName> text in First Name textbox
    And user clicks on submit button
    Then user should be able to view the grid with following columns
      | Visit #             |
      | Invoice #           |
      | Name                |
      | Facility Code       |
      | MRN                 |
      | Gender              |
      | PT                  |
      | Service Date        |
      | PPC                 |
      | Defect Type         |
      | Defect Sub-Category |
    When user login to SQL Server and connect to facility database
    And user runs the <queryname11> query to fetch name
    Then user should be able to view the same result in grid as SQL result for Last Name/First Name

    Examples: 
      | queryname11                 | lastName | firstName |
      | SearchExternal_429995_SQL11 | a        | b         |

  @433519 @PRCMUser
  Scenario Outline: Verify that user is able to see the search result grid for Claim Number with operators
    Given user is on R1 Decision search page
    When user selects "Claim Number" from Search By dropdown
    And user login to SQL Server and connect to facility database
    And user runs the <queryname7> query for search
    And user selects <operator> from Operator dropdown
    And user enters the query result in Claim Number textbox
    And user clicks on submit button
    Then user should be able to view the grid with following columns
      | Visit #             |
      | Invoice #           |
      | Name                |
      | Facility Code       |
      | MRN                 |
      | Gender              |
      | PT                  |
      | Service Date        |
      | PPC                 |
      | Defect Type         |
      | Defect Sub-Category |
    When user runs the <queryname13> query for claim search
    Then user should be able to view the same result in grid as SQL result for Claim Number

    Examples: 
      | queryname13                 | queryname7                 | operator |
      | SearchExternal_433519_SQL13 | SearchExternal_433519_SQL7 | =        |
      | SearchExternal_433519_SQL13 | SearchExternal_433519_SQL7 | Like     |