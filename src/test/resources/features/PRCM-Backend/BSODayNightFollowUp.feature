@BSODayNight
Feature: Verify BSODayNight related scenarios in PRCM
  To check BSODayNight related scenarios in PRCM application

  Background: User is able to navigate to R1D Search Page
    Given user is on R1 Hub page
    When user clicks on Billing & Follow-up link
    And user hovers on R1_Decision link
    And user clicks on search sub menu

  @391276 @Sprint101 @PRCMUser
  Scenario Outline: Verify User should able to View BSO-Day/Night Followup Handoff at Invoice Level
    Given user is able to login to sql server and connect to database
    When user executes the query to fetch InvoiceNumber <queryName1>
    And user fetch the InvoiceNumber from DB
    And user enters InvoiceNumber in the InvoiceNumber field and click on submit button
    And user clicks on handOff button
    Then user should be able to view Hand-off pop-up window
    When user selects "BSO-Day/Night FollowUp" from hand off type dropdown
    Then user should be able to select Hand Off Type as "BSO-Day/Night FollowUp"

    Examples: 
      | queryName1                              |
      | BSO-DayNight_query_Followup_391276_SQL1 |