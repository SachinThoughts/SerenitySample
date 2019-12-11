@E-parsProHandoff
Feature: Verify E-pars pro handoff related testcases in PRCM-BE

  Background: 
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And User clicks on ePARS-Pro link

  @438746 @PRCMUser @Sprint103
  Scenario: Verify that ePARS Pro page for PRCM site Invoice number should be default criteria
    Given user is on ePARS Pro screen
    Then user should be able to view following value selected by default in Search By dropdown on ePARS Pro Page : "Invoice Number"

  @438747 @PRCMUser @Sprint103
  Scenario: Verify that when user does not enter anything in Search textbox then message appeared or not on ePARS Pro Page
    Given user is on ePARS Pro screen
    When user selects following values from Search By drop down on Epars Page followed by click on Submit Button
      | Visit Number           |
      | Invoice Number         |
      | Social Security Number |
      | Last/First Name        |
      | Medical Records Number |
      | Claim Number           |
      | DOB                    |
      | Date of Service        |
    Then user should be able to view following validation messages on Epars page
      | Please enter the value for Visit Number          |
      | Please enter the value for Invoice Number        |
      | Please enter the value for SSN                   |
      | Please enter the value for Last Name/First Name  |
      | Please enter the value for Medical Record Number |
      | Please enter the value for Claim Number          |
      | Please enter the value for Date of Birth         |
      | Please enter the value for Date of Service       |

  @438750 @PRCMUser @Sprint103
  Scenario: Verify the error message displayed when user searches an invalid Search textbox like 'abc1@' with equal operator on ePARS Pro Page
    Given user is on ePARS Pro screen
    When user selects following values from Search By drop down on Epars Page, with operator "=" enters "abc1@" in Search textbox followed by click on Submit Button
      | Visit Number           |
      | Invoice Number         |
      | Social Security Number |
      | Last/First Name        |
      | Medical Records Number |
      | Claim Number           |
      | DOB                    |
      | Date of Service        |
    Then user should be able to view following message on Epars page "No record found!"

  @438752 @PRCMUser @Sprint103
  Scenario: Verify the error message displayed when user searches an invalid Search textbox like 'abc1@' with Like operator on ePARS Pro Page
    Given user is on ePARS Pro screen
    When user selects following values from Search By drop down on Epars Page, with operator "Like" enters "abc1@" in Search textbox followed by click on Submit Button
      | Visit Number           |
      | Invoice Number         |
      | Social Security Number |
      | Last/First Name        |
      | Medical Records Number |
      | Claim Number           |
      | DOB                    |
      | Date of Service        |
    Then user should be able to view following message on Epars page "No record found!"

  @438753 @PRCMUser @Sprint103
  Scenario: Verify the error message for Search textbox for Like Operator if user enters less than 5 characters on ePARS Pro Page
    Given user is on ePARS Pro screen
    When user selects following values from Search By drop down on Epars Page, with operator "Like" enters "1234" in Search textbox followed by click on Submit Button
      | Visit Number           |
      | Invoice Number         |
      | Medical Records Number |
      | Claim Number           |
    Then user should be able to view following message on Epars page "Please add five or more characters"

  @438791 @PRCMUser @Sprint103
  Scenario: Verify the error message for Search textbox for Like Operator if user enters 5 or more characters on ePARS Pro Page
    Given user is on ePARS Pro screen
    When user selects following values from Search By drop down on Epars Page, with operator "Like" enters "12d45" in Search textbox followed by click on Submit Button
      | Visit Number           |
      | Invoice Number         |
      | Medical Records Number |
      | Claim Number           |
    Then user should be able to view following message on Epars page "No record found!"

  @438913 @PRCMUser @Sprint103
  Scenario: Verify that user is able to search an account with Search textbox using operators on ePARS Pro Page
    Given user is on ePARS Pro screen
    When user selects following values from Search By drop down on Epars Page, with operator "Like" enters following data "12345" in Search textbox followed by click on Submit Button
      | Visit Number           |
      | Invoice Number         |
      | Medical Records Number |
       Then user should be able to view the grid with following columns:
      | Account #                   |
      | Invoice #                   |
      | MRN                         |
      | FName                       |
      | LName                       |
      | Service Code/DRG            |
      | Department                  |
      | Location                    |
      | Major Payer                 |
      | # of Touches                |
      | Total Balance               |
      | Disposition                 |
      | Risk                        |
      | Last User                   |
      | Days Open                   |
      | Billing Provider Name       |
      | Billing Physician Specialty |
    When user is able to login to sql server and connect to database
    And EparsPro user runs the query to get the expected columns in the grid: "ePARSPro_438913_SQL1" 
    Then user should be able to view the same result in grid as SQL result    