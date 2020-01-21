@ExternalSearch
Feature: This is to verify external search functionality in R1 PRCM-BE

  Background: User is able to navigate to R1 Decision search page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link

  @433634 @Sprint8 @PRCMUser @NonDB
  Scenario: Verify that if user does not have queue than user navigates to R1D Search Page
    When user clicks on R1_Decision link
    Then user should be able to view R1D Search page
    And user should be able to view message "You currently have no accounts in your work queue.To continue, search for accounts below or contact your supervisor."
