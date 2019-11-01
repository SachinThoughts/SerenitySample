@ExternalSearch
Feature: This is to verify external search functionality in R1 PRCM-BE

  Background: User is able to navigate to R1 Decision search page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hover on R1_Decision link
    And user clicks on search sub menu

  @430694 @PRCMUser
  Scenario Outline: Verify that user is able to see the search result grid for Medical Record Number with operators
    Given user is on R1 Decision search page
    When user selects "Medical Record Number" from Search By dropdown
    And user login to SQL Server and connect to facility database
    And user runs the <queryname6> query for search
    And user selects <operator> from Operator dropdown
    And user enters the query result in Medical Record Number textbox
    And user clicks on Submit Button
    Then user should be able to view the grid with following columns:
      | Visit #             |
      | Invoice #           |
      | Name                |
      | Facility Code       |
      | MRN                 |
      | Gender              |
      | PT                  |
      | Service Date        |
      | PPC                 |
      | Defect Type         |
      | Defect Sub-Category |
    When user runs the <queryname12> query
    Then user should be able to view the same result in grid as SQL result

    Examples: 
      | queryname12                 | queryname6                 | operator |
      | SearchExternal_430694_SQL12 | SearchExternal_430694_SQL6 | =        |
      | SearchExternal_430694_SQL12 | SearchExternal_430694_SQL6 | Like     |
