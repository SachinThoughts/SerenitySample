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