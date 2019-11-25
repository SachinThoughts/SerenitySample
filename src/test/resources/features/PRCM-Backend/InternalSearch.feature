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

  @391034 @Sprint101 @PRCMUser
  Scenario Outline: Verify that Submit button is enabled for Search textbox for Like Operator if user enters 5 or more characters
    Given user is on "R1 Hub Technologies 2.0 - 01 R1_Decision" page
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

  @391035 @Sprint101 @PRCMUser
  Scenario Outline: Verify that when user does not enter anything in Search textbox then message appeared or not
    Given user is on "R1 Hub Technologies 2.0 - 01 R1_Decision" page
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

  @391036 @Sprint101 @PRCMUser
  Scenario Outline: Verify the error message displayed when user searches an invalid Search textbox with Like operator on R1D Page
    Given user is on "R1 Hub Technologies 2.0 - 01 R1_Decision" page
    When user selects <dropdown> from Search By drop down
    And user selects "Like" operator from operator dropdown
    And user enters invalid value in <Invalid Data> textbox 
    And user clicks on Submit button
    Then user should be able to view error message <ErrorMsg>

    Examples: 
      | dropdown              | Invalid Data | ErrorMsg         |
      | Visit Number          | q31&@$^      | No Record Found! |
      | Invoice Number        | 34%&^*45     | No Record Found! |
      | Medical Record Number | 5643^*^$     | No Record Found! |
      | Claim Number          | 5345fg^**    | No Record Found! |

  @391037 @Sprint101 @PRCMUser
  Scenario: Verify that user is able to search an account with Invoice Number using equal operator on R1D Page
    Given user is on "R1 Hub Technologies 2.0 - 01 R1_Decision" page
    When user enter the query result of SQL1 in Invoice Number search textbox
    And user clicks on Submit button
    Then user should be able to navigate to the R1D account page for searched Invoice Number

  @428162 @Sprint101 @PRCMUser
  Scenario Outline: Verify that user is able to search an account with Visit Number having invoice number associated to it on R1D Page
    Given user is on "R1 Hub Technologies 2.0 - 01 R1_Decision" page
    When user login to SQL Server and connect to facility database
    And user runs the <queryname3> query to fetch account data
    And user selects "Visit Number" from Search By dropdown
    And user selects <Operator> from Operator dropdown
    And user enters the query result in Visit Number search textbox
    And user clicks on Submit button
    Then user should be able to view the grid with following columns if they are visible else verify the searched account
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
    And user runs the <queryname9> query to search Visit number
    Then user should be able to view the same result in grid as SQL result for searched Visit number

    Examples: 
      | queryname3                 | queryname9                 | Operator |
      | SearchInternal_428162_SQL3 | SearchInternal_428162_SQL9 | Like     |
      | SearchInternal_428162_SQL3 | SearchInternal_428162_SQL9 | =        |

  @428160 @Sprint101 @PRCMUser
  Scenario Outline: Verify that user is able to search an account with Visit Number using equal operator on R1D Page
    Given user is on "R1 Hub Technologies 2.0 - 01 R1_Decision" page
    When user login to SQL Server and connect to facility database
    And user runs the <queryname2> query for search
    And user selects <dropdown> from Search By drop down
    And user selects "=" operator from operator dropdown
    And user enters the query result in Visit Number search textbox
    And user clicks on Submit button
    Then user should be able to navigate to the R1D account page for searched visit Number

    Examples: 
      | queryname2                 | dropdown     |
      | SearchInternal_428160_SQL2 | Visit Number |

  @428161 @Sprint101 @PRCMUser
  Scenario Outline: Verify that user is able to search an account with Visit Number does not having invoice number associated to it on R1D Page
    Given user is on "R1 Hub Technologies 2.0 - 01 R1_Decision" page
    And user selects <dropdown> from Search By drop down
    And user selects <Operator> from Operator dropdown
    And user login to SQL Server and connect to facility database
    And user runs the <queryname14> query for search
    And user enters the query result in Visit Number search textbox
    And user clicks on Submit button
    Then user should be able to navigate to the R1D account page for searched Visit Number and verify invoice number should not be visible

    Examples: 
      | queryname14                 | dropdown      | Operator |
      | SearchInternal_428161_SQL14 | Visit Number  | Like     |
      | SearchInternal_428161_SQL14 | Visit Number  | =        |

  @391038
  Scenario Outline: Verify that user is able to search an account with Search textbox using Like operator on R1D Page
    Given user is on "R1 Hub Technologies 2.0 - 01 R1_Decision" page
    And user selects <dropdown> from Search By drop down
    And user selects "Like" operator from operator dropdown
    And user enters <AnyFiveDigitNumber> in <option> textbox
    And user clicks on Submit button
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
    And user runs the <queryname8> query for search
    Then user should be able to view the same result in grid as SQL result

    Examples: 
      | option                | AnyFiveDigitNumber | queryname8                 |
      | Visit Number          |              12345 | SearchInternal_391038_SQL8 |
      | Invoice Number        |              12345 | SearchInternal_391038_SQL8 |
      | Medical Record Number |              12345 | SearchInternal_391038_SQL8 |
