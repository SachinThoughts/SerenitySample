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
    Given User is on R1 Account information page
    Then user should be able to view Invoice Number selected by default in Search By drop down

  @391032 @Sprint101 @PRCMUser
  Scenario Outline: Verify the error message displayed when user searches an invalid data in Search textbox with equal operator
    Given User is on R1 Account information page
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
    Given User is on R1 Account information page
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
    Given User is on R1 Account information page
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
    Given User is on R1 Account information page
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
    Given User is on R1 Account information page
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
    Given User is on R1 Account information page
    When user enter the query result of SQL1 in Invoice Number search textbox
    And user clicks on Submit button
    Then user should be able to navigate to the R1D account page for searched Invoice Number

  @428162 @Sprint101 @PRCMUser
  Scenario Outline: Verify that user is able to search an account with Visit Number having invoice number associated to it on R1D Page
    Given User is on R1 Account information page
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

  @428172 @PRCMUser @Sprint102
  Scenario Outline: Verify the error message displayed when user enter special characters in Last Name/First Name textbox
    Given User is on R1 Account information page
    When user selects "Last Name/First Name" from Search By dropdown
    And user enters <LastName> text in Last Name textbox
    And user enters <FirstName> text in First Name textbox
    And user clicks on Submit button
    Then user should be able to view the "Special Character are not allowed in Search criteria!" error message

    Examples: 
      | LastName | FirstName |
      | @$#%._   | Test1     |
      | Test1    | @$%^&     |
      | @#$*( _  | @!~`^/    |

  @433695 @PRCMUser @Sprint102
  Scenario Outline: Verify the Search functionality of Cross Site Facility on R1D Page
    Given User is on R1 Account information page
    When user selects <option> from Search By drop down
    And user selects "Like" operator from operator dropdown
    And user enters value <textvalue> in <option> textbox
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
    And user runs the <queryname15> query for search
    Then user should be able to view only those facilities in Facility Code column which are coming in SQL result

    Examples: 
      | option         | textvalue | queryname15                 |
      | Visit Number   |     12345 | SearchInternal_433695_SQL15 |
      | Invoice Number |     12345 | SearchInternal_433695_SQL15 |

  @428877 @PRCMUser @Sprint102
  Scenario Outline: Verify that user is able to see the search result grid for exact Last Name/First Name on R1D Page
    Given User is on R1 Account information page
    When user selects "Last Name/First Name" from Search By dropdown
    And user login to SQL Server and connect to facility database
    And user runs the <queryname5> query to fetch account data
    Then user should be able to fetch Firstname and Lastname from the query
    When user enters the fetched Lastname in Last Name textbox
    And user enters the fetched Firstname in First Name textbox
    And user clicks on Submit button
    Then user should be able to view the grid with following columns and verify searched name
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
    When user runs the <queryname11> query to fetch name
    Then user should be able to view the same names in grid as SQL result

    Examples: 
      | queryname11                 | queryname5                 |
      | SearchInternal_428877_SQL11 | SearchInternal_428877_SQL5 |

  @428167 @PRCMUser @Sprint102
  Scenario Outline: Verify that user is able to see the search result grid for SSN on R1D Page
    Given User is on R1 Account information page
    When user selects "SSN" from Search By dropdown
    And user login to SQL Server and connect to facility database
    And user runs the <queryname4> query to fetch account data
    And user enters the query result in SSN textbox
    And user clicks on Submit button
    Then user should be able to view the grid with following columns and verify searched SSN
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
    When user runs the <queryname10> query to fetch SSN result
    Then user should be able to view the same SSN in grid as SQL result

    Examples: 
      | queryname10                 | queryname4                 |
      | SearchInternal_428167_SQL10 | SearchInternal_428167_SQL4 |

  @433692 @PRCMUser @Sprint102
  Scenario Outline: Verify that user is able to see the search result grid for Last Name/First Name on R1D Page
    Given User is on R1 Account information page
    When user selects "Last Name/First Name" from Search By dropdown
    And user enters <lastName> text in Last Name textbox
    And user enters <firstName> text in First Name textbox
    And user clicks on Submit button
    Then user should be able to view the grid with following columns for Last Name/First Name search
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
      | queryname11                  | lastName | firstName |
      | SearchInternal2_428877_SQL11 | a        | b         |

  @433693 @PRCMUser @Sprint102
  Scenario Outline: Verify that user is able to see the search result grid for Medical Record Number with operators on R1D Page
    Given User is on R1 Account information page
    When user selects "Medical Record Number" from Search By dropdown
    And user login to SQL Server and connect to facility database
    And user runs the <queryname6> query to fetch account data
    And user selects <Operator> from Operator dropdown
    And user enters the query result in Medical Record Number textbox
    And user clicks on Submit button
    Then user should be able to view the grid with following columns if they are visible else verify the searched account with MRN
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
    When user runs the <queryname12> query for MRN search
    Then user should be able to view the same MRN in grid as SQL result

    Examples: 
      | queryname12                 | queryname6                 | Operator |
      | SearchInternal_433693_SQL12 | SearchInternal_433693_SQL6 | =        |
      | SearchInternal_433693_SQL12 | SearchInternal_433693_SQL6 | Like     |

  @428160 @Sprint102 @PRCMUser
  Scenario Outline: Verify that user is able to search an account with Visit Number using equal operator on R1D Page
    Given User is on R1 Account information page
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

  @428161 @Sprint102 @PRCMUser
  Scenario Outline: Verify that user is able to search an account with Visit Number does not having invoice number associated to it on R1D Page
    Given User is on R1 Account information page
    And user selects <dropdown> from Search By drop down
    And user selects <Operator> from Operator dropdown
    And user login to SQL Server and connect to facility database
    And user runs the <queryname14> query for search
    And user enters the query result in Visit Number search textbox
    And user clicks on Submit button
    Then user should be able to navigate to the R1D account page for searched Visit Number and verify invoice number should not be visible

    Examples: 
      | queryname14                 | dropdown     | Operator |
      | SearchInternal_428161_SQL14 | Visit Number | Like     |
      | SearchInternal_428161_SQL14 | Visit Number | =        |

  @391038 @Sprint102 @PRCMUser
  Scenario Outline: Verify that user is able to search an account with Search textbox using Like operator on R1D Page
    Given User is on R1 Account information page
    And user selects <dropdown> from Search By drop down
    And user selects "Like" operator from operator dropdown
    And user enters value <AnyFiveDigitNumber> in <option> textbox
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
    And user runs query and fetch visit number <queryname8>
    Then user should be able to view the same result in grid as SQL result for visit number

    Examples: 
      | dropdown     | option            | AnyFiveDigitNumber | queryname8                 |
      | Visit Number | Visit Number      |             12345 | SearchInternal_391038_SQL8 |

  @391038 @Sprint102 @PRCMUser
  Scenario Outline: Verify that user is able to search an Invoice Number with Search textbox using Like operator on R1D Page
    Given User is on R1 Account information page
    And user selects <dropdown> from Search By drop down
    And user selects "Like" operator from operator dropdown
    And user enters value <AnyFiveDigitNumber> in <option> textbox
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
    And user runs query and fetch invoice number <queryname9>
    Then user should be able to view the same result in grid as SQL result for invoice number

    Examples: 
      | dropdown       | option            | AnyFiveDigitNumber | queryname9                   |
      | Invoice Number | Invoice Number    |              12345 | SearchInternal_391038_1_SQL8 |

  @391038 @Sprint102 @PRCMUser
  Scenario Outline: Verify that user is able to search an MRN with Search textbox using Like operator on R1D Page
    Given User is on R1 Account information page
    And user selects <dropdown> from Search By drop down
    And user selects "Like" operator from operator dropdown
    And user enters value <AnyFiveDigitNumber> in <option> textbox
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
    And user runs query and fetch MRN number <queryname9>
    Then user should be able to view the same result in grid as SQL result for MRN number

    Examples: 
      | dropdown              | option                | AnyFiveDigitNumber | queryname9                   |
      | Medical Record Number | Medical Record Number |              12345 | SearchInternal_391038_2_SQL8 |
