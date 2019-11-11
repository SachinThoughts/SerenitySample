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
    And user clicks on Submit button
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

  @429058 @Sprint8 @PRCMUser
  Scenario Outline: Verify that Submit button is enabled for Search textbox for Like Operator if user enters 5 or more characters
    Given user is on "R1 Hub Technologies 2.0 - 01 R1_Decision - Search" page
    When user selects <dropdown> from Search By drop down
    And user selects "Like" operator from operator dropdown
    And user enters more than or equal to 5 characters <moreThanFivetext> in textbox
    Then user should not able to view tool-tip message
    And user should be able to view Submit Button in enabled state

    Examples: 
      | dropdown              | moreThanFivetext |
      | Visit Number          |            12345 |
      | Visit Number          |           123456 |
      | Invoice Number        |            12345 |
      | Invoice Number        |           123456 |
      | Medical Record Number |            12345 |
      | Medical Record Number |           123456 |
      | Claim Number          |            12345 |
      | Claim Number          |           123456 |

  @429061 @Sprint8 @PRCMUser
  Scenario Outline: Verify that when user does not enter anything in Search textbox then message appeared or not
    Given user is on "R1 Hub Technologies 2.0 - 01 R1_Decision - Search" page
    When user selects <option> from Search By drop down
    And user clicks on Submit button
    Then user should be able to view message "Please enter the value for" <option>

    Examples: 
      | option                |
      | Visit Number          |
      | Invoice Number        |
      | SSN                   |
      | Last Name/First Name  |
      | Medical Record Number |
      | Claim Number          |
