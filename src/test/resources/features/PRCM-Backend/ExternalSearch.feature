@ExternalSearch
Feature: This is to verify external search functionality in R1 PRCM-BE

  Background: User is able to navigate to R1 Decision search page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hover on R1_Decision link
    And user clicks on search sub menu

  @429996 @PRCMUser @Sprint8
  Scenario Outline: Verify that user is able to see the search result grid for Last Name/First Name
    Given user is on R1 Decision search page
    When user selects "Last Name/First Name" from Search By dropdown
    And user enters <lastNameInitial> text in Last Name textbox
    And user enters <firstNameInitial> text in First Name textbox
    And user clicks on submit button
    Then user should be able to view the grid with following columns
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
    When user login to SQL Server and connect to facility database
    And user runs the <queryname11> query for search
    Then user should be able to view the same result in grid as SQL result

    Examples: 
      | queryname11                 | lastNameInitial | firstNameInitial |
      | SearchExternal_429995_SQL11 | a               | b                |
